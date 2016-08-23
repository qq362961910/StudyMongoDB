package entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public abstract class BaseEntity {

    @Id
    private ObjectId id;

    public ObjectId getId() {
        return id;
    }

    public BaseEntity setId(ObjectId id) {
        this.id = id;
        return this;
    }
}
