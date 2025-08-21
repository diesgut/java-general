package com.diesgut.utils;

import com.diesgut.config.WebDriverConfig;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Optional;

@Slf4j
public class BrowserUtils {
    private static final WebDriver DRIVER = WebDriverConfig.getInstance().getWebDriver();
    private static final WebDriverWait DEFAULT_WAIT = new WebDriverWait(DRIVER, Duration.ofSeconds(3));

    public static void waitFor(By element) {
        DEFAULT_WAIT.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public static void waitFor(By element, Duration duration) {
        WebDriver driver = WebDriverConfig.getInstance().getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public static void waitForTitle(String title, Duration duration) throws Exception {
        WebDriver driver = WebDriverConfig.getInstance().getWebDriver();
        WebDriverWait exists = new WebDriverWait(driver, duration);
        exists.until(ExpectedConditions.refreshed(ExpectedConditions.titleContains(title)));
    }

    public static void waitForURL(String url, Duration duration)
            throws Exception {

        WebDriver driver = WebDriverConfig.getInstance().getWebDriver();
        WebDriverWait exists = new WebDriverWait(driver, duration);

        exists.until(ExpectedConditions.refreshed(ExpectedConditions.urlContains(url)));
    }

    public static void waitForNotURL(String url) {
        try {
            WebDriverWait wait = new WebDriverWait(DRIVER, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
        } catch (TimeoutException e) {
            // e.printStackTrace();
        }
    }

    public static void waitForClickable(By element)  {
        Optional<WebElement> waitElement = waitUntilInteractable(element);
        waitElement.ifPresent(WebElement::click);
    }

    public static void waitForSendKeys(By element, String value) {
        Optional<WebElement> waitElement = waitUntilInteractable(element);
        waitElement.ifPresent(webElement -> {
            webElement.clear();
            webElement.sendKeys(value);
        });
    }

    public static Optional<WebElement> waitUntilInteractable(By selector) {
        try {
            WebElement element = DEFAULT_WAIT.until(ExpectedConditions.refreshed(
                    ExpectedConditions.elementToBeClickable(selector)
            ));
            return Optional.of(element);
        } catch (TimeoutException e) {
           // e.printStackTrace();
            return Optional.empty();
        }
    }

    public static String waitForGetText(By element) {
        Optional<WebElement> waitElement = waitUntilVisible(element);
        return waitElement.map(WebElement::getText).orElse("");
    }

    public static Optional<WebElement> waitUntilVisible(By selector) {
        try {
            WebElement element = DEFAULT_WAIT.until(ExpectedConditions.refreshed(
                    ExpectedConditions.visibilityOfElementLocated(selector)
            ));
            return Optional.of(element);
        } catch (TimeoutException e) {
            return Optional.empty();
        }
    }
}
