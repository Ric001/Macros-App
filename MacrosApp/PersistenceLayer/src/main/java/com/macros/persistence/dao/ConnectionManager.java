package com.macros.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Logger;

public class ConnectionManager {

    private Connection connection;
    private static ConnectionManager manager;
    
    private final static Logger LOG = Logger.getLogger(ConnectionManager.class.getName());

    private static ConnectionLoader loader;

    private ConnectionManager() {
    }

    public static ConnectionManager manager() {
        LOG.info("[ENTERING static ConnectionManager manager()]");

        if (Objects.isNull(manager))
            manager = new ConnectionManager();

        LOG.info("[RETURNING FROM static ConnectionManager manager() " + manager + "]");
        return manager;
    }

    public Connection connect() throws SQLException {
        LOG.info("[ENTERING Connection connect() throws SQLException ]");

        if (Objects.isNull(connection) || connection.isClosed())
            connection = loadConnection();
        
        LOG.info("[RETURNING Connection connect() throws SQLException " + connection + "]");
        return connection;
    }

    public static Connection loadConnection() throws SQLException 
    {
        return DriverManager.getConnection(loader.connectionString());
    }

    @Override
    public String toString() {
        return "ConnectionManager [connection=" + connection + "]";
    }  
}