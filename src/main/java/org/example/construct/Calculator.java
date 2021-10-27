package org.example.construct;

import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @className: Calculator
 * @description: 计算器
 * @author: rainple
 * @create: 2021-10-26 20:50
 **/
public class Calculator {

    private int scale = 0;

    public Calculator(int scale) {
        if (scale < 0) {
            scale  = 0;
        }
        if (scale > 8) {
            scale = 8;
        }
        this.scale = scale;
    }

    public Calculator() {
    }

    public String calculate(String express) {
        //转化成前缀表达式
        Queue<Element> elements = prefixExp(express);
        if (elements == null) {
            return "0";
        }
        double cal = cal(elements);
        BigDecimal decimal = new BigDecimal(cal);
        BigDecimal scale = decimal.setScale(this.scale, BigDecimal.ROUND_HALF_UP);
        return scale.toString();
    }

    private double cal(Queue<Element> elements) {
        Stack<Element> opStack = new Stack<>();
        for (Element ele : elements) {
            if (ele.getClass() == Number.class) {
                opStack.push(ele);
            } else {
                opStack.push(operate(opStack.pop(),opStack.pop(),ele));
            }
        }
        Element result = opStack.pop();
        if (opStack.isEmpty()) {
            return (double) result.getElement();
        }
        throw new RuntimeException("表达式有误");
    }

    private Element operate(Element number1,Element number2,Element operator) {
        String op = operator.getElement().toString();
        double n1 = (double) number2.getElement();
        double n2 = (double) number1.getElement();
        double result;
        switch (op) {
            case "+" : result =  n1 + n2; break;
            case "-" : result =  n1 - n2; break;
            case "*" : result = n1 * n2; break;
            case "/" : result =  n1 / n2; break;
            default: throw new RuntimeException("非法运算符符");
        }
        return new Number(result);
    }

    private Queue<Element> prefixExp(String express) {

        if (!StringUtils.hasText(express)) {
            return null;
        }

        Queue<Element> queue = new LinkedList<>();
        Stack<Element> temp = new Stack<>();

        List<Element> elements = parseElement(express);

        for (Element element : elements) {
            if (element.getClass() == Number.class) {
                queue.offer(element);
            } else if (")".equals(element.getElement())) {
                while (!temp.isEmpty() && !"(".equals(temp.peek().getElement())) {
                    queue.offer(temp.pop());
                }
                temp.pop();
            } else if ("(".equals(element.getElement())) {
                temp.push(element);
            } else {
                if (!temp.isEmpty() && !privilege(element, temp.peek())) {
                    queue.offer(temp.pop());
                }
                temp.push(element);
            }
        }
        while (!temp.isEmpty()) {
            queue.offer(temp.pop());
        }
        return queue;
    }

    private boolean privilege(Element c1,Element c2) {
        return weight(c1) > weight(c2);
    }

    private int weight(Element operator) {
        String op = operator.getElement().toString();
        switch (op) {
            case "+":
            case "-": return 1;
            case "(":
            case ")": return 0;
            default: return 2;
        }
    }

    private List<Element> parseElement(String express) {
        List<Element> elements = new ArrayList<>();
        for (int i = 0; i < express.length();) {
            if (' ' == express.charAt(i)) {
                i++;
                continue;
            }
            String str = "";
            char c = express.charAt(i);
            while (((int) c >= 48 && (int) c <= 57) || '.' == c) {
                str += express.substring(i,i + 1);
                if (++i == express.length()) {
                    break;
                }
                c = express.charAt(i);
            }
            if (!str.equals("")) {
                elements.add(new Number(Double.parseDouble(str)));
            } else if (c == '+' || c == '-' || c == '/' || c == '*' || c == '(' || c == ')') {
                elements.add(new Operator(c + ""));
                i++;
            } else {
                throw new RuntimeException("非法表达式");
            }
        }
        return elements;
    }

}
