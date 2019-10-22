package com.macros.persistence.services;

import java.util.List;
import java.util.Set;

import com.macros.persistence.model.ExecutedOrder;
import com.macros.persistence.model.Order;

public interface IPersistenceService 
{
    public void create(Order order);
    public void modify(Order order);
    public void remove(Order order);
    public Order findOrderById(Integer id);
    public List<Order> findAll();
    public List<ExecutedOrder> executedOrders();
    
}