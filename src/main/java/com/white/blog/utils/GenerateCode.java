package com.white.blog.utils;

import org.springframework.stereotype.Component;

/**
 * @author white_
 * @version 1.0
 * @project MyWeb-system
 * @description 生成邮箱验证码
 * @date 2024/1/8 09:23:40
 */
@Component
public class GenerateCode {
    public String CreateRandomCode() {
        String[] letters = new String[]{
                "q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m",
                "A", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String stringBuilder = "";
        for (int i = 0; i < 6; i++) {
            stringBuilder = stringBuilder + letters[(int) Math.floor(Math.random() * letters.length)];
        }
        return stringBuilder;
    }

}
