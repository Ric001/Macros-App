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
        assertNotNull(manager);
    }
    @Test
     public void testConnection() {
        final ConnectionManager manager = ConnectionManager.manager();
        Connection connection = null;
        try {
            connection = manager.connect();
            assertNotNull(connection);
        } catch (SQLException e) {
            assertNotNull(connection);
            e.printStackTrace();
        }
    }

}