package service.test.impl;

import dao.test.StudentTestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.test.StudentTestService;
import test.Student;

@Service
public class StudentTestServiceImpl implements StudentTestService {

    @Autowired
    private StudentTestDao studentTestDao;

    @Override
    public void save(Student student) {
        studentTestDao.insert(student);
    }

    @Override
    public void modify(Student student) {
        studentTestDao.update(student);
    }

    @Override
    public void removeById(Long id) {
        studentTestDao.deleteById(id);
    }

    @Override
    public Student queryById(Long id) {
        return studentTestDao.selectById(id);
    }
}
