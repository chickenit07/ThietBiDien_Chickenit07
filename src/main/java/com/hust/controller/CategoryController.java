package com.hust.controller;

import com.google.gson.Gson;
import com.hust.model.Category;
import com.hust.model.JsonResult;
import com.hust.service.CategoryService;
import com.hust.service_impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

// value is contextPath
@WebServlet(name = "CategoryController", value = "/api/v1/category/*")
public class CategoryController extends HttpServlet {
    //them category
    private CategoryService categoryService = new CategoryServiceImpl();
    private JsonResult jsonResult = new JsonResult();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String rs = "";
        try {
            Category category = new Gson().fromJson(request.getReader(), Category.class);
            Category newCategory = categoryService.insert(category.getName());
            rs = newCategory != null ? jsonResult.jsonSuccess(newCategory) : jsonResult.jsonFail(newCategory);
        } catch (SQLException e) {
            e.printStackTrace();
            jsonResult.jsonFail("Failed");
        }
        response.getWriter().write(rs);
    }

    //tim kiem category
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // find-by-id
        //find all
        String pathInfo = request.getPathInfo();
        String rs = "";
        if (pathInfo.indexOf("/find-by-id") == 0) {
            //service find-by-id
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                Category category = categoryService.findById(id);
                rs = jsonResult.jsonSuccess(category == null ? "Not found category" : category);
            } catch (SQLException e) {
                e.printStackTrace();
                rs = jsonResult.jsonFail("Error when finding category by id");
            }
            response.getWriter().write(rs);
        } else if (pathInfo.indexOf("/find-all") == 0) {
            //service find-all
            try {
                List<Category> categoryList = categoryService.findAll();
                rs = jsonResult.jsonSuccess(categoryList);
            } catch (Exception e) {
                e.printStackTrace();
                rs = jsonResult.jsonFail("Error when finding all");
            }
            response.getWriter().write(rs);
        } else {
            response.sendError(404, "URL is not supported");
        }
    }

    //Sua category
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rs = "";
        try{
            Category category = new Gson().fromJson(request.getReader(),Category.class);
            rs = jsonResult.jsonSuccess(categoryService.update(category));
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.jsonFail("update failed");
        }
        response.getWriter().write(rs);
    }

    //xoa category
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String rs = "";
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            rs = jsonResult.jsonSuccess(categoryService.delete(id));
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.jsonFail("Remove Failed");
        }
        response.getWriter().write(rs);
    }
}
