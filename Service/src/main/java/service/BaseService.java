package service;

public interface BaseService<Entity, IdType> {

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
    void removeById(IdType id);

    /**
     * queryById
     */
    Entity queryById(IdType id);

}
