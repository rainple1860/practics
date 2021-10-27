package org.example.construct.link.stack;

/**
 * @className: StackTest
 * @description:
 * @author: rainple
 * @create: 2021-09-16 19:55
 **/
public class StackTest {

    public static void main(String[] args) {
        LinkStack<Integer> stack = new LinkStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push(4);
        System.out.println(stack.pop());
    }

}
