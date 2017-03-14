import entity.Lbs;
import entity.Student;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.geo.Point;
import service.LbsService;
import service.StudentService;

import java.util.Date;

public class MongoTemplateTest {

    private ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    private StudentService studentTestService;
    private LbsService lbsService;

    @Before
    public void init() {
        studentTestService = applicationContext.getBean(StudentService.class);
        lbsService = applicationContext.getBean(LbsService.class);
    }

    @Test
    public void testInsert() {
        Student student = new Student();
        student.setName("xiaowang");
        student.setAge(18);
        student.setBirthday(new Date());
        student.setEmail("750661390@qq.com");
        studentTestService.save(student);
    }

    @Test
    public void testFindById() {
        ObjectId id = new ObjectId("58c69c6085f894201d4de665");
        Student student = studentTestService.queryById(id, Student.class);
        System.out.println(student);
    }

    @Test
    public void testInsertLbs() {
        Lbs lbs = new Lbs();
        lbs.setAddress("xizhimen");
        lbs.setCrTime(new Date());
        lbs.setLoc(new Point(10.00, 11.11));
        lbs.setTitle("test-1");
        lbs.setType(1);
        lbsService.save(lbs);
    }

    @Test
    public void testQueryLbsById() {
        ObjectId id = new ObjectId("58c6a15d85f8946c3c25d036");
        Lbs lbs = lbsService.queryById(id, Lbs.class);
        System.out.println(lbs);
    }

    @After
    public void destroy() {
        applicationContext.close();
    }

}
