package cn.zyf.springboot.springbootweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

@Configuration
public class MyExceptionConfig{

    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
        SimpleMappingExceptionResolver ser = new SimpleMappingExceptionResolver();
        Properties mapper = new Properties();
        mapper.put("java.lang.ArithmeticException","myError");
        ser.setExceptionMappings(mapper);
        ser.setExceptionAttribute("err");
        return ser;
    }
}
