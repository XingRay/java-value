package com.github.xingray.java.value.page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TimePage<T> {
    private long total;

    private long timestamp;

    private long pageSize;

    private List<T> items;

    public static <E> TimePage<E> of(long pageSize, long timestamp, long total, List<E> items) {
        if (pageSize <= 0) {
            throw new IllegalArgumentException("pageSize must greater than 0");
        }
        if (total < 0) {
            throw new IllegalArgumentException("total must greater than or equals 0");
        }
        TimePage<E> page = new TimePage<>();
        page.pageSize = pageSize;
        page.timestamp = timestamp;
        page.total = total;
        if (items != null) {
            page.items = new ArrayList<>(items);
        }
        return page;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
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

    public long getTimestamp() {
        return timestamp;
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
        return "TimePage{" +
                "total=" + total +
                ", timestamp=" + timestamp +
                ", pageSize=" + pageSize +
                ", items=" + items +
                '}';
    }
}
