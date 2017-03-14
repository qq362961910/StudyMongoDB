package entity.test;

import entity.Student;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Book {

    private String bookName;

    @DBRef
    private Student student;


    public String getBookName() {
        return bookName;
    }

    public Book setBookName(String bookName) {
        this.bookName = bookName;
        return this;
    }

    public Student getStudent() {
        return student;
    }

    public Book setStudent(Student student) {
        this.student = student;
        return this;
    }
}
