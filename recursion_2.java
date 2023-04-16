import java.util.Scanner;

public class recursion_2 {

    static Scanner s = new Scanner(System.in);

    public static String replace(String str, char c1, char c2, int start_index)
    {
        if(start_index == str.length())
            return "";
        if(str.charAt(start_index) == c1)
            return c2 + replace(str, c1, c2, start_index + 1);
        
        return str.charAt(start_index) + replace(str, c1, c2, start_index + 1);
    }

    public static String replace(String str, char c1, char c2)
    {
        return replace(str, c1, c2, 0);
    }

    public static String remove_consecutive_duplicates(String str)
    {
        if(str.length() == 1)
            return str;
        String res_n_1 = remove_consecutive_duplicates(str.substring(1));
        if(str.charAt(0) == res_n_1.charAt(0))
            return res_n_1;
        return str.charAt(0) + res_n_1;        
    }

    public static void merge_sort(int[] arr, int start, int end)
    {
        if(end <= start)
            return;
        int mid = (start + end)/2;
        merge_sort(arr, start, mid);
        merge_sort(arr, mid + 1, end);
        
        int[] sorted_arr = new int[end + 1 - start];
        int i = start, j = mid + 1, k = 0;
        while(i <= mid && j <= end)
        {
            if(arr[i] >= arr[j])
            {
                sorted_arr[k] = arr[j];
                j++;
            }
            else
            {
                sorted_arr[k] = arr[i];
                i++;
            }
            k++;
        }

        for(; i <= mid; i++, k++)
            sorted_arr[k] = arr[i];
        for(; j <= end; j++, k++)
            sorted_arr[k] = arr[j];
        
        for(k = 0; k < sorted_arr.length; k++)
            arr[start + k] = sorted_arr[k];
    }

    public static void merge_sort(int[] arr)
    {
        merge_sort(arr, 0, arr.length - 1);
    }

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
    }

    public static int partition(int[] arr, int start, int end)
    {
        int count = 0, pivot = arr[start];
        for(int i = start + 1; i <= end; i++)
        {
            if(arr[i] <= pivot)
                count++;
        }
        int pivot_pos = start + count;
        arr[start] = arr[pivot_pos];
        arr[pivot_pos] = pivot;
        int i = start, j = end, temp;
        while(i < pivot_pos && j > pivot_pos)
        {
            if(arr[i] > pivot && arr[j] <= pivot)
            {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
            if(arr[i] <= pivot)
                i++;
            if(arr[j] > pivot)
                j--;
        }
        return pivot_pos;
    }

    public static void quick_sort(int[] arr, int start, int end)
    {
        if(end <= start)
            return;
        int pivot_pos = partition(arr, start, end);
        quick_sort(arr, start, pivot_pos - 1);
        quick_sort(arr, pivot_pos + 1, end);
    }

    public static void quick_sort(int[] arr)
    {
        quick_sort(arr, 0, arr.length - 1);
    }

    public static String replace_pi(String str)
    {
        if(str.length() <= 1)
            return str;
        String res_n_1 = replace_pi(str.substring(1));
        str = str.charAt(0) + res_n_1;
        if(str.substring(0, 2).equals("pi"))
            str = "3.14" + str.substring(2);
        return str;
    }

    public static String remove_x(String str)
    {
        if(str.length() <= 0)
            return "";
        if(str.charAt(0) == 'x')
            return remove_x(str.substring(1));
        else
            return str.charAt(0) + remove_x(str.substring(1));
    }

    public static int string_to_integer(String str)
    {
        if(str.length() == 0)
            return 0;
        int res_n_1 = string_to_integer(str.substring(0, str.length() - 1));
        return (res_n_1 * 10) + (str.charAt(str.length() - 1) - 48);
    }

    public static String pair_stars(String str)
    {
        if(str.length() <= 1)
            return str;
        String res_n_1 = pair_stars(str.substring(1));
        if(str.charAt(0) == res_n_1.charAt(0))
            return str.charAt(0) + "*" + res_n_1;
        return str.charAt(0) + res_n_1;
    }

    public static void tower_of_hanoi(int n, char source, char aux, char destination)
    {
        if(n == 0)
            return;
        tower_of_hanoi(n - 1, source, destination, aux);
        System.out.println(source + " " + destination);
        tower_of_hanoi(n - 1, aux, source, destination);
    }

    public static void tower_of_hanoi(int n)
    {
        tower_of_hanoi(n, 'a', 'b', 'c');
    }

    public static void main(String[] args) {
        int n = s.nextInt();
        tower_of_hanoi(n);
        s.close();
    }
}
