package org.xdq.demo.common;


import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error("系统错误", e);

        List<FieldError> fieldErrorList =  e.getFieldErrors();

        /*
            将错误集合中的每一个错误信息转化为一个字符串，然后再将这些字符串用分号分割连接成一个字符串
         */
        String str =
            fieldErrorList.stream()
                        .map(fieldError -> "("+fieldError.getField()+")"+fieldError.getDefaultMessage())
                        .collect(Collectors.joining("；"));


        return Result.err(Result.CODE_ERR_SYS, str);

    }


    @ExceptionHandler(ConstraintViolationException.class)
    public Result handleConstraintViolationException(ConstraintViolationException e){
        log.error("系统错误", e);

        String str =
            e.getConstraintViolations().stream()
                .map(violation -> "("+violation.getPropertyPath()+")"+violation.getMessage())
                .collect(Collectors.joining("；"));

        return Result.err(Result.CODE_ERR_SYS, str);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
        log.error("系统错误", e);

        return Result.err(Result.CODE_ERR_SYS,"(参数"+e.getName()+")值"+e.getValue()+"不匹配类型"+e.getRequiredType().getName());
    }

    @ExceptionHandler(Exception.class)  //具体拦截异常的方法
    public Result handleException(Exception e){
        log.error("系统错误！", e);
        return Result.err(Result.CODE_ERR_SYS, "系统错误！");
    }

}
