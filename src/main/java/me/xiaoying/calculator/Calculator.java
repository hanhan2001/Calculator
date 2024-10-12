package me.xiaoying.calculator;

import me.xiaoying.calculator.algurment.*;
import me.xiaoying.calculator.container.Container;
import me.xiaoying.calculator.utils.NumberUtil;

import java.math.BigDecimal;
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

    public static Algorithm getAlgorithm(String key) {
        return Calculator.algorithms.get(key);
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
            number.initialize(Double.parseDouble(stringBuilder.toString())).initialize(Calculator.getSymbol("+"));
            numbers.add(number);
        }

        if (numbers.isEmpty())
            return "";

        List<CalculatorNumber> newNumbers = new ArrayList<>();
        CalculatorNumber currencyNumber = null;
        for (int i = 0; i < numbers.size(); i++) {
            CalculatorNumber _number = numbers.get(i);

            if (currencyNumber == null) {
                currencyNumber = _number;
                continue;
            }

            if (!_number.ready(currencyNumber)) {
                newNumbers.add(new CalculatorNumber().initialize(currencyNumber.getLeft()).initialize(currencyNumber.getNumber().doubleValue()).initialize(currencyNumber.getRight()));
                currencyNumber = _number;
                continue;
            }

            BigDecimal result = Calculator.getAlgorithm(currencyNumber.getRight().getKey()).calculator(currencyNumber.getNumber(), _number.getNumber());
            String symbol = result.doubleValue() >= 0 ? "+" : "-";
            currencyNumber = new CalculatorNumber().initialize(Calculator.getSymbol(symbol)).initialize(result.doubleValue()).initialize(_number.getRight());

            if (i == numbers.size() - 1 && !newNumbers.isEmpty()) {
                numbers.clear();
                numbers.addAll(newNumbers);
                newNumbers.clear();
                i = -1;
            }
        }

        // handle container

        return currencyNumber.getNumber().toString();
    }
}