package org.maxing.learning.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet("/user/download")
public class DownloadServlet extends HttpServlet {
    private static final String DOWNLOAD_PATH="/home/michael/workspace/java_workspace/JavaLearn/src/main/webapp/files";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DownloadServlet doGet");
        //get filename from query-parameter
        String filename=req.getParameter("filename");
        if(filename==null || filename.isBlank()){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST,"miss filename");
            return;
        }

        //set response header
        Path file= Paths.get(DOWNLOAD_PATH,filename);
        String contentType= Files.probeContentType(file);
        resp.setContentType(contentType);
        resp.setHeader("Content-Disposition","attachment; filename=\""+filename+"\"");
        resp.setHeader("Content-Length",""+Files.size(file));

        //get file from server disk, and add to response body
        try(InputStream is=Files.newInputStream(file);
                OutputStream os=resp.getOutputStream()){
            is.transferTo(os);
        }

        System.out.println("Download filename:"+filename);
    }
}
