package com.example.shortvideo.start;

import com.example.shortvideo.stream.PullStream;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * 直播拉流--启动
 */
@SpringBootApplication
@Configuration
public class PullApplication {
    public static void main(String[] args) throws Exception {
        System.err.println("开始播放");
        //rtmp服务器拉流地址（视频流服务器公网地址）
        String inputPath = "rtmp://10.20.77.55:1935/live/test3";
        PullStream pullStream = new PullStream();
        pullStream.getPullStream(inputPath);
    }
}
