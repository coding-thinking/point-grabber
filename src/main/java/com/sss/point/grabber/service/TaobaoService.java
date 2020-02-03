package com.sss.point.grabber.service;

import com.sss.point.grabber.configuration.TimeConfig;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TaobaoService {

    @Value("${account.taobao.username}")
    private String username;

    @Value("${account.taobao.password}")
    private String password;

    @Value("${webdriver.chrome.path}")
    private String driverPath;

    @Autowired
    TimeConfig timeManager;

    ChromeDriver browser = null;


    @Autowired
    public void init() {
    }

    public void login() {

    }
}
