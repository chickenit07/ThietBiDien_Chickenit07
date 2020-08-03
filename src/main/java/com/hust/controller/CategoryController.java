package com.hust.controller;

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
import java.util.List;

@WebServlet(name = "CategoryController", value = "/api/v1/category/*")
public class CategoryController extends HttpServlet {
    //them category
    private CategoryService categoryService = new CategoryServiceImpl();
    private JsonResult jsonResult = new JsonResult();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String rs = "";
        if (pathInfo.indexOf("/insert") == 0) {
            String name = request.getParameter("name");
            if (name != null) {
                try {
                    categoryService.insert(name);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else response.sendError(404, "Not found argument");
            response.getWriter().write("Success!");
        } else {
            response.sendError(404, "URL is not supported");
        }
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
                rs = jsonResult.jsonSuccess(category == null ? "null" : category);
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
        String pathInfo = request.getPathInfo();
        if (pathInfo.indexOf("/update") == 0) {
            String name = request.getParameter("name");
            Integer id = Integer.parseInt(request.getParameter("id"));
            if (name != null && id != null) {
                try {
                    categoryService.update(new Category(id, name, false));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else response.sendError(404, "Not found argument");
        } else {
            response.sendError(404, "URL is not supported");
        }
    }

    //xoa category
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo.indexOf("/delete") == 0) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            if (id != null) {
                try {
                    categoryService.delete(id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else response.sendError(404, "Not found argument");
        } else {
            response.sendError(404, "URL is not supported");
        }
    }
}
