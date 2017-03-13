package entity.test;

import entity.BaseEntity;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 学生
 */
@Document
public class Student extends BaseEntity {

    @Field("name")
    private String name;

    @Field("age")
    private Integer age;

    @Field("birthday")
    private Date birthday;

    @Indexed(unique = true)
    @Field("email")
    private String email;

    @Field("address")
    private Address address;

    @Field("books")
    private Set<Book> books = new HashSet<>();

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Student setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Student setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Student setEmail(String email) {
        this.email = email;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Student setAddress(Address address) {
        this.address = address;
        return this;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public Student setBooks(Set<Book> books) {
        this.books = books;
        return this;
    }
}
