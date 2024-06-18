package com.github.xingray.java.value.range;

import java.util.Objects;

/**
 * Description : 范围
 */
public class IntRange {

    private int start;
    private int end;

    public IntRange(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
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
        IntRange intRange = (IntRange) o;
        return start == intRange.start &&
                end == intRange.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
