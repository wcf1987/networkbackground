package com.flow.network.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zeromq.SocketType;
import org.zeromq.ZMQ;

import java.nio.ByteBuffer;


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
        // 设置发送缓冲区大小
        requester.setSndHWM(0); // 设置发送高峰水平为0，即无限制
        requester.setSendBufferSize(1500); // 设置发送缓冲区为1500B


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
        public void sendFile(String fileContent,String flowiD ) {
            //zmq发送文件，一次发送1500Byte，直到文件发送完，参数是文件内容，文件需要拆分成片发送，考虑序号和包总数等问题
            int chunkSize = 1500;
            int chunkCount = (int) Math.ceil((double) fileContent.length() / chunkSize);
            int readBytes;
            ByteBuffer buffer = ByteBuffer.allocate(chunkSize); // 10 bytes for the header

            for (int i = 0; i < chunkCount; i++) {
                buffer.clear();
                int chunkNo = (int) (fileContent.length() / chunkSize);
                byte[] header = String.format("ID:%s:%05d", flowiD, chunkNo).getBytes();
                buffer.put(header); // File header

               // fileChannel.position(sentBytes);
                //readBytes = fileChannel.read(buffer);
                //buffer.limit(readBytes + 10); // Include the header in the message
                //buffer.position(0);


                int start = i * chunkSize;
                int end = Math.min(start + chunkSize, fileContent.length());
                String chunk = fileContent.substring(start, end);
                requester.send(chunk.getBytes(), 0);
                // 接收应答
            }

        }
}
