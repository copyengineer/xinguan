package com.xjb.newcrowncommon.Enum;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author 辛集斌
 * @create 2021/12/11
 */
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionEnum {

    internal_system_error("系统内部错误",500),
    user_not_exist("用户不存在",600),
    token_error("token错误",601),
    token_expire("token过期",602),
    account_locked("账户被锁定",603);

    private String msg;

    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
