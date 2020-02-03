package com.sss.point.grabber.service;
        import java.io.UnsupportedEncodingException;

        import java.util.concurrent.TimeUnit;

        import com.sss.point.grabber.configuration.TimeConfig;
        import com.sss.point.grabber.configuration.WebDriverManager;
        import org.openqa.selenium.By;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Value;
        import org.springframework.stereotype.Service;

@Service
public class NetEaseService {

    @Value("${account.netease.username}")
    private String username;

    @Value("${account.netease.password}")
    private String password;

    @Value("${webdriver.chrome.path}")
    private String driverPath;

    @Autowired
    TimeConfig timeManager;

    ChromeDriver browser = null;

    @Autowired
    WebDriverManager webDriverManager ;

    @Autowired
    public void init() {
        browser = webDriverManager.getBrowser() ;
    }

    public void loginMusic() throws UnsupportedEncodingException, InterruptedException {

        String baseUrl = "https://music.163.com/";

        browser.get(baseUrl);

        browser.findElement(By.cssSelector("a.link.s-fc3")).click();

        browser.findElement(By.id("j-official-terms")).click();

        browser.findElement(By.cssSelector(".u-mlg2.u-mlg2-wy")).click();

        browser.findElement(By.id("e")).sendKeys(username);

        browser.findElement(By.id("epw")).sendKeys(password);

        browser.findElement(By.cssSelector(".js-primary.u-btn2.u-btn2-2")).click();

        browser.findElement(By.cssSelector(".u-btn2.u-btn2-dis")).click();

    }

    public void signAllNetEase() throws UnsupportedEncodingException, InterruptedException {
        loginMusic();
        // signInZhidao();

    }

}
