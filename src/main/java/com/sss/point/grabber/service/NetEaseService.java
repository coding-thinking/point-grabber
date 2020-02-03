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

        Thread.sleep(timeManager.mid());

        browser.findElement(By.cssSelector("a.link.s-fc3")).click();

        Thread.sleep(timeManager.small());

        browser.findElement(By.id("j-official-terms")).click();

        Thread.sleep(timeManager.small());

        browser.findElement(By.cssSelector("a.u-btn2.u-btn2-2")).click();

        Thread.sleep(timeManager.small());

        browser.findElement(By.id("p")).sendKeys(username);

        Thread.sleep(timeManager.mid());

        browser.findElement(By.id("pw")).sendKeys(password);

        Thread.sleep(timeManager.small());

        browser.findElement(By.cssSelector("a.j-primary.u-btn2.u-btn2-2")).click();

        Thread.sleep(timeManager.longer());

    }

    public void signAllNetEase() throws UnsupportedEncodingException, InterruptedException {
        loginMusic();
        // signInZhidao();

    }

}
