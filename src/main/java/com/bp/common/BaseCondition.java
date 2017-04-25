package com.bp.common;

/**
 * 分页基本条件
 * @author current_bp
 * @createTime 20170323
 */
public class BaseCondition {


    private final Integer DEFAULT_PAGE_SIZE = 10;

    private Integer pageIndex;
    private Integer pageSize;
    private String orderBy;
    private String sort = "1";//1默认是按照时间将序排列，2是按照焦点排序，先生命周期升续，在时间降序。
    private long  count;   //分页总条数

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getStartRow() {
        int size = 0;
        if (pageIndex == null || pageIndex < 1) {
            pageIndex = 1;
        }
        if (pageSize == null) {
            size = DEFAULT_PAGE_SIZE;
        } else {
            size = pageSize;
        }
        return (pageIndex - 1) * size;
    }
}
