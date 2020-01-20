package com.sss.point.grabber;


import com.sss.point.grabber.service.BaiduService;
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

    @Test
    public void testBaiduService(){
        try {
            baiduService.login();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
	
}
