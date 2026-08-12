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
