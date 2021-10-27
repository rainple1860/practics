package org.example.construct;

/**
 * @className: CalculatorTest
 * @description:
 * @author: rainple
 * @create: 2021-10-27 09:17
 **/
public class CalculatorTest {

    public static void main(String[] args) {
        Calculator calculator = new Calculator(3);
        String result = calculator.calculate("2+3*(5-2 * (1+2))*(4)/2");
        System.out.println(result);
    }

}
