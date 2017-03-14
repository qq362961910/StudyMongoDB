package service;

import entity.BaseEntity;

import java.io.Serializable;

public interface BaseService<Entity extends BaseEntity, IdType extends Serializable> {

    /**
     * save
     */
    void save(Entity entity);

    /**
     * modify
     */
    void modify(Entity entity);

    /**
     * removeById
     */
    void removeById(IdType id, Class<Entity> entityClass);

    /**
     * queryById
     */
    Entity queryById(IdType id, Class<Entity> entityClass);

}
