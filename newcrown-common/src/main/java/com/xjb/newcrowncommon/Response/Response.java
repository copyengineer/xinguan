package com.xjb.newcrowncommon.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 辛集斌
 * @create 2021/12/11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response<T> {

    private int code;

    private String msg;

    private T data;

    public static <K> Response<K> success(int code, String msg, K data){
        Response<K> response = new Response<>();
        response.setCode(code);
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

    public static <K> Response<K> fail(int code, String msg, K data){
        Response<K> response = new Response<>();
        response.setCode(code);
        response.setMsg(msg);
        response.setData(data);
        return response;
    }

}
