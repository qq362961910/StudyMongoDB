package dao.impl;

import dao.StudentDao;
import entity.Student;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDaoImpl extends BaseDaoImpl<Student, ObjectId> implements StudentDao {

    @Override
    public String queryNameByEmail(String email) {
        Query query = new Query();
        query.addCriteria(new Criteria("email").is(email)).fields().include("name");
        List<String> names = mongoTemplate.find(query, String.class, "student");
        return names != null && names.size() > 0 ? names.get(0) : null;
    }
}
