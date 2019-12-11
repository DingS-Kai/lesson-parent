package com.fosu.lesson.pojo;

import java.io.Serializable;
import java.util.List;

public class PageResult implements Serializable {

    private List rows;
    private long total;

    public PageResult() {
    }

    public PageResult(long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public List getRows() {
        return rows;
    }
    public void setRows(List rows) {
        this.rows = rows;
    }
    public long getTotal() {
        return total;
    }
    public void setTotal(long l) {
        this.total = l;
    }
}
