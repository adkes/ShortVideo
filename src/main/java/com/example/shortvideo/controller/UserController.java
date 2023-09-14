package com.example.shortvideo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.shortvideo.dto.Result;
import com.example.shortvideo.entity.UserEntity;
import com.example.shortvideo.mapper.UserMapper;
import com.example.shortvideo.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @PostMapping("register")
    public Result register(@RequestParam("username") String username,
                        @RequestParam("password") String password){
        QueryWrapper<UserEntity> qw = new QueryWrapper<>();
        qw.eq("user_name",username);
        Long count = userMapper.selectCount(qw);
        if(count >= 1){
            return Result.fail("该账号已被注册");
        }
        UserEntity user = new UserEntity();
        user.setUserName(username);
        user.setPassword(sha(password)); //加密保存密码
        userMapper.insert(user);
        return Result.ok();
    }

    @PostMapping("login")
    public Result login(@RequestParam("username") String username,
                           @RequestParam("password") String password){
        QueryWrapper<UserEntity> qw = new QueryWrapper<>();
        qw.eq("user_name",username);
        UserEntity user = userMapper.selectOne(qw);
        if(user == null){
            return Result.fail("账号不存在");
        }
        if(!user.getPassword().equals(sha(password))){
            return Result.fail("密码错误");
        }
        String token = TokenUtil.generateToken(username);
        return Result.ok(token);
    }

    private static String sha(String inStr) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            byte[] byteArray = inStr.getBytes(StandardCharsets.UTF_8);
            byte[] md5Bytes = sha.digest(byteArray);
            StringBuilder hexValue = new StringBuilder();
            for (byte md5Byte : md5Bytes) {
                int val = ((int) md5Byte) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
