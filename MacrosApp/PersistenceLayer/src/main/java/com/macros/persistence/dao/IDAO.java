package com.macros.persistence.dao;

import java.sql.Timestamp;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    final static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE; 
    //final static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

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

    public static Timestamp toSqlTimestamp(final String localDateTime) 
    {
        if (Strings.nonNullOrEmpty(localDateTime)) 
            return toSqlTimestamp(LocalDateTime.parse(localDateTime.toString()));
        return null;
    }

    public static Timestamp toSqlTimestamp(final LocalDateTime localDateTime)
    {
        if (Objects.nonNull(localDateTime))
        {
            final Timestamp timestamp = Timestamp.valueOf(localDateTime);
            
            return timestamp;
        }
        return null;
    }


}