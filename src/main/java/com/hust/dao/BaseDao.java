package com.hust.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//Lập trình tổng quát với <T>
public interface BaseDao<T> {

    T getObject(ResultSet resultSet) throws SQLException;

    List<T> getList(ResultSet resultSet) throws SQLException;

    // lấy tất cả các bán ghi trong database của bảng category
    List<T> findAll() throws SQLException;

    T findById(int id) throws SQLException;

    T insert(T t) throws SQLException;

    boolean update(T category) throws SQLException;

    boolean delete(int id) throws SQLException;
}
