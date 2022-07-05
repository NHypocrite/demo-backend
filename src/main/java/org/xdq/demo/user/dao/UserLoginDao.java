package org.xdq.demo.user.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.xdq.demo.user.dto.LoginUserDto;
import org.xdq.demo.user.model.User;

@Mapper
public interface UserLoginDao {

    @Select("select * from t_user where u_id = #{u_id} and u_pwd = #{u_pwd} ")
    public User findUserByIdAndPwd(LoginUserDto loginUserDto);

}
