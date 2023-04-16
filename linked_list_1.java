import java.util.Scanner;

public class linked_list_1 {
    public static Scanner s = new Scanner(System.in);

    public static void increment(Node head)
    {
        Node temp = head;
        while(temp != null)
        {
            temp.data++;
            temp = temp.next;
        }
    }

    public static Node take_input_sll()
    {
        SinglyLinkedList list = new SinglyLinkedList();
        int n = s.nextInt();
        while(n != -1)
        {
            list.add_last(n);
            n = s.nextInt();
        }
        return list.head;
    }

    public static int get_elem(Node head, int i)
    {
        Node temp = head;
        int j = 0;
        while(j < i && temp != null)
        {
            temp = temp.next;
            j++;
        }
        if(temp == null)
        {
            System.out.println("Index out of bounds\n");
            return -1;
        }
        return temp.data;
    }

    public static void print(Node head)
    {
        Node temp = head;
        while(temp != null)
        {
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
        System.out.print("null\n");
    }

    public static Node take_input()
    {
        Node head = null, tail = null;
        int val = s.nextInt();
        while(val != -1)
        {
            Node new_node = new Node(val);
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
            val = s.nextInt();
        }
        return head;
    }

    public static Node insert(Node head, int pos, int val)
    {
        Node new_node = new Node(val);
        if(pos <= 0)
        {
            new_node.next = head;
            head = new_node;
        }
        else
        {
            Node temp = head, prev_temp = null;
            for(int i = 0; i < pos; i++)
            {
                prev_temp = temp;
                temp = temp.next;
            }
            prev_temp.next = new_node;
            new_node.next = temp;
        }
        return head;
    }

    public static Node delete(Node head, int pos)
    {
        if(head == null)
            return null;
        if(pos == 0)
        {
            head = head.next;
            return head;
        }
        int i = 0;
        Node temp = head;
        while(i < pos - 1 && temp != null)
        {
            temp = temp.next;
            i++;
        }
        if(temp != null)
        {
            if(temp.next != null)
            {
                temp.next = temp.next.next;
            }
            else
            {
                temp.next = null;
            }
        }
        return head;
    }

    public static int index_of(Node head, int n)
    {
        if(head == null)
            return -1;
        Node temp = head;
        int i = 0;
        while(temp != null)
        {
            if(temp.data == n)
                return i;
            temp = temp.next;
            i++;
        }
        return -1;
    }

    public static Node append_last_n_to_first(Node head, int n)
    {
        if(head == null)
            return head;
        Node temp = head;
        for(int i = 0; i < n; i++)
            temp = temp.next;
        Node last = head;
        while(temp.next != null)
        {
            temp = temp.next;
            last = last.next;
        }
        temp.next = head;
        head = last.next;
        last.next = null;
        return head;

    }

    public static Node remove_duplicates(Node head)
    {
        if(head == null || head.next == null)
            return head;
        Node slow = head, fast = head.next;
        while(fast != null)
        {
            if(fast.data == slow.data)
            {
                slow.next = fast.next;
            }
            else
            {
                slow = slow.next;
            }
            fast = fast.next;
        }
        return head;
    }

    public static void print_rev(Node head)
    {
        if(head == null)
            return;
        int i = 0;
        while(true)
        {
            Node fast = head, slow = null;
            for(int j = 0; j < i; j++)
            {
                fast = fast.next;
            }
            if(fast == null)
                break;
            slow = head;
            while(fast.next != null)
            {
                fast = fast.next;
                slow = slow.next;
            }
            System.out.print(slow.data + " ");
            i++;
        }
        System.out.println();
    }

    public static boolean is_palindrome(Node head)
    {
        if(head == null || head.next == null)
            return true;
        
        Node tail = head, tail_prev = null;
        while(tail.next != null)
        {
            tail_prev = tail;
            tail = tail.next;
        }
        tail_prev.next = null;
        boolean res_n_1 = is_palindrome(head.next);
        boolean res = res_n_1 && (head.data == tail.data);
        tail_prev.next = tail;
        return res;
    }

    public static boolean is_palindrome_optimized(Node head)
    {
        if(head == null)
            return true;
        
        int count = 0;
        Node temp = head;
        while(temp != null)
        {
            count++;
            temp = temp.next;
        }

        Node head_2 = head;
        temp = head;
        for(int i = 0; i < (count - 1)/2; i++)
        {
            temp = temp.next;
        }

        head_2 = temp.next;
        temp.next = null;

        Node curr = head_2, prev = null, next = curr;
        while(next != null)
        {
            next = next.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head_2 = prev;
        print(head_2);

        temp = head;
        Node temp_2 = head_2;
        while(temp != null && temp_2 != null)
        {
            if(temp.data != temp_2.data)
                return false;
            temp = temp.next;
            temp_2 = temp_2.next;
        }
        return true;

    }

    public static void main(String[] args) {
        int t = s.nextInt();
        for(int i = 0; i < t; i++)
        {
            Node head = take_input();
            System.out.println(is_palindrome_optimized(head));
        }
        s.close();
    }
}

class SinglyLinkedList {
    Node head;

    SinglyLinkedList() {
        head = null;
    }

    public void add(int val)
    {
        Node new_node = new Node(val);
        new_node.next = head;
        head = new_node;
    }

    public int remove_first()
    {
        Node temp = head;
        if(head == null)
        {
            System.out.println("List Empty. Can't Remove");
            return -1;
        }
        head = head.next;
        return temp.data;
    }

    public void print()
    {
        Node temp = head;
        if(head == null)
        {
            System.out.println("List is empty. Cannot traverse");
            return;
        }
        while(temp != null)
        {
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
        System.out.print("null\n");
    }

    public int size()
    {
        int size = 0;
        Node temp = head;
        while(temp != null)
        {
            size++;
            temp = temp.next;
        }
        return size;
    }

    public void add_last(int val)
    {
        Node temp = head;
        if(temp == null)
        {
            add(val);
            return;
        }
        Node new_node = new Node(val);
        while(temp.next != null)
        {
            temp = temp.next;
        } 
        temp.next = new_node;
    }
}