package com.diesgut.utils;

import com.diesgut.config.GlobalConstants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {
    private static Properties properties = new Properties();

    static {
        try (InputStream input = PropertiesUtils.class.getClassLoader().getResourceAsStream(GlobalConstants.SELENIUM_PROPERTIES)) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Fatal error on load app", e);
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }

}
