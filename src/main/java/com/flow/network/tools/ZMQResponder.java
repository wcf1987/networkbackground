package com.flow.network.tools;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zeromq.SocketType;
import org.zeromq.ZMQ;

import javax.annotation.PostConstruct;

/**
 * @author Muratjan
 * @date 2023-11-11
 * @desc ZMQ响应者
 */
@Component
@Slf4j
public class ZMQResponder {
    private ZMQ.Socket responder;
    @Value("${zmq.responder}")
    private String addr;



    /**
     * 构造函数
     */
    @PostConstruct
    public void init() {
        ZMQ.Context context = ZMQ.context(1);
        try {
        responder = context.socket(SocketType.REP);


        //responder.bind(addr); // 绑定发布者到地址
        log.debug("ZMQResponder初始化成功，地址为：{}", addr);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 接收消息
     */
    public void receiveMessages() {
        log.info("ZMQResponder开始接收消息:");
        String message = "";
        while (!Thread.currentThread().isInterrupted()) {
            // 接收请求
            byte[] request = responder.recv(0);
            message = new String(request);
            String response;
            if (JSON.isValid(message)) {
                log.info("ZMQResponder接收到消息：{}", message);
                System.out.println("ZMQResponder接收到消息："+ message);
                response = "1";
            } else {
                log.error("ZMQResponder接收到非法消息：{}", message);
                response = "0";
            }
            responder.send(response.getBytes(), 0);
            handleMsg(message);
        }
    }

    /**
     * 处理消息
     * @param msg 消息
     */
    public void handleMsg(String msg){
        // 解析消息
    }
}
