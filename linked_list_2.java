import java.util.Scanner;

class Node {
    int data;
    Node next;

    Node()
    {

    }

    public Node(int data)
    {
        this.data = data;
    }

};

public class linked_list_2 {

    public static Scanner s = new Scanner(System.in);

    public static void print(Node head)
    {
        Node temp = head;
        while(temp != null)
        {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static Node take_input()
    {
        int data = s.nextInt();
        Node head = null, tail = null;
        while(data != -1)
        {
            Node new_node = new Node();
            new_node.data = data;
            if(head == null)
            {
                head = new_node;
                tail = new_node;
            }
            else
            {
                tail.next = new_node;
                tail = tail.next;
            }
            data = s.nextInt();
        }
        return head;
    }

    public static void print_rec(Node head)
    {
        if(head == null)
            return;
        System.out.print(head.data + " ");
        print_rec(head.next);
    }

    public static void print_rev_rec(Node head)
    {
        if(head == null)
            return;
        print_rev_rec(head.next);
        System.out.print(head.data + " ");
    }

    public static Node insert_rec(Node head, int data, int position)
    {
        if(position <= 0)
        {
            Node new_node = new Node();
            new_node.data = data;
            new_node.next = head;
            head = new_node;
            return head;
        }
        else if(head == null)
            return null;
        
        head.next = insert_rec(head.next, data, position - 1);
        return head;
    }

    public static Node delete(Node head, int position)
    {
        if(head == null)
            return head;
        else if(position == 0)
            return head.next;
        head.next = delete(head.next, position - 1);
        return head;
    }

    public static Node rev_rec(Node prev, Node head)
    {
        if(head == null)
            return prev;
        Node temp = head.next;
        head.next = prev;
        return rev_rec(head, temp);
    }

    public static Node mid_point(Node head)
    {
        if(head == null)
            return null;
        Node slow = head, fast = head;
        while(fast.next != null && fast.next.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node compare(Node n1, Node n2)
    {
        if(n1 == null)
            return n2;
        else if(n2 == null)
            return n1;
        else
            return (n1.data < n2.data)?n1:n2;
    }

    public static Node merge(Node head1, Node head2)
    {
        Node head3 = compare(head1, head2), tail3 = head3;
        if(tail3 == null)
            return null;
        while(tail3.next != null)
        {
            if(tail3 == head1)
                head1 = head1.next;
            else
                head2 = head2.next;

            tail3.next = compare(head1, head2);
            tail3 = tail3.next;
        }
        if(tail3 == head1)
            tail3.next = head2;
        else
            tail3.next = head1;
        return head3;
    }

    public static Node merge_sort(Node head)
    {
        if(head == null || head.next == null)
            return head;
        Node head1 = head, head2 = mid_point(head), temp = head2;
        if(head2 != null)
            head2 = head2.next;
        temp.next = null;
        head1 = merge_sort(head1);
        head2 = merge_sort(head2);
        return merge(head1, head2);
    }

    public static int find_rec(Node node, int n)
    {
        if(node == null)
            return -1;
        else if(node.data == n)
            return 0;
        else
        {
            int res_n_1 = find_rec(node.next, n);
            if(res_n_1 == -1)
                return -1;
            else
                return res_n_1 + 1;
        }
    }

    public static Node even_after_odd(Node head)
    {
        Node odd_head = null, odd_tail = null, even_head = null, even_tail = null, temp = head;
        while(temp != null)
        {
            if(temp.data%2 != 0)
            {
                if(odd_head == null)
                {
                    odd_head = temp;
                    odd_tail = temp;
                }
                else
                {
                    odd_tail.next = temp;
                    odd_tail = odd_tail.next;
                }
            }
            else
            {
                if(even_head == null)
                {
                    even_head = temp;
                    even_tail = temp;
                }
                else
                {
                    even_tail.next = temp;
                    even_tail = even_tail.next;
                }
            }
            temp = temp.next;
        }
        if(even_tail != null)
            even_tail.next = null;
        if(odd_tail == null)
            return even_head;
        odd_tail.next = even_head;
        return odd_head;
    }

    public static Node del_n_nodes(Node head, int m, int n)
    {
        if(m == 0 || head == null)
            return null;
        if(n == 0)
            return head;
        Node slow = head, fast = head, temp;
        while(fast != null)
        {
            for(int i = 0; i < m && fast != null; i++)
            {
                temp = fast;
                fast = fast.next;
                slow.next = temp;
                slow = slow.next;
            }
            for(int i = 0; i < n && fast != null; i++)
                fast = fast.next;
        }
        slow.next = null;
        return head;
    }

    public static Node find_node(Node head, int index)
    {
        if(head == null)
            return null;
        Node temp = head;
        for(int i = 0; i < index && temp != null; i++)
            temp = temp.next;
        return temp;
    }

    public static Node swap(Node head, int i, int j)
    {
        if(head == null || i == j)
            return head;

        Node prev_1 = head, prev_2 = head;
        int k = 0;
        for(k = 0; k < i - 1; k++)
            prev_1 = prev_1.next;
        prev_2 = prev_1;
        for(; k < j - 1; k++)
            prev_2 = prev_2.next;

        Node node_1 = head, node_2 = prev_2.next, temp;

        if(i != 0)
        {
            node_1 = prev_1.next;
            prev_1.next = node_2;
        }

        prev_2.next = node_1;
        temp = node_1.next;
        node_1.next = node_2.next;
        node_2.next = temp;

        if(i != 0)
            return head;
        else
            return node_2;
    }

    public static Node rev_k(Node head, int k)
    {
        if(head == null || k <= 1)
            return head;
        Node curr = head, curr_next = head, curr_prev = null;
        for(int i = 0; i < k && curr_next != null ;i++)
        {
            curr_next = curr_next.next;
            curr.next = curr_prev;
            curr_prev = curr;
            curr = curr_next;
        }
        if(curr != null)
            head.next = rev_k(curr, k);
        return curr_prev;
    }

    public static void swap_nodes_after(Node node)
    {
        Node temp = node.next.next;
        node.next.next = temp.next;
        temp.next = node.next;
        node.next = temp;
    }

    public static Node ite_bubble_sort(Node head)
    {
        if(head == null || head.next == null)
            return head;
        
        Node sorted = null, temp;
        while(head.next != sorted)
        {
            if(head.next.data < head.data)
            {
                temp = head.next;
                head.next = temp.next;
                temp.next =  head;
                head = temp;
            }
            temp = head;
            while(temp.next != sorted)
            {
                if(temp.next.next != null && temp.next.next.data < temp.next.data)
                    swap_nodes_after(temp);
                temp = temp.next;
            }
            sorted = temp;
        }
        return head;
    }

    public static int increment_rec(Node node)
    {
        if(node == null)
            return 1;
        
        int carry = increment_rec(node.next);
        node.data += carry;
        if(node.data >= 10)
        {
            node.data -= 10;
            return 1;
        }
        return 0;
    }

    public static Node increment(Node head)
    {
        int carry = increment_rec(head);
        if(carry == 1)
        {
            Node new_node = new Node();
            new_node.data = 1;
            new_node.next = head;
            head = new_node;
        }
        return head;
    }

    public static void replace_duplicates(Node head)
    {
        int n = 0;
        Node temp = head;
        while(temp != null)
        {
            if(temp.data > n)
                n = temp.data;
            temp = temp.next;
        }
        boolean[] arr = new boolean[n + 1];
        temp = head;
        while(temp != null)
        {
            if(arr[temp.data] == true)
            {
                n++;
                temp.data = n;
            }
            else
                arr[temp.data] = true;
            temp = temp.next;
        }
    }
    
    public static void main(String[] args)
    {
        Node head = take_input();
        replace_duplicates(head);
        print(head);
        s.close();
    }
    
}