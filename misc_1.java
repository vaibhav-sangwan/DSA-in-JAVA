import java.util.Scanner;
import java.util.Arrays;;

public class misc_1 {

    static Scanner s = new Scanner(System.in);

    public static int[][] take_input_2d()
    {
        int m  = s.nextInt(), n = s.nextInt();
        int[][] arr = new int[m][n];
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
             arr[i][j] = s.nextInt();
        }
        return arr;
    }

    public static void print_pattern(int[][] arr)
    {
        int m = arr.length;
        if(m == 0)
            return;
        int n = arr[0].length;
        for(int i = 0; i < m; i++)
        {
            for(int k = 0; k < m - i; k++)
            {
                for(int j = 0; j < n; j++)
                    System.out.print(arr[i][j] + " ");
                System.out.println();
            }
        }
    }

    public static int[] take_input()
    {
        int n = s.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++)
            arr[i] = s.nextInt();
        return arr;
    }

    public static int max_profit(int[] arr)
    {
        Arrays.sort(arr);
        int res = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length; i++)
        {
            res = Math.max(res, arr[i] * (arr.length - i));
        }
        return res;
    }

    public static int count_min(int n)
    {
        if(n <= 1)
            return n;
        int i_sq;
        int res = n;
        for(int i = 1; i <= Math.sqrt(n); i++)
        {
            i_sq = i * i;
            if(n - i_sq >= 0)
                res = Math.min(res, 1 + count_min(n - i_sq));
            else
                break;
        }
        return res;
    }

    public static String[] strings_of_length_k(String str, int k)
    {
        if(k == 0)
            return new String[]{""};
        String[] res_n_1 = strings_of_length_k(str, k - 1);
        String[] res = new String[res_n_1.length * str.length()];
        int z = 0;
        for(int i = 0; i < str.length(); i++)
        {
            for(int j = 0; j < res_n_1.length; j++, z++)
            {
                res[z] = str.charAt(i) + res_n_1[j];
            }
        }
        return res;
    }

    public static boolean sum_exists(int[] arr, int index, int sum)
    {
        if(index >= arr.length)
            return false;
        if(sum == 0)
            return true;
        return sum_exists(arr, index + 1, sum) || sum_exists(arr, index + 1, sum - arr[index]);
    }

    public static boolean split_array(int[] arr)
    {
        int sum_5 = 0, sum_3 = 0, total_sum = 0;
        for(int i = 0; i < arr.length; i++)
        {
            total_sum += arr[i];
            if(arr[i] % 5 == 0)
            {
                sum_5 += arr[i];
                arr[i] = 0;
            }
            else if(arr[i] % 3 == 0)
            {
                sum_3 += arr[i];
                arr[i] = 0;
            }   
        }
        if(total_sum % 2 != 0)
            return false;
        return sum_exists(arr, 0, (total_sum/2) - Math.min(sum_3, sum_5));
    }

    public static void main(String[] args) {
        int[] arr = take_input();
        System.out.println(split_array(arr));
        s.close();
    }
}
