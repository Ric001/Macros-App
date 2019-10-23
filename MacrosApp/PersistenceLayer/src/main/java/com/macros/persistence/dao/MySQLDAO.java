package com.macros.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    // This method is used for the new orders creation
    @Override
    public void create(Order order) throws SQLException {
        LOG.info("[ENTERING void create(Order order)]");

        final Querys createQuery = Querys.CREATE;
        prepareStatementAndConnection(createQuery);
        pStatement.setString(1, order.getName());
        pStatement.setString(2, order.getContent());
        pStatement.setTimestamp(3, IDAO.toSqlTimestamp(order.getRequestedDate()));
        pStatement.setTimestamp(4, IDAO.toSqlTimestamp(order.getParsedDate()));
        final int response = pStatement.executeUpdate();

        LOG.info("[ENDING void create(Order order) Response: " + response + "]");
    }

    // This method is used to modify existing orders
    @Override
    public void modify(Order order) throws SQLException {
        LOG.info("[ENTERING void modify(Order order)]");

        final Querys modifyQuery = Querys.MODIFY;
        prepareStatementAndConnection(modifyQuery);
        pStatement.setString(1, order.getName());
        pStatement.setString(2, order.getContent());
        pStatement.setInt(3, order.getId());
        final int result = pStatement.executeUpdate();

        LOG.info("[ENDING void modify(Order order) " + result + "]");
    }

    // This method is used to remove orders
    @Override
    public void remove(Order order) throws SQLException {
        LOG.info("[ENTERING void remove(Order order)]");

        final Querys removeQuery = Querys.REMOVE;
        prepareStatementAndConnection(removeQuery);
        pStatement.setInt(1, order.getId());
        final int response = pStatement.executeUpdate();

        LOG.info("[ENDING void remove(Order order) RESPONSE =====> " + response + " <====]");
    }

    // This method is used to find an specifique order
    @Override
    public Order findOrderById(Integer id) throws SQLException {
        LOG.info("[ENTERING Order findOrderById(Integer id) throws SQLException]");

        final Order order = new Order();
        prepareStatementAndConnection(Querys.FIND_BY_ID);
        pStatement.setInt(1, id);
        this.resultSet = pStatement.executeQuery();

        if (Objects.nonNull(resultSet) && !resultSet.isClosed() && resultSet.next()) {
            order.setId(resultSet.getInt("ORDERS_ID"));
            order.setName(resultSet.getString("NAME"));
            order.setContent(resultSet.getString("CONTENT"));
            order.setRequestedDate(IDAO.toLocalDateTime(resultSet.getTimestamp("REQUESTED_DATETIME")));
            order.setParsedDate(IDAO.toLocalDateTime(resultSet.getTimestamp("PARSED_DATE")));
        }

        LOG.info("[RETURNING FROM Order findOrderById(Integer id) throws SQLException " + order + "]");
        return order;
    }

    // This method is used to list a set of orders.
    @Override
    public List<Order> findAll() throws SQLException {
        LOG.info("[ENTERING Set<Order> findAll()]");

        final List<Order> orders = new ArrayList<>();
        prepareStatementAndConnection(Querys.FIND_ALL);
        resultSet = pStatement.executeQuery();
        if (Objects.nonNull(resultSet) && !resultSet.isClosed())
            while (resultSet.next()) {
                final Order order = new Order();
                order.setId(resultSet.getInt("ORDERS_ID"));
                order.setName(resultSet.getString("NAME"));
                order.setContent(resultSet.getString("CONTENT"));
                order.setRequestedDate(IDAO.toLocalDateTime(resultSet.getTimestamp("REQUESTED_DATETIME")));
                order.setParsedDate(IDAO.toLocalDateTime(resultSet.getDate("PARSED_DATE")));
                orders.add(order);
            }

        LOG.info("[ENDING Set<Order> findAll() Orders -> " + orders + "]");
        return orders;
    }

    // This method is used to list the exectuded orders by the system
    @Override
    public List<ExecutedOrder> executedOrders() throws SQLException {
        LOG.info("[ENTERIGN List<Orde> executedOrders() throws SQLException]");

        final List<ExecutedOrder> executedOrders = new ArrayList<>();
        prepareStatementAndConnection(Querys.LIST_EXECUTED_ORDERS);
        final ResultSet _resultSet = pStatement.executeQuery();
        if (Objects.nonNull(_resultSet) && !_resultSet.isClosed())
            while (_resultSet.next()) {
                final Integer id = _resultSet.getInt("EXECUTIONS_ID");
                final Integer executedOrderId = _resultSet.getInt("EXECUTED_ORDER");
                final LocalDateTime executionDateTime = IDAO.toLocalDateTime(_resultSet.getTimestamp("EXECUTION_DATE"));
                final ExecutedOrder executedOrder = new ExecutedOrder(id, findOrderById(executedOrderId),
                        executionDateTime);
                executedOrders.add(executedOrder);
            }
        _resultSet.close();
        LOG.info("[RETURNING FROM List<Order> executedOrders() throws SQLException]");
        return executedOrders;
    }

    @Override
    public void create(ExecutedOrder executedOrder) throws SQLException {

    }

    @Override
    public void modify(ExecutedOrder executedOrder) throws SQLException {

    }

    @Override
    public void remove(ExecutedOrder executedOrder) throws SQLException {

    }

    @Override
    public ExecutedOrder findExecutedOrderById(Integer id) throws SQLException {
        LOG.info("[ENTERING void findExecutedOrderById(Integer id) throws SQLException]");

        final ExecutedOrder executedOrder = new ExecutedOrder();
        prepareStatementAndConnection(Querys.FIND_EXECUTED_ORDER_BY_ID);
        pStatement.setInt(1, id);
        final ResultSet _resultSet = pStatement.executeQuery();
        if (Objects.nonNull(_resultSet) && _resultSet.isClosed() && _resultSet.isClosed()) {
            executedOrder.setId(_resultSet.getInt("EXECUTIONS_ID"));
            executedOrder.setExecutedOrder(findOrderById(_resultSet.getInt("EXECUTED_ORDER")));
            executedOrder.setExecutionDatetime(IDAO.toLocalDateTime(_resultSet.getTimestamp("EXECUTION_DATE")));
        }

        LOG.info("[RETURNING FROM ExecutedOrder findExecutedOrderById(Integer id) throws SQLException]");
        return executedOrder;
    }

    // this one is used to set the connection and prepare the statement to send to
    // the DBs
    private void prepareStatementAndConnection(final Querys query) throws SQLException {
        connection = manager.connect();
        pStatement = connection.prepareStatement(query.toString());
    }

    // here we close all the resources which we dont need anymore
    @Override
    public void closeResources() {
        LOG.info("[ENTERING void closeResources() throws SQLException]");

        try {
            if (Objects.nonNull(resultSet) && !resultSet.isClosed())
                resultSet.close();
            if (Objects.nonNull(pStatement) && !pStatement.isClosed())
                pStatement.close();
            if (Objects.nonNull(connection) && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        LOG.info("[ENDING void closeResources() throws SQLException]");
    }

    // Here we try to remove those instances that we dont really need
    @Override
    public void nullResources() {
        LOG.info("[NULLING RESOURCES -> void closeResources() throws SQLException]");

        connection = null;
        pStatement = null;
        resultSet = null;

        LOG.info("[connection = null; pStatement = null; resultSet = null]");
    }

}