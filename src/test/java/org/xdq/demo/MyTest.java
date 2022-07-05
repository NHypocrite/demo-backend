package org.xdq.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.xdq.demo.dao.MealDao;
import org.xdq.demo.model.Meal;

import java.util.List;

@SpringBootTest
public class MyTest {

    @Autowired //自动装配属性
    private MealDao mealDao;

    @Test
    public void test1(){

        List<Meal> list = mealDao.findMealList();

        for(int i=0;i<list.size();i++){
            Meal meal = list.get(i);
            System.out.println(meal.getMeal_name());
        }
    }
}
