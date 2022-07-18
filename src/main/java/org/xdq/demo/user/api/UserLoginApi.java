package org.xdq.demo.user.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.xdq.demo.common.*;
import org.xdq.demo.user.dao.UserLoginDao;
import org.xdq.demo.user.dto.LoginUserDto;
import org.xdq.demo.user.model.User;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Validated //在类上加注解@Validated可以实现对单一参数（未封装到Java对象中的参数）的验证
@RestController
@RequestMapping("/user")
@Transactional //事务声明，表示该类的所有方法都是事务性的，即方法中的所有访问数据库操作构成了一个整体事务，具有原子性
public class UserLoginApi {


    @Autowired
    private UserLoginDao userLoginDao;

    @GetMapping("/t01")
    public Result test01(
            @NotNull(message = "缺少参数num")
            @Min(value=10,message = "参数num的值应不小于10")
            @Max(value=100,message = "参数num的值应不大于100")
            Integer num
    ){
        return Result.OK("num="+num);
    }

    //@Validated 说明接收的数据启用验证
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginUserDto dto){

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
