package me.xiaoying.calculator;

import me.xiaoying.calculator.algurment.Symbol;

import java.math.BigDecimal;

public class CalculatorNumber {
    private BigDecimal number = null;
    private Symbol left = null;
    private Symbol right = null;

    public CalculatorNumber initialize(BigDecimal number) {
        this.number = number;
        return this;
    }

    public CalculatorNumber initialize(Symbol symbol) {
        if (this.number != null && this.left == null)
            this.left = Calculator.getSymbol("+");

        if (this.left == null)
            this.left = symbol;
        else if  (this.right == null)
            this.right = symbol;

        return this;
    }

    public BigDecimal getNumber() {
        return this.number;
    }

    public Symbol getLeft() {
        return this.left;
    }

    public Symbol getRight() {
        return this.right;
    }

    public boolean ready(CalculatorNumber number) {
        if (number.getLeft().getPriorityLeft() > number.getRight().getPriorityRight())
            return false;

        return this.left.getPriorityLeft() >= this.right.getPriorityRight();
    }

    /**
     * Determine Calculator initialized
     *
     * @return Boolean
     */
    public boolean complete() {
        return this.number != null && this.left != null && this.right != null;
    }
}