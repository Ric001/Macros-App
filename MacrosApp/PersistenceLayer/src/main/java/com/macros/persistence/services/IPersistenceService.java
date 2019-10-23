package com.macros.persistence.services;


import java.util.List;

import com.macros.persistence.model.ExecutedOrder;
import com.macros.persistence.model.Order;

public interface IPersistenceService {

    public void create(Order order);

    public void modify(Order order);

    public void remove(Order order);

    public Order findOrderById(Integer id);

    public List<Order> findAll();

    public void create(ExecutedOrder executedOrder);

    public void modify(ExecutedOrder executedOrder);

    public void remove(ExecutedOrder executedOrder);

    public ExecutedOrder findExecutedOrderById(Integer id);

    public List<ExecutedOrder> executedOrders();
}