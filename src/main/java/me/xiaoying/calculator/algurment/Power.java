package me.xiaoying.calculator.algurment;

import java.math.BigDecimal;

public class Power extends Algorithm {
    public Power() {
        super("^");
    }

    @Override
    public BigDecimal calculator(BigDecimal first, BigDecimal second) {
        return first.pow(second.intValue());
    }
}