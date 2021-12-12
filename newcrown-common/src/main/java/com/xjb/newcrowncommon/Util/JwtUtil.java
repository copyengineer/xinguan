package com.xjb.newcrowncommon.Util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xjb
 * @create 2021-05-27-20:59
 * JWT工具类
 */
public class JwtUtil {

    /**
     * token秘钥
     */
    public static final String SIGN = "xjbNB!!!";

    /**
     * 获取token
     * @param map token负载
     * @return token字符串
     */
    public static String getToken(Map<String,String> map){
        Calendar instance = Calendar.getInstance();
        //30分钟过期时间
        instance.add(Calendar.SECOND,60*30);
        Map<String,Object> header = new HashMap<>();
        map.forEach((k,v) -> JWT.create().withClaim(k,v));
        return JWT.create()
                .withHeader(header)
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SIGN));
    }

    /**
     * 返回用于获取JWT信息的DecodedJWT对象
     * @param token token令牌
     * @return 用于获取token信息的对象
     */
    public static DecodedJWT encoding(String token){
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }

    /**
     * 判断token是否过期
     * @param token token令牌
     * @return 是否过期（true代表过期了）
     */
    public static boolean isExpire(String token){
        DecodedJWT jwt = JWT.decode(token);
        return System.currentTimeMillis()>jwt.getExpiresAt().getTime();
    }

    /**
     * 解析、获取token中的用户名
     * @param token token令牌
     * @return 用户名
     */
    public static String getUserNameFromTokenStr(String token) throws Exception{
        DecodedJWT encoding = encoding(token);
        return encoding.getClaim("userName").asString();
    }

}
