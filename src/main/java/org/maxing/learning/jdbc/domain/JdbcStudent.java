package org.maxing.learning.jdbc.domain;

import java.math.BigDecimal;

public class JdbcStudent {
    private Long id;
    private String studentNo;
    private String name;
    private Integer age;
    private BigDecimal grade;

    public JdbcStudent(){}

    public JdbcStudent(Long id, String studentNo, String name, Integer age, BigDecimal grade) {
        this.id = id;
        this.studentNo = studentNo;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
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
                ", studentNo='" + studentNo + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", grade=" + grade +
                '}';
    }
}
