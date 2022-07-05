package org.xdq.demo.user.dto;

import lombok.Data;

@Data
public class RegisterDto {

    private String u_id;
    private String u_pwd;
    private String u_nickname;
    private Integer u_sex;
    private String u_avatar;
}
