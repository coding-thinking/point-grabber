package com.sss.point.grabber.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
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
public class BaiduService {

    @Value("${account.baidu.username}")
    private String username;

    @Value("${account.baidu.password}")
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
    }

    public void login() throws UnsupportedEncodingException, InterruptedException {

        String baseUrl = "https://www.baidu.com/";

        browser.get(baseUrl);

        Thread.sleep(timeManager.mid());
        browser.findElements(By.name("tj_login")).get(1).click();

        Thread.sleep(timeManager.small());
        browser.findElement(By.id("TANGRAM__PSP_10__footerULoginBtn")).click();

        Thread.sleep(timeManager.high());
        browser.findElement(By.name("userName")).sendKeys(username);

        Thread.sleep(timeManager.high());
        browser.findElement(By.name("password")).sendKeys(password);

        Thread.sleep(timeManager.longer());
        browser.findElement(By.id("TANGRAM__PSP_10__submit")).click();

        Thread.sleep(timeManager.longer());

    }

    public void signInZhidao() throws UnsupportedEncodingException, InterruptedException {
        String zhidaoUrl = "https://zhidao.baidu.com";
        browser.get(zhidaoUrl);

        Thread.sleep(timeManager.mid());
        browser.findElementByClassName("go-sign-in").click();

        Thread.sleep(timeManager.mid());
        browser.findElementById("sign-in-btn").click();
    }

    public void signInWenKu() throws UnsupportedEncodingException, InterruptedException {
        String wenkuUrl = "https://wenku.baidu.com/task/browse/daily" ;
        browser.get(wenkuUrl);

        Thread.sleep(timeManager.mid());
        browser.findElementByClassName("g-btn-pass").click();

    }

    public void signInTieBa() throws InterruptedException, UnsupportedEncodingException {
        String tieBaUrl = "https://tieba.baidu.com/" ;
        browser.get(tieBaUrl);

        Thread.sleep(timeManager.mid());
        List<WebElement> links = browser.findElements(By.xpath("//div[@id='likeforumwraper']/a[@class='u-f-item unsign']"));

        List<String> detailUrls = new ArrayList<String>();
        if(links!=null && !links.isEmpty()){
            for (WebElement e: links){

                // https://tieba.baidu.com/f?kw=%E5%81%A5%E8%BA%AB&fr=index
                String tieBaDetailUrl = "https://tieba.baidu.com/f?fr=index&kw=" ;
                tieBaDetailUrl += URLEncoder.encode(e.getText(), "UTF-8") ;
                detailUrls.add(tieBaDetailUrl) ;
                System.out.println(e.getText() + " " + tieBaDetailUrl);
            }
            for(String url: detailUrls){
                try{
                    browser.get(url);
                    Thread.sleep(timeManager.mid());
                    browser.findElement(By.xpath("//a[@title='签到']")).click();
                    browser.findElement(By.xpath("//a[@title='签到']")).click();
                    Thread.sleep(timeManager.mid());
                }catch (Throwable e1){

                }
            }
        }
    }

    public void signAllBaidu() throws UnsupportedEncodingException, InterruptedException {
        login();
        signInZhidao();
        signInWenKu();
        signInTieBa();
    }
    public void shutdown(){
        if(browser!=null){
            browser.quit();
            browser = null ;
        }
    }


}
