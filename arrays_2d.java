import java.util.Scanner;

public class arrays_2d {

    static Scanner s = new Scanner(System.in);

    public static int[][] take_input(int m, int n)
    {
        int[][] arr = new int[m][n];
        for(int i = 0; i < m ; i++)
        {
            for(int j = 0; j < n; j++)
                arr[i][j] = s.nextInt();
        }
        return arr;
    }

    public static int[][] take_input()
    {
        int m = s.nextInt();
        int n = s.nextInt();
        return take_input(m, n);
    }

    public static void display_array_2d(int[][] arr)
    {
        int m = arr.length;
        if(m == 0)
            return;
        int n = arr[0].length;
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
                System.out.print(arr[i][j] + " ");
            System.out.println();
        }
    }

    public static String array_1d_to_string(int[] arr)
    {
        String res = "";
        for(int x: arr)
            res += x + " ";
        return res;
    }

    public static int[] row_sum(int[][] arr)
    {
        int r = arr.length, c = arr[0].length;
        int[] res = new int[arr.length];
        for(int i = 0; i < r; i++)
        {
            for(int j = 0; j < c; j++)
                res[i] += arr[i][j];
        }
        return res;
    }

    public static int largest_column_sum(int[][] arr)
    {
        int r = arr.length, sum = 0, max_sum = 0;
        if(r == 0)
            return 0;
        int c = arr[0].length;
        for(int j = 0; j < c; j++)
        {
            for(int i = 0; i < r; i++)
                sum += arr[i][j];
            if(sum > max_sum)
                max_sum = sum;
            sum = 0;
        }
        return max_sum;
    }

    public static String largest_row_column_sum(int[][] arr)
    {
        String axis = "row";
        int index = 0, max_sum = Integer.MIN_VALUE, sum = 0;
        int r = arr.length;
        if(r == 0)
            return (axis + " " + index + " " + max_sum);
        int c = arr[0].length;
        for(int i = 0; i < r; i++)
        {
            for(int j = 0; j < c; j++)
                sum += arr[i][j];
            if(sum > max_sum)
            {
                index = i;
                max_sum = sum;
            }
            sum = 0;
        }
        for(int j = 0; j < c; j++)
        {
            for(int i = 0; i < r; i++)
                sum += arr[i][j];
            if(sum > max_sum)
            {
                axis = "column";
                index = j;
                max_sum = sum;
            }
            sum = 0;
        }
        return (axis + " " + index + " " + max_sum);
    }

    public static int total_sum_on_the_boundaries_and_diagonals(int[][] arr)
    {
        int sum = 0, n = arr.length;
        if(n == 0)
            return sum;
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if(i == 0 || i == n - 1 || j == 0 || j == n - 1 || i == j || j == n - 1 - i)
                    sum += arr[i][j];
            }
        }
        return sum;
    }

    public static int[][] take_input(int n)
    {
        return take_input(n, n);
    }

    public static String print_like_a_wave(int[][] arr)
    {
        String res = "";
        int mod = 1, r = arr.length;
        if(r == 0)
            return res;
        int c = arr[0].length, i = 0;
        for(int j = 0; j < c; j++)
        {
            for(; i >= 0 && i < r; i += mod)
                res += arr[i][j] + " ";
            mod *= -1;
            i += mod;
        }
        return res;
    }

    public static String spiral_print(int[][] arr)
    {
        String res = "";
        int r = arr.length;
        if(r == 0)
            return res;
        int c = arr[0].length;
        int x = 0, y = 0, left_bound = -1, right_bound = c, top_bound = -1, bottom_bound = r, x_dir = 1, y_dir = 0;
        while(left_bound < x && x < right_bound && top_bound < y && y < bottom_bound)
        {
            res += arr[y][x] + " ";
            if(x + x_dir >= right_bound)
            {
                top_bound++;
                x_dir = 0;
                y_dir = 1;
            }
            else if(y + y_dir >= bottom_bound)
            {
                right_bound--;
                x_dir = -1;
                y_dir = 0;
            }
            else if(x + x_dir <= left_bound)
            {
                bottom_bound--;
                x_dir = 0;
                y_dir = -1;
            }
            else if(y + y_dir <= top_bound)
            {
                left_bound++;
                x_dir = 1;
                y_dir = 0;
            }
            x += x_dir;
            y += y_dir;
        }
        
        return res;
    }

    public static void main(String[] args)
    {
        int[][] arr;
        int t = s.nextInt();
        String[] res = new String[t];
        for(int i = 0; i < t; i++)
        {
            arr = take_input();
            res[i] = spiral_print(arr);
        }
        for(String x : res)
            System.out.println(x);
        s.close();
    }
}