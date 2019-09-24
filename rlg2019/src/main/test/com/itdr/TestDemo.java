package com.itdr;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class TestDemo {
    @Test
    public void test1(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("sm.xml");
        DriverManagerDataSource dataSource = ac.getBean("dataSource", DriverManagerDataSource.class);
        String url = dataSource.getUrl();
        System.out.println(url);
    }
    @Test
    public void test2(){
        System.out.println(-5+1/4+2*-3+5.0);
    }
}
