package com.ricks.utils.ricksio.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


import org.junit.Test;

public class ReadUtilsTest {

    @Test
    public void readToEndOnPropertiesTest() {
        final String fileRoute = "C:\\Users\\jose osorio soto\\HDOOA\\Macros-App\\"
         + "MacrosApp\\PersistenceLayer\\config\\application.properties";
        final File file = new File(fileRoute);
        
        try {
            final BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            final Properties properties = ReadUtils.readToEndOnProperties(bufferedReader);
            System.out.println("=====> User <======= " + properties.getProperty("username"));
            System.out.println("=====> Pass <====== " + properties.getProperty("password"));
            System.out.println("=====> DB <======= " + properties.getProperty("db"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}