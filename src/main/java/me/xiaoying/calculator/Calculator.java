package me.xiaoying.calculator;

import me.xiaoying.calculator.algurment.*;
import me.xiaoying.calculator.container.Container;
import me.xiaoying.calculator.utils.NumberUtil;

import java.util.*;

public class Calculator {
    private static final Map<String, Algorithm> algorithms  = new HashMap<>();
    private static final Map<String, Container> containers = new HashMap<>();
    private static final Map<String, Symbol> symbols = new HashMap<>();

    public Calculator() {
        // algorithm
        this.registerAlgorithm(new Additive());
        this.registerAlgorithm(new Subtraction());
        this.registerAlgorithm(new Multiplication());
        this.registerAlgorithm(new Division());

        // symbols
        this.registerSymbol(new Symbol("+", 1, 1));
        this.registerSymbol(new Symbol("-", 1, 1));
        this.registerSymbol(new Symbol("*", 2, 2));
        this.registerSymbol(new Symbol("/", 2, 2));
        this.registerSymbol(new Symbol("^", 2, 3));
    }

    public void registerAlgorithm(Algorithm algorithm) {
        Calculator.algorithms.put(algorithm.getKey(), algorithm);
    }

    public void registerSymbol(Symbol symbol) {
        Calculator.symbols.put(symbol.getKey(), symbol);
    }

    public static Symbol getSymbol(String key) {
        return Calculator.symbols.get(key);
    }

    public void registerContainer(Container container) {
        Calculator.containers.put(container.getBegin(), container);
        Calculator.containers.put(container.getEnd(), container);
    }

    public String calculator(String content) {
        content = content.replace(" ", "").replace("̀\u3000", "");

        StringBuilder stringBuilder = new StringBuilder();

        String matchingType = "string";
        Symbol lastSymbol = null;
        CalculatorNumber number = new CalculatorNumber();
        List<CalculatorNumber> numbers = new ArrayList<>();

        String[] split = content.split("");

        for (int i = 0; i < split.length; i++) {
            if (number.complete()) {
                numbers.add(number);
                number = new CalculatorNumber();
                if (lastSymbol != null)
                    number.initialize(lastSymbol);
            }

            String string = split[i];

            if (matchingType.equalsIgnoreCase("number") && (!NumberUtil.isInteger(string) && !string.equalsIgnoreCase("."))) {
                number.initialize(Double.parseDouble(stringBuilder.toString()));
                stringBuilder = new StringBuilder();
                matchingType = "string";
                i--;
                continue;
            }

            if (matchingType.equalsIgnoreCase("string") && NumberUtil.isInteger(string)) {
                lastSymbol = Calculator.getSymbol(stringBuilder.toString());
                stringBuilder = new StringBuilder();
                number.initialize(lastSymbol);
                matchingType = "number";
                i--;
                continue;
            }
            stringBuilder.append(string);
        }

        if (stringBuilder.length() != 0 && NumberUtil.isInteger(stringBuilder.toString())) {
            number.initialize(lastSymbol).initialize(Double.parseDouble(stringBuilder.toString())).initialize(Calculator.getSymbol("+"));
            numbers.add(number);
        }

        for (CalculatorNumber calculatorNumber : numbers)
            System.out.println(calculatorNumber.getNumber() + " " + calculatorNumber.getLeft().getKey() + " " + calculatorNumber.getRight().getKey());

        // handle container

        return null;
    }
}