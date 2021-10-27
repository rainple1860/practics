package org.example.construct;

/**
 * @InterfaceName: Element
 * @description:
 * @author: rainple
 * @create: 2021-10-26 21:43
 **/
public interface Element {

    int NUMBER = 0;
    int OPERATOR = 1;

    int operator();

    Object getElement();

    void setElement(Object e);

}
