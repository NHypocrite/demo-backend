package org.xdq.demo.common.page;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  分页工具类
 */
public class PageUtils {

    /**
     * @param action    查询对象
     * @param pageParam
     * @return
     */
    public static <T> Map<String, Object> getPage(QueryAction<T> action, PageParam pageParam) {

        //第一步：紧挨在查询之前设置分页参数
        PageHelper.startPage(pageParam);

        //第二步：执行查询，但这里具体的查询实现未知，需要由使用者传入
        List<T> list = action.query();

        //第三步：紧挨在查询之后，构造PageInfo（pagehelper提供）对象
        PageInfo<T> pageInfo = new PageInfo<>(list);

        Map<String, Object> map = new HashMap<>();
        map.put("current", pageInfo.getPageNum());//当前页码
        map.put("pageSize",pageInfo.getPageSize());//每页记录数
        map.put("total",pageInfo.getTotal());//总记录数
        map.put("pages",pageInfo.getPages());//总页数

        map.put("first",1);
        map.put("prev",pageInfo.getPrePage());//上页页码
        map.put("next",pageInfo.getNextPage());//下页页码
        map.put("last", pageInfo.getPages());//尾页页码

        map.put("list",pageInfo.getList());//当前页数据记录
        map.put("currentSize",pageInfo.getSize());//当前页实际记录数

        /*
            返回Map的数据结构
            total:58,
            pages:6,
            pageSize:10,
            current:2,
            ........

         */


        return map;
    }
}
