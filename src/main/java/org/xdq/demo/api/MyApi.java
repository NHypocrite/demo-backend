package org.xdq.demo.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.xdq.demo.common.DemoConstants;
import org.xdq.demo.common.Result;
import org.xdq.demo.dao.MealDao;
import org.xdq.demo.model.Meal;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@RestController //表示本类是一个可以接收并响应客户端请求的服务端组件（api组件）
public class MyApi {

    //接收GET请求
    @GetMapping("/hi")
    public String sayHello(){
        return "大家好！";
    }

    @GetMapping("/test")
    public String test(){
        return "The background part works functionally OK!";
    }

    @GetMapping("/t1")
    public Result test1(int num){
        if(num%2==0){
            return Result.OK();
        }else{
            return Result.err(Result.CODE_ERR_BUSINESS,"不允许奇数！");
        }
    }

    @Autowired
    private MealDao mealDao;

    @GetMapping("/meal-list")
    public Result mealList(){
        List<Meal> list = mealDao.findMealList();
        return Result.OK(list);
    }

    //向浏览器输出图片
    @GetMapping("/blob/{filename}")
    public void getBlob(@PathVariable String filename, HttpServletResponse response) throws IOException {
        //response 表示响应对象，负责向浏览器输出
        OutputStream out = response.getOutputStream();//向浏览器输出的输出流
        //读取文件的输入流
        InputStream in = new FileInputStream(
                DemoConstants.UPLOAD_DIR+"/"+filename);

        byte[] b = new byte[1024*1024];
        // 不用管这个注释
        int len = -1;//每次从流中读取的字节数

        while((len = in.read(b))!= -1 ){
            out.write(b,0,len);
        }

        out.flush();
        out.close();
        in.close();
    }



}
