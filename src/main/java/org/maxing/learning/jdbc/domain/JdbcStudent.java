package org.maxing.learning.jdbc.domain;

import org.maxing.learning.jdbc.annotation.Column;

import java.math.BigDecimal;

public class JdbcStudent {
    @Column(value="id")
    private Long id;

    @Column(value="student_no")
    private String student_no;

    @Column(value="name")
    private String name;

    @Column(value="age")
    private Integer age;

    @Column(value="grade")
    private BigDecimal grade;

    public JdbcStudent(){}

    public JdbcStudent(Long id, String student_no, String name, Integer age, BigDecimal grade) {
        this.id = id;
        this.student_no = student_no;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public Boolean isEmpty(){
        return id==null;
    }

    public Long getId() {
        return id;
    }

    public String getStudent_no() {
        return student_no;
    }

    public void setStudent_no(String student_no) {
        this.student_no = student_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getGrade() {
        return grade;
    }

    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "JdbcStudent{" +
                "id=" + id +
                ", student_no='" + student_no + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", grade=" + grade +
                '}';
    }
}
