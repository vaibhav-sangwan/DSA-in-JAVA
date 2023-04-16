import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class binary_trees {

    public static Scanner s = new Scanner(System.in);

    public static void print_tree(BinaryTreeNode<Integer> root)
    {
        if(root == null)
            return;
        System.out.print(root.data + ":");
        if(root.left != null)
            System.out.print("L-" + root.left.data);
        if(root.right != null)
            System.out.print(", R-" + root.right.data);
        System.out.println();
        print_tree(root.left);
        print_tree(root.right);
    }

    // Depth First Input - take input for root node, then recursively for left child of root node and then for right child of root.
    public static BinaryTreeNode<Integer> take_tree_input()
    {
        int data = s.nextInt();
        if(data == -1)
            return null;
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(data);
        root.left = take_tree_input();
        root.right = take_tree_input();
        return root;
    }

    //Depth-first input but more user friendly.
    public static BinaryTreeNode<Integer> take_tree_input_mod()
    {
        int data = s.nextInt();
        if(data == -1)
            return null;
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(data);
        System.out.print("Enter left child of " + data + ":");
        root.left = take_tree_input_mod();
        System.out.print("Enter right child of " + data + ":");
        root.right = take_tree_input_mod();
        return root;
    }

    //Level-wise Input - take input of the binary tree level wise(Breadth-wise).
    public static BinaryTreeNode<Integer> take_tree_input_level_wise()
    {
        Queue<BinaryTreeNode<Integer>> q = new LinkedList<>();
        int data = s.nextInt();
        BinaryTreeNode<Integer> root = null;
        if(data != -1)
            root = new BinaryTreeNode<>(data);
        q.add(root);
        while(!q.isEmpty())
        {
            BinaryTreeNode<Integer> curr = q.remove();
            if(curr == null)
                continue;
            int left_data = s.nextInt(), right_data = s.nextInt();
            if(left_data != -1)
                curr.left = new BinaryTreeNode<>(left_data);
            if(right_data != -1)
                curr.right = new BinaryTreeNode<>(right_data);
            q.add(curr.left);
            q.add(curr.right);      
        }
        return root;
    }

    //Returns the total number of nodes present in the tree.
    public static int number_of_nodes(BinaryTreeNode<Integer> root)
    {
        if(root == null)
            return 0;
        return 1 + number_of_nodes(root.left) + number_of_nodes(root.right);
    }

    // Returns the sum of all the nodes present in the binary tree.
    public static int sum_of_nodes(BinaryTreeNode<Integer> root)
    {
        if(root == null)
            return 0;
        return root.data + sum_of_nodes(root.left) + sum_of_nodes(root.right);
    }

    // Returns true if the tree contains a node with value x. False otherwise.
    public static boolean contains(BinaryTreeNode<Integer> root, int x)
    {
        if(root == null)
            return false;
        if(root.data == x)
            return true;
        return contains(root.left, x) || contains(root.right, x);
    }

    //Pre Order Traversal
    public static void preorder(BinaryTreeNode<Integer> root)
    {
        if(root == null)
            return;
        System.out.println(root.data);
        preorder(root.left);
        preorder(root.right);
    }

    //Post Order Traversal
    public static void postorder(BinaryTreeNode<Integer> root)
    {
        if(root == null)
            return;
        postorder(root.left);
        postorder(root.right);
        System.out.println(root.data);
    }

    //In Order Traversal
    public static void inorder(BinaryTreeNode<Integer> root)
    {
        if(root == null)
            return;
        inorder(root.left);
        System.out.println(root.data);
        inorder(root.right);
    }

    //find maximum number present in the binary tree
    public static int find_max(BinaryTreeNode<Integer> root)
    {
        if(root == null)
            return -1;
        return Math.max(Math.max(root.data, find_max(root.left)), find_max(root.right));
    }

    // Returns the number of nodes which have a greater vakue than x
    public static int nodes_greater_than_k(BinaryTreeNode<Integer> root, int x)
    {
        if(root == null)
            return 0;
        int left_res = nodes_greater_than_k(root.left, x), right_res = nodes_greater_than_k(root.right, x);
        if(root.data > x)
            return left_res + right_res + 1;
        return left_res + right_res;
    }

    // Finding height of a binary tree. NO Node? Height = 0. 1 Node? Height = 1.
    public static int height(BinaryTreeNode<Integer> root)
    {
        if(root == null)
            return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }

    // returns the number of leaf nodes.
    public static int num_of_leaf_nodes(BinaryTreeNode<Integer> root)
    {
        if(root == null)
            return 0;
        if(root.left == null && root.right == null)
            return 1;
        return num_of_leaf_nodes(root.left) + num_of_leaf_nodes(root.right);
    }

    public static void replace_nodes_with_depth(BinaryTreeNode<Integer> root, int depth)
    {
        if(root == null)
            return;
        root.data = depth;
        replace_nodes_with_depth(root.left, depth + 1);
        replace_nodes_with_depth(root.right, depth + 1);
    }

    // Replace node values with their respective depths. Root Node Depth = 0.
    public static void replace_nodes_with_depth(BinaryTreeNode<Integer> root)
    {
        replace_nodes_with_depth(root, 0);
    }

    // prints the nodes without siblings
    public static void nodes_without_siblings(BinaryTreeNode<Integer> root)
    {
        if(root == null)
            return;
        if(root.left != null && root.right == null)
            System.out.print(root.left.data + " ");
        else if(root.left == null && root.right != null)
            System.out.print(root.right.data + " ");
 
        nodes_without_siblings(root.left);
        nodes_without_siblings(root.right);
    }

    // Remove all the leaf nodes of a tree
    public static BinaryTreeNode<Integer> remove_leaf_nodes(BinaryTreeNode<Integer> node)
    {
        if(node == null || (node.left == null && node.right == null))
            return null;
        node.left = remove_leaf_nodes(node.left);
        node.right = remove_leaf_nodes(node.right);
        return node;
    }

    // Mirror the binary tree. 
    public static BinaryTreeNode<Integer> mirror_tree(BinaryTreeNode<Integer> node)
    {
        if(node == null)
            return null;
        BinaryTreeNode<Integer> temp = node.left;
        node.left = mirror_tree(node.right);
        node.right = mirror_tree(temp);
        return node;
    }

    // Returns true if tree is balanced. False otherwise (Time complexity = O(n^2))
    public static boolean is_balanced(BinaryTreeNode<Integer> node)
    {
        if(node == null)
            return true;
        if(!is_balanced(node.left) || !is_balanced(node.right))
            return false;
        return ((Math.abs(height(node.left) - height(node.right))) <= 1);
    }

    private static tree_info is_balanced_improved_helper(BinaryTreeNode<Integer> node)
    {
        tree_info node_info = new tree_info();
        if(node == null)
        {
            node_info.is_balanced = true;
            return node_info;
        }
        tree_info left_info = is_balanced_improved_helper(node.left);
        tree_info right_info = is_balanced_improved_helper(node.right);
        if(!(left_info.is_balanced) || !(right_info.is_balanced))
            return node_info;
        node_info.height = 1 + Math.max(left_info.height, right_info.height);
        node_info.is_balanced = (Math.abs(left_info.height - right_info.height) <= 1);
        return node_info;
    }

    // Returns true if tree is balanced. False otherwise (Time complexity = O(n))
    public static boolean is_balanced_improved(BinaryTreeNode<Integer> node)
    {
        return is_balanced_improved_helper(node).is_balanced;
    }

    private static tree_info find_diameter_helper(BinaryTreeNode<Integer> node)
    {
        tree_info curr = new tree_info();
        if(node == null)
            return curr;
        
        tree_info left = find_diameter_helper(node.left);
        tree_info right = find_diameter_helper(node.right);

        curr.height = 1 + Math.max(left.height, right.height);
        curr.diameter = Math.max(1 + left.height + right.height, Math.max(left.diameter, right.diameter));
        return curr;
    }

    // To find the maximum distance between two leaf nodes. Distance here means the number of nodes along the path from A to B.
    public static int find_diameter(BinaryTreeNode<Integer> node)
    {
        tree_info t = find_diameter_helper(node);
        return t.diameter;
    }

    // To print the tree in a level wise fashion.
    public static void print_tree_level_wise(BinaryTreeNode<Integer> root)
    {
        Queue<BinaryTreeNode<Integer>> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty())
        {
            BinaryTreeNode<Integer> node = q.remove();
            if(node == null)
                continue;
            q.add(node.left);
            q.add(node.right);
            System.out.println(node.data + ":L:" + ((node.left == null)?-1:node.left.data) + ",R:" + ((node.right == null)?-1:node.right.data));
        }
    }

    private static BinaryTreeNode<Integer> build_using_pre_and_in(int[] preorder, int[] inorder, int pre_start, int in_start, int in_end)
    {
        BinaryTreeNode<Integer> node;
        if(in_end < in_start || pre_start >= preorder.length)
            return null;

        node = new BinaryTreeNode<>(preorder[pre_start]);
        int i = in_start;

        for(; i <= in_end; i++)
        {
            if(inorder[i] == preorder[pre_start])
                break;
        }

        node.left = build_using_pre_and_in(preorder, inorder, pre_start + 1, in_start, i - 1);
        node.right = build_using_pre_and_in(preorder, inorder, pre_start + i - in_start + 1, i + 1, in_end);
        return node;
    }

    // Build a tree using inorder and preorder traversals of the binary tree. All elements are unique.
    public static BinaryTreeNode<Integer> build_using_pre_and_in(int[] preorder, int[] inorder)
    {
        return build_using_pre_and_in(preorder, inorder, 0, 0, preorder.length - 1);
    }

    private static BinaryTreeNode<Integer> build_using_post_and_in(int[] postorder, int postStart, int postEnd, int[] inorder, int inStart, int inEnd)
    {
        if((postStart > postEnd) || (inStart > inEnd))
            return null;
        int rootVal = postorder[postEnd], i = inStart;
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(rootVal);
        for(; i <= inEnd; i++)
        {
            if(inorder[i] == rootVal)
                break;
        }
        root.left = build_using_post_and_in(postorder, postStart, postStart + i - inStart - 1, inorder, inStart, i - 1);
        root.right = build_using_post_and_in(postorder, postStart + i - inStart, postEnd - 1, inorder, i + 1, inEnd);
        return root;
    }
    
    // Build a tree using inorder and postorder traversals of the binary tree. All elements are unique.
    public static BinaryTreeNode<Integer> build_using_post_and_in(int[] postorder, int[] inorder)
    {
        return build_using_post_and_in(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1);
    }

    // Duplicate nodes and attach the duplicated node to the left of itself.
    public static void duplicate(BinaryTreeNode<Integer> node)
    {
        if(node == null)
            return;
        duplicate(node.left);
        duplicate(node.right);
        BinaryTreeNode<Integer> duplicated = new BinaryTreeNode<>(node.data);
        duplicated.left = node.left;
        node.left = duplicated;
    }

    // Finding min and max value present inside a binary tree.
    public static tree_info find_min_and_max(BinaryTreeNode<Integer> root)
    {
        tree_info t = new tree_info();
        t.min = Integer.MAX_VALUE;
        t.max = Integer.MIN_VALUE;
        if(root == null)
            return t;
        tree_info tLeft = find_min_and_max(root.left);
        tree_info tRight = find_min_and_max(root.right);
        t.min = Math.min(Math.min(tLeft.min, tRight.min), root.data);
        t.max = Math.max(Math.max(tLeft.max, tRight.max), root.data);
        return t;
    }

    private static void print_paths(BinaryTreeNode<Integer> root, int k, String res)
    {
        if(root == null)
            return;
        if(root.data == k && root.left == null && root.right == null)
            System.out.println(res + k);
        print_paths(root.left, k-root.data, res + root.data + " ");
        print_paths(root.right, k-root.data, res + root.data + " ");
    }

    // Print all root to leaf paths whose sum is equal to K.
    public static void print_paths(BinaryTreeNode<Integer> root, int k)
    {
        print_paths(root, k, "");
    }

    private static void print_at_depth(BinaryTreeNode<Integer> root, int k)
    {
        if(root == null || k < 0)
            return;
        if(k == 0)
        {
            System.out.println(root.data);
            return;
        }
        print_at_depth(root.left, k - 1);
        print_at_depth(root.right, k - 1);
    }

    private static int print_at_distance_k_helper(BinaryTreeNode<Integer> root, int target, int k)
    {
        if(root == null)
            return -1;
        if(root.data == target)
        {
            print_at_depth(root, k);
            return 0;
        }

        int left = print_at_distance_k_helper(root.left, target, k);
        int right = print_at_distance_k_helper(root.right, target, k);
        int res = -1;

        if(left != -1)
        {
            res = left + 1;
            if(res == k)
                System.out.println(root.data);
            print_at_depth(root.right, k-res-1);
        }
        else if(right != -1)
        {
            res = right + 1;
            if(res == k)
                System.out.println(root.data);
            print_at_depth(root.left, k-res-1);
        }

        return res;
    }

    // Print nodes at distance K from a given target node
    public static void print_at_distance_k(BinaryTreeNode<Integer> root, int target, int k)
    {
        print_at_distance_k_helper(root, target, k);
    }

    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = take_tree_input_level_wise();
        int target = s.nextInt();
        int k = s.nextInt();
        print_at_distance_k(root, target, k);
    }
    
}

class BinaryTreeNode<T> {
    T data;
    BinaryTreeNode<T> left, right;

    BinaryTreeNode()
    {
    }

    BinaryTreeNode(T data)
    {
        this.data = data;
    }
}

class tree_info
{
    int height;
    boolean is_balanced; //used for check balance problem
    int diameter;
    int min;
    int max;
    boolean is_bst;
}