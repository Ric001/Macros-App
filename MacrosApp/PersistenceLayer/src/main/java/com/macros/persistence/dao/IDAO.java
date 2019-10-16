package com.macros.persistence.dao;

import java.sql.SQLException;
import java.util.Set;

import com.macros.persistence.model.Order;

public interface IDAO {
    
    public void create(Order order) throws SQLException;
    public void modify(Order order) throws SQLException;
    public void remove(Order order) throws SQLException;
    public Order findOrderById(Integer id) throws SQLException;
    public Set<Order> findAll();
    
}