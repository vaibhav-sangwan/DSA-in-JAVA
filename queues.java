import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Scanner;

public class queues {

    public static Scanner s = new Scanner(System.in);

    public static void rev_q(Queue<Integer> q)
    {
        if(q.size() <= 1)
            return;
        int temp = q.remove();
        rev_q(q);
        q.add(temp);
    }

    //Reversing the first k elements of a queue using recursion
    public static void rev_first_k_elems_in_q(Queue<Integer> q, int k)
    {
        if(k <= 1)
            return;
        int temp = q.remove();
        rev_first_k_elems_in_q(q, k-1);
        for(int i = 0; i < k - 1; i++)
            q.add(q.remove());
        q.add(temp);
        for(int i = 0; i < q.size() -  k; i++)
            q.add(q.remove());
    }

    //Reversing the first k elements of a queue using a Stack
    public static void rev_first_k_elems_in_q_2(Queue<Integer> q, int k)
    {
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < k; i++)
            st.push(q.remove());
        while(!st.empty())
            q.add(st.pop());
        for(int i = 0; i < q.size() - k; i++)
            q.add(q.remove());
    }

    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<>();
        
        int n = s.nextInt();
        int k = s.nextInt();
        for(int j = 0; j < n; j++)
            q.add(s.nextInt());

        rev_first_k_elems_in_q_2(q, k);
        while(!q.isEmpty())
            System.out.print(q.remove() + " ");

        s.close();
    }
}

//Implementing Stack ADT using 2 Queues 
class StackUsingQueues {
    private Queue<Integer> q1, q2;

    public StackUsingQueues()
    {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    public void push(int elem)
    {
        while(!q1.isEmpty())
            q2.add(q1.remove());
        q1.add(elem);
        while(!q2.isEmpty())
            q1.add(q2.remove());
    }

    public int pop()
    {
        if(q1.size() == 0)
            return -1;
        return q1.remove();
    }

    public int top()
    {
        if(q1.size() == 0)
            return -1;
        return q1.peek();
    }

    public int size()
    {
        return q1.size();
    }

    public boolean isEmpty()
    {
        return q1.size() == 0;
    }
}

// Implementing Queue ADT using 2 Stacks
class QueueUsingStacks<T> {

    private Stack<T> s1, s2;

    public QueueUsingStacks()
    {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    public void enqueue(T elem)
    {
        while(!s1.empty())
            s2.push(s1.pop());
        s1.push(elem);
        while(!s2.empty())
            s1.push(s2.pop());
    }

    public T dequeue() throws QueueUnderflowException
    {
        if(s1.size() == 0)
            throw new QueueUnderflowException();
        return s1.pop();
    }

    public T peek() throws QueueUnderflowException
    {
        if(s1.size() == 0)
            throw new QueueUnderflowException();
        return s1.peek();
    }

    public int size()
    {
        return s1.size();
    }

    public boolean isEmpty()
    {
        return s1.size() == 0;
    }
}

// Implementing Queue ADT using Linked Lists
class queueLL<T> {
    private class qNode
    {
        T data;
        qNode next;

        public qNode(T data)
        {
            this.data = data;
            this.next = null;
        }
    }

    private qNode head, tail;
    private int size;

    public queueLL()
    {
        head = null;
        tail = null;
        size = 0;
    }

    public void enqueue(T elem)
    {
        qNode node = new qNode(elem);
        if(head == null)
            head = node;
        else
            tail.next = node;
        tail = node;
        size++;
            
    }

    public T dequeue() throws QueueUnderflowException
    {
        if(head == null)
            throw new QueueUnderflowException();
        T res = head.data;
        head = head.next;
        if(head == null)
            tail = null;
        size--;
        return res;
    }

    public T peek() throws QueueUnderflowException
    {
        if(head == null)
            throw new QueueUnderflowException();
        return head.data;
        
    }

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }
}

// Implementing Queue ADT using array
class queue {
    private int[] data;
    private int front, rear, size;

    public queue()
    {
        data = new int[10];
        front = -1;
        rear = -1;
        size = 0;
    }

    public queue(int cap)
    {
        data = new int[cap];
        front = -1;
        rear = -1;
        size = 0;
    }

    public void enqueue(int elem)
    {
        if(size == data.length)
            doubleCapacity();
        rear = (rear + 1)%data.length;
        data[rear] = elem;
        if(front == -1)
            front++;
        size++;
    }

    private void doubleCapacity()
    {
        int[] temp = data;
        data = new int[2*temp.length];
        int i = 0;
        for(int k = front; k < temp.length; i++, k++)
            data[i] = temp[k];
        for(int k = 0; k < front - 1; i++, k++)
            data[i] = temp[k];
        front = 0;
        rear = temp.length - 1;
    }

    public int dequeue() throws QueueUnderflowException
    {
        if(size == 0)
            throw new QueueUnderflowException();
        int res = data[front];
        front = (front + 1)%data.length;
        size--;
        if(size == 0)
        {
            front = -1;
            rear = -1;
        }
        return res;
    }

    public int front() throws QueueUnderflowException
    {
        if(size == 0)
            throw new QueueUnderflowException();
        return data[front];
    }

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }
}

class QueueOverflowException extends Exception {

}

class QueueUnderflowException extends Exception {

}