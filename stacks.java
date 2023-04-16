import java.util.Scanner;
import java.util.Stack;

public class stacks {
    public static Scanner s = new Scanner(System.in);

    public static boolean brackets_balanced(String str)
    {
        Stack<Character> st = new Stack<>();
        for(int i = 0; i < str.length(); i++)
        {
            if(str.charAt(i) == '(')
                st.push('(');
            else
            {
                if(st.empty())
                    return false;
                st.pop();
            }
        }
        return st.empty();
    }

    public static void reverse_stack(Stack<Integer> st, Stack<Integer> aux)
    {
        if(st.size() <= 1)
            return;
        int temp = st.pop();
        reverse_stack(st, aux);
        while(!st.empty())
            aux.push(st.pop());
        st.push(temp);
        while(!aux.empty())
            st.push(aux.pop());
    }

    private static boolean isOp(Character c)
    {
        return ((c == '+') || (c == '-') || (c == '*') || (c == '/'));
    }

    public static boolean redundant_brackets(String str)
    {
        Stack<Character> st = new Stack<>();
        for(int i = 0; i < str.length(); i++)
        {
            if(str.charAt(i) == '(')
                st.push('(');
            else if(isOp(str.charAt(i)) && (!st.empty()) && st.peek() == '(')
                st.push('+');
            else if(str.charAt(i) == ')')
            {
                if(st.empty() || !isOp(st.pop()))
                    return true;
                if(st.empty() || st.pop() != '(')
                    return true;
            }
        }
        return false;
    }

    public static int[] stock_span(int[] stock_price)
    {
        int[] span = new int[stock_price.length];
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < stock_price.length; i++)
        {
            int curr_span = 1;
            while(!st.empty() && (stock_price[i] > stock_price[st.peek()]))
                curr_span += span[st.pop()];
            span[i] = curr_span;
            st.push(i);
        }
        return span;
    }

    public static int min_bracket_reversal(String str)
    {
        if(str.length() % 2 != 0)
            return -1;
        Stack<Character> st = new Stack<>();
        for(int i = 0; i < str.length(); i++)
        {
            if(str.charAt(i) == '{')
                st.push('{');
            else
            {
                if(!st.empty() && st.peek() == '{')
                    st.pop();
                else
                    st.push('}');
            }
        }
        int count = 0;
        while(!st.empty())
        {
            char c1 = st.pop();
            char c2 = st.pop();
            if(c1 != c2)
                count += 2;
            else
                count++;
        }
        return count;
    }
    
    public static void main(String[] args) {
        String str = s.next();
        System.out.println(min_bracket_reversal(str));
    }
    
}

//Stack ADT implementation using Array
class stack {

    private int[] data;
    private int topIndex;

    public stack()
    {
        data = new int[10];
        topIndex = -1;
    }

    public stack(int max_size)
    {
        data = new int[max_size];
        topIndex = -1;
    }

    public void push(int e)
    {
        if(topIndex >= data.length - 1)
            doubleCapacity();
        topIndex++;
        data[topIndex] = e;
    }

    private void doubleCapacity()
    {
        int[] temp = data;
        data = new int[2 * temp.length];
        for(int i = 0; i < temp.length; i++)
            data[i] = temp[i];
    }

    public int pop() throws StackUnderflowException
    {
        if(topIndex <= -1)
            throw new StackUnderflowException();
        topIndex--;
        return data[topIndex + 1];
    }

    public int peek() throws StackUnderflowException
    {
        if(topIndex <= -1)
            throw new StackUnderflowException();
        return data[topIndex];
    }

    public int size()
    {
        return topIndex + 1;
    }

    public boolean isEmpty()
    {
        return topIndex == -1;
    }
    
}

// Stack ADT implementation using Linked Lists
class StackLL {

    private class StackNode {
        int data;
        StackNode next;

        StackNode(int data)
        {
            this.data = data;
        }
    }
    private StackNode head;
    private int size;

    public StackLL()
    {
        head = null;
        size = 0;
    }

    public void push(int e)
    {
        StackNode new_node = new StackNode(e);
        new_node.next = head;
        head = new_node;
        size++;
    }

    public int pop() throws StackUnderflowException
    {
        if(head == null)
            throw new StackUnderflowException();
        int res = head.data;
        head = head.next;
        size--;
        return res;
    }

    public int peek() throws StackUnderflowException
    {
        if(head == null)
            throw new StackUnderflowException();
        return head.data;
    }

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return this.size() == 0;
    }
}

class StackOverflowException extends Exception {
    public StackOverflowException()
    {
        System.out.println("Stack Overflow. Couldn't push element.");
    }
}

class StackUnderflowException extends Exception {
    public StackUnderflowException()
    {
        System.out.println("Stack Underflow. Couldn't access element.");
    }
}