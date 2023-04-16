import java.util.Scanner;

public class recursion_1 {

    public static Scanner s = new Scanner(System.in);

    public static int calculate_power(int x, int n)
    {
        if(n == 0)
            return 1;
        return x * calculate_power(x, n - 1);
    }

    public static void print_numbers(int n)
    {
        if(n == 0)
            return;
        print_numbers(n - 1);
        System.out.print(n + " ");
    }

    public static int count_no_of_digits(int n)
    {
        if(n == 0)
            return 0;
        return 1 + count_no_of_digits(n/10);
    }

    public static int[] take_input()
    {
        int n = s.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++)
            arr[i] = s.nextInt();
        return arr;
    }

    public static int array_sum(int[] arr)
    {
        int n = arr.length;
        if(n == 1)
            return arr[0];
        int[] small_arr = new int[n - 1];
        for(int i = 0; i < n - 1; i++)
            small_arr[i] = arr[i];
        return array_sum(small_arr) + arr[n - 1];
    }

    public static boolean contains(int[] arr, int start_index, int x)
    {
        if(start_index == arr.length)
            return false;
        if(arr[start_index] == x)
            return true;
        boolean ans = contains(arr, start_index + 1, x);
        return ans;
    }

    public static boolean contains(int[] arr, int x)
    {
        return contains(arr, 0, x);
    }

    public static int find_index(int[] arr, int x, int start_index)
    {
        if(start_index >= arr.length)
            return -1;
        if(arr[start_index] == x)
            return start_index;
        return find_index(arr, x, start_index + 1);
    }

    public static int find_index(int[] arr, int x)
    {
        return find_index(arr, x, 0);
    }

    public static int find_last_index(int[] arr, int x, int start_index)
    {
        if(start_index >= arr.length)
            return -1;
        int res_n_1 = find_last_index(arr, x, start_index + 1);
        if(arr[start_index] == x)
            return Math.max(start_index, res_n_1);
        return res_n_1;
    }

    public static int find_last_index(int[] arr, int x)
    {
        return find_last_index(arr, x, 0);
    }
    
    public static int[] all_indices_of_number(int[] arr, int x, int start_index)
    {
        if(start_index == arr.length)
            return new int[0];
        int[] res_n_1 = all_indices_of_number(arr, x, start_index + 1);
        if(arr[start_index] == x)
        {
            int[] res_n = new int[res_n_1.length + 1];
            res_n[0] = start_index;
            for(int i = 0; i < res_n_1.length; i++)
                res_n[i + 1] = res_n_1[i];
            return res_n;
        }
        return res_n_1;
    }

    public static int[] all_indices_of_number(int[] arr, int x)
    {
        return all_indices_of_number(arr, x, 0);
    }

    public static void display_arr(int[] arr)
    {
        for(int x: arr)
            System.out.print(x + " ");
    }

    public static int multiply(int m, int n)
    {
        if(m == 0 || n == 0)
            return 0;
        return m + multiply(m, n - 1);
    }

    public static int num_of_zeros(int x)
    {
        if(x == 0)
            return 0;
        int mod_10 = x % 10;
        if(mod_10 == 0)
            return 1 + num_of_zeros(x / 10);
        else
            return num_of_zeros(x / 10);
    }

    public static double geometric_sum(int k)
    {
        if(k == 0)
            return 1;
        return geometric_sum(k -1) + (1/Math.pow(2, k));
    }

    public static boolean check_plaindrome(String str, int start_index, int end_index)
    {
        if(end_index <= start_index)
            return true;
        return ((str.charAt(start_index) == str.charAt(end_index)) && check_plaindrome(str, start_index + 1, end_index - 1));
    }

    public static boolean check_plaindrome(String str)
    {
        return check_plaindrome(str, 0, str.length() - 1);
    }

    public static int digit_sum(int x)
    {
        if(x == 0)
            return 0;
        return (x%10) + digit_sum(x/10);
    }

    public static void main(String[] args)
    {
        int x = s.nextInt();
        System.out.println(digit_sum(x));
        s.close();
    }
}
