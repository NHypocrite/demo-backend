package org.xdq.demo.user.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class LoginUserDto {

    @NotBlank(message = "账号不得为空！") //不允许为null、长度为0、纯空白字符串
    private String u_id;
    @NotBlank(message = "密码不得为空！")
    private String u_pwd;
}
