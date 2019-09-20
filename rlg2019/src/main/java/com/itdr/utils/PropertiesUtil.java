package com.itdr.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
    public static String getValue(String imageHost) throws IOException {
        Properties properties = new Properties();
        properties.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("set.properties"));
        String property=properties.getProperty(imageHost);
        return property;
    }
}
