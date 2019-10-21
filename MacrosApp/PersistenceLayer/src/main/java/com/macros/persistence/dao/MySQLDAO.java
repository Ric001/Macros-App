package com.macros.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;

import com.macros.persistence.dao.connectionlogic.ConnectionManager;
import com.macros.persistence.dao.constants.Querys;
import com.macros.persistence.model.ExecutedOrder;
import com.macros.persistence.model.Order;

//MySQLDAO is the Implementation of the IDAO interface to the MySQL Database Provider
public class MySQLDAO implements IDAO {

    private ConnectionManager manager;
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement pStatement;

    private final static Logger LOG = Logger.getLogger(MySQLDAO.class.getName());

    public MySQLDAO() {
        manager = ConnectionManager.manager();
    }

    //This method is used for the new orders creation
    @Override
    public void create(Order order) throws SQLException {
        LOG.info("[ENTERING void create(Order order)]");

        final Querys createQuery = Querys.CREATE;
        setCommonResources(createQuery);
        pStatement.setString(1, order.getName());
        pStatement.setString(2, order.getContent());
        pStatement.setTimestamp(3, IDAO.toSqlTimestamp(order.getRequestedDate()));
        pStatement.setTimestamp(4, IDAO.toSqlTimestamp(order.getParsedDate()));
        final int response = pStatement.executeUpdate();
        closeResources();
        nullResources();

        LOG.info("[ENDING void create(Order order) Response: " + response + "]");
    }

    //This method is used to modify existing orders
    @Override
    public void modify(Order order) throws SQLException {
        LOG.info("[ENTERING void modify(Order order)]");

        final Querys modifyQuery = Querys.MODIFY;
        setCommonResources(modifyQuery);
        pStatement.setInt(1, order.getId());
        pStatement.setString(2, order.getName());
        pStatement.setString(3, order.getContent());
        pStatement.setTimestamp(4, IDAO.toSqlTimestamp(order.getRequestedDate()));
        pStatement.setTimestamp(5, IDAO.toSqlTimestamp(order.getParsedDate()));
        final int result = pStatement.executeUpdate();
        closeResources();
        nullResources();

        LOG.info("[ENDING void modify(Order order) " + result + "]");
    }

    // This method is used to remove orders
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

    //This method is used to find an specifique order
    @Override
    public Order findOrderById(Integer id) throws SQLException {
        LOG.info("[ENTERING Order findOrderById(Integer id) throws SQLException]");

        final Order order = new Order();
        setCommonResources(Querys.FIND_BY_ID);
        pStatement.setInt(1, id);
        resultSet = pStatement.executeQuery();

        if (Objects.nonNull(resultSet) && !resultSet.isClosed() && resultSet.next()) {
            order.setId(resultSet.getInt("ID"));
            order.setName(resultSet.getString("NAME"));
            order.setContent(resultSet.getString("CONTENT"));
            order.setRequestedDate(IDAO.toLocalDateTime(resultSet.getString("REQUESTED_DATE")));
            order.setParsedDate(IDAO.toLocalDateTime(resultSet.getString("PARSED_DATE")));
        }

        closeResources();
        nullResources();
        LOG.info("[RETURNING FROM Order findOrderById(Integer id) throws SQLException " + order + "]");
        return order;
    }

    //This method is used to list a set of orders.
    @Override
    public Set<Order> findAll() throws SQLException {
        LOG.info("[ENTERING Set<Order> findAll()]");

        final Set<Order> orders = new HashSet<>();
        setCommonResources(Querys.FIND_ALL);
        resultSet = pStatement.executeQuery();
        if (Objects.nonNull(resultSet) && !resultSet.isClosed())
            while (resultSet.next()) {
                final Order order = new Order();
                order.setId(resultSet.getInt("ID"));
                order.setName(resultSet.getString("NAME"));
                order.setContent(resultSet.getString("CONTENT"));
                order.setRequestedDate(IDAO.toLocalDateTime(resultSet.getString("REQUESTED_DATE")));
                order.setParsedDate(IDAO.toLocalDateTime(resultSet.getString("PARSED_DATE")));
                orders.add(order);
            }
        closeResources();
        nullResources();

        LOG.info("[ENDING Set<Order> findAll() Orders -> " + orders + "]");
        return orders;
    }

    //This method is used to list the exectuded orders by the system
    @Override
    public List<ExecutedOrder> executedOrders() throws SQLException {
        LOG.info("[ENTERIGN List<Orde> executedOrders() throws SQLException]");

        final List<ExecutedOrder> executedOrders = new ArrayList<>();
        setCommonResources(Querys.LIST_EXECUTED_ORDERS);
        final ResultSet resultSet = pStatement.executeQuery();
        if (Objects.nonNull(resultSet) && !resultSet.isClosed())
            while(resultSet.next())
            {
                final Integer id = resultSet.getInt("EXECUTIONS_ID");
                final Integer executedOrderId = resultSet.getInt("EXECUTED_ORDER");
                final LocalDateTime executionDateTime = IDAO.toLocalDateTime(resultSet.getDate("EXECTION_DATE"));
                final ExecutedOrder executedOrder = new ExecutedOrder(id, findOrderById(executedOrderId), executionDateTime);
                executedOrders.add(executedOrder);
            }
        closeResources();
        nullResources();

        LOG.info("[RETURNING FROM List<Order> executedOrders() throws SQLException]");
        return executedOrders;
    }

    //this one is used to set the connection and prepare the statement to send to the DBs
    private void setCommonResources(final Querys query) throws SQLException {
        connection = manager.connect();
        pStatement = connection.prepareStatement(query.toString());
    }

    //here we close all the resources which we dont need anymore
    private void closeResources() throws SQLException {
        LOG.info("[ENTERING void closeResources() throws SQLException]");

        if (Objects.nonNull(resultSet) && !resultSet.isClosed())
            resultSet.close();
         if (Objects.nonNull(pStatement) && !pStatement.isClosed())
            pStatement.close();
        if (Objects.nonNull(connection) && !connection.isClosed())
            connection.close();

        LOG.info("[ENDING void closeResources() throws SQLException]");
    }

    //Here we try to remove those instances that we dont really need
    private void nullResources() {
        LOG.info("[NULLING RESOURCES -> void closeResources() throws SQLException]");

        connection = null;
        pStatement = null;
        resultSet = null;

        LOG.info("[connection = null; pStatement = null; resultSet = null]");
    }

    
}