//package com.xjb.newcrowncore.shiro;
//
//import com.alibaba.fastjson.JSONObject;
//import com.xjb.newcrowncommon.Response.Response;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.subject.Subject;
//import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
//import org.apache.shiro.web.util.WebUtils;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
///**
// * @author 辛集斌
// * @create 2021/12/11
// */
//@Component
//@Slf4j
//public class JwtFilter extends BasicHttpAuthenticationFilter {
//
//    /**
//     * 认证之前执行该方法
//     */
//    @Override
//    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
//        log.info("[JwtFilter.isAccessAllowed]" + "自定义jwt过滤器执行");
//        Subject subject = SecurityUtils.getSubject();
//        return  null != subject && subject.isAuthenticated();
//    }
//
//    /**
//     * 认证未通过执行该方法
//     */
//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response){
//        log.info("[JwtFilter.onAccessDenied]" + "自定义jwt过滤器执行");
//        //完成token登入
//        //1.检查请求头中是否含有token
//        HttpServletRequest httpServletRequest= (HttpServletRequest) request;
//        String token = httpServletRequest.getHeader("Authorization");
//        //2.如果客户端没有携带token，拦下请求
//        if(null==token||"".equals(token)){
//            log.info("[JwtFilter.isAccessAllowed]" + "未携带token,请求失败");
//            responseTokenError(response,"未携带token,请求失败");
//            return false;
//        }
//        //3.如果有，对进行进行token验证
//        JWTToken jwtToken = new JWTToken(token);
//        try {
//            log.info("[JwtFilter.onAccessDenied]" + "有token,开始登录");
//            SecurityUtils.getSubject().login(jwtToken);
//        } catch (AuthenticationException e) {
//            log.error(e.getMessage());
//            responseTokenError(response,e.getMessage());
//            return false;
//        }
//
//        return true;
//    }
//
//    /**
//     * 客户端没有携带token时，直接返回错误信息
//     */
//    private void responseTokenError(ServletResponse response, String msg) {
//        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
//        httpServletResponse.setStatus(HttpStatus.OK.value());
//        httpServletResponse.setCharacterEncoding("UTF-8");
//        httpServletResponse.setContentType("application/json; charset=utf-8");
//        try (PrintWriter out = httpServletResponse.getWriter()) {
//            Response<String> res = Response.fail(-1,msg,null);
//            String data = JSONObject.toJSONString(res);
//            out.append(data);
//        } catch (IOException e) {
//            e.printStackTrace();
//            log.error(e.getMessage());
//        }
//    }
//}
