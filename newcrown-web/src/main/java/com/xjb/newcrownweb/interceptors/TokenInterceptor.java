package com.xjb.newcrownweb.interceptors;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xjb.newcrowncommon.Response.Response;
import com.xjb.newcrowncommon.Util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 辛集斌
 * @create 2021/12/12
 */
@Slf4j
@SuppressWarnings("all")
public class TokenInterceptor implements HandlerInterceptor {

    public static Map<String,String> USER_MAP = new HashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("进入拦截器"+ response.getStatus() + request.getRequestURI());
        response.setCharacterEncoding("UTF-8");
        //1.检查请求头中是否含有token
        String token = request.getHeader("Authorization");
        //2.如果客户端没有携带token，拦下请求
        if(null==token || "undefined".equals(token)){
            log.info("[TokenInterceptor.preHandle]" + "未携带token,请求失败");
            Response<Object> res = Response.fail(500, "未携带token", null);
            PrintWriter writer = response.getWriter();
            writer.write(JSONObject.toJSONString(res));
            writer.close();
            return false;
        }
        //3.token是否失效
        try {
            DecodedJWT encoding = JwtUtil.encoding(token);
            //如果token有效则获取token中的userName
            Map<String, String> userInfoMap = JwtUtil.getUserNameFromTokenStr(token);
            USER_MAP = userInfoMap;
        }catch (Exception e){
            Response<Object> res = Response.fail(500, "token失效", null);
            PrintWriter writer = response.getWriter();
            writer.write(JSONObject.toJSONString(res));
            writer.close();
            return false;
        }
        return true;
    }
}
