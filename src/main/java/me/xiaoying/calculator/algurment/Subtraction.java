package me.xiaoying.calculator.algurment;

import java.math.BigDecimal;

public class Subtraction extends Algorithm {
    public Subtraction() {
        super("-");
    }

    @Override
    public BigDecimal calculator(BigDecimal first, BigDecimal second) {
        return first.subtract(second);
    }
}