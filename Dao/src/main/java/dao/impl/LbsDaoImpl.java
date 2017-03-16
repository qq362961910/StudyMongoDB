package dao.impl;

import dao.LbsDao;
import dao.geo.query.LbsAggregationOperation;
import dao.geo.query.LbsNearQuery;
import entity.Lbs;
import enums.LbsExtensionType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.aggregation.AggregationOptions;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Repository
public class LbsDaoImpl extends BaseDaoImpl<Lbs, ObjectId> implements LbsDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<Lbs> searchNear(LbsQueryParam param) {

        Criteria filter = new Criteria();

        //ID
        if (param.getId() != null) {
            filter.and("_id").is(new ObjectId(param.getId()));
        }
        //address
        if (StringUtils.hasText(param.getAddress())) {
            filter.and("address").is(param.getAddress());
        }

        //title
        if (StringUtils.hasText(param.getTitle())) {
            filter.and("title").is(param.getTitle());
        }

        //原始ID
        if (param.getOriginalId() != null) {
            filter.and("objId").is(param.getOriginalId());
        }

        //类型
        if (param.getCoordinateType() != null) {
            filter.and("type").is(param.getCoordinateType().getValue());
        }

        //扩展信息
        if (param.getExtensions() != null) {
            Map<LbsExtensionType, Object> extensionsRestrictions = param.getExtensions();
            //A
            if (extensionsRestrictions.get(LbsExtensionType.A) != null) {
                filter.and("extensions.A").is(extensionsRestrictions.get(LbsExtensionType.A));
            }
            // B
            else if (extensionsRestrictions.get(LbsExtensionType.B) != null) {
                if (extensionsRestrictions.get(LbsExtensionType.B) instanceof List) {
                    List<Object> deviceTyps = (List<Object>) extensionsRestrictions.get(LbsExtensionType.B);
                    if (!deviceTyps.isEmpty()) {
                        filter.and("extensions.DEVICE_TYPE").in(deviceTyps);
                    }
                } else {
                    filter.and("extensions.DEVICE_TYPE").is(extensionsRestrictions.get(LbsExtensionType.B));
                }
            }
        }
        //坐标
        if (param.getLongitude() != null && param.getLatitude() != null) {
            GeoJsonPoint point = new GeoJsonPoint(param.getLongitude(), param.getLatitude());
            LbsNearQuery nearQuery = LbsNearQuery.near(point);
            nearQuery.spherical(true);
            if (param.getMinDistance() != null) {
                nearQuery.minDistance(param.getMinDistance());
            }
            if (param.getMaxDistance() != null) {
                nearQuery.maxDistance(param.getMaxDistance());
            }
            nearQuery.query(new Query(filter));
            LbsAggregationOperation geoNearOperation = new LbsAggregationOperation(nearQuery, "distance");

            TypedAggregation<Lbs> typedAggregation = new TypedAggregation<Lbs>(Lbs.class, geoNearOperation);
            typedAggregation.withOptions(new AggregationOptions.Builder().allowDiskUse(true).build());
            AggregationResults<Lbs> results = mongoTemplate.aggregate(typedAggregation, Lbs.class);
            return results.getMappedResults();
        } else {
            return mongoTemplate.find(new Query(filter), Lbs.class);
        }
    }
}
