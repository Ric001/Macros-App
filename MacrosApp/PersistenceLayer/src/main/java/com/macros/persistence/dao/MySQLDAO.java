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
import com.macros.persistence.dao.constants.DBProviders;
import com.macros.persistence.dao.constants.ExecutionQuerys;
import com.macros.persistence.dao.constants.Querys;
import com.macros.persistence.model.ExecutedOrder;
import com.macros.persistence.model.Order;


public class MySQLDAO implements IDAO {

    private ConnectionManager manager;
    private Connection connection;
    private PreparedStatement pStatement;

    private final static Logger LOG = Logger.getLogger(MySQLDAO.class.getName());

    public MySQLDAO(DBProviders provider) {
        manager = ConnectionManager.manager(provider);
    }

    @Override
    public void create(Order order) throws SQLException {
        LOG.info("[ENTERING void create(Order order)]");

        int response = 0;
        if (Objects.nonNull(order)) {
            final Querys createQuery = Querys.CREATE;
            prepareStatementAndConnection(createQuery);
            if (Objects.nonNull(pStatement) && !pStatement.isClosed()) {
                pStatement.setString(1, order.getName());
                pStatement.setString(2, order.getContent());
                pStatement.setTimestamp(3, IDAO.toSqlTimestamp(order.getRequestedDate()));
                pStatement.setTimestamp(4, IDAO.toSqlTimestamp(order.getParsedDate()));
                response = pStatement.executeUpdate();
            }
        }

        LOG.info("[ENDING void create(Order order) Response: " + response + "]");
    }

    
    @Override
    public void modify(Order order) throws SQLException {
        LOG.info("[ENTERING void modify(Order order)]");

        int result = 0;
        if (Objects.nonNull(order)) {
            final Querys modifyQuery = Querys.MODIFY;
            prepareStatementAndConnection(modifyQuery);
            if (Objects.nonNull(pStatement) && !pStatement.isClosed()) {
                pStatement.setString(1, order.getName());
                pStatement.setString(2, order.getContent());
                pStatement.setInt(3, order.getId());
                result = pStatement.executeUpdate();
            }
        }

        LOG.info("[ENDING void modify(Order order) " + result + "]");
    }


    @Override
    public void remove(Order order) throws SQLException {
        LOG.info("[ENTERING void remove(Order order)]");

        int response = 0;
        if (Objects.nonNull(order)) {
            final Querys removeQuery = Querys.REMOVE;
            prepareStatementAndConnection(removeQuery);
            if (Objects.nonNull(pStatement) && !pStatement.isClosed()) {
                pStatement.setInt(1, order.getId());
                response = pStatement.executeUpdate();
            }
        }

        LOG.info("[ENDING void remove(Order order) RESPONSE =====> " + response + " <====]");
    }

    
    @Override
    public Order findOrderById(Integer id) throws SQLException {
        LOG.info("[ENTERING Order findOrderById(Integer id) throws SQLException]");

        final Order order = new Order();
        prepareStatementAndConnection(Querys.FIND_BY_ID);
        if (Objects.nonNull(pStatement) && !pStatement.isClosed()) {
            pStatement.setInt(1, id);
            final ResultSet resultSet = pStatement.executeQuery();

            if (Objects.nonNull(resultSet) && !resultSet.isClosed() && resultSet.next()) {
                order.setId(resultSet.getInt("ORDERS_ID"));
                order.setName(resultSet.getString("NAME"));
                order.setContent(resultSet.getString("CONTENT"));
                order.setRequestedDate(IDAO.toLocalDateTime(resultSet.getTimestamp("REQUESTED_DATETIME")));
                order.setParsedDate(IDAO.toLocalDateTime(resultSet.getTimestamp("PARSED_DATE")));
            }
        }

        LOG.info("[RETURNING FROM Order findOrderById(Integer id) throws SQLException " + order + "]");
        return order;
    }

    
    @Override
    public List<Order> findAll() throws SQLException {
        LOG.info("[ENTERING Set<Order> findAll()]");

        final List<Order> orders = new ArrayList<>();
        prepareStatementAndConnection(Querys.FIND_ALL);
        if (Objects.nonNull(pStatement) && !pStatement.isClosed()) {

            final ResultSet resultSet = pStatement.executeQuery();
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
        }

        LOG.info("[ENDING Set<Order> findAll() Orders -> " + orders + "]");
        return orders;
    }


    @Override
    public void create(ExecutedOrder executedOrder) throws SQLException {
        LOG.info("[ENTERING List<Order> create() throws SQLException]");

        int response = 0;
        if (Objects.nonNull(executedOrder)) {
            prepareStatementAndConnection(ExecutionQuerys.CREATE);
            if (Objects.nonNull(pStatement) && pStatement.isClosed()) {
                pStatement.setInt(1, executedOrder.getExecutedOrder().getId());
                pStatement.setTimestamp(2, IDAO.toSqlTimestamp(executedOrder.getExecutionDatetime()));
                response = pStatement.executeUpdate();
            }
        }

        LOG.info("[ENDING List<Order> create() throws SQLException -> Update Result: " + response + "]");
    }

    @Override
    public void modify(ExecutedOrder executedOrder) throws SQLException {
        LOG.info("[ENTERING void modify(ExecutedOrder executedOrder) throws SQLException]");

        int response = 0;
        if (Objects.isNull(executedOrder)) {
            prepareStatementAndConnection(ExecutionQuerys.MODIFY);
            if (Objects.nonNull(pStatement) && !pStatement.isClosed()) {
                pStatement.setInt(1, executedOrder.getExecutedOrder().getId());
                pStatement.setTimestamp(2, IDAO.toSqlTimestamp(executedOrder.getExecutionDatetime()));
                response = pStatement.executeUpdate();
            }
        }

        LOG.info("[ENDING void modify(ExecutedOrder executedOrder) throws SQLException response: " + response + "]");
    }

    @Override
    public void remove(ExecutedOrder executedOrder) throws SQLException {
        LOG.info("[ENTERING void remove(ExecutedOrder executedOrder) throws SQLException]");

        int response = 0;
        if (Objects.nonNull(executedOrder)) {
            prepareStatementAndConnection(ExecutionQuerys.REMOVE);
            if (Objects.nonNull(pStatement) && !pStatement.isClosed()) {
                pStatement.setInt(1, executedOrder.getId());
                response = pStatement.executeUpdate();
            }

        }

        LOG.info("[ENDING void remove(ExecutedOrder executedOrder) throws SQLException reponse -> " + response + "]");
    }

    @Override
    public ExecutedOrder findExecutedOrderById(Integer id) throws SQLException {
        LOG.info("[ENTERING void findExecutedOrderById(Integer id) throws SQLException]");

        final ExecutedOrder executedOrder = new ExecutedOrder();
        prepareStatementAndConnection(ExecutionQuerys.FIND_BY_ID);
        pStatement.setInt(1, id);
        if (Objects.nonNull(pStatement) && !pStatement.isClosed()) {
            final ResultSet _resultSet = pStatement.executeQuery();
            if (Objects.nonNull(_resultSet) && !_resultSet.isClosed() && _resultSet.next()) {
                executedOrder.setId(_resultSet.getInt("EXECUTIONS_ID"));
                executedOrder.setExecutedOrder(findOrderById(_resultSet.getInt("EXECUTED_ORDER")));
                executedOrder.setExecutionDatetime(IDAO.toLocalDateTime(_resultSet.getTimestamp("EXECUTION_DATE")));
            }
        }
        LOG.info("[RETURNING FROM ExecutedOrder findExecutedOrderById(Integer id) throws SQLException]");
        return executedOrder;
    }

    @Override
    public List<ExecutedOrder> executedOrders() throws SQLException {
        LOG.info("[ENTERING List<Orde> executedOrders() throws SQLException]");

        final List<ExecutedOrder> executedOrders = new ArrayList<>();
        prepareStatementAndConnection(ExecutionQuerys.FIND_ALL);

        if (Objects.nonNull(pStatement) && !pStatement.isClosed()) {
            final ResultSet _resultSet = pStatement.executeQuery();

            if (Objects.nonNull(_resultSet) && !_resultSet.isClosed()) {
                while (_resultSet.next()) {
                    final Integer id = _resultSet.getInt("EXECUTIONS_ID");
                    final Integer executedOrderId = _resultSet.getInt("EXECUTED_ORDER");
                    final LocalDateTime executionDateTime = IDAO
                            .toLocalDateTime(_resultSet.getTimestamp("EXECUTION_DATE"));
                    final ExecutedOrder executedOrder = new ExecutedOrder(id, findOrderById(executedOrderId),
                            executionDateTime);
                    executedOrders.add(executedOrder);
                }
            }
            _resultSet.close();
        }

        LOG.info("[RETURNING FROM List<Order> executedOrders() throws SQLException]");
        return executedOrders;
    }

    private void prepareStatementAndConnection(final Querys query) throws SQLException {
        connection = manager.connect();
        pStatement = connection.prepareStatement(query.toString());
    }

    private void prepareStatementAndConnection(final ExecutionQuerys query) throws SQLException {
        connection = manager.connect();
        pStatement = connection.prepareStatement(query.toString());
    }

    @Override
    public void closeResources() {
        LOG.info("[ENTERING void closeResources() throws SQLException]");

        try {
            if (Objects.nonNull(pStatement) && !pStatement.isClosed())
                pStatement.close();
            if (Objects.nonNull(connection) && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        LOG.info("[ENDING void closeResources() throws SQLException]");
    }

    @Override
    public void nullResources() {
        LOG.info("[NULLING RESOURCES -> void closeResources() throws SQLException]");

        connection = null;
        pStatement = null;

        LOG.info("[connection = null; pStatement = null; resultSet = null]");
    }

}