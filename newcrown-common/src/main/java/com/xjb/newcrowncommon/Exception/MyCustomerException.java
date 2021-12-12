package com.xjb.newcrowncommon.Exception;

import com.xjb.newcrowncommon.Enum.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 辛集斌
 * @create 2021/12/11
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyCustomerException extends RuntimeException{

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @param message 异常信息
     * @param code 异常代码
     * @param cause 异常源对象
     */
    public MyCustomerException(String message, int code, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    /**
     * 接收一个异常枚举
     * @param exceptionEnum 异常枚举
     */
    public MyCustomerException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
    }
}
