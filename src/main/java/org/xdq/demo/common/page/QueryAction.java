package org.xdq.demo.common.page;

import java.util.List;

/**
 * 查询对象，封装查询行为
 */
public interface QueryAction<T> {


    //执行查询获得结果的List集合
    List<T> query();
}
