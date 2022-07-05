package org.xdq.demo.model;

import lombok.Data;

@Data
public class Meal {

    private Integer meal_id;//餐品编号
    private String meal_name;
    private Double meal_price;
    private String meal_desc;
    private String meal_pic;

    private Integer cls_id;//分类


}
