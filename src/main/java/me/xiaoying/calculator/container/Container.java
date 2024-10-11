package me.xiaoying.calculator.container;

public class Container {
    private final String begin;
    private final String end;
    private final int priority;

    public Container(String begin, String end, int priority) {
        this.begin = begin;
        this.end = end;
        this.priority = priority;
    }

    public String getBegin() {
        return this.begin;
    }

    public String getEnd() {
        return this.end;
    }

    public int getPriority() {
        return this.priority;
    }
}