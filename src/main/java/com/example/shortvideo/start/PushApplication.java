package com.example.shortvideo.start;

import com.example.shortvideo.stream.PushStream;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * 直播推流--启动
 */
@SpringBootApplication
@Configuration
public class PushApplication {
    public static void main(String[] args) throws Exception {
        //设置rtmp服务器推流地址（视频流服务器公网地址）
        String outputPath = "rtmp://***/444?txSecret" +
                "=f824753ba13b78e4796961c30d56c6a8&txTime=64F947F4";
        PushStream recordPush = new PushStream();
        recordPush.getRecordPush(outputPath, 25);
    }
}
