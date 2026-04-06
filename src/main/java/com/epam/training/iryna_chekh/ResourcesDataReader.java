package com.epam.training.iryna_chekh;

import java.util.MissingResourceException;
import java.util.ResourceBundle;


public class ResourcesDataReader {
    private static final ResourceBundle resourceBundle =
            ResourceBundle.getBundle("config");

    public static String getData(String key) {
        return resourceBundle.getString(key);
    }
}
