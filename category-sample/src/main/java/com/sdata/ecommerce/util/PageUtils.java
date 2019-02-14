package com.sdata.ecommerce.util;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author nedli
 */
public class PageUtils {
    public static long totalPages(List list) {
        return new PageInfo(list).getTotal();
    }
}
