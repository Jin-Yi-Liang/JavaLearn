package org.maxing.learning.io.service;

import org.maxing.learning.io.domain.Student;
import org.maxing.learning.io.exception.InvalidFileException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    public static void main(String[]args){
        String path="doc/info.bin";
        List<Student>students=new ArrayList<Student>();
        students.add(new Student("Michael",19,"ox123123",123));
        students.add(new Student("Jack",19,"ox123345",57));
        students.add(new Student("James",19,"ox12314633",113));
        students.add(new Student("Peter",19,"ox1343433",122));
        students.add(new Student("James",19,"ox1346123",123));

        writeAllObject(path,students);
        List<Student>copy_students=readAllObject(path);
        for(Student cur:copy_students){
            System.out.println(cur);
        }
    }

    public static void writeData(String fileFullName, Student student)throws InvalidFileException{
        File file=new File(fileFullName);
        if(!file.getParentFile().exists() ||  !file.getParentFile().isDirectory()){
            throw new NullPointerException("path is null");
        }
        if(!file.exists() || !file.isFile()) {
            try{
            file.createNewFile();
            }catch (IOException e){
                throw new InvalidFileException("file create failed",e);
            }
        }

        try(OutputStream outputStream=new FileOutputStream(file);
            DataOutputStream dataOutputStream=new DataOutputStream(outputStream);) {
            dataOutputStream.writeUTF(student.getName());
            dataOutputStream.writeInt(student.getAge());
            dataOutputStream.writeUTF(student.getNo());
            dataOutputStream.writeDouble(student.getGrade());
            dataOutputStream.flush();
        }catch(FileNotFoundException ex){
            throw new IllegalArgumentException(file.getAbsolutePath()+" is not found");
        }catch(IOException ex){
            throw new IllegalArgumentException(file.getAbsolutePath()+" is not found");
        }
    }

    public static Student readData(String fileFullName)throws InvalidFileException{
        File file=new File(fileFullName);
        if(!file.getParentFile().exists() || !file.getParentFile().isDirectory()){
            throw new NullPointerException("path is null");
        }
        if(!file.exists() || !file.isFile()) {
           throw new InvalidFileException("file is not found");
        }

        try(InputStream inputStream=new FileInputStream(file);
            DataInputStream dataInputStream=new DataInputStream(inputStream);){
            Student stu=new Student();
            stu.setName(dataInputStream.readUTF());
            stu.setAge(dataInputStream.readInt());
            stu.setNo(dataInputStream.readUTF());
            stu.setGrade(dataInputStream.readDouble());
            return stu;
        } catch(IOException ex){
            throw new IllegalArgumentException("read from "+file.getAbsolutePath()+" failed",ex);
        }
    }

    public static void writeObject(String fillFullName,Object object){
        File file=new File(fillFullName);
        if(!file.getParentFile().exists() || !file.getParentFile().isDirectory()){
            throw new NullPointerException("path is null");
        }
        if(!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    throw new IllegalStateException("create file failed");
                }
            }catch (IOException ex){
                throw new IllegalStateException("create file failed",ex);
            }
        }
        try(OutputStream outputstream=new FileOutputStream(file,true);
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(outputstream) ){
            objectOutputStream.writeObject(object);
        }catch(FileNotFoundException ex){
            throw new IllegalArgumentException(file.getAbsolutePath()+" is not found",ex);
        }catch(IOException ex){
            throw new IllegalArgumentException(file.getAbsolutePath()+" write in filed",ex);
        }
    }

    public static Object readObject(String fillFullName){
        File file=new File(fillFullName);
        if(!file.getParentFile().exists() || !file.getParentFile().isDirectory()){
            throw new NullPointerException("path is null");
        }
        if(!file.exists()) {
            throw new IllegalArgumentException("file is not found");
        }
        if(!file.isFile()) {
            throw new IllegalArgumentException("file is not found");
        }
        try(InputStream inputstream=new FileInputStream(file);
            ObjectInputStream objectInputStream=new ObjectInputStream(inputstream)){
            Object obj=objectInputStream.readObject();
            return obj;
        }catch(FileNotFoundException ex){
            throw new IllegalArgumentException(file.getAbsolutePath()+" is not found");
        }catch(IOException ex){
            throw new IllegalArgumentException(file.getAbsolutePath()+" is not found");
        }catch(ClassNotFoundException ex){
            throw new IllegalArgumentException(file.getAbsolutePath()+" is not found");
        }
    }

    public static <T extends Serializable>
    void writeAllObject(String fileFullName,List<T>objects){
        if(fileFullName.isEmpty()){
            throw new IllegalArgumentException("fileFullName is empty");
        }
        File file=new File(fileFullName);
        if(!file.getParentFile().exists()){
            throw new NullPointerException("path is null");
        }
        if(!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    throw new IllegalStateException("create file failed");
                }
            }catch(IOException ex){
                throw new IllegalStateException("create file failed",ex);
            }
        }
        if(!file.isFile()) {
            throw new IllegalArgumentException(file.getName()+" is not file");
        }
        try(OutputStream outputstream=new FileOutputStream(file);
            ObjectOutputStream objectoutputstream=new ObjectOutputStream(outputstream)){
            objectoutputstream.writeObject(objects);
        }catch(FileNotFoundException ex){
            throw  new IllegalArgumentException(file.getAbsolutePath()+" is not found");
        }catch(IOException ex){
            throw new IllegalArgumentException(file.getAbsolutePath()+" write in filed",ex);
        }
    }

    public static <T extends Serializable> List<T> readAllObject(String fileFullName){
        if(fileFullName.isEmpty()){
            throw new IllegalArgumentException("fileFullName is empty");
        }
        File file=new File(fileFullName);
        if(!file.getParentFile().exists()){
            throw new NullPointerException("path is null");
        }
        if(!file.exists()) {
            throw new IllegalArgumentException("file is not found");
        }
        if(!file.isFile()) {
            throw new IllegalArgumentException("file is not found");
        }
        try(InputStream inputstream=new FileInputStream(file);
            ObjectInputStream objectInputStream=new ObjectInputStream(inputstream)){
            List<T>list=(List<T>)objectInputStream.readObject();
            return list;
        }catch(FileNotFoundException ex){
            throw new IllegalArgumentException(file.getAbsolutePath()+" is not found");
        }catch(IOException ex){
            throw new IllegalArgumentException(file.getAbsolutePath()+" write failed");
        }catch(ClassNotFoundException ex){
            throw new IllegalArgumentException(file.getAbsolutePath()+" is not found");
        }
    }
}
