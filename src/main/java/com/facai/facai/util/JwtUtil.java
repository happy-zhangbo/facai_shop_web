package com.facai.facai.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.facai.facai.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class JwtUtil {



    private static Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    private static final String secret = "zhangbo";
    /**
     * 加密生成token
     * @param maxAge  有效时长
     * @return
     */
    public static String createToken(UserInfo userInfo,long maxAge) {
        try {


            final Algorithm signer = Algorithm.HMAC256(secret);//生成签名
            String token = JWT.create()
                    .withIssuer("签发者")
                    .withSubject("用户")//主题，科目
                    .withClaim("uId", userInfo.getuId().toString())
                    .withExpiresAt(new Date(System.currentTimeMillis()+maxAge))
                    .sign(signer);
            return token;
        } catch(Exception e) {
            e.printStackTrace();
            logger.error("生成token异常：",e);
            return null;
        }
    }

    /**
     * 解析验证token
     * @param token 加密后的token字符串
     * @return
     */
    public static Boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("认证通过：");
            System.out.println("issuer: " + jwt.getIssuer());
            System.out.println("uId: " + jwt.getClaim("uId").asString());
            System.out.println("过期时间：      " + jwt.getExpiresAt());
            return true;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            System.out.println("校验失败");
        }
        return false;
    }


    /**
     * 获取用户信息
     */
    public static String getCurUserId(String token){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        String uid = jwt.getClaim("uId").asString();
        return uid;
    }

}
