package org.xdq.demo.user.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.xdq.demo.common.*;
import org.xdq.demo.user.dao.UserLoginDao;
import org.xdq.demo.user.dto.LoginUserDto;
import org.xdq.demo.user.model.User;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Transactional //事务声明，表示该类的所有方法都是事务性的，即方法中的所有访问数据库操作构成了一个整体事务，具有原子性
public class UserLoginApi {


    @Autowired
    private UserLoginDao userLoginDao;

    @PostMapping("/login")
    public Result login(@RequestBody LoginUserDto dto){


        User user = null;
        try {
            user = userLoginDao.findUserByIdAndPwd(dto);
        } catch (Exception e) {
           throw new SysException("访问数据库错误！",e);
        }
        if(user != null){
            String token = TokenUtils.loginSign(new TokenUser(
                    user.getU_id(), user.getU_nickname()));
            return Result.OK((Object)token);

        }

        throw new BusinessException("登录失败");

        //return Result.err(Result.CODE_ERR_BUSINESS, "登录失败");


    }

    @GetMapping("/curr-user")
    public Result getTokenUser(@RequestHeader(DemoConstants.HEADER_PARAM_TOKEN)  String token){
        TokenUser tokenUser = TokenUtils.getTokenUser(token);
        return Result.OK(tokenUser);
    }

    @GetMapping("/is-login")
    public Result isLogin(@RequestHeader(DemoConstants.HEADER_PARAM_TOKEN)  String token){
        Map<String,Boolean> map  = new HashMap<>();
        try {
            TokenUtils.getTokenUser(token);
            map.put("logined", true);

        } catch (Exception e) {
            map.put("logined", false);
        }
        return Result.OK(map);
    }
}
