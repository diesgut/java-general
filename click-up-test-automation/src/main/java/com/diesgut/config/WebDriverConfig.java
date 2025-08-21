package com.diesgut.config;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Map;
import java.util.Properties;

@Slf4j
public class WebDriverConfig {
    private static WebDriverConfig instance = null;
    private WebDriver webDriver;
    private Properties props = new Properties();

    public static WebDriverConfig getInstance() {
        if (instance == null) {
            instance = new WebDriverConfig();
        }
        return instance;
    }

    public final void setDriver(String browser) {
        if (this.webDriver != null) {
            try {
                this.webDriver.quit();
            } catch (Exception e) {
                log.warn("Error al cerrar WebDriver existente: {}", e.getMessage());
            }
            this.webDriver = null;
        }
        switch (browser) {
            case "chrome":

                ChromeOptions chOptions = new ChromeOptions();
                chOptions.addArguments("--incognito");
                chOptions.addArguments("--lang=en");
                chOptions.setExperimentalOption("prefs", Map.of(
                        "intl.accept_languages", "en"
                ));
                /*
                chOptions.addArguments("--disable-plugins", "--disable-extensions", "--disable-popup-blocking");
                chOptions.setCapability("applicationCacheEnabled", false);
               // System.setProperty("webdriver.chrome.driver", props.getProperty("chrome.driver.windows.path"));
                webDriver = new ChromeDriver(chOptions);*/

                webDriver = new ChromeDriver(chOptions);
                break;
            case "firefox":
                webDriver = new FirefoxDriver();
                break;
        }
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void driverRefresh() {
        webDriver.navigate().refresh();
    }

    public void closeDriver() {
        try {
            webDriver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
