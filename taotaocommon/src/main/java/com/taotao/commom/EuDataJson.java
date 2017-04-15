package com.taotao.commom;

import java.util.List;

/**
 * Created by winsion on 2017/4/15.
 */
public class EuDataJson {

    private long total;
    private List<?> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
