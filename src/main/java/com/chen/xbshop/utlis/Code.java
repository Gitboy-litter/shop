package com.chen.xbshop.utlis;

import java.util.Random;

/**
 * @ProjectName: xb
 * @Package: com.chen.xb.utlis
 * @Author: ChenZengWen
 * @Description: 描述
 * @Date: 2020/11/18 12:04
 * @Version: 1.0
 */
public class Code {
    public static String code() {
        String str = "abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
        Random r = new Random();
        String arr[] = new String[4];
        String b = "";
        for (int i = 0; i < 4; i++) {
            int n = r.nextInt(62);
            arr[i] = str.substring(n, n + 1);
            b += arr[i];

        }
        return b;
    }
}
