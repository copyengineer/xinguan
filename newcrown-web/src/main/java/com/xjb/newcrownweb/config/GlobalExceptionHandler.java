package com.xjb.newcrownweb.config;

import com.xjb.newcrowncommon.Exception.MyCustomerException;
import com.xjb.newcrowncommon.Response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author 辛集斌
 * @create 2021/12/11
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MyCustomerException.class)
    public Response<String> global(MyCustomerException e){
        log.error("[GlobalExceptionHandler.global]" + e.getMessage());
        Response<String> response = new Response(e.getCode(),e.getMessage(),null);
        return response;
    }

}
