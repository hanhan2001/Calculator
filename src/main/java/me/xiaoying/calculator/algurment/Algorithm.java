package me.xiaoying.calculator.algurment;

import java.math.BigDecimal;

public abstract class Algorithm {
    private final String key;

    public Algorithm(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    public abstract BigDecimal calculator(BigDecimal first, BigDecimal second);
}