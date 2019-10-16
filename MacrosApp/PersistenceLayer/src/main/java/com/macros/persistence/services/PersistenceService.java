package com.macros.persistence.services;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import com.macros.persistence.dao.IDAO;
import com.macros.persistence.dao.MySQLDAO;
import com.macros.persistence.model.Order;

public class PersistenceService implements IPersistenceService {

    private IDAO dao;
    private final static Logger LOG = Logger.getLogger(PersistenceService.class.getName());
    public PersistenceService() 
    {
        dao = new MySQLDAO();
    }
    @Override
    public void create(Order order) {
        LOG.info("[ENTERING void create(Order order)]");

        try {
            dao.create(order);
        } catch (SQLException e) {
            e.printStackTrace();
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
        }

        LOG.info("[RETURNING FROM Order findOrderById(Integer id) " + order + "]");
        return order;
    }

    @Override
    public Set<Order> findAll() {
        LOG.info("[ENTERING Set<Order> findAll()]");

        Set<Order> orders = new HashSet<>();
        try {
            orders = dao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        LOG.info("RETURNING FROM Set<Order> findAll() WITH -> " + orders + "]");
        return orders;
    }

    @Override
    public String toString() {
        return "PersistenceService [dao=" + dao + "]";
    }

    
}