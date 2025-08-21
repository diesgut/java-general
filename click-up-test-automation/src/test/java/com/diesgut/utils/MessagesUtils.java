package com.diesgut.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessagesUtils {
    private static ResourceBundle bundle;

    static {
        Locale locale = new Locale("en");
        bundle = ResourceBundle.getBundle("messages", locale);
    }

    public static String getMessage(String key){
        return bundle.getString(key);
    }
}
