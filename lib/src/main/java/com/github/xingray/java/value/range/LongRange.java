package com.github.xingray.java.value.range;

import java.util.Objects;

/**
 * Description : 范围
 */
public class LongRange {

    private long start;
    private long end;

    public LongRange(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "\"Range\": {"
                + "\"from\": \"" + start
                + ", \"to\": \"" + end
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LongRange longRange = (LongRange) o;
        return start == longRange.start &&
                end == longRange.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
