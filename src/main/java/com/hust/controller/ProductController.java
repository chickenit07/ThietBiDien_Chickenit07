package com.hust.controller;

import com.google.gson.Gson;
import com.hust.model.JsonResult;
import com.hust.model.Product;
import com.hust.service.ProductService;
import com.hust.service_impl.ProductService_Impl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductController", value = "/api/v1/product/*")
public class ProductController extends HttpServlet {
    private ProductService productService = new ProductService_Impl();
    private JsonResult jsonResult = new JsonResult();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rs = "";
        try {
            Product product = new Gson().fromJson(request.getReader(),Product.class);
            Product newProduct = productService.insert(product);
            rs = newProduct != null ? jsonResult.jsonSuccess(newProduct) : jsonResult.jsonFail(null);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.jsonFail("Failed");
        }
        response.getWriter().write(rs);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String rs = "";
        if (pathInfo.indexOf("/find-all") == 0) {
            try {
                List<Product> productList = productService.findAll();
                rs = jsonResult.jsonSuccess(productList);
            } catch (Exception e) {
                e.printStackTrace();
                rs = jsonResult.jsonFail("Error when finding all");
            }
            response.getWriter().write(rs);
        } else if (pathInfo.indexOf("/find-by-id") == 0) {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                Product product = productService.findById(id);
                rs = jsonResult.jsonSuccess(product == null ? "Not found product" : product);
            } catch (Exception e) {
                e.printStackTrace();
                rs = jsonResult.jsonFail("Error when finding product by id");
            }
            response.getWriter().write(rs);
        } else if (pathInfo.indexOf("/sort-by") == 0) {
            String field = request.getParameter("field");
            boolean isAsc = Boolean.parseBoolean(request.getParameter("isasc"));
            try {
                List<Product> productList = productService.sortBy(field, isAsc);
                rs = jsonResult.jsonSuccess(productList);
                response.getWriter().write(rs);
            } catch (Exception e) {
                e.printStackTrace();
                rs = jsonResult.jsonFail("Error when sorting");
            }
        } else if (pathInfo.indexOf("/find-by-category") == 0) {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                List<Product> productList = productService.findByCategory(id);
                rs = jsonResult.jsonSuccess(productList);
                response.getWriter().write(rs);
            } catch (Exception e) {
                e.printStackTrace();
                rs = jsonResult.jsonFail("Error when finding");
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rs = "";
        try{
            Product product = new Gson().fromJson(req.getReader(), Product.class);
            rs = jsonResult.jsonSuccess(productService.update(product));
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.jsonFail("update failed");
        }
        resp.getWriter().write(rs);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rs = "";
        try{
            int id = Integer.parseInt(req.getParameter("id"));
            rs = jsonResult.jsonSuccess(productService.delete(id));
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.jsonFail("Remove Failed");
        }
        resp.getWriter().write(rs);
    }
}
