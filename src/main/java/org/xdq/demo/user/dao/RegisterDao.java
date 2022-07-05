package org.xdq.demo.user.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.xdq.demo.user.dto.RegisterDto;

@Mapper
public interface RegisterDao {

    @Insert("insert into t_user(u_id,u_pwd,u_nickname,u_sex,u_avatar,create_time) values(#{u_id},#{u_pwd},#{u_nickname},#{u_sex},#{u_avatar},now())")
    void insertUser(RegisterDto dto);
}
