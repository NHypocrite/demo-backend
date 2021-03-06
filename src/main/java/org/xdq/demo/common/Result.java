package org.xdq.demo.common;

//表示服务端向客户端发送的结果数据
public class Result {

    /**
     * 成功
     */
    public static final int CODE_OK = 200;
    public static final int CODE_ERR_BUSINESS = 500;
    public static final int CODE_ERR_SYS = 530;
    public static final int CODE_ERR_UNLOGIN = 520;


    public static Result OK(){
        return new Result(true,CODE_OK,null,null);
    }

    public static Result OK(String message){
        return new Result(true,CODE_OK,message,null);
    }

    public static Result OK(Object data){
        return new Result(true,CODE_OK,null,data);
    }

    public static Result OK(String message,Object data){
        return new Result(true,CODE_OK,message,data);
    }

    public static Result err(int errCode,String message){
        return new Result(false,errCode,message,null);
    }

    public static Result err(int errCode,String message,Object data){
        return new Result(false,errCode,message,data);
    }



    private boolean success;//是否成功
    private int code;//编码：200 成功 、500 业务失败、530 系统错误 、520 未登录
    private String message;// 概述信息
    private Object data;//服务端向前端回送的数据

    private Result(boolean success, int code, String message, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
