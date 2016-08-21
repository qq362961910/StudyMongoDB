package dao.test.impl;

import dao.test.StudentTestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import test.Student;

import java.util.Date;

@Repository
public class StudentTestDaoImpl implements StudentTestDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insert(Student entity) {
        mongoTemplate.insert(entity);
        System.out.println("insert student success");
    }

    @Override
    public void update(Student entity) {
        System.out.println("update student success");
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("delete student success");
    }

    @Override
    public Student selectById(Long id) {
        Student student = new Student();
        student.setId(id);
        student.setAge(18);
        student.setBirthday(new Date());
        student.setName("xiaoming");
        return student;
    }
}
