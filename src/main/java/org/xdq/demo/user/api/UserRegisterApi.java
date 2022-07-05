package org.xdq.demo.user.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xdq.demo.common.DemoConstants;
import org.xdq.demo.common.Result;
import org.xdq.demo.user.dao.RegisterDao;
import org.xdq.demo.user.dto.RegisterDto;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@Transactional
public class UserRegisterApi {

    @Autowired
    private RegisterDao registerDao;

    @PostMapping("/register")
    public Result register(@RequestBody RegisterDto dto){
        registerDao.insertUser(dto);
        return Result.OK();
    }

    @PostMapping("/upload")
    public Result uploadAvatar(MultipartFile avatar) throws IOException {
        String filename = UUID.randomUUID().toString().replace("-", "");
        InputStream in = avatar.getInputStream();

        OutputStream out = new FileOutputStream(DemoConstants.UPLOAD_DIR+"/"+filename);


        byte[] b = new byte[1024*1024];
        int len = -1;//每次从流中读取的字节数

        while((len = in.read(b))!= -1 ){
            out.write(b,0,len);
        }

        out.flush();
        out.close();
        in.close();

        return Result.OK((Object)filename);

    }

}
