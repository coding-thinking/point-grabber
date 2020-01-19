package com.sss.point.grabber.configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
 
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new Converter<String, Date>(){

			@Override
			public Date convert(String source) {
				Date target = null;
		        if(!StringUtils.isEmpty(source)) {
		            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		            try {
		                target =  format.parse(source);
		            } catch (ParseException e) {
		            	try {
		            		 target =  format2.parse(source);
		            	}catch (ParseException e1) {
		                  throw new RuntimeException(String.format("parser %s to Date fail", source));
		            	}
		            }
		        }
		        return target;
			}
        	
        });
    }
}
