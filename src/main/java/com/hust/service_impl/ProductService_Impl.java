package com.hust.service_impl;

import com.hust.dao.ProductDao;
import com.hust.dao_impl.ProductDaoImpl;
import com.hust.model.Product;
import com.hust.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductService_Impl implements ProductService {
    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public Product insert(Product product) throws SQLException {
        return productDao.insert(product);
    }

    @Override
    public boolean update(Product product) throws SQLException {
        return product.getId() > 0 ? productDao.update(product) : false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return id > 0 ? productDao.delete(id) : false;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        return productDao.findAll();
    }

    @Override
    public Product findById(int id) throws SQLException {
        return id > 0 ? productDao.findById(id) : null;
    }

    @Override
    public List<Product> search(String name, String startDate,
                                String endDate, Boolean soldOut,
                                int guarantee, int category,
                                int bought, int promotion) throws Exception {
        return productDao.search(name, startDate, endDate, soldOut, guarantee,
                category, bought, promotion);
    }

    @Override
    public List<Product> sortBy(String field, boolean isAsc) throws SQLException {
        return productDao.sortBy(field, isAsc);
    }

    @Override
    public List<Product> findByCategory(int idCategory) throws Exception {
        return productDao.findByCategory(idCategory);
    }
}
