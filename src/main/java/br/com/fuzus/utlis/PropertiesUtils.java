package br.com.fuzus.utlis;

import java.io.*;
import java.util.Properties;

public class PropertiesUtils {

    public Properties getProps() {
        Properties properties = new Properties();
        InputStream file = null;
        try {
            file = getClass().getResourceAsStream("/application.properties");
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

}
