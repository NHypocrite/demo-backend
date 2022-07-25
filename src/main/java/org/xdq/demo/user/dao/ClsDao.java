package org.xdq.demo.user.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.xdq.demo.user.dto.ClsDto;
import org.xdq.demo.user.model.Cls;

import java.util.List;

@Mapper
public interface ClsDao {

    @Select("select * from t_cls where cls_name like concat('%',#{cls_name},'%')")
    public List<Cls> findClsList(ClsDto clsDto);

}
