package com.sss.point.grabber.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import com.sss.point.grabber.manager.TimeManager;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
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
    TimeManager timeManager;

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

        browser.findElements(By.name("tj_login")).get(1).click();

        Thread.sleep(timeManager.small());
        browser.findElement(By.id("TANGRAM__PSP_10__footerULoginBtn")).click();

        Thread.sleep(timeManager.mid());
        browser.findElement(By.name("userName")).sendKeys(username);

        Thread.sleep(timeManager.high());
        browser.findElement(By.name("password")).sendKeys(password);

        Thread.sleep(timeManager.mid());
        browser.findElement(By.id("TANGRAM__PSP_10__submit")).click();

        Thread.sleep(timeManager.mid());

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

    public void shutdown(){
        if(browser!=null){
            browser.quit();
            browser = null ;
        }
    }


}
