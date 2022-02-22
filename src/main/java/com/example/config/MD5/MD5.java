package com.example.config.MD5;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5 {
    public static String SysMd5(String username,String password) {
        String hashAlgorithmName = "MD5";//加密方式
        ByteSource salt = ByteSource.Util.bytes(username);//以账号作为盐值
        int hashIterations = 1024;//加密1024次
        return new SimpleHash(hashAlgorithmName,password,salt,hashIterations).toHex();
    }

    public static void main(String[] args) {
        System.out.println(SysMd5("LEEF","123456"));
    }
}
