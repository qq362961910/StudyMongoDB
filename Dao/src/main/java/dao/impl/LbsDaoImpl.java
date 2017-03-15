package dao.impl;

import com.mongodb.BasicDBObject;
import dao.LbsDao;
import entity.Lbs;
import enums.LbsExtensionType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class LbsDaoImpl extends BaseDaoImpl<Lbs, ObjectId> implements LbsDao{


    @Override
    public List<Lbs> searchNear(LbsQueryParam param) {

        Criteria filter = new Criteria();

        //ID
        if (param.getId() != null) {
            filter.and("_id").is(new ObjectId(param.getId()));
        }

        //坐标
        if(param.getLongitude() != null && param.getLatitude() != null){
            BasicDBObject searchObj = new BasicDBObject("geoNear",new BasicDBObject("$geometry",new BasicDBObject("coordinates",
                    new ArrayList(){{add(param.getLongitude());add(param.getLatitude());}})
                    .append("type", "Point"))
                    .append("$minDistance",param.getMinDistance() != null ? param.getMinDistance() : 0)
                    .append("$maxDistance",param.getMaxDistance() != null ? param.getMaxDistance() : 500));
            filter.and("loc").is(searchObj);
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
        if (param.getOriginalId() != null){
            filter.and("objId").is(param.getOriginalId());
        }

        //类型
        if(param.getCoordinateType() != null){
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
                    List<Object> deviceTyps = (List<Object>)extensionsRestrictions.get(LbsExtensionType.B);
                    if (!deviceTyps.isEmpty()) {
                        filter.and("extensions.DEVICE_TYPE").in(deviceTyps);
                    }
                }
                else {
                    filter.and("extensions.DEVICE_TYPE").is(extensionsRestrictions.get(LbsExtensionType.B));
                }
            }
        }
        return mongoTemplate.find(new Query(filter), Lbs.class);
    }
}
