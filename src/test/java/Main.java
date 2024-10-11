import me.xiaoying.calculator.Calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        String content = "2 * (5 + 7) + {6 * (9 - 6)}";
//        long ms = System.currentTimeMillis();
//        String content = "6 + 9 - 50 - 8 + 90 + 999 + 50 + 989 + 1513 + 4561 + 891123";

        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();
        String content;
        while (true) {
            content = scanner.nextLine();
            if (content.equalsIgnoreCase("exit"))
                break;

            System.out.println(calculator.calculator(content));
        }
    }
}