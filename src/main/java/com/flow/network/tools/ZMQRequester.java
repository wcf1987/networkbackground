package com.flow.network.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zeromq.SocketType;
import org.zeromq.ZMQ;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

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

    public byte[] getByteArray(int a) {
        byte[] bytes = new byte[4];
        ByteBuffer buffertemp = ByteBuffer.allocate(4);
        buffertemp.order(ByteOrder.LITTLE_ENDIAN);
        buffertemp.putInt(a);

        buffertemp.clear();
        buffertemp.get(bytes);
        return bytes;

    }

    public String sendFile(String fileContent, Integer FlowID) {
        //zmq发送文件，一次发送1500Byte，直到文件发送完，参数是文件内容，文件需要拆分成片发送，考虑序号和包总数等问题
        int chunkSize = 1000;
        int packSize = 1024;
        byte[] fileBytes = new byte[0];
        try {

            fileBytes = fileContent.getBytes("UTF-8");
        } catch (java.io.UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int chunkCount = (int) Math.ceil((double) fileBytes.length / chunkSize);

        //byte[] dst = new byte[packSize];
        //System.out.println("文件字符串长度："+fileContent.length());
        byte[] reply=new byte[0];
        for (int i = 0; i < chunkCount; i++) {
            byte[] dst = new byte[packSize];
            ByteBuffer buffer = ByteBuffer.allocate(packSize); // 10 bytes for the header

            buffer.clear();
            int chunkNo = (int) (fileBytes.length / chunkSize);
            int start = i * chunkSize;
            int end = Math.min(start + chunkSize, fileBytes.length);
            int lens = (end - start);
            byte[] chunk = Arrays.copyOfRange(fileBytes, start, end);
            int flag = 0x89236283;
            int act = 0;
            ;//请求类型
            int flowid = FlowID;//消息的ID，传文件的时候当作文件名使用
            int totalcount = chunkCount;    //文件的总报文数
            int subindex = i + 1;    //文件报文的顺序编号
            int len = lens;    //后面跟着的消息长度

            //byte[] header = String.format("%40d%d%d%d%d%d",flag,act,flowid, totalcount,subindex,len).getBytes();
            //System.out.println("header:"+new String(header));
            buffer.put(getByteArray(flag));
            buffer.put(getByteArray(act));
            buffer.put(getByteArray(flowid));
            buffer.put(getByteArray(totalcount));
            buffer.put(getByteArray(subindex));
            buffer.put(getByteArray(len));

            //buffer.put(header); // File header
            buffer.put(chunk);

            buffer.clear();
            buffer.get(dst);
            // fileChannel.position(sentBytes);
            //readBytes = fileChannel.read(buffer);
            //buffer.limit(readBytes + 10); // Include the header in the message
            //buffer.position(0);


            requester.send(dst, 0);
            reply = requester.recv(0);
            // 接收应答
        }
        //byte[] reply = requester.recv(0);
        return new String(reply);

    }
}
