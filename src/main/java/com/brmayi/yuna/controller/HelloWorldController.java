package com.brmayi.yuna.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

@RestController
public class HelloWorldController {
    @GetMapping(path = "hi")
    public String hi() throws Exception {
        client();
        return "end";
    }

    public void client() throws Exception {
        SocketChannel socket = SocketChannel.open();
        socket.connect(new InetSocketAddress("127.0.0.1", 520));
        FileChannel io = new FileInputStream("D:\\github-cat.jpg").getChannel();
        long begin = System.currentTimeMillis();
        io.transferTo(0, io.size(), socket);
        System.out.println("耗时为" + (System.currentTimeMillis() - begin) + "ms");
        io.close();
        socket.close();
    }


    public static void server() throws Exception {
        ServerSocket serverSocket = new ServerSocket(520);
        int i = 1;
        while (true) {
            Socket socket = serverSocket.accept();
            Thread.sleep(20*60*1000);
            int left = 0;
            while (left >= 0) {
                InputStream io = socket.getInputStream();
                byte[] bytes = new byte[1024];
                left = io.read(bytes);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        server();
    }
}
