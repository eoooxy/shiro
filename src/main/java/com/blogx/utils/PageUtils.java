package com.blogx.utils;

import com.blogx.constant.GlobalConstant;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * author： xueyuan
 * date  ： 2017-09-08 上午9:45
 */
@Component
public class PageUtils implements Serializable {


    private Integer pageNum;
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum == null ? GlobalConstant.PAGE_NUMBER : pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize == null ? GlobalConstant.PAGE_SIZE : pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


}
