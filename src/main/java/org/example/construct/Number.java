package org.example.construct;

/**
 * @className: Number
 * @description:
 * @author: rainple
 * @create: 2021-10-26 21:49
 **/
public class Number implements Element {

    private double number;

    public Number(double number) {
        this.number = number;
    }

    @Override
    public int operator() {
        return NUMBER;
    }

    @Override
    public Object getElement() {
        return number;
    }

    @Override
    public void setElement(Object number) {
        this.number = (int)number;
    }
}
