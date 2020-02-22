package com.sss.point.grabber.service;

import java.io.UnsupportedEncodingException;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.sss.point.grabber.configuration.TimeConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



@Service
public class JDService {

    @Value("${account.jingdong.username}")
    private String username;

    @Value("${account.jingdong.password}")
    private String password;

    @Value("${webdriver.chrome.path}")
    private String driverPath;

    @Autowired
    TimeConfig timeManager;

    ChromeDriver browser = null;

    @Autowired
    public void init() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        browser = new ChromeDriver();
        browser.manage().timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS);

        try {
            login();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void login() throws UnsupportedEncodingException, InterruptedException {

        String baseUrl = "https://www.jd.com/";

        browser.get(baseUrl);

        browser.findElement(By.cssSelector("a.link-login")).click();

        //browser.findElement(By.cssSelector("login-tab-r.a")).click();
        List<WebElement> allInputs = browser.findElements(By.tagName("a")); // 生成WebElement实例对象allInputs
        System.out.println(allInputs.size());
        int i = 0;
        for (WebElement e : allInputs) { // 循环
            if(e.getText().equals("账户登录")){
                System.out.println("账户登录a标签位置"+i);
                e.click();
                break;
            }
            i++;
            //System.out.println(e.getText());
        }

        Thread.sleep(timeManager.mid());
        browser.findElement(By.id("loginname")).sendKeys("songxibinjingdong");

        Thread.sleep(timeManager.high());
        browser.findElement(By.id("nloginpwd")).sendKeys("songxibin1989");

        Thread.sleep(timeManager.mid());
        browser.findElement(By.id("loginsubmit")).click();

        Thread.sleep(timeManager.mid());


    }



    public void shutdown(){
        if(browser!=null){
            browser.quit();
            browser = null ;
        }
    }

}
