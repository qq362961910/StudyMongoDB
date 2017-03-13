import entity.test.Address;
import entity.test.Book;
import entity.test.Student;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class XmlConfigMongoTemplateTest {

    private ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void init() {
        mongoTemplate = applicationContext.getBean(MongoTemplate.class);
    }


    @Test
    public void testInsertStudent() {

        Address address = new Address();
        address.setCountry("China");
        address.setCity("Beijing");
        address.setStreet("XingFuNanDaJie");

        Book book = new Book();
        book.setBookName("math");
        Set<Book> books = new HashSet<>();
        books.add(book);

        Student student = new Student();
        student.setName("zhangsan");
        student.setEmail("362961910@qq.com");
        student.setAge(18);
        student.setBirthday(new Date());
        student.setAddress(address);
        student.setBooks(books);

        mongoTemplate.insert(student);

        System.out.println("ok");
    }

    @Test
    public void testFindStudentById() {

        List<Student> studentList = mongoTemplate.find(new Query(), Student.class);

        System.out.println("ok");
    }

    @Test
    public void testUpdateStudent() {

        Query query = new Query(new Criteria("_id").is(new ObjectId("57bc1204bfeef61300d5d599")));
        Update update = new Update().set("email", "362961910@qq.com.update");
        mongoTemplate.updateFirst(query, update, Student.class);

    }

    @Test
    public void testDelete() {
        Query query = new Query(new Criteria("_id").is(new ObjectId("57bc1204bfeef61300d5d599")));
        mongoTemplate.remove(query, Student.class);
    }

    @After
    public void destroy() {
        applicationContext.close();
    }

}
