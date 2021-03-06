package com.macros.persistence;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import com.macros.persistence.dao.connectionlogic.ConnectionManager;
import com.macros.persistence.dao.constants.DBProviders;

import org.junit.Test;

public class ConnectionManagerTest {

    @Test
    public void testConnectionManager()
    {
        final ConnectionManager manager = ConnectionManager.manager(DBProviders.MYSQL);
        assertNotNull(manager);
    }
    @Test
     public void testConnection() {
        final ConnectionManager manager = ConnectionManager.manager(DBProviders.MYSQL);
        assertNotNull(manager);
        try {
            assertNotNull(manager.connect());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}