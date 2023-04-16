import java.util.Scanner;

public class recursion_3 {
    static Scanner s = new Scanner(System.in);

    public static String[] get_subsequences(String str)
    {
        if(str.length() == 0)
            return new String[]{""};
        String[] res_n_1 = get_subsequences(str.substring(1));
        int n = res_n_1.length;
        String[] res = new String[n * 2];
        for(int i = 0; i < res_n_1.length; i++)
        {
            res[i] = res_n_1[i];
            res[n + i] = str.charAt(0) + res_n_1[i];
        }
        return res;
    }

    public static String[] codes = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static String[] keypad_codes(int x)
    {
        if(x == 0)
            return new String[]{""};
        String[] res_n_1 = keypad_codes(x/10);
        int n = res_n_1.length;
        char[] codes_for_last_digit = codes[x%10].toCharArray();
        int m = codes_for_last_digit.length;
        String[] res = new String[m * n];
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                res[(i * n) + j] = res_n_1[j] + codes_for_last_digit[i];
            }
        }
        return res;
    }

    public static void print_subsequences(String str, String output)
    {
        if(str.length() == 0)
        {
            System.out.println(output);
            return;
        }
        print_subsequences(str.substring(1), output);
        print_subsequences(str.substring(1), output + str.charAt(0));
    }

    public static void print_subsequences(String str)
    {
        print_subsequences(str, "");
    }

    public static void print_keypad(int x, String output)
    {
        if(x == 0)
            System.out.println(output);
        char[] codes_for_first_digit = codes[x%10].toCharArray();
        for(char c : codes_for_first_digit)
        {
            print_keypad(x/10, c + output);
        }
    }

    public static void print_keypad(int x)
    {
        print_keypad(x, "");
    }

    public static boolean check_ab(String str)
    {
        if(str.length() == 0)
            return true;
        else if(str.charAt(0) == 'a')
            return check_ab(str.substring(1));
        else if(str.length() >= 2 && str.substring(0, 2).equals("bb"))
            return check_ab(str.substring(2));
        return false;  
    }

    public static int staircase(int n)
    {
        if(n < 0)
            return 0;
        if(n == 0)
            return 1;
        return staircase(n - 1) + staircase(n - 2) + staircase(n - 3);
    }

    
    public static int binary_search(int[] arr, int x, int start, int end)
    {
        if(end < start) return -1;
        int mid = (start + end)/2;
        if(arr[mid] == x) return mid;
        else if(arr[mid] < x) return binary_search(arr, x, mid + 1, end);
        return binary_search(arr, x, start, mid - 1);
    }

    public static int binary_search(int[] arr, int x)
    {
        return binary_search(arr, x, 0, arr.length - 1);
    }

    public static int[] take_input()
    {
        int n = s.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++)
            arr[i] = s.nextInt();
        return arr;
    }

    public static int[][] subsets(int[] arr, int start)
    {
        if(start >= arr.length)
        {
            int[][] res = new int[1][0];
            return res;
        }
        int[][] res_n_1 = subsets(arr, start + 1);
        int n = res_n_1.length;
        int[][] res = new int[n * 2][];
        for(int i = 0; i < n; i++)
        {
            res[i] = new int[res_n_1[i].length];
            res[i + n] = new int[res_n_1[i].length + 1];
            for(int j = 0; j < res_n_1[i].length; j++)
            {
                res[i][j] = res_n_1[i][j];
                res[n + i][j + 1] = res_n_1[i][j];
            }
            res[n + i][0] = arr[start];
        }
        return res;
    }

    public static int[][] subsets(int[] arr)
    {
        return subsets(arr, 0);
    }

    public static void print_subsets(int[] arr, int start, String output)
    {
        if(start >= arr.length)
        {
            System.out.println(output);
            return;
        }
        print_subsets(arr, start + 1, output);
        print_subsets(arr, start + 1, output + arr[start] + " ");
    }

    public static void print_subsets(int[] arr)
    {
        print_subsets(arr, 0, "");
    }

    public static int[][] subset_sum_to_k(int[] arr, int start, int sum)
    {
        if(sum == 0)
            return new int[1][0];
        if(start >= arr.length)
            return new int[0][0];
        int[][] res_n_1 = subset_sum_to_k(arr, start + 1, sum - arr[start]);
        int[][] res_n_2 = subset_sum_to_k(arr, start + 1, sum);
        int n = res_n_1.length;
        int m = res_n_2.length;
        int[][] res = new int[m + n][];
        int k = 0;
        for(int i = 0; i < m; i++, k++)
            res[k] = res_n_2[i];
        for(int i = 0; i < n; i++, k++)
        {
            res[k] = new int[res_n_1[i].length + 1];
            res[k][0] = arr[start];
            for(int j = 0; j < res_n_1[i].length; j++)
                res[k][j + 1] = res_n_1[i][j];
        }
        return res;
    }

    public static int[][] subset_sum_to_k(int[] arr, int sum)
    {
        return subset_sum_to_k(arr, 0, sum);
    }

    public static void print_subset_sum_to_k(int[] arr, int start, int sum, String output)
    {
        if(sum == 0)
        {
            System.out.println(output);
            return;
        }
        if(start >= arr.length)
            return;
        print_subset_sum_to_k(arr, start + 1, sum, output);
        print_subset_sum_to_k(arr, start + 1, sum - arr[start], output + arr[start] + " ");
    }

    public static void print_subset_sum_to_k(int[] arr, int sum)
    {
        print_subset_sum_to_k(arr, 0, sum, "");
    }

    public static String[] codes(String str)
    {
        if(str.length() == 0)
            return new String[]{""};
        if(str.length() == 1)
            return new String[]{(char)(Integer.valueOf(str) + 96) + ""};
        int one_digit_num = Integer.valueOf(str.substring(0, 1));
        int two_digit_num = Integer.valueOf(str.substring(0, 2));
        String[] res_n_1 = codes(str.substring(1));
        String[] res_n_2 = codes(str.substring(2));
        String[] res;
        int i = 0;
        if(two_digit_num <= 26)
        {
            res = new String[res_n_1.length + res_n_2.length];
            for(; i < res_n_2.length; i++)
            {
                res[i] = (char)(two_digit_num + 96) + res_n_2[i];
            }
        }
        else
            res = new String[res_n_1.length];
        for(int k = 0; i < res.length; i++, k++)
        {
            res[i] = (char)(one_digit_num + 96) + res_n_1[k];
        }
        return res; 
    }

    public static char get_char(int n)
    {
        return (char)(n + 'a' - 1);
    }     

    public static void print_codes(String str, String output)
    {
        if(str.length() == 0)
        {
            System.out.println(output);
            return;
        }
        int first_digit_num = str.charAt(0) - '0';
        print_codes(str.substring(1), output + get_char(first_digit_num));
        if(str.length() >= 2)
        {
            int two_digit_num = (str.charAt(0) - '0') * 10 + (str.charAt(1) - '0');
            if(two_digit_num <= 26)
                print_codes(str.substring(2), output + get_char(two_digit_num));
        }
    }

    public static void print_codes(String str)
    {
        print_codes(str, "");
    }

    public static String[] permutations(String str)
    {
        if(str.length() == 0)
            return new String[]{""};
        String[] res_n_1 = permutations(str.substring(1));
        String[] res = new String[str.length() * res_n_1.length];
        int k = 0;
        for(int i = 0; i < str.length(); i++)
        {
            for(int j = 0; j < res_n_1.length; j++, k++)
            {
                res[k] = res_n_1[j].substring(0, i) + str.charAt(0) + res_n_1[j].substring(i);
            }
        }
        return res;
    }
    
    public static void print_permutations(String str, String output)
    {
        if(str.length() == 0)
        {
            System.out.println(output);
            return;
        }
        for(int i = 0; i < output.length() + 1; i++)
            print_permutations(str.substring(1), output.substring(0, i) + str.charAt(0) + output.substring(i));
    }

    public static void print_permutations(String str)
    {
        print_permutations(str, "");
    }

    public static void main(String[] args)
    {
        String str = s.nextLine();
        print_permutations(str);
        s.close();
    }
}
