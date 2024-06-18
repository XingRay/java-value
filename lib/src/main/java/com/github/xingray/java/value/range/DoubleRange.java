package com.github.xingray.java.value.range;

import java.util.Objects;

/**
 * Description : 范围
 */
public class DoubleRange {

    private double start;
    private double end;

    public DoubleRange(double start, double end) {
        this.start = start;
        this.end = end;
    }

    public double getStart() {
        return start;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public double getEnd() {
        return end;
    }

    public void setEnd(double end) {
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
        DoubleRange that = (DoubleRange) o;
        return Double.compare(that.start, start) == 0 &&
                Double.compare(that.end, end) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
