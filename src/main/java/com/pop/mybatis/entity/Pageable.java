package com.pop.mybatis.entity;

import java.io.Serializable;

/**
 * Created by xugang on 16/7/12.
 */
public class Pageable implements Serializable{
    private int pageNumber;//获取页码,页编码从0开始
    private int pageSize;//每页数目大小
    private int offset;//偏移量

    public Pageable(){

    }

    public Pageable(int pageNumber,int pageSize){
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }


    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getOffset() {
        return this.pageNumber * this.pageSize;
    }
}
