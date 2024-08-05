package com.flow.network.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zeromq.SocketType;
import org.zeromq.ZMQ;


/**
 * @author Muratjan
 * @date 2023-11-11
 * @desc dashbord
 */
@Component
@Slf4j
public class ZMQRequester {
    @Value("${zmq.requester}")
    private String addr;
    ZMQ.Socket requester;
    ZMQ.Context context;

    /**
     * 构造函数
     */

    public void newZMQ(String addr1) {
        context = ZMQ.context(1);
        requester = context.socket(SocketType.REQ);
        requester.connect(addr1); // 连接到应答方地址


    }
    public String sendRequest(String message) {

        requester.send(message.getBytes(), 0);
        // 接收应答
        byte[] reply = requester.recv(0);
        return new String(reply);

    }
    public void closeZMQ() {
        requester.close();

        // 关闭Context，如果没有其他socket在使用中
        context.term();
    }
}
