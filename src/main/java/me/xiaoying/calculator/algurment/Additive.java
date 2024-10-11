package me.xiaoying.calculator.algurment;

import java.math.BigDecimal;

public class Additive extends Algorithm {
    public Additive() {
        super("+");
    }

    @Override
    public BigDecimal calculator(BigDecimal first, BigDecimal second) {
        return first.add(second);
    }
}