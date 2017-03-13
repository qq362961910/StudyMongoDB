import entity.test.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.test.StudentTestService;

import java.util.Date;

public class MongoTemplateTest {

    private ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    private StudentTestService studentTestService;

    @Before
    public void init() {
        studentTestService = applicationContext.getBean(StudentTestService.class);
    }

    @Test
    public void testInsert() {
        Student student = new Student();
//        student.setId(1l);
        student.setName("xiaowang");
        student.setAge(18);
        student.setBirthday(new Date());
        student.setEmail("750661390@qq.com");
        studentTestService.save(student);
    }

    @After
    public void destroy() {
        applicationContext.close();
    }

}
