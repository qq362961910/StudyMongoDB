package dao;

import entity.Lbs;
import enums.CoordinateType;
import enums.LbsExtensionType;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface LbsDao extends BaseDao<Lbs, ObjectId> {


    List<Lbs> searchNear(LbsQueryParam param);

    class LbsQueryParam implements Serializable {

        /**
         * ID
         */
        private String id;

        /**
         * 原始ID
         */
        private Long originalId;

        /**
         * 经度
         */
        private Double longitude;

        /**
         * 纬度
         */
        private Double latitude;

        /**
         * 开始距离
         */
        private Long minDistance;

        /**
         * 结束距离
         */
        private Long maxDistance;

        /**
         * 坐标类别
         */
        private CoordinateType coordinateType;

        /**
         * title
         */
        private String title;

        /**
         * address
         */
        private String address;


        /**
         * 扩展类型
         */
        private Map<LbsExtensionType, Object> extensions = new HashMap<>();

        /**
         * ID
         */
        public String getId() {
            return id;
        }

        /**
         * ID
         */
        public LbsQueryParam setId(String id) {
            this.id = id;
            return this;
        }

        /**
         * 经度
         */
        public Double getLongitude() {
            return longitude;
        }

        /**
         * 经度
         */
        public LbsQueryParam setLongitude(Double longitude) {
            this.longitude = longitude;
            return this;
        }

        /**
         * 纬度
         */
        public Double getLatitude() {
            return latitude;
        }

        /**
         * 纬度
         */
        public LbsQueryParam setLatitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }

        /**
         * 开始距离
         */
        public Long getMinDistance() {
            return minDistance;
        }

        /**
         * 开始距离
         */
        public LbsQueryParam setMinDistance(Long minDistance) {
            this.minDistance = minDistance;
            return this;
        }

        /**
         * 结束距离
         */
        public Long getMaxDistance() {
            return maxDistance;
        }

        /**
         * 结束距离
         */
        public LbsQueryParam setMaxDistance(Long maxDistance) {
            this.maxDistance = maxDistance;
            return this;
        }

        /**
         * 坐标类别
         */
        public CoordinateType getCoordinateType() {
            return coordinateType;
        }

        /**
         * 坐标类别
         */
        public LbsQueryParam setCoordinateType(CoordinateType coordinateType) {
            this.coordinateType = coordinateType;
            return this;
        }

        /**
         * title
         */
        public String getTitle() {
            return title;
        }

        /**
         * title
         */
        public LbsQueryParam setTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * address
         */
        public String getAddress() {
            return address;
        }

        /**
         * address
         */
        public LbsQueryParam setAddress(String address) {
            this.address = address;
            return this;
        }

        /**
         * 原始ID
         */
        public Long getOriginalId() {
            return originalId;
        }

        /**
         * 原始ID
         */
        public LbsQueryParam setOriginalId(Long originalId) {
            this.originalId = originalId;
            return this;
        }

        public Map<LbsExtensionType, Object> getExtensions() {
            return extensions;
        }

        public LbsQueryParam setExtensionRestriction(LbsExtensionType type, Object value) {
            extensions.put(type, value);
            return this;
        }
    }
}
