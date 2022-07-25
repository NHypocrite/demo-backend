package org.xdq.demo.common.page;

import lombok.Data;

/**
 * 封装分页参数
 */
@Data
public class PageParam {

    private Integer pageNum = 1;//请求页码，注意：属性名称pageNum是依据PageHelper插件的规定定义的
    private Integer pageSize = 10; //请求的每页记录数，注意：属性名称pageSize是依据PageHelper插件的规定定义的
}
