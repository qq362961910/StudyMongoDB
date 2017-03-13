package service.test;


import entity.test.Student;
import remote.test.StudentRemoteService;
import service.BaseService;

public interface StudentTestService extends BaseService<Student, Long>, StudentRemoteService {

}
