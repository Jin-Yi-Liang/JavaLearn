package org.maxing.learning.io.service;

import org.maxing.learning.io.domain.Student;
import org.maxing.learning.io.exception.InvalidFileException;

import java.io.*;

public class StudentService {
    public static void main(String[]args){
        Student st=new Student("Michale",19,"ox123123",128);
        try{
            writeData("doc/info.bin",st);
            Student stu=readData("doc/info.bin");
            System.out.println(stu);
        }catch(InvalidFileException e){
            e.printStackTrace();
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
}
