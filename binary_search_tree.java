import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;

public class binary_search_tree {

    public static Scanner s = new Scanner(System.in);

    // Search an element K in a given BST.
    public static boolean search(BinaryTreeNode<Integer> root, int k)
    {
        if(root == null)
            return false;
        if(root.data == k)
            return true;
        else if(root.data > k)
            return search(root.left, k);
        return search(root.right, k);
    }

    // prints all the values which lie in the range of k1 to k2(Both inclusive)
    public static void print_between_k1_and_k2(BinaryTreeNode<Integer> root, int k1, int k2)
    {
        if(root == null)
            return;
        if(root.data <= k2 && root.data >= k1)
        {
            print_between_k1_and_k2(root.left, k1, k2);
            System.out.print(root.data + " ");
            print_between_k1_and_k2(root.right, k1, k2);
        }
        if(root.data < k1)
            print_between_k1_and_k2(root.right, k1, k2);
        if(root.data > k2)
            print_between_k1_and_k2(root.left, k1, k2); 
    }

    private static BinaryTreeNode<Integer> construct(int[] arr, int s, int e)
    {
        if(s > e)
            return null;
        int mid = (s + e)/2;
        BinaryTreeNode<Integer> node = new BinaryTreeNode<>(arr[mid]);
        node.left = construct(arr, s, mid - 1);
        node.right = construct(arr, mid + 1, e);
        return node;
    }

    // Construct BST from a sorted array containing unique elements.
    public static BinaryTreeNode<Integer> construct(int[] arr)
    {
        return construct(arr, 0, arr.length - 1);
    }

    private static tree_info is_bst_helper(BinaryTreeNode<Integer> root)
    {
        tree_info t = new tree_info();
        if(root == null)
        {
            t.is_bst = true;
            t.max = Integer.MIN_VALUE;
            t.min = Integer.MAX_VALUE;
            return t;
        }

        tree_info left = is_bst_helper(root.left);
        tree_info right = is_bst_helper(root.right);

        if(left.is_bst && right.is_bst && (root.data > left.max) && (root.data <= right.min))
            t.is_bst = true;
        t.max = Math.max(root.data, right.max);
        t.min = Math.min(root.data, left.min);
        return t;
    }

    // Returns true if the binary tree is a BST. False otherwise.
    public static boolean is_bst(BinaryTreeNode<Integer> root)
    {
        return is_bst_helper(root).is_bst;
    }

    private static boolean is_bst_2(BinaryTreeNode<Integer> root, int low, int high)
    {
        if(root == null)
            return true;
        return ((root.data >= low) && (root.data <= high) && is_bst_2(root.left, low, root.data - 1) && is_bst_2(root.right, root.data, high));
    }

    // Returns true if the binary tree is a BST. False otherwise.
    public static boolean is_bst_2(BinaryTreeNode<Integer> root)
    {
        return is_bst_2(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static NodePair convert_bst_to_ll_helper(BinaryTreeNode<Integer> root)
    {
        if(root == null)
            return null;

        NodePair pair = new NodePair();
        Node curr = new Node();
        curr.data = root.data;

        NodePair left = convert_bst_to_ll_helper(root.left);
        NodePair right = convert_bst_to_ll_helper(root.right);
        
        if(left == null && right == null)
        {
            pair.head = curr;
            pair.tail = curr;
        }
        else if(left == null)
        {
            pair.head = curr;
            curr.next = right.head;
            pair.tail = right.tail;
        }
        else if(right == null)
        {
            pair.head = left.head;
            left.tail.next = curr;
            pair.tail = curr;
        }
        else
        {
            left.tail.next = curr;
            curr.next = right.head;
            pair.head = left.head;
            pair.tail = right.tail;
        }
        return pair;
    }

    // Convert BST to Linked List
    public static Node convert_bst_to_ll(BinaryTreeNode<Integer> root)
    {
        return convert_bst_to_ll_helper(root).head;
    }

    // Finding LCA(Lowest Common Ancestor) of nodes with data A and B in BST. Every node is a descendant of itself. If none of the two nodes is present, return -1. If only one of the nodes is present, return it's value. Time Complexity - O(h).
    public static int LCA(BinaryTreeNode<Integer> root, int a, int b)
    {
        if(root == null)
            return -1;
        
        int left = -1, right = -1;
        if(!(root.data < Math.min(a, b)))
            left = LCA(root.left, a, b);
        if(!(root.data > Math.max(a, b)))
            right = LCA(root.right, a, b);
        
        if(left == -1 && right == -1)
            return ((root.data == a) || (root.data == b))?root.data:-1;
        else if(left == -1)
            return right;
        else if(right == -1)
            return left;
        return root.data;
    }

    private static int replace_node_data_with_larger_nodes(BinaryTreeNode<Integer> root, int sum_from_parent)
    {
        if(root == null)
            return sum_from_parent;
        
        int right = replace_node_data_with_larger_nodes(root.right, sum_from_parent);
        root.data += right;
        int left = replace_node_data_with_larger_nodes(root.left, root.data);

        return left;
    }

    // Replace every node's data with sum of nodes which are equal to or greater than node's data. Include the node itself in it's sum.
    public static void replace_node_data_with_larger_nodes(BinaryTreeNode<Integer> root)
    {
        replace_node_data_with_larger_nodes(root, 0);
    }

    // find the path of a node from the node to the root of the binary tree. Return it in the form of an array list
    public static ArrayList<BinaryTreeNode<Integer>> node_to_root(BinaryTreeNode<Integer> root, int x)
    {
        if(root == null)
            return null;

        ArrayList<BinaryTreeNode<Integer>> res;
        if(root.data == x)
            res = new ArrayList<>();
        else
        {
            res = node_to_root(root.left, x);
            if(res == null)
                res = node_to_root(root.right, x);
        }
        if(res != null)
            res.add(root);
        return res;
    }

    // find the path of a node from the node to the root of the binary tree. Return it in the form of an array list
    public static ArrayList<BinaryTreeNode<Integer>> node_to_root_in_bst(BinaryTreeNode<Integer> root, int x)
    {
        ArrayList<BinaryTreeNode<Integer>> res;
        if(root == null)
        {
            res = new ArrayList<>();
            return res;
        }
        if(root.data == x)
        {
            res = new ArrayList<>();
            res.add(root);
            return res;
        }

        ArrayList<BinaryTreeNode<Integer>> small_res;
        if(root.data > x)
            small_res = node_to_root_in_bst(root.left, x);
        else
            small_res = node_to_root_in_bst(root.right, x);
        
        if(small_res.size() != 0)
            small_res.add(root);
        return small_res;
    }

    private static void level_wise_linked_lists(BinaryTreeNode<Integer> root, Node[] res, int depth)
    {
        if(root == null)
            return;
        Node node = new Node(root.data);
        node.next = res[depth];
        res[depth] = node;
        level_wise_linked_lists(root.right, res, depth + 1);
        level_wise_linked_lists(root.left, res, depth + 1);
    }

    // Make linked lists for nodes on same level and return the result as an array of linked lists.
    public static Node[] level_wise_linked_lists(BinaryTreeNode<Integer> root)
    {
        int h = binary_trees.height(root);
        Node[] res = new Node[h];
        level_wise_linked_lists(root, res, 0);
        return res;
    }

    public static Misc highestBST_helper(BinaryTreeNode<Integer> root)
    {
        if(root == null)
        {
            Misc res = new Misc();
            res.isBST = true;
            res.min = Integer.MAX_VALUE;
            res.max = Integer.MIN_VALUE;
            res.height = 0;
            return res;
        }
        
        Misc left = highestBST_helper(root.left);
        Misc right = highestBST_helper(root.right);
        Misc res = new Misc();

        if(left.isBST && right.isBST && (left.max < root.data) && (root.data <= right.min))
        {
            res.max = Math.max(root.data, right.max);
            res.min = Math.min(root.data, left.min);
            res.isBST = true;
            res.height = 1 + Math.max(left.height, right.height);
            System.out.println("Node:" + root.data + ",  is bst:" + res.isBST + ", height:" + res.height + ", min:" + res.min + ", max:" + res.max);
            return res;
        }

        res.isBST = false;
        res.height = Math.max(left.height, right.height);
        return res;
    }

    // Returns the height of the largest BST present in a Binary Tree.
    public static int highestBST(BinaryTreeNode<Integer> root)
    {
        return highestBST_helper(root).height;
    }

    private static void push_min(BinaryTreeNode<Integer> root, Stack<BinaryTreeNode<Integer>> st)
    {
        if(root == null)
            return;
        st.push(root);
        push_min(root.left, st);
    }

    private static void push_max(BinaryTreeNode<Integer> root, Stack<BinaryTreeNode<Integer>> st)
    {
        if(root == null)
            return;
        st.push(root);
        push_max(root.right, st);
    }

    // Print all the pairs that are present in the BST that sum upto K. Space Complexity - O(log n). Time Complexity - O(n). An equivalent thing of two pointer method on sorted array is done on BST using stacks.
    public static void print_pairs(BinaryTreeNode<Integer> root, int k)
    {
        if(root == null)
            return;
        Stack<BinaryTreeNode<Integer>> left = new Stack<>();
        Stack<BinaryTreeNode<Integer>> right = new Stack<>();
        push_min(root, left);
        push_max(root, right);
        BinaryTreeNode<Integer> leftnode = left.peek(), rightnode = right.peek();
        while(!left.empty()  && !right.empty() && leftnode.data < rightnode.data)
        {
            if(leftnode.data + rightnode.data == k)
            {
                System.out.println(leftnode.data + " " + rightnode.data);
                left.pop();
                push_min(leftnode.right, left);
            }
            else if(leftnode.data + rightnode.data > k)
            {
                right.pop();
                push_max(rightnode.left, right);
            }
            else
            {
                left.pop();
                push_min(leftnode.right, left);
            }
            
            leftnode = left.peek();
            rightnode = right.peek();
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = binary_trees.take_tree_input_level_wise();
        int k = s.nextInt();
        print_pairs(root, k);
    }
}

class BST
{
    private BinaryTreeNode<Integer> root;
    private int size;

    public BST()
    {
        root = null;
        size = 0;
    }

    // Time complexity - O(h)
    private static BinaryTreeNode<Integer> insert_helper(BinaryTreeNode<Integer> root, int elem)
    {
        if(root == null)
            return new BinaryTreeNode<>(elem);
        if(elem < root.data)
            root.left = insert_helper(root.left, elem);
        else
            root.right = insert_helper(root.right, elem);
        return root;
    }

    public void insert(int elem)
    {
        this.root = insert_helper(root, elem);
        size++;
    }

    // Time complexity - O(h)
    private static BinaryTreeNode<Integer> find_min_node(BinaryTreeNode<Integer> root)
    {
        if(root == null || root.left == null)
            return root;
        return find_min_node(root.left);
    }

    // Time complexity - O(h)
    private static BinaryTreeNode<Integer> delete(BinaryTreeNode<Integer> root, int elem)
    {
        if(root == null)
            return null;
        if(root.data == elem)
        {
            if(root.left == null && root.right == null)
                return null;
            else if(root.left != null && root.right == null)
                return root.left;
            else if(root.left == null && root.right != null)
                return root.right;
            else
            {
                BinaryTreeNode<Integer> replacement = find_min_node(root.right);
                root.data = replacement.data;
                root.right = delete(root.right, replacement.data);
                return root;
            }
        }
        else if(elem < root.data)
            root.left = delete(root.left, elem);
        else
            root.right = delete(root.right, elem);
        return root;
    }

    public boolean delete(int elem)
    {
        if(!contains(elem))
            return false;
        size--;
        this.root = delete(this.root, elem);
        return true;
    }

    public void print()
    {
        binary_trees.print_tree(root);
    }

    // Time complexity - O(h)
    public boolean contains(int elem)
    {
        return binary_search_tree.search(root, elem);
    }

    public int size()
    {
        return size;
    }

}

class NodePair
{
    Node head, tail;
}

class Misc
{
    int height;
    boolean isBST;
    int min;
    int max;
    Node node;
    int direction;
    int start, end;
    int cellMaxSquare, matMaxSquare;
}