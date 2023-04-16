import java.util.Scanner;
import java.util.Arrays;

public class arrays
{
    public static int find_arr_sum(int[] arr)
    {
        int n = arr.length;
        int res = 0;
        for(int i = 0; i < n; i++)
            res += arr[i];
        return res;
    }

    public static int find_index(int[] arr, int x)
    {
        int n = arr.length;
        for(int i = 0; i < n; i++)

        {
            if(arr[i] == x)
                return i;
        }
        return -1;
    }

    public static int[] populate_array(int n)
    {
        int[] arr = new int[n];
        int x = 1, i = 0;
        for(;;i++, x += 2)
        {
            if(x > n)
                break;
            arr[i] = x;
        }
        x--;
        if(n % 2 != 0)
            x -= 2;
        for(;;i++, x-= 2)
        {
            if(x <= 0)
                break;
            arr[i] = x;
        }
        display_arr(arr);
        return arr;
    }

    public static void display_arr(int[] arr)
    {
        int n = arr.length;
        for(int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void print_pairs(int[] arr)
    {
        int n = arr.length;
        for(int i = 0; i < n; i++)
        {
            for(int j = i + 1; j < n; j++)
            {
                System.out.println("(" + arr[i] + ", " + arr[j] + ")");
            }
        }
    }

    public static int[] take_input(Scanner s)
    {
        int n = s.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++)
        {
            arr[i] = s.nextInt();
        }
        return arr;
    }

    public static String swap_alternate(int[] arr)
    {
        String res = "";
        int n = arr.length, temp;
        for(int i = 0; i < n - 1; i += 2)
        {
            temp = arr[i];
            arr[i] = arr[i + 1];
            arr[i + 1] = temp;
        }
        for(int i = 0; i < n; i++)
        {
            res += arr[i] + " ";
        }
        return res;
    }

    public static int find_unique_element(int[] arr)
    {
        int n = arr.length;
        Arrays.sort(arr);
        for(int i = 0; i < n - 1; i += 2)
        {
            if(arr[i] != arr[i + 1])
                return arr[i];
        }
        return arr[n - 1];

    }

    public static int find_duplicate_element(int[] arr)
    {
        int n = arr.length;
        for(int i = 0; i < n; i++)
        {
            for(int j = i + 1; j < n; j++)
            {
                if(arr[i] == arr[j])
                    return arr[i];
            }
        }
        return arr[0];
    }

    public static String find_intersection(int[] arr1, int[] arr2)
    {
        String res = "";
        int n = arr1.length, m = arr2.length;
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < m; j++)
            {
                if(arr1[i] == arr2[j])
                {
                    arr2[j] = Integer.MIN_VALUE;
                    res += arr1[i] + " ";
                    break;
                }
            }
        }
        return res;
    }

    public static int pair_sum(int[] arr, int x)
    {
        int n = arr.length, res = 0;
        for(int i = 0; i < n; i++)
        {
            for(int j = i + 1; j < n; j++)
            {
                if(arr[i] + arr[j] == x)
                    res++;
            }
        }
        return res;
    }

    public static int triplet_sum(int[] arr, int x)
    {
        int n = arr.length, res = 0;
        for(int i = 0; i < n; i++)
        {
            for(int j = i + 1; j < n; j++)
            {
                for(int k = j + 1; k < n; k++)
                {
                    if(arr[i] + arr[j] + arr[k] == x)
                        res++;
                }
            }
        }
        return res;
    }

    public static String sort_0_1(int[] arr)
    {
        String res = "";
        int n = arr.length, index = 0;
        for(int i = 0; i < n; i++)
        {
            if(arr[i] == 0)
            {
                arr[i] = 1;
                arr[index] = 0;
                index++;
            }
        }
        for(int i = 0; i < n; i++)
        {
            res += arr[i] + " ";
        }
        return res;
    }

    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        String[] res = new String[t];
        for(int i = 0; i < t; i++)
        {
            int[] arr1 = take_input(s);
            res[i] = sort_0_1(arr1);
        }
        for(int i = 0; i < t; i++)
        {
            System.out.println(res[i]);
        }
        s.close();
    }
}