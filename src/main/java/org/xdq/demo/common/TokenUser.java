package org.xdq.demo.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 令牌用户信息，即封装在令牌中的用户信息
 */

@Getter //编译时仅生成get方法
@AllArgsConstructor //编译时，生成全参的构造方法
public class TokenUser {

    private String userId;//账号
    private String userName;//姓名
}
