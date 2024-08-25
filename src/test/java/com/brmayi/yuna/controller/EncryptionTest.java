package com.brmayi.yuna.controller;

import cn.hutool.core.codec.Base64;
import org.junit.Before;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Key;
import java.util.Arrays;

public class EncryptionTest {

    @Test
    public void caesarCipher() {
        String text = "love was growing in eyes";  //明文
        int key = 3; //秘钥
        String cipher = encryptCaesarCipher(text, key); //密文
        System.out.println(cipher);
    }

    //凯撒密码加密算法
    private String encryptCaesarCipher(String text, int key) {
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                chars[i] = (char) (chars[i] + key);
            }

            //如果超过了26个字母，则减去26
            if (chars[i] > 122) {
                chars[i] = (char) (chars[i] - 26);
            }
        }
        return new String(chars);
    }

    @Test
    public void desCiper() throws Exception {
        String key = "12345678";
        String text = "我知道我是任性很任性，伤透了你的心";  //明文
        byte[] cipherBytes = encryptDesCipher(text.getBytes(), key); //密文
        String cipher = Base64.encode(cipherBytes);
        System.out.println(cipher);
        byte[] bytes = decryptDesCipher(Base64.decode(cipher), key);  //明文
        System.out.println(new String(bytes));
    }

    @Test
    public void client() throws Exception {
        int i = 1;
        while (i <= 2) {
            Socket socket = new Socket("127.0.0.1", 520);
            //向服务器端第一次发送字符串
            OutputStream netOut = socket.getOutputStream();
            InputStream io = socket.getInputStream();
            String msg = i == 1 ? "客户端：我知道我是任性太任性，伤透了你的心。我是追梦的人，追一生的缘分。":
            "客户端：我愿意嫁给你，你却不能答应我。";
            System.out.println(msg);
            netOut.write(encryptDesCipher(msg.getBytes(), "12345678"));
            netOut.flush();
            byte[] bytes = new byte[i==1?104:64];
            io.read(bytes);
            String response = new String(decryptDesCipher(bytes,"12345678"));
            System.out.println(response);
            netOut.close();
            io.close();
            socket.close();
            i++;
        }
    }

    @Test
    public void server() throws Exception {
        ServerSocket serverSocket = new ServerSocket(520);
        int i = 1;
        while (i <= 2) {
            String msg = i == 1 ? "服务端：我知道你是任性太任性，伤透了我的心。同是追梦的人，难舍难分。" :
                    "服务端：你愿意嫁给你，我却不能向你承诺。";
            Socket socket = serverSocket.accept();
            InputStream io = socket.getInputStream();
            byte[] bytes = new byte[i==1?112:64];
            io.read(bytes);
            System.out.println(new String(decryptDesCipher(bytes,"12345678")));
            OutputStream os = socket.getOutputStream();
            System.out.println(msg);
            byte[] outBytes = encryptDesCipher(msg.getBytes(), "12345678");
            os.write(outBytes);
            os.flush();
            os.close();
            io.close();
            i++;
        }
    }

    //DES加密算法
    private byte[] encryptDesCipher(byte[] text, String origKey) throws Exception {
        Key key = new SecretKeySpec(origKey.getBytes(), "DES");

        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(text);
    }

    //DES加密算法
    private byte[] decryptDesCipher(byte[] text, String origKey) throws Exception {
        Key key = new SecretKeySpec(origKey.getBytes(), "DES");

        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(text);
    }

    @Test
    public void doPadding() throws Exception {
        String key = "1";
        System.out.println(Base64.encode(key.getBytes()));
    }
}
