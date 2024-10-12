package me.xiaoying.calculator.algurment;

import java.math.BigDecimal;

public class Division extends Algorithm {
    public Division() {
        super("/");
    }

    @Override
    public BigDecimal calculator(BigDecimal first, BigDecimal second) {
        if (second.doubleValue() == 0)
            return new BigDecimal(0);

        return first.divide(second);
    }
}