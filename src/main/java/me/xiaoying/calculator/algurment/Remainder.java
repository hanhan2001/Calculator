package me.xiaoying.calculator.algurment;

import java.math.BigDecimal;

public class Remainder extends Algorithm {
    public Remainder() {
        super("%");
    }

    @Override
    public BigDecimal calculator(BigDecimal first, BigDecimal second) {
        return first.remainder(second);
    }
}