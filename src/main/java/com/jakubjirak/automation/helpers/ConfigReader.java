package com.jakubjirak.automation.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static ConfigReader instance = null;
    private Properties properties;

    private ConfigReader() {
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/config.properties");

            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getProperty(final String propertyName){
        return properties.getProperty(propertyName);
    }

    public String getOperaDriver(){
        return this.getProperty("driver.opera");
    }

    public String getOperaBinary(){
        return this.getProperty("binary.opera");
    }

    public static ConfigReader getInstance() {
        if(instance==null){
            instance = new ConfigReader();
        }
        return instance;
    }
}
