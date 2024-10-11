package me.xiaoying.calculator;

import me.xiaoying.calculator.algurment.Symbol;

import java.math.BigDecimal;

public class CalculatorNumber {
    private BigDecimal number = null;
    private Symbol left = null;
    private Symbol right = null;

    public CalculatorNumber initialize(double number) {
        this.number = new BigDecimal(number);
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

    public boolean ready() {
        return this.left.getPriorityLeft() >= this.left.getPriorityRight();
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