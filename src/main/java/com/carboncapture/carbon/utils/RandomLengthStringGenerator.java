package com.carboncapture.carbon.utils;

import java.security.SecureRandom;

public class RandomLengthStringGenerator {
    public static String generateRandomString(int length) {
        // 定义包含数字和大小写字母的字符集
        String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // 使用 SecureRandom 生成随机数
        SecureRandom random = new SecureRandom();

        // 生成随机字符串
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }
}
