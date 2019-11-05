package com.macros.persistence.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import com.macros.persistence.dao.IDAO;
import com.macros.persistence.model.ExecutedOrder;
import com.macros.persistence.model.Order;

public class PersistenceService implements IPersistenceService {

    private IDAO dao;
    private final static Logger LOG = Logger.getLogger(PersistenceService.class.getName());

    public PersistenceService(IDAO dao) {
        this.dao = dao;
    }

    @Override
    public void create(Order order) {
        LOG.info("[ENTERING void create(Order order)]");

        try {
            dao.create(order);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.closeResources();
            dao.nullResources();
        }

        LOG.info("[ENDING void create(Order order)]");
    }

    @Override
    public void modify(Order order) {
        LOG.info("[ENTERING void modify(Order order)]");

        try {
            dao.modify(order);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.closeResources();
            dao.nullResources();
        }

        LOG.info("[ENDING void modify(Order order)]");
    }

    @Override
    public void remove(Order order) {
        LOG.info("[ENTERING void remove(Order order)]");

        try {
            dao.remove(order);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.closeResources();
            dao.nullResources();
        }

        LOG.info("[ENDING void remove(Order order)]");
    }

    @Override
    public Order findOrderById(Integer id) {
        LOG.info("[ENTERING Order findOrderById(Integer id)]");

        Order order = new Order();
        try {
            order = dao.findOrderById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.closeResources();
            dao.nullResources();
        }

        LOG.info("[RETURNING FROM Order findOrderById(Integer id) " + order + "]");
        return order;
    }

    @Override
    public List<Order> findAll() {
        LOG.info("[ENTERING Set<Order> findAll()]");

        List<Order> orders = new ArrayList<>();
        try {
            orders = dao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.closeResources();
            dao.nullResources();
        }

        LOG.info("RETURNING FROM Set<Order> findAll() WITH -> " + orders + "]");
        return Collections.unmodifiableList(orders);
    }

    @Override
    public List<ExecutedOrder> executedOrders() {
        LOG.info("[ENTERING List<ExecutedOrder> executedOrders()]");
        List<ExecutedOrder> executedOrders = new ArrayList<>();

        try {
            executedOrders = dao.executedOrders();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.closeResources();
            dao.nullResources();
        }

        LOG.info("[ENTERING List<ExecutedOrder> executedOrders()]");
        return Collections.unmodifiableList(executedOrders);
    }

    @Override
    public String toString() {
        return "PersistenceService [dao=" + dao + "]";
    }

    @Override
    public void create(ExecutedOrder executedOrder) {
        LOG.info("[ENTERING void create(ExecutedOrder executedOrder)]");

        try {
            dao.create(executedOrder);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.closeResources();
            dao.nullResources();
        }

        LOG.info("[ENDING void create(ExecutedOrder executedOrder)]");
    }

    @Override
    public void modify(ExecutedOrder executedOrder) {
        LOG.info("[ENTERING void modify(ExecutedOrder executedOrder)]");
        
        try {
            dao.modify(executedOrder);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.closeResources();
            dao.nullResources();
        }

        LOG.info("[ENDING void modify(ExecutedOrder executedOrder)]");
    }

    @Override
    public void remove(ExecutedOrder executedOrder) {
        LOG.info("[ENTERING void remove(ExecutedOrder executedOrder)]");
        try {
            dao.remove(executedOrder);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.closeResources();
            dao.nullResources();
        }
        LOG.info("[ENDING void remove(ExecutedOrder executedOrder)]");
    }

    @Override
    public ExecutedOrder findExecutedOrderById(Integer id) {
        LOG.info("[ENTERING ExecutedOrder findExecutedOrderById(Integer id)]");
        try {
            dao.findExecutedOrderById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.closeResources();
            dao.nullResources();
        }
        LOG.info("[ENDING ExecutedOrder findExecutedOrderById(Integer id)]");
        return null;
    }
}