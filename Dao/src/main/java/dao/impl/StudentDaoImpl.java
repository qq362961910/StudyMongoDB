package dao.impl;

import dao.StudentDao;
import entity.Student;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDaoImpl extends BaseDaoImpl<Student, ObjectId> implements StudentDao{

}
