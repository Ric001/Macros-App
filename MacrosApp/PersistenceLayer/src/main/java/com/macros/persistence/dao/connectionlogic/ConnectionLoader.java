package com.macros.persistence.dao.connectionlogic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Objects;

import com.macros.persistence.dao.constants.DBLinks;
import com.macros.persistence.dao.constants.DBProviders;

public class ConnectionLoader {

    private String username;
    private String password;
    private String dbName;
    private String configurationFileRoute;
    private DBLinks accessLink;

    public ConnectionLoader(DBProviders provider, String configurationFileRoute) {
        setDBLinkType(provider);
        findConfigurationByRoute();
        this.configurationFileRoute = configurationFileRoute;
    }

    public String connectionString() {
        return new StringBuilder().append(accessLink + "/").append(dbName + "/").append(username + "/").append(password)
                .toString();
    }

    private void setDBLinkType(DBProviders provider) {
        if (Objects.nonNull(provider))
            switch (provider) {
            case MYSQL:
                accessLink = DBLinks.MYSQL_LINK;
                break;
            }
    }

    private void setCredentials(final String credentialRead) {

        if (Objects.isNull(credentialRead) || credentialRead.isEmpty())
            return;
            
        final String[] credentialsArray = credentialRead.split("|");
        username = credentialsArray[0];
        password = credentialsArray[1];
        dbName = credentialsArray[2];
    }

    private void findConfigurationByRoute() {
        BufferedReader bReader = null;
        
        try {
            final File file = new File(configurationFileRoute);
            if (file.exists() && file.isFile()) {
                bReader = new BufferedReader(new FileReader(file));
                String credentialsString = bReader.readLine();
                setCredentials(credentialsString);   
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
        catch (IOException e) {
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