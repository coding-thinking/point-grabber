package com.sss.point.grabber.configuration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebDriverManager {

    @Value("${webdriver.chrome.path}")
    private String driverPath;

    private ChromeDriver browser = null;

    @Autowired
    public void init() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        this.browser = new ChromeDriver();
        this.browser.manage().timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void shutdown(){
        if(browser!=null) {
            browser.quit();
        }
    }

    public ChromeDriver getBrowser() {
        return this.browser;
    }
}
