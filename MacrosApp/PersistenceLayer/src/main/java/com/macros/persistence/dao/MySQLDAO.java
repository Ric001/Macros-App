package com.macros.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

import com.macros.persistence.dao.connectionlogic.ConnectionManager;
import com.macros.persistence.dao.constants.Querys;
import com.macros.persistence.model.Order;

public class MySQLDAO implements IDAO {

    private ConnectionManager manager;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement pStatement;

    private final static Logger LOG = Logger.getLogger(MySQLDAO.class.getName());

    public MySQLDAO() {
        manager = ConnectionManager.manager();
    }

    @Override
    public void create(Order order) throws SQLException 
    {
        LOG.info("[ENTERING void create(Order order)]");

        final Querys createQuery = Querys.CREATE;
        setCommonResources(createQuery);
        pStatement.setString(1, order.getName());
        pStatement.setString(2, order.concatContent());
        final int response = pStatement.executeUpdate();
        closeResources();
        nullResources();

        LOG.info("[ENDING void create(Order order) Response: " + response + "]");
    }

    @Override
    public void modify(Order order) throws SQLException {
        LOG.info("[ENTERING void modify(Order order)]");

        final Querys modifyQuery = Querys.MODIFY;

        setCommonResources(modifyQuery);
        pStatement.setInt(1, order.getId());
        pStatement.setString(2, order.getName());
        pStatement.setString(3, order.concatContent());
        final int result = pStatement.executeUpdate();
        closeResources();
        nullResources();
    
        LOG.info("[ENDING void modify(Order order) " + result + "]");
    }

    @Override
    public void remove(Order order) throws SQLException {
        LOG.info("[ENTERING void remove(Order order)]");
        
        final Querys removeQuery = Querys.REMOVE;
        setCommonResources(removeQuery);
        pStatement.setInt(1, order.getId());
        pStatement.executeUpdate();

        closeResources();
        nullResources();
        
        LOG.info("[ENDING void remove(Order order)]");
    }

    @Override
    public Order findOrderById(Integer id) throws SQLException {
        LOG.info("[ENTERING Order findOrderById(Integer id) throws SQLException]");

        final Order order = new Order();
        setCommonResources(Querys.FIND_BY_ID);
        pStatement.setInt(1, id);
        resultSet = pStatement.executeQuery();
        
        if (Objects.nonNull(resultSet) && !resultSet.isClosed() && resultSet.next())
        {
            order.setId(resultSet.getInt("ID"));
            order.setName(resultSet.getString("NAME"));
            order.setConcatContent(resultSet.getString("CONTENT"));
        }
        
        closeResources();
        nullResources();
        LOG.info("[RETURNING FROM Order findOrderById(Integer id) throws SQLException " + order + "]");
        return order;
    }

    @Override
    public Set<Order> findAll() throws SQLException {
        LOG.info("[ENTERING Set<Order> findAll()]");

        final Set<Order> orders = new HashSet<>();
        setCommonResources(Querys.FIND_ALL);
        resultSet = pStatement.executeQuery();
        if (Objects.nonNull(resultSet) && !resultSet.isClosed())
            while(resultSet.next())
            {
                final Order order = new Order();
                order.setId(resultSet.getInt("ID"));
                order.setName(resultSet.getString("NAME"));
                order.setConcatContent(resultSet.getString("CONTENT"));
                orders.add(order);
            }
        closeResources();
        nullResources();
        
        LOG.info("[ENDING Set<Order> findAll() Orders -> " + orders + "]");
        return orders;
    }

    private void setCommonResources(final Querys query) throws SQLException {
        connection = manager.connect();
        pStatement = connection.prepareStatement(query.toString());
    }

    private void closeResources() throws SQLException
    {
        LOG.info("[ENTERING void closeResources() throws SQLException]");

        if (Objects.nonNull(connection) || !connection.isClosed())
            connection.close();
        if (Objects.nonNull(pStatement) || !pStatement.isClosed())
            pStatement.close();
        if (Objects.nonNull(resultSet) || !resultSet.isClosed())
            resultSet.close();
        
        LOG.info("[ENDING void closeResources() throws SQLException]");
    }

    private void nullResources() 
    {
        LOG.info("[NULLING RESOURCES -> void closeResources() throws SQLException]");

        connection = null;
        pStatement = null;
        resultSet = null;
    }
}