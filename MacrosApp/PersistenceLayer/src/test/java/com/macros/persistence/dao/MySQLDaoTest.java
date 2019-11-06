package com.macros.persistence.dao;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.macros.persistence.dao.constants.DBProviders;
import com.macros.persistence.factory.DAOFactory;
import com.macros.persistence.model.ExecutedOrder;
import com.macros.persistence.model.Order;

import org.junit.Test;


public class MySQLDaoTest {

    @Test
    public void createTest() {
        final IDAO dao = new MySQLDAO(DBProviders.MYSQL);
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
        final IDAO dao = new MySQLDAO(DBProviders.MYSQL);
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
        final IDAO dao = new MySQLDAO(DBProviders.MYSQL);
        try {
            final Order order = dao.findOrderById(1);
            assertNotNull(order);
            System.out.println("\n ====> Gotten Order From DB ======> " + order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   /* @Test 
    public void modifyTest() {
        final IDAO dao = new MySQLDAO();
        try {
            final Order order = dao.findOrderById(59);
            order.setName("[Toaster On At Morning]");
            order.setContent("Iron Maiden");
            System.out.println("======> Order " + order + "<========");
            dao.modify(order);
            assertNotNull(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    @Test
    public void removeTest() {
        final IDAO dao = new MySQLDAO(DBProviders.MYSQL);
        assertNotNull(dao);
        try {
            final Order order = dao.findOrderById(65);
            assertNotNull(order);
            System.out.println("\n========> Order TO Remove <======= \n" + order);
            dao.remove(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void executedOrdersTest() {
        final IDAO dao = new MySQLDAO(DBProviders.MYSQL);
        try {
            final List<ExecutedOrder> executedOrders =  dao.executedOrders();
            assertNotNull(executedOrders);
            assertNotEquals(executedOrders.size(), 0);
            System.out.println("\n========> Exectued Orders <==========\n");
            System.out.println(executedOrders + "\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findExecutionByIdTest() {
        final Optional<IDAO> dao = new DAOFactory().daoByProvider(DBProviders.MYSQL);
        assertNotNull(dao.isPresent() ? dao.get() : dao.get());
        System.out.println(String.format("\n=======> RETURNED DAO [%s] <=========\n", dao));
        try {
            final Order order = dao.get().findOrderById(1);
            assertNotNull(order);
            final ExecutedOrder executedOrder = dao.get().findExecutedOrderById(order.getId());
            System.out.println(String.format("========> Executed Order [%s] <========", executedOrder));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test 
    public void listExecutionsTest() {
        final Optional<IDAO> dao = new DAOFactory().daoByProvider(DBProviders.MYSQL);
        assertNotNull(dao.isPresent() ? dao.get() : dao.get());
        System.out.println(String.format("\n========> RETURNED DAO [%s] <========\n", dao));
        try {
            final List<ExecutedOrder> executedOrders = dao.get().executedOrders();
            assertNotNull(executedOrders);
            System.out.println(String.format("\n=====> Executed Orders [%s] <======\n", executedOrders));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 

    @Test
    public void createExecutionTest() {
        final Optional<IDAO> dao = new DAOFactory().daoByProvider(DBProviders.MYSQL);
        assertTrue(dao.isPresent());
        System.out.println(String.format("\n========> RETURNED DAO [%s] <======\n", dao));
        try {
            final ExecutedOrder executedOrder = dao.get().findExecutedOrderById(2);
            executedOrder.setId(3);
            executedOrder.setExecutionDatetime(LocalDateTime.now());
            dao.get().create(executedOrder);
            final ExecutedOrder executedOrder2 = dao.get().findExecutedOrderById(3); 
            assertNotNull(executedOrder2);
            System.out.println(String.format("\n=======> NEW Executed Order [%s] <=======\n", executedOrder2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
    
    /**
    @Test
    public void modifyExecutionOrdersTest() {
        final Optional<IDAO> dao = new DAOFactory().daoByProvider(DBProviders.MYSQL);
        assertTrue(dao.isPresent());
        try {
            final ExecutedOrder executedOrder = dao.get().findExecutedOrderById(1);
            executedOrder.setOrder(dao.get().findOrderById(15));
            assertNotNull(executedOrder);
            assertNotNull(executedOrder.getOrder());
            dao.get().modify(executedOrder);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } */

    @Test
    public void removeExecutionOrderTest() {
        final Optional<IDAO> dao = new DAOFactory().daoByProvider(DBProviders.MYSQL);
        assertNotNull(dao);
        assertTrue(dao.isPresent());
        System.out.println(String.format("\n=======> DAOFactory <=======\n", dao));
        try {
            final ExecutedOrder executedOrder = dao.get().findExecutedOrderById(7);
            assertNotNull(executedOrder);
            System.out.println(String.format("\n========> Executed Order to Remove %s <=======\n", executedOrder));
            dao.get().remove(executedOrder);
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }
    
    private Order order() {
        final String name = "Order needed";
        final String content = "Julues Verne";
        final LocalDateTime requDateTime = LocalDateTime.now();
        final LocalDateTime parsedDateTime = LocalDateTime.now();
        System.out.println(requDateTime);
        System.out.println(parsedDateTime);
        return new Order(name, content, requDateTime, parsedDateTime);
    }
}