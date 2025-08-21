package com.diesgut.config;

import com.diesgut.utils.PropertiesUtils;

public class GlobalConstants {
    public static final String SELENIUM_PROPERTIES = "selenium.properties";
    public static final String PAGE_URL_BASE =  PropertiesUtils.getProperty("page.url.base");
    public static final String BROWSER_CHROME =  "chrome";

    public static final int TIMEOUT_SECONDS = 5;
    public static final int TIMEOUT_ELEMENT = 1;
}
