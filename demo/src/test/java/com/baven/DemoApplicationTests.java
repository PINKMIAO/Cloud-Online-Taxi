package com.baven;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.HashMap;


class DemoApplicationTests {

    @Test
    void contextLoads() {

        HashMap<String, Object> map = new HashMap<>();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 60);

        String token = JWT.create()
                .withHeader(map)                                // header
                .withClaim("userID", 24)            // payload  点Ctrl+P 可以查看所有类型
                .withClaim("username", "baven")
                .withExpiresAt(instance.getTime())              // 令牌token过期时间
                .sign(Algorithm.HMAC256("ASFWT#@HF*"));         // 签名  其中密钥不能随意给其他人

        System.out.println(token);
    }

    @Test
    public void test() {
        JWTVerifier jwtRequire = JWT.require(Algorithm.HMAC256("ASFWT#@HF*")).build();// 生成验签对象 参数即签名内的算法和密钥

        DecodedJWT verify = jwtRequire.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MTYyOTU3MTQsInVzZXJJRCI6MjQsInVzZXJuYW1lIjoiYmF2ZW4ifQ.szIg_3gxOm83pygep2Tqv7gocnINfdA2p6Aj2wHGzq4");
        System.out.println("用户ID " + verify.getClaim("userID").asInt());
        System.out.println("用户名 " + verify.getClaim("username").asString());
        System.out.println("过期时间" + verify.getExpiresAt());


    }

    @Test
    public void test01() {

        // 连接阿里云
        DefaultProfile profile = DefaultProfile.getProfile("cn-qingdao", "LTAI5tRALnsVz5AJfvMvDx9L", "<accessSecret>");
        IAcsClient client = new DefaultAcsClient(profile);

        // 构建请求
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");

        request.putQueryParameter("PhoneNumbers", "13134439827");
        request.putQueryParameter("SignName", "");
        request.putQueryParameter("TemplateCode", "");


        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

    }

}
