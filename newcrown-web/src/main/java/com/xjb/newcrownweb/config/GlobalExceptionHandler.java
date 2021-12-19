package com.xjb.newcrownweb.config;

import com.xjb.newcrowncommon.Exception.MyCustomerException;
import com.xjb.newcrowncommon.Response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 辛集斌
 * @create 2021/12/11
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MyCustomerException.class)
    public Response<String> global1(MyCustomerException e){
        log.info("[GlobalExceptionHandler.global1]" + e.getMessage());
        Response<String> response = new Response(e.getCode(),e.getMessage(),null);
        return response;
    }

    @ExceptionHandler(value = Exception.class)
    public Response<String> global2(Exception e){
        log.info("[GlobalExceptionHandler.global2]" + e.getMessage());
        Response<String> response = new Response(500,e.getMessage(),null);
        return response;
    }

}
