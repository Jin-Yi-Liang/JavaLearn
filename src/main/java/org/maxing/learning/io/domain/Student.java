package org.maxing.learning.io.domain;

import java.io.Serializable;

public class Student
    implements Serializable {
    private String name;
    private int age;
    private String no;
    private double grade;

    public Student(){}
    public Student(String name,int age,String no,double grade) {
        this.name = name;
        this.age = age;
        this.no = no;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student name:"+name+",Age:"+age+",No:"+no+",Grade:"+grade;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getNo() {
        return no;
    }
    public void setNo(String no) {
        this.no = no;
    }
    public double getGrade() {
        return grade;
    }
    public void setGrade(double grade) {
        this.grade = grade;
    }
}
