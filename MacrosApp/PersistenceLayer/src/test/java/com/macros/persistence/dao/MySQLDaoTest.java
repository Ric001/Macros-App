package com.macros.persistence.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;

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