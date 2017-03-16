import dao.LbsDao;
import entity.Lbs;
import entity.Student;
import enums.CoordinateType;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.geo.Point;
import service.LbsService;
import service.StudentService;

import java.util.Date;
import java.util.List;

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
    public void testInsertStudent() {
        Student student = new Student();
        student.setName("xiaowang");
        student.setAge(18);
        student.setBirthday(new Date());
        student.setEmail("750661390@qq.com");
        studentTestService.save(student);
    }

    @Test
    public void testFindStudentById() {
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

    @Test
    public void initCoordinates() {
        for (int i = 0; i < 10; i++) {
            for (int k = 0; k < 10; k++) {
                double longitude = i + k * 0.1;
                double latitude = i + k * 0.1;
                Point point = new Point(longitude, latitude);
                Lbs lbs = new Lbs();
                lbs.setType(CoordinateType.HUMAN.getValue());
                lbs.setTitle("title: [" + longitude + ", " + latitude + "]");
                lbs.setLoc(point);
                lbs.setAddress("address: [精度: " + longitude + ", 维度: " + latitude + "]");
                lbs.setObjId(i * k + k);
                lbs.setCrTime(new Date());
                lbsService.save(lbs);
            }
        }
    }

    @Test
    public void testSearchNearLbs() {
        LbsDao.LbsQueryParam param = new LbsDao.LbsQueryParam();
//        param.setTitle("title: [0.5, 0.5]");
        param.setAddress("address: [精度: 0.5, 维度: 0.5]");
//        param.setCoordinateType(CoordinateType.HUMAN);
//        param.setId("58c8d70885f89417d4371e48");
//        param.setOriginalId(1l);
        param.setLongitude(5.0);
        param.setLatitude(5.0);
        param.setMinDistance(0L);
        param.setMaxDistance(1000000L);
        List<Lbs> lbsList = lbsService.searchNear(param);
        System.out.println(lbsList);
    }

    @After
    public void destroy() {
        applicationContext.close();
    }

}
