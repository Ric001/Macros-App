package com.macros.persistence.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
    public List<Order> findAll() throws SQLException;
    public List<ExecutedOrder> executedOrders() throws SQLException;

    //final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"); 
    final static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static LocalDateTime toLocalDateTime(final Date date)
    { 
        if (Strings.nonNullOrEmpty(date.toString()))  
        {
            final String formattedDate = formatter.format(date);
            final Timestamp timestampFromFormattedDate = Timestamp.valueOf(formattedDate);
            System.out.println("==========> Converted Timestamp =====>" + timestampFromFormattedDate);
            return timestampFromFormattedDate.toLocalDateTime(); 
        }
            
        return null;
    }

    public static LocalDateTime toLocalDateTime(final Timestamp timestamp)
    {
        if (Objects.nonNull(timestamp))
        {
            LocalDateTime localDateTimeFromTimestamp = timestamp.toLocalDateTime();
            System.out.println("==========> Converted Timestamp =====>" + localDateTimeFromTimestamp);
            return localDateTimeFromTimestamp;
        }
        return null;
    }

    public static Timestamp toSqlTimestamp(final String localDateTime) 
    {
        if (Strings.nonNullOrEmpty(localDateTime)) 
            return toSqlTimestamp(LocalDateTime.parse(localDateTime));
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

    public static Date toSqlDate(final LocalDate localDate)
    {
        if (Objects.nonNull(localDate))
        {
            final Date convertedDate = Date.valueOf(localDate);
            return convertedDate;
        }
        return null;
    }

    public static Date toSqlDate(final String localDate)
    {
        if (Strings.nonNullOrEmpty(localDate))
        {
            final Date convertedFromString  = Date.valueOf(LocalDate.parse(localDate));
            return convertedFromString;
        }
        return null;
    }
}