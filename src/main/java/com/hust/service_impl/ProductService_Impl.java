package com.hust.service_impl;

import com.hust.dao.ProductDao;
import com.hust.dao_impl.ProductDaoImpl;
import com.hust.model.Product;
import com.hust.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductService_Impl implements ProductService{
    private ProductDao productDao = new ProductDaoImpl();
    @Override
    public Product insert(Product product) throws SQLException {
        return productDao.insert(product);
    }

    @Override
    public boolean update(Product product) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        return null;
    }

    @Override
    public Product findById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Product> search(String name, String startDate, String endDate, Boolean soldOut, int guarantee, int category, int bouth, int promotion) throws SQLException {
        return null;
    }

    @Override
    public List<Product> sortBy(String field, boolean isAsc) throws SQLException {
        return null;
    }

    @Override
    public List<Product> findByCategory(int idCategory) throws SQLException {
        return null;
    }
}
