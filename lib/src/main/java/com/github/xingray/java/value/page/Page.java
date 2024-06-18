package com.github.xingray.java.value.page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Page<T> {
    private long total;

    private long pageIndex;

    private long pageSize;

    private List<T> items;

    public static <E> Page<E> of(long pageIndex, long pageSize, long total, List<E> items) {
        if (pageIndex <= 0) {
            throw new IllegalArgumentException("pageIndex must greater than 0");
        }
        if (pageSize <= 0) {
            throw new IllegalArgumentException("pageSize must greater than 0");
        }
        if (total < 0) {
            throw new IllegalArgumentException("total must greater than or equals 0");
        }

        Page<E> page = new Page<>();
        page.pageIndex = pageIndex;
        page.pageSize = pageSize;
        page.total = total;
        if (items != null) {
            page.items = new ArrayList<>(items);
        }

        return page;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public void setPageIndex(long pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public long getTotal() {
        return total;
    }

    public long getPageIndex() {
        return pageIndex;
    }

    public long getPageSize() {
        return pageSize;
    }

    public List<T> getItems() {
        return Collections.unmodifiableList(items);
    }

    public long getTotalPageCount() {
        return total / pageSize + (total % pageSize == 0 ? 0 : 1);
    }

    public long getItemCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public String toString() {
        return "Page{" +
                "total=" + total +
                ", pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", items=" + items +
                '}';
    }
}
