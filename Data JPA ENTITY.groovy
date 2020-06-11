import com.intellij.database.model.DasTable
import com.intellij.database.model.ObjectKind
import com.intellij.database.util.Case
import com.intellij.database.util.DasUtil

/*
 * Available context bindings:
 *   SELECTION   Iterable<DasObject>
 *   PROJECT     project
 *   FILES       files helper
 */

packageName = ""
importPackages = "import java.sql.*; \n" +
        "import lombok.Data; \n" +
        "import javax.persistence.*;\n" +
        "import java.io.Serializable;\n"

typeMapping = [
        (~/(?i)int/)                  : "Integer",
        (~/(?i)float|double|real/)    : "double",
        (~/(?i)decimal/)              : "BigDecimal",
        (~/(?i)datetime | timestamp /): "Timestamp",
        (~/(?i)date/)                 : "Date",
        (~/(?i)time/)                 : "Time",
        (~/(?i)/)                     : "String"
]

FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
    SELECTION.filter { it instanceof DasTable && it.getKind() == ObjectKind.TABLE }.each { generate(it, dir) }
}

// 获取包所在文件夹路径
def getPackageName(dir) {
    return dir.toString().replaceAll("\\\\", ".").replaceAll("/", ".").replaceAll("^.*src(\\.main\\.java\\.)?", "") + ";"
}

def generate(table, dir) {
    def className = javaName(table.getName(), true)
    def fields = calcFields(table)
    packageName = getPackageName(dir)
    new File(dir, className + ".java").withPrintWriter { out -> generate(out, className, fields) }
}

def generate(out, className, fields) {
    out.println "package $packageName"
    out.println ""
    out.println importPackages
    out.println ""
    out.println "@Data"
    out.println "@Entity"
    out.println "public class $className implements Serializable{"
    out.println ""
    out.println "    @Id\n" +
            "    @GeneratedValue(strategy = GenerationType.IDENTITY)"
    fields.each() {
        out.println "    /* ${it.commoent} */"
        if (it.annos != "") out.println "  ${it.annos}"
        out.println "    private ${it.type} ${it.name};"
    }
    out.println ""
    out.println "}"
}

def calcFields(table) {
    DasUtil.getColumns(table).reduce([]) { fields, col ->
        def spec = Case.LOWER.apply(col.getDataType().getSpecification())
        def typeStr = typeMapping.find { p, t -> p.matcher(spec).find() }.value
        fields += [[
                           commoent: col.getComment(),
                           name    : javaName(col.getName(), false),
                           type    : typeStr,
                           annos   : ""]]
    }
}

def javaName(str, capitalize) {
    def s = com.intellij.psi.codeStyle.NameUtil.splitNameIntoWords(str)
            .collect { Case.LOWER.apply(it).capitalize() }
            .join("")
            .replaceAll(/[^\p{javaJavaIdentifierPart}[_]]/, "_")
    capitalize || s.length() == 1 ? s : Case.LOWER.apply(s[0]) + s[1..-1]
}
