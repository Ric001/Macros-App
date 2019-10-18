package com.macros.persistence.dao.connectionlogic;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Logger;

import com.macros.persistence.dao.constants.DBProviders;

public class ConnectionManager {

    private Connection connection;
    private static ConnectionManager manager;
    private static ConnectionLoader loader;
    private final static Logger LOG = Logger.getLogger(ConnectionManager.class.getName());
    
    private final static String CREDENTIALS_FILE = "credentials.txt";
    private final static String CONFIG_FOLDER = "\\config";
    
    private ConnectionManager() {
        loader = new ConnectionLoader(DBProviders.MYSQL, pathToConfiguration());
    }

    public synchronized static ConnectionManager manager() {
        LOG.info("[ENTERING static ConnectionManager manager()]");

        if (Objects.isNull(manager))
            manager = new ConnectionManager();

        LOG.info("[RETURNING FROM static ConnectionManager manager() " + manager + "]");
        return manager;
    }

    public synchronized Connection connect() throws SQLException {
        LOG.info("[ENTERING Connection connect() throws SQLException ]");
        
        if (Objects.isNull(connection) || connection.isClosed())
            if (Objects.nonNull(loader))
                connection = DriverManager.getConnection(loader.connectionString());
        
        LOG.info("[RETURNING Connection connect() throws SQLException " + connection + "]");
        return connection;
    }

    private String pathToConfiguration() 
    {
        LOG.info("[ENTERING String pathConfiguration()]");

        final String route = new File(CONFIG_FOLDER).getAbsolutePath() + "\\" + CREDENTIALS_FILE; 
        
        LOG.info("[RETURNING FROM String pathConfiguration()]");
        return route;
    }

    @Override
    public String toString() {
        return "ConnectionManager [connection=" + connection + "]";
    }  
}