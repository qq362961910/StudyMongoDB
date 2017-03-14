package dao;

import entity.BaseEntity;

import java.io.Serializable;

public interface BaseDao<Entity extends BaseEntity, IdType extends Serializable> {

    /**
     * insert
     */
    void insert(Entity entity);

    /**
     * update
     */
    void update(Entity entity);

    /**
     * delete
     */
    <T> void deleteById(IdType id, Class<Entity> type);

    /**
     * selectById
     */
    <T> Entity selectById(IdType id, Class<Entity> type);

}
