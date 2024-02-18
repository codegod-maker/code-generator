package io.renren.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.renren.entity.PageResult;

import java.util.List;

public class PageHelper {

    public static <T> Page<T> getPage(Integer pageNum, Integer pageSize) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        return new Page<>(pageNum, pageSize);
    }

    public static <T> PageResult<T> getPageResult(IPage<T> page) {
        return getPageResult(page.getRecords(), page.getPages(), page.getTotal());
    }

    public static <T> PageResult<T> getPageResult(List<T> data, Long pages, Long total) {
        PageResult<T> result = new PageResult<>();
        result.setList(data);
        result.setPages(pages);
        result.setTotal(total);
        return result;
    }
}
