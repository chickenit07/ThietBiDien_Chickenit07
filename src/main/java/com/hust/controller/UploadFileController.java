package com.hust.controller;

import com.hust.model.JsonResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@WebServlet(name = "UploadFileController", value = "/upload-file/*")
/*
 fileSizeThreshold: neu kich thuoc cua 1 file size nho hon thi upload luon khong qua bo dem
 maxFileSize: kich thuoc toi da cua 1 file dc upload
 maxRequestSize: kich thuoc toi da cua 1 request upload
* */

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024 * 50)
public class UploadFileController extends HttpServlet {
    JsonResult jsonResult = new JsonResult();

    private static final String SAVE_DIRECTORY = "file_upload";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rs = "";
        List<String> listRs = new ArrayList<>();

        try {
            Collection<Part> partCollection = request.getParts();
            long time = new Date().getTime();
            String fileDirect = getFolderUpload(time).getAbsolutePath();
            for (Part part : partCollection) {
                String fileName = getFileName(part);
                if (fileName != null) {

                    String filePath = fileDirect + File.separator + fileName;
                    System.out.println("write file :" + filePath);
                    part.write(filePath);   // ghi file
                    listRs.add(SAVE_DIRECTORY + "/" + time + "/" + fileName);
                }
            }
            rs = jsonResult.jsonSuccess(listRs);
        } catch (Exception e) {
            e.printStackTrace();
            rs = jsonResult.jsonFail("failed");
        }
        response.getWriter().write(rs);
    }

    private File getFolderUpload(long time) {
        String appPath = "C:\\apache-tomcat-8.5.57\\webapps\\";
        appPath += SAVE_DIRECTORY;
        File folderUpload = new File(appPath + "\\" + time);

        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String filename = s.substring(s.indexOf("=") + 2, s.length() - 1);
                filename = filename.replace("\\", "/");
                int i = filename.lastIndexOf("/");
                return filename.substring(i + 1);
            }
        }
        return null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
