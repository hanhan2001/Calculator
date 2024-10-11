package me.xiaoying.calculator.algurment;

public class Symbol {
    private final String key;
    private final int priorityLeft;
    private final int priorityRight;

    public Symbol(String key, int priorityLeft, int priorityRight) {
        this.key = key;
        this.priorityLeft = priorityLeft;
        this.priorityRight = priorityRight;
    }

    public String getKey() {
        return this.key;
    }

    public int getPriorityLeft() {
        return this.priorityLeft;
    }

    public int getPriorityRight() {
        return this.priorityRight;
    }
}