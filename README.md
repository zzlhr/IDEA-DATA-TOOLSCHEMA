# IDEA-DATA-TOOLSCHEMA
idea data model tools
## idea生成实体类工具集合



#### Data JPA ENTITY.groovy -->  spring data jpa实体类


效果
```
package com.hntxrj.txerp.entity;

import java.math.BigDecimal;
import java.sql.*;
import lombok.Data; 
import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
public class GpsLocateTempInfo implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /* 车辆代号 */
    private String vehicleId;
    /* 车载SIM卡号 */
    private String simCode;
    /* 最新纬度 */
    private BigDecimal dataX;
    /* 最新经度 */
    private BigDecimal dataY;
    /* 速度 */
    private BigDecimal speed;
    /* 方位角 */
    private BigDecimal postion;
    /* 上传时间 */
    private Date sDatetime;
    /* 报警类型 */
    private String warnType;
    /* 报警超时时间 */
    private Integer warnTime;
    /* 车辆状态 */
    private String carRunState;
    /* 车辆外设状态 */
    private String carPartsState;
    /* GPRS状态 */
    private String gprsState;
    /* GPS外设状态 */
    private String wsState;
    /* 滚桶状态 */
    private Integer turnStatus;
    /* 工地距离 */
    private BigDecimal sLineDistance;
    /* 报警类别 */
    private Integer leibie;
    /* 当前里程 */
    private BigDecimal carMil;
    /* 起始里程 */
    private BigDecimal alMil;
    /* 结束状态 */
    private String revState;
    /* null */
    private BigDecimal goalX;
    /* null */
    private BigDecimal goalY;
    /* 场站经度 */
    private BigDecimal stX;
    /* 场站纬度 */
    private BigDecimal stY;
    /* 工地经度 */
    private BigDecimal waX;
    /* 工地纬度 */
    private BigDecimal waY;
    /* 场站越界判断距离 */
    private BigDecimal stDist;
    /* 工地越界判断距离 */
    private BigDecimal waDist;
    /* 到路越界判断距离 */
    private BigDecimal roadDist;
    /* 设定路线名称 */
    private String roadName;
    /* 运输时间范围 */
    private Integer trTime;
    /* 待卸时间范围 */
    private Integer waitTime;
    /* 是否在卸料标志 */
    private String unloadNow;
    /* 开始卸料标志 */
    private String unloadStar;
    /* 结束卸料标志 */
    private String unloadOver;
    /* 回厂标志 */
    private String arriveSt;
    /* 离厂标志 */
    private String leaveSt;
    /* 工地到达标志 */
    private String arriveWa;
    /* 离开工地标志 */
    private String leaveWa;
    /* 是否在线 */
    private String onlineStatus;
    /* 车辆位置状态 */
    private Integer locadStatus;
    /* 车辆小票ID */
    private Integer tSaleId;
    /* null */
    private String upDown;
    /* null */
    private Integer upDownMark;
    /* null */
    private String recStatus;

}
```
