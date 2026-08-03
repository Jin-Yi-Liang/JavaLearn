package org.maxing.learning.collection.service;

public class Student implements Comparable<Student>{
    private String name;
    private int age;
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
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
    @Override
    public int compareTo(Student other){
        int ageAns=this.age-other.age;
        if(ageAns!=0){
            return ageAns;
        }
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return "Name:"+name+",Age:"+age;
    }

    @Override
    public boolean equals(Object o) {
        return this.getName().equals(((Student) o).getName());
    }

}
