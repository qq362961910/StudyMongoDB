package dao.impl;

import dao.BaseDao;
import entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.io.Serializable;

public class BaseDaoImpl<Entity extends BaseEntity, IdType extends Serializable> implements BaseDao<Entity, IdType>{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insert(Entity entity) {
        mongoTemplate.insert(entity);
    }

    @Override
    public void update(Entity entity) {
        // TODO: 17-3-13
    }

    @Override
    public <T> void deleteById(IdType id, Class<Entity> type) {
        Criteria filter = new Criteria();
        filter.and("_id").is(id);
        Query query = new Query(filter);
        mongoTemplate.remove(query, type);
    }

    @Override
    public <T> Entity selectById(IdType id, Class<Entity> type) {
        Criteria filter = new Criteria();
        filter.and("_id").is(id);
        Query query = new Query(filter);
        return mongoTemplate.findOne(query, type);
    }
}
