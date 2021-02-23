package com.bobo.javaDemo.http_chunk_test;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * client
 *
 * @author BO
 * @date 2021-02-06 14:55
 * @since 2021/2/6
 **/
public class Client {
    public static void main(String[] args) {
        HttpResponse httpResponse = HttpUtil.createGet("http://127.0.0.1:10789/test/chunk").executeAsync();
        if (!httpResponse.isOk()) {
            System.out.println(httpResponse.body());
            return;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.bodyStream()));
        String str;
        try {
            while (StrUtil.isNotEmpty(str = bufferedReader.readLine())) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
