import me.xiaoying.calculator.Calculator;

public class Main {
    public static void main(String[] args) {
//        String content = "2 * (5 + 7) + {6 * (9 - 6)}";
//        long ms = System.currentTimeMillis();
        String content = "6 + 9 - 50 - 8 + 90 + 999 + 50 + 989 + 1513 + 4561 + 891123";
        Calculator calculator = new Calculator();
        calculator.calculator(content);
//        System.out.println(System.currentTimeMillis() - ms + "ms");
    }
}