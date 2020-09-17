package com.brmayi.yuna.controller;

import org.junit.Test;

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
        for(int i=0; i<chars.length; i++) {
            if(chars[i] != ' ') {
                chars[i] = (char)(chars[i]+key);
            }

            //如果超过了26个字母，则减去26
            if(chars[i]>122) {
                chars[i] = (char)(chars[i]-26);
            }
        }
        return new String(chars);
    }
}
