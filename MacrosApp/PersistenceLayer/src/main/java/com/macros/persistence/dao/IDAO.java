package com.macros.persistence.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.macros.persistence.model.ExecutedOrder;
import com.macros.persistence.model.Order;
import com.ricks.utils.string.Strings;

public interface IDAO {
    
    public void create(Order order) throws SQLException;
    public void modify(Order order) throws SQLException;
    public void remove(Order order) throws SQLException;
    public Order findOrderById(Integer id) throws SQLException;
    public Set<Order> findAll() throws SQLException;
    public List<ExecutedOrder> executedOrders() throws SQLException;

    public static LocalDateTime toLocalDateTime(final Date date)
    { 
        if (Strings.nonNullOrEmpty(date.toString()))  
            return toLocalDateTime(date.toString());
        return null;
    }

    public static LocalDateTime toLocalDateTime(final String dateStr)
    {
        if (Strings.nonNullOrEmpty(dateStr))
            return LocalDateTime.parse(dateStr);
        return null;
    }

    public static Date toSqlDate(final String localDateTime) 
    {
        if (Strings.nonNullOrEmpty(localDateTime)) {
            return Date.valueOf(localDateTime);
        }
        return null;
    }

    public static Date toSqlDate(final LocalDateTime localDateTime)
    {
        if (Objects.nonNull(localDateTime))
            return toSqlDate(localDateTime.toString());
        return null;
    }
}