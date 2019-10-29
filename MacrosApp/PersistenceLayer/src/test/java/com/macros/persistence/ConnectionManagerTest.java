package com.macros.persistence;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;

import com.macros.persistence.dao.connectionlogic.ConnectionManager;

import org.junit.Test;

public class ConnectionManagerTest {

    @Test
    public void testConnectionManager()
    {
        final ConnectionManager manager = ConnectionManager.manager();
        
    }
    @Test
     public void testConnection() {
        final ConnectionManager manager = ConnectionManager.manager();
        Connection connection = null;
        try {
            connection = manager.connect();
        
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }

}