package com.diesgut.pages;

import com.diesgut.config.GlobalConstants;
import com.diesgut.config.WebDriverConfig;
import com.diesgut.utils.BrowserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

@Slf4j
@RequiredArgsConstructor
public abstract class ClickUpPO {
    private final WebDriver driver;

    /*
        public ClickUpPO(WebDriver driver) {
            this.driver = driver;
        }
    */
    public void visit() {
        throw new RuntimeException("This page does not have a visit link");
    }

    protected void visit(String url) {
        driver.get(GlobalConstants.PAGE_URL_BASE + url);
        isReady();
    }

    public abstract void isReady();

    public void closeDriver() {
        try {
            if(driver != null){
                driver.quit();
            }
        } catch (Exception e) {
            log.error("Error attempting close driver", e);
        }
    }

    public void loadPage(String url) throws Exception {
        WebDriver driver = WebDriverConfig.getInstance().getWebDriver();
        driver.navigate().to(url);

        // wait for page URL
        BrowserUtils.waitForURL(GlobalConstants.PAGE_URL_BASE, Duration.ofSeconds(GlobalConstants.TIMEOUT_SECONDS));
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
