package com.flow.network;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.flow.network.mapper")
@MapperScan("com.flow.network.mapper2")
@ComponentScan(basePackages = {"com.flow.network"})
public class NetworkApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context =SpringApplication.run(NetworkApplication.class, args);
         //启动 ZeroMQResponder

        //ZMQResponder responder =   context.getBean(ZMQResponder.class);
        //responder.receiveMessages();
    }

}
