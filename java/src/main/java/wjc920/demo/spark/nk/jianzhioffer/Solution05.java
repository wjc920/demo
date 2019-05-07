package wjc920.demo.spark.nk.jianzhioffer;

import java.util.Stack;

/**
 * 用两个栈实现队列
 *
 * @author wjc
 * @date 2019年05月03日 下午11:31:25
 */
public class Solution05 {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        int r;
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        r=stack2.pop();
        while (!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        return r;
    }

    public static void main(String[] args) {
        Solution05 s = new Solution05();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);

        int size=s.stack1.size();
        for (int i = 0; i < size; i++) {
            System.out.println(s.pop());
        }
    }

}

