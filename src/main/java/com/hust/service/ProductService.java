package com.hust.service;

import com.hust.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    Product insert(Product product) throws SQLException;

    boolean update(Product product) throws SQLException;

    boolean delete(int id) throws SQLException;

    List<Product> findAll() throws SQLException;

    Product findById(int id) throws SQLException;

    List<Product> search(String name, String startDate, String endDate,
                         Boolean soldOut, int guarantee, int category, int bouth, int promotion) throws Exception;

    List<Product> sortBy(String field, boolean isAsc) throws SQLException;

    List<Product> findByCategory(int idCategory) throws Exception;
}
