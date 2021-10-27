package org.example.construct;

/**
 * @className: Operator
 * @description:
 * @author: rainple
 * @create: 2021-10-26 21:47
 **/
public class Operator implements Element {

    private String op;

    public Operator(String operator) {
        this.op = operator;
    }

    @Override
    public int operator() {
        return OPERATOR;
    }

    @Override
    public Object getElement() {
        return op;
    }

    @Override
    public void setElement(Object s) {
        this.op = (String) s;
    }
}
