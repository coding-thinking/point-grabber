package com.sss.point.grabber;


import com.sss.point.grabber.configuration.WebDriverManager;
import com.sss.point.grabber.service.BaiduService;
import com.sss.point.grabber.service.NetEaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    BaiduService baiduService ;

    @Autowired
    WebDriverManager webDriverManager ;
    @Test
    public void testBaiduService(){
        try {
            baiduService.login();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            webDriverManager.shutdown();
        }
    }

    @Test
    public void testBaiduSignInZhidao(){
        try {
            baiduService.login();
            baiduService.signInZhidao();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            webDriverManager.shutdown();
        }
    }


    @Test
    public void testBaiduSignInWenku(){
        try {
            baiduService.login();
            baiduService.signInWenKu();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            webDriverManager.shutdown();
        }
    }


    //signInTieBa
    @Test
    public void testBaiduSignInTieBa(){
        try {
            baiduService.login();
            baiduService.signInTieBa();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            webDriverManager.shutdown();
        }
    }

    //signAllBaidu
    @Test
    public void testBaiduSignAllBaidu(){
        try {
            baiduService.signAllBaidu();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            webDriverManager.shutdown();
        }
    }

    @Autowired
    NetEaseService netEaseService;
    @Test
    public void testNetease(){
        try {
            netEaseService.loginMusic();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            webDriverManager.shutdown();
        }
    }

}
