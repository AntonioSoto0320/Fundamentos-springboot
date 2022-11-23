package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.bean.*;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MyConfigurationBean {
    @Bean
    public MyBean beanOperation(){
        return new MyBean2Implement();
    }



    @Bean
    public MyOperation MyOperation(){
        return new MyOperationImplement();
    }

    @Bean
    public MyBeanWithDependency myBeanWithProperties(MyOperation myOperation){
        return new MyBeanWithDependencyImplement(myOperation);
    }

    @Bean
    public MyBeanWithDependency MyOperationImplements(MyOperation myOperation){
        return new MyBeanWithDependencyImplement(myOperation);
    }



}
