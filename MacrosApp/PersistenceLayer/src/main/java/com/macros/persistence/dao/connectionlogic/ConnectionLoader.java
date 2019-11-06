package com.macros.persistence.dao.connectionlogic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Objects;
import java.util.Properties;

import com.macros.persistence.dao.constants.DBLinks;
import com.macros.persistence.dao.constants.DBProviders;

import com.ricks.utils.ricksio.reader.ReadUtils;

public class ConnectionLoader {

    private String username;
    private String password;
    private String dbName;
    private String configurationFileRoute;
    private DBLinks accessLink;

    public ConnectionLoader(DBProviders provider, String configurationFileRoute) {
        System.out.println(configurationFileRoute);
        this.configurationFileRoute = configurationFileRoute;
        setDBLinkType(provider);
        findConfigurationByRoute();
    }

    public String connectionString() {
        final String connectionString = new StringBuilder().append(accessLink).append(dbName + "?user=")
                .append(username + "&password=").append(password).toString();
        System.out.println(String.format("======> Connection String ====> [%s]", connectionString));
        return connectionString;
    }

    private void setDBLinkType(DBProviders provider) {
        if (Objects.nonNull(provider))
            switch (provider) {
            case MYSQL:
                accessLink = DBLinks.MYSQL_LINK;
                break;
            default: accessLink = DBLinks.UNSUPPORTED_DB_LINK;
            }
    }

    private void setCredentials(final Properties properties) {
        if (Objects.nonNull(properties) && !properties.isEmpty()) {
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            dbName = properties.getProperty("db");
        }
    }

    private void findConfigurationByRoute() {
        BufferedReader bReader = null;
        try {
            final File file = new File(configurationFileRoute);
            if (file.exists() && file.isFile()) {
                System.out.println(String.format(">>>>> Route Maps To A File? >>>> [%s]", file.isFile()));
                System.out.println(String.format(">>>>> File Exits? >>>>> [%s]", file.exists()));
                bReader = new BufferedReader(new FileReader(file));
                setCredentials(ReadUtils.readToEndOnProperties(bReader));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(bReader);
        }
    }

    private void closeStream(Reader reader) {
        if (Objects.nonNull(reader)) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "ConnectionLoader [configurationFileRoute=" + configurationFileRoute + ", dbName=" + dbName + ", link="
                + accessLink + ", password=" + password + ", username=" + username + "]";
    }
}