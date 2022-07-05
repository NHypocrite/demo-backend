package org.xdq.demo.common;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j //该注解可以实现方便的日志输出
@RestControllerAdvice //说明本类可以拦截Api异常
public class DemoApiAdvice {


    @ExceptionHandler(BusinessException.class)  //具体拦截异常的方法
    public Result handleBusinessException(BusinessException e){

        return Result.err(Result.CODE_ERR_BUSINESS, e.getMessage());
    }

    @ExceptionHandler(SysException.class)  //具体拦截异常的方法
    public Result handleSysException(SysException e){
        log.error("系统错误！", e);
        return Result.err(Result.CODE_ERR_SYS, "系统错误！");
    }

    @ExceptionHandler(Exception.class)  //具体拦截异常的方法
    public Result handleException(Exception e){
        log.error("系统错误！", e);
        return Result.err(Result.CODE_ERR_SYS, "系统错误！");
    }

}
