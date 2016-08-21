package test;

import entity.BaseEntity;

import java.util.Date;

/**
 * 学生
 * */
public class Student extends BaseEntity{

    private String name;

    private Integer age;

    private Date birthday;

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
}
