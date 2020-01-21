package com.sss.point.grabber.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

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
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BaiduService {

    @Value("${account.baidu.username}")
    private String username ;

    @Value("${account.baidu.password}")
    private String password ;

    public void login() throws UnsupportedEncodingException {

        ChromeDriver browser = null;
        System.setProperty("webdriver.chrome.driver", "/Users/peakrock/my/src/coding-thinking/point-grabber/drivers/chrome-79.0.3945.36/chromedriver");
        try{
            browser = new ChromeDriver();
            browser.manage().timeouts()
                    .implicitlyWait(10, TimeUnit.SECONDS);

            String baseUrl = "https://www.baidu.com/";

            browser.get(baseUrl) ;

            browser.findElements(By.name("tj_login")).get(1).click();

            Thread.sleep(1400);
            browser.findElement(By.id("TANGRAM__PSP_10__footerULoginBtn")).click();

            Thread.sleep(5200);
            browser.findElement(By.name("userName")).sendKeys(username);

            Thread.sleep(5300);
            browser.findElement(By.name("password")).sendKeys(password);

            Thread.sleep(3500);
            browser.findElement(By.id("TANGRAM__PSP_10__submit")).click();

            Thread.sleep(5500);
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            if( browser !=null ) {
                browser.quit();
            }
        }



    }

}
