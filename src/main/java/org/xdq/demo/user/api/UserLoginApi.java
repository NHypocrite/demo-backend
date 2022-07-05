package org.xdq.demo.user.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xdq.demo.common.Result;
import org.xdq.demo.user.dao.UserLoginDao;
import org.xdq.demo.user.dto.LoginUserDto;
import org.xdq.demo.user.model.User;

@RestController
@RequestMapping("/user")
@Transactional //事务声明，表示该类的所有方法都是事务性的，即方法中的所有访问数据库操作构成了一个整体事务，具有原子性
public class UserLoginApi {


    @Autowired
    private UserLoginDao userLoginDao;

    @PostMapping("/login")
    public Result login(@RequestBody LoginUserDto dto){

        User user = userLoginDao.findUserByIdAndPwd(dto);
        if(user == null){
            return Result.err(Result.CODE_ERR_BUSINESS, "登录失败");
        }
        return Result.OK();
    }
}
