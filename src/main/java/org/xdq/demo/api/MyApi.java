package org.xdq.demo.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xdq.demo.common.Result;

@RestController //表示本类是一个可以接收并响应客户端请求的服务端组件（api组件）
public class MyApi {

    //接收GET请求
    @GetMapping("/hi")
    public String sayHello(){
        return "大家好！";
    }

    @GetMapping("/t1")
    public Result test1(int num){
        if(num%2==0){
            return Result.OK();
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"不允许奇数！");
        }
    }


}
