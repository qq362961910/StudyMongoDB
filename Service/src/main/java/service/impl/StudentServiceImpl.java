package service.impl;

import entity.Student;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import service.StudentService;

@Service
public class StudentServiceImpl extends BaseServiceImpl<Student, ObjectId> implements StudentService {
}
