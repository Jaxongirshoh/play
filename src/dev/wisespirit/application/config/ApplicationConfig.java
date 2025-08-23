package dev.wisespirit.application.config;

import java.util.ResourceBundle;

public class ApplicationConfig {
    private static final ResourceBundle seetings = ResourceBundle.getBundle("application");

    public static String getValue(String key){
        if (!seetings.containsKey(key)) {
            throw  new RuntimeException();
        }
        return seetings.getString(key);
    }
}
