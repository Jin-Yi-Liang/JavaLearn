package org.maxing.learning.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

@WebServlet("/user/upload")
@MultipartConfig(maxFileSize = 5*1024*1024)
public class UploadServlet extends HttpServlet {
    private static final Path FILE_PATH=Path.of("/home/michael/workspace/java_workspace/JavaLearn/src/main/webapp/files");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Files.createDirectories(FILE_PATH);

        //normal format request parameter
        Map<String,String[]>parameterMap=req.getParameterMap();
        for(Map.Entry<String,String[]> entry:parameterMap.entrySet()){
            String name=entry.getKey();
            String[] values=entry.getValue();
            for(String value:values){
                System.out.println(name+" => "+value);
            }
        }

        //save request file into local disk
        int fileCount=0;
        for(Part part:req.getParts()){
            String name=part.getSubmittedFileName();
            //filter file request parameter
            if(name==null || name.isBlank()){
                continue;
            }
            //log info into terminal
            System.out.println("part name => "+part.getName());
            System.out.println("File name => "+part.getSubmittedFileName());
            System.out.println("file content type=> "+part.getContentType());
            System.out.println("file size=> "+part.getSize());

            //generate server filename
            String serverFilename= UUID.randomUUID()+"_"+name;
            System.out.println("serverFilename => "+serverFilename);
            Path target=FILE_PATH.resolve(serverFilename);
            System.out.println("target location => "+target);

            //save file into server disk
            try(InputStream in=part.getInputStream()) {
                Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
            }

            ++fileCount;
        }

        //prepare return result in map
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Map<String,Object>result=new LinkedHashMap<>();
        result.put("success",true);
        result.put("message","Upload successfully!");
        result.put("fileCount",fileCount);

        //use jackson to convert java object to JSON
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(resp.getWriter(),result);
    }
}
