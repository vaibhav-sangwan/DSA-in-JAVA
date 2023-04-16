import java.util.Scanner;
import java.util.Arrays;

public class arrays_2 {

    public static Scanner s = new Scanner(System.in);

    public static int[] take_input()
    {
        int n = s.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++)
            arr[i] = s.nextInt();
        return arr;
    }

    public static void display_array(int[] arr)
    {
        for(int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static int binary_search(int[] arr, int x)
    {
        int s = 0, e = arr.length - 1, m;
        while(s <= e)
        {
            m = (s + e)/2;
            if(arr[m] < x)
                s = m + 1;
            else if(arr[m] > x)
                e = m - 1;
            else
                return m;
        }
        return -1;
    }

    public static void selection_sort(int[] arr)
    {
        int min, min_index, temp; 
        for(int i = 0; i < arr.length - 1; i++)
        {
            min = arr[i];
            min_index = i;
            for(int j = i + 1; j < arr.length; j++)
            {
                if(arr[j] < min)
                {
                    min_index = j;
                    min = arr[j];
                }
            }
            temp = arr[i];
            arr[i] = min;
            arr[min_index] = temp;
        }
    }

    public static void bubble_sort(int[] arr)
    {
        int temp;
        for(int i = 0; i < arr.length - 1; i++)
        {
            for(int j = 0; j < arr.length - 1 - i; j++)
            {
                if(arr[j] > arr[j + 1])
                {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void insertion_sort(int[] arr)
    {
        int temp;
        for(int i = 1; i < arr.length; i++)
        {
            temp = arr[i];
            for(int j = i - 1; j >= -1; j--)
            {
                if(j == -1 || arr[j] < temp)
                {
                    arr[j + 1] = temp;
                    break;
                }
                arr[j + 1] = arr[j];
            }
        }
    }

    public static int[] merge_sorted_arrays(int[] arr1, int[] arr2)
    {
        int m = arr1.length, n = arr2.length, i = 0, j = 0, k = 0;
        int[] res = new int[m + n];
        while(i < m && j < n)
        {
            if(arr1[i] < arr2[j])
            {
                res[k] = arr1[i];
                i++;
            }
            else
            {
                res[k] = arr2[j];
                j++;
            }
            k++;
        }
        for(; i < m; i++, k++)
            res[k] = arr1[i];
        for(; j < n; j++, k++)
            res[k] = arr2[j];
        return res;
    }

    public static void push_zeros_to_end(int[] arr)
    {
        int first_zero_index = Integer.MAX_VALUE;
        for(int i = 0; i < arr.length; i++)
        {
            if(arr[i] != 0 && i > first_zero_index)
            {
                arr[first_zero_index] = arr[i];
                arr[i] = 0;
                first_zero_index++;
            }
            else if(arr[i] == 0 && i < first_zero_index)
                first_zero_index = i;
        }
    }

    public static void rotate_array(int[] arr, int d)
    {
        int[] temp = new int[d];
        int i = 0, j = 0, n = arr.length;
        for(; i < d; i++)
            temp[i] = arr[i];
        for(i = 0; i < n - d; i++)
            arr[i] = arr[i + d];
        for(; i < n; i++, j++)
            arr[i] = temp[j];
    }

    public static int get_second_max(int[] arr)
    {
        int n = arr.length, max = Integer.MIN_VALUE, second_max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++)
        {
            if(arr[i] > max)
            {
                second_max = max;
                max = arr[i];
            }
            else if(arr[i] > second_max && arr[i] < max)
                second_max = arr[i];
        }
        return second_max;
    }

    public static int check_rotation(int[] arr)
    {
        int index = -1, min = Integer.MAX_VALUE;
        for(int i = 0; i < arr.length; i++)
        {
            if(arr[i] < min)
            {
                index = i;
                min = arr[i];
            }
        }
        return index;
    }

    public static void add_two_arrays(int[] arr1, int[] arr2, int[] res)
    {
        int a, b, carry = 0, m = arr1.length, n = arr2.length, temp_sum;
        for(int i = 0; i < res.length - 1; i++)
        {
            a = ((m - 1 - i >= 0)?arr1[m - 1 - i]:0);
            b = ((n - 1 - i >= 0)?arr2[n - 1 - i]:0);
            temp_sum = a + b + carry;
            res[res.length - 1 - i] = temp_sum % 10;
            carry = temp_sum / 10;
        }
        res[0] = carry;
    }

    public static void sort_0_1_2(int[] arr)
    {
        int i = 0, nz = 0, n = arr.length, nt = n - 1;
        while(i <= nt)
        {
            if(arr[i] == 0)
            {
                arr[i] = arr[nz];
                arr[nz] = 0;
                i++;
                nz++;
            }
            else if(arr[i] == 2)
            {
                arr[i] = arr[nt];
                arr[nt] = 2;
                nt--;
            }
            else
            {
                i++;
            }
        }
    }

    public static String find_intersection(int[] arr1, int[] arr2)
    {
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        String res = "";
        int i = 0, j = 0;
        while(i < arr1.length && j < arr2.length)
        {
            if(arr1[i] == arr2[j])
            {
                res += arr1[i] + " ";
                i++;
                j++;
            }
            else if(arr1[i] < arr2[j])
                i++;
            else j++;
        }
        return res;
    }

    public static int equilibrium_index(int[] arr)
    {
        int total_sum = 0;
        for(int x: arr)
            total_sum += x;
        int left_sum = 0, right_sum = total_sum - arr[0];
        if(left_sum == right_sum) return 0;
        for(int i = 1; i < arr.length; i++)
        {
            left_sum += arr[i - 1];
            right_sum = total_sum - arr[i] - left_sum;
            if(left_sum == right_sum)
                return i;

        }
        return -1;
    }

    public static String find_leaders(int[] arr)
    {
        String res = "";
        int curr_max = Integer.MIN_VALUE;
        for(int i = arr.length - 1; i >= 0; i--)
        {
            if(arr[i] > curr_max)
            {
                res += arr[i] + " ";
                curr_max = arr[i];
            }
        }
        return res;
    }

    public static String minimum_length_word(String str)
    {
        String res = "";
        int min_len = Integer.MAX_VALUE, space_at = -1;
        for(int i = 0; i < str.length(); i++)
        {
            if(str.charAt(i) == ' ')
            {
                if((i - space_at + 1) < min_len)
                {
                    res = str.substring(space_at + 1, i);
                    min_len = i - space_at + 1;
                }
                space_at = i;
            }
        }
        if((str.length() - space_at + 1) < min_len)
            res = str.substring(space_at + 1);
        return res;
    }

    public static int maximise_the_sum(int[] arr1, int[] arr2)
    {
        int res = 0, i = 0, j = 0, arr1_sum = 0, arr2_sum = 0, m = arr1.length, n = arr2.length;
        while(i < m && j < n)
        {
            if(arr1[i] == arr2[j])
            {
                res += Math.max(arr1_sum, arr2_sum) + arr1[i];
                i++;
                j++;
                arr1_sum = 0;
                arr2_sum = 0;
            }
            else if(arr1[i] < arr2[j])
            {
                arr1_sum += arr1[i];
                i++;
            }
            else
            {
                arr2_sum += arr2[j];
                j++;
            }
        }
        for(;i < m; i++)
            arr1_sum += arr1[i];

        for(;j < n; j++)
            arr2_sum += arr2[j];
            
        res += Math.max(arr1_sum, arr2_sum);
        return res;
    }


    public static void main(String[] args)
    {
        int[] arr1, arr2;
        int t = s.nextInt();
        for(int i = 0; i < t; i++)
        {
            arr1 = take_input();
            arr2 = take_input();
            System.out.println(maximise_the_sum(arr1, arr2));
        }
        s.close();
    }
}