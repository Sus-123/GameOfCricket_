package com.company.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileReaderConfiguration {

    public static Properties getProperties(String filename)  {
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            Properties properties = new Properties();
            properties.load(fileInputStream);
            return properties;
        } catch (IOException e){
            e.printStackTrace();
            throw new IllegalStateException("Error while loading properties file");
        }
    }

}