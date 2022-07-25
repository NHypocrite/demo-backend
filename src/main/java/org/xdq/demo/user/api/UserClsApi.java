package org.xdq.demo.user.api;


import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xdq.demo.common.Result;

import org.xdq.demo.common.page.PageParam;
import org.xdq.demo.common.page.PageUtils;
import org.xdq.demo.common.page.QueryAction;
import org.xdq.demo.user.dao.ClsDao;
import org.xdq.demo.user.dto.ClsDto;
import org.xdq.demo.user.model.Cls;

import javax.swing.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Transactional
public class UserClsApi {

    @Autowired
    private ClsDao clsDao;

    @GetMapping("/cls-list")
    public Result clsList(ClsDto clsDto){


        Map<String, Object> page = PageUtils.getPage(() -> clsDao.findClsList(clsDto), clsDto);

        return Result.OK(page);

    }



}
