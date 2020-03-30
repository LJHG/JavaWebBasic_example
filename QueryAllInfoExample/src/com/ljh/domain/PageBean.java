package com.ljh.domain;

import java.util.List;

/*
    分页类
 */
public class PageBean<T> {
    private int currentPage;
    private int totalPage;
    private int totalNums;
    private List<T> currentInfo; //其实好像叫users也挺好的
    private int rows;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalNums() {
        return totalNums;
    }

    public void setTotalNums(int totalNums) {
        this.totalNums = totalNums;
    }

    public List<T> getCurrentInfo() {
        return currentInfo;
    }

    public void setCurrentInfo(List<T> currentInfo) {
        this.currentInfo = currentInfo;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", totalNums=" + totalNums +
                ", currentInfo=" + currentInfo +
                ", rows=" + rows +
                '}';
    }
}
