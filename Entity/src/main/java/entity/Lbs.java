/*
 * Lbs.java
 * 北京无限时空网络技术有限公司
 * www.51play.com
 */
package entity;


import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document(collection = "lbs")
public class Lbs extends BaseEntity implements Serializable {

    /** 原始ID */
    private long objId;
    /** 物体类型 */
    private Integer type;
    /** 物体名称 */
    private String title;
    /** 坐标位置 */
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private Point loc;
    /** 位置概述 */
    private String address;
    /** 创建时间 */
    private Date crTime;

    public long getObjId() {
        return objId;
    }

    public Lbs setObjId(long objId) {
        this.objId = objId;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public Lbs setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Lbs setTitle(String title) {
        this.title = title;
        return this;
    }

    public Point getLoc() {
        return loc;
    }

    public Lbs setLoc(Point loc) {
        this.loc = loc;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Lbs setAddress(String address) {
        this.address = address;
        return this;
    }

    public Date getCrTime() {
        return crTime;
    }

    public Lbs setCrTime(Date crTime) {
        this.crTime = crTime;
        return this;
    }

    @Override
    public String toString() {
        return "Lbs{" +
                "objId=" + objId +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", loc=" + loc +
                ", address='" + address + '\'' +
                ", crTime=" + crTime +
                "} " + super.toString();
    }
}
