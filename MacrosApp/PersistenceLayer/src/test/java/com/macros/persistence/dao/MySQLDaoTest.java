package com.macros.persistence.dao;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import com.macros.persistence.model.Order;

import org.junit.Test;


public class MySQLDaoTest {

    @Test
    public void createTest() {
        final IDAO dao = new MySQLDAO();
        final Order order = order();
        System.out.println("\n Test Order => " + order + "\n");
        try {
            dao.create(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllTest() {
        final IDAO dao = new MySQLDAO();
        try {
            final List<Order> orders = dao.findAll();
            assertNotNull(orders);
            assertNotEquals(orders.size(), 0);
            for (Order orderFromSet : orders)
                System.out.println("\n ORDER =======> " + orderFromSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test 
    public void findOrderByIdTest() {
        final IDAO dao = new MySQLDAO();
        try {
            final Order order = dao.findOrderById(1);
            assertNotNull(order);
            System.out.println("\n ====> Gotten Order From DB ======> " + order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test 
    public void modifyTest() {
        final IDAO dao = new MySQLDAO();
        try {
            final Order order = dao.findOrderById(1);
            order.setName("[Toaster On At Morning]");
            dao.modify(order);
            assertNotNull(order);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private Order order()
    {
        final String name = "Order needed";
        final String content = "{orders: ['One', 'Two']}";
        final LocalDateTime requDateTime = LocalDateTime.now();
        final LocalDateTime parsedDateTime = LocalDateTime.now();
        System.out.println(requDateTime);
        System.out.println(parsedDateTime);
        return new Order(name, content, requDateTime, parsedDateTime);
    }
}