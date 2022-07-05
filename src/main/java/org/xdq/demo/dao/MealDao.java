package org.xdq.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.xdq.demo.model.Meal;

import java.util.List;

@Mapper //告诉spring这是一个dao对象，你把它创建并管理起来
public interface MealDao {

    @Select("select * from t_meal")
    public List<Meal> findMealList();

}
