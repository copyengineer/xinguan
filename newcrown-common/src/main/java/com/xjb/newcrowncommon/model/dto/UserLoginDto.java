package com.xjb.newcrowncommon.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 辛集斌
 * @create 2021/12/12
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginDto {

    private String username;

    private String password;

}
