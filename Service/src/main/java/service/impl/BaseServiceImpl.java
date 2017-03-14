package service.impl;

import dao.BaseDao;
import entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import service.BaseService;

import java.io.Serializable;

public class BaseServiceImpl<Entity extends BaseEntity, IdType extends Serializable> implements BaseService<Entity, IdType> {

    @Autowired
    private BaseDao<Entity, IdType> baseDao;

    @Override
    public void save(Entity entity) {
        baseDao.insert(entity);
    }

    @Override
    public void modify(Entity entity) {
        // TODO: 17-3-13
    }

    @Override
    public void removeById(IdType id, Class<Entity> entityClass) {
        baseDao.deleteById(id, entityClass);
    }

    @Override
    public Entity queryById(IdType id, Class<Entity> entityClass) {
        return baseDao.selectById(id, entityClass);
    }
}
