package com.shirongbao.boot.ssm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ShiRongbao
 * @create 2024/2/22 16:39
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TUser {
    private Long id;
    private String loginName;
    private String nickName;
    private String passwd;
}
