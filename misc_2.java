import java.util.Scanner;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class misc_problems_2 {

    static Scanner s = new Scanner(System.in);

    public static int[] take_input()
    {
        int n = s.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++)
            arr[i] = s.nextInt();
        return arr;
    }

    public static int bookstore(int[] arr)
    {
        Arrays.sort(arr);
        int i = 0;
        int res = 0;
        for(; i < arr.length; i++)
        {
            if(i % 3 != 2)
                res += arr[arr.length - 1 - i];
        }
        return res;
    }

    public static boolean is_safe(int row, int column, int[][] board)
    {
        boolean res = true;
        for(int r = 0; r < board.length; r++)
        {
            for(int c = 0; c < board.length; c++)
            {
                if((r + c == row + column || r - c == row - column) && board[r][c] == 1)
                    res = false;
            }
        }
        return res;
    }

    public static void place_queen(int row, int[][] board, boolean[] columns_occupied)
    {
        int n = board.length;
        if(row >= n)
        {
            for(int[] rows: board)
            {
                for(int cell: rows)
                    System.out.print(cell + " ");
                System.out.println();
            }
            System.out.println();
            return;
        }
        for(int i = 0; i < n; i++)
        {
            if(columns_occupied[i] == false)
            {
                if(is_safe(row, i, board))
                {
                    board[row][i] = 1;
                    columns_occupied[i] = true;
                    place_queen(row + 1, board, columns_occupied);
                    board[row][i] = 0;
                    columns_occupied[i] = false;
                }
            }
        }
    }

    public static void n_queens(int n)
    {
        int[][] board = new int[n][n];
        boolean[] columns_occupied = new boolean[n];
        place_queen(0, board, columns_occupied);
    }

    public static int[] convert_2d_array_to_1d(int[][] arr)
    {
        int m = arr.length, n = 0;
        if(m != 0)
            n = arr[0].length;
        int[] res = new int[m * n];
        int k = 0;
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++, k++)
            {
                res[k] = arr[i][j];
            }
        }
        return res;
    }

    public static void rats_in_a_maze(int[][] maze, int sr, int sc, int er, int ec, int[][] solution, List<int[]> res)
    {
        if(sr < 0 || sc < 0 || sr >= maze.length || sc >= maze.length || solution[sr][sc] == 1 || maze[sr][sc] == 0)
            return;
        if(sr == er && sc == ec)
        {
            solution[er][ec] = 1;
            res.add(convert_2d_array_to_1d(solution));
            solution[er][ec] = 0;
        } 
        solution[sr][sc] = 1;
        rats_in_a_maze(maze, sr + 1, sc, er, ec, solution, res);
        rats_in_a_maze(maze, sr, sc + 1, er, ec, solution, res);
        rats_in_a_maze(maze, sr - 1, sc, er, ec, solution, res);
        rats_in_a_maze(maze, sr, sc - 1, er, ec, solution, res);
        solution[sr][sc] = 0;
    }

    public static LinkedList<int[]> rats_in_a_maze(int[][] maze)
    {
        LinkedList<int[]> res = new LinkedList<int[]>();
        int[][] solution = new int[maze.length][maze.length];
        rats_in_a_maze(maze, 0, 0, maze.length - 1, maze.length - 1, solution, res);
        return res;
    }

    public static void print_cross(char[][] crossword)
    {
        for(int r = 0; r < 10; r++)
        {
            for(int c = 0; c < 10; c++)
            {
                if(crossword[r][c] == '-')
                    return;
            }
        }
        for(int r = 0; r < 10; r++)
        {
            for(int c = 0; c < 10; c++)
            {
                System.out.print(crossword[r][c]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean swap(char[][] crossword, int[] point, char[] word, int direction)
    {
        boolean result = true;
        char temp;
        int r = point[0], c = point[1], dx = 0, dy = 0;
        if(direction == 1) // horizontal
            dx = 1;
        else //vertical
            dy = 1;

        int i = 0;
        while(r < 10 && c < 10 && i < word.length)
        {
            if(crossword[r][c] != '-' && crossword[r][c] != word[i])
            {
                result = false;
            }
            temp = word[i];
            word[i] = crossword[r][c];
            crossword[r][c] = temp;
            c += dx;
            r += dy;
            i++;
        }
        if((r == 10 || c == 10) && (i != word.length))
            result = false;

        for(char alphabet:word)
        {
            if(alphabet == '+')
                result = false;
        }
        return result;
    }

    public static void solve_crossword(char[][] crossword, char[][] word_array, int[][] starting_points, int point_index, boolean[] word_used)
    {
        if(point_index >= starting_points.length)
        {
            print_cross(crossword);
            return;
        }
        int[] point = starting_points[point_index];
        for(int i = 0; i < word_array.length; i++)
        {
            char[] word = word_array[i];
            if(!word_used[i])
            {
                boolean inserted = swap(crossword, point, word, point[2]);
                if(inserted)
                {
                    word_used[i] = true;
                    solve_crossword(crossword, word_array, starting_points, point_index + 1, word_used);
                    word_used[i] = false;
                }
                swap(crossword, point, word, point[2]);
            }
        }
    }

    public static void find_starting_points(char[][] crossword, int[][] starting_points)
    {
        int point = 0;
        for(int r = 0; r < 10; r++)
        {
            for(int c = 0; c < 10; c++)
            {
                if(crossword[r][c] == '-')
                {
                    if((c < 9 && crossword[r][c + 1] == '-') && (c == 0 || crossword[r][c - 1] == '+'))
                    {
                        starting_points[point][0] = r;
                        starting_points[point][1] = c;
                        starting_points[point][2] = 1;
                        point++;
                        
                    }
                    if((r < 9 && crossword[r + 1][c] == '-') && (r == 0 || crossword[r - 1][c] == '+'))
                    {
                        starting_points[point][0] = r;
                        starting_points[point][1] = c;
                        starting_points[point][2] = -1;
                        point++;
                        
                    }
                }
            }
        }
    }

    public static void solve_crossword(char[][] crossword, char[][] word_array)
    {
        int[][] starting_points = new int[word_array.length][3];
        find_starting_points(crossword, starting_points);
        boolean[] word_used = new boolean[word_array.length];
        solve_crossword(crossword, word_array, starting_points, 0,word_used);
    }

    public static boolean sort_skills(int[] arr)
    {
        int n = arr.length, temp;
        for(int i = 0; i < n - 1; i++)
        {
            if(arr[i + 1] < arr[i] - 1)
                return false;
            if(arr[i + 1] == arr[i] - 1)
            {
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] =  temp;
            }
        }
        return true;
    }

    public static boolean sudoku_res = false;
    public static void sudoku_solver(int[][] sudoku, int r, int c)
    {
        if(c == 9)
        {
            c = 0;
            r = r + 1;
        }
        if(r >= 9)
        {
            System.out.println();
            for(int i = 0; i < 9; i++)
            {
                for(int j = 0; j < 9; j++)
                {
                    System.out.print(sudoku[i][j] + " ");
                }
                System.out.println();
            }
            sudoku_res = true;
            return;
        }

        if(sudoku[r][c] == 0)
        {
            boolean[] digits_available = new boolean[10];
            for(int i = 0; i < 10; i++)
                digits_available[i] = true;
            digits_available[0] = false;
            for(int i = 0; i < 9; i++)
            {
                digits_available[sudoku[r][i]] = false;
                digits_available[sudoku[i][c]] = false;
            }
            for(int row = 3*(r/3); row < 3*((r/3)+1); row++)
            {
                for(int col = 3*(c/3); col < 3*((c/3)+1); col++)
                {
                    digits_available[sudoku[row][col]] = false;
                }
            }

            for(int i = 1; i < 10; i++)
            {
                if(digits_available[i])
                {
                    sudoku[r][c] = i;
                    sudoku_solver(sudoku, r, c + 1);
                    sudoku[r][c] = 0;
                }
            }
        }
        else
            sudoku_solver(sudoku, r, c + 1);
    }

    public static boolean sudoku_solver(int[][] sudoku)
    {
        sudoku_res = false;
        sudoku_solver(sudoku, 0, 0);
        return sudoku_res;
    }

    public static void team_selection(int[] players, String team, int n, int r)
    {
        if(n < r)
        {
            return;
        }
        if(r == 0)
        {
            System.out.println(team);
            return;
        }
        team_selection(players, team + players[players.length - n] + " ", n - 1, r - 1);
        team_selection(players, team, n - 1, r);
    }

    public static void team_selection(int[] players)
    {
        String team = "";
        team_selection(players, team, players.length, 12);
    }

    public static boolean check(int n, int k)
    {
        int sum = 0, curr = n;
        while(curr > 0)
        {
            sum += Math.min(k, curr);
            curr -= k;
            curr -= (curr/10);
        }
        return (sum*2 >= n);
    }

    public static int collect_balls(int n)
    {
        int res = n;
        int left = 1, right = n, mid;
        boolean satisfies = false;
        while(left <= right)
        {
            mid = (left + right)/2;
            satisfies = check(n, mid);
            if(satisfies)
            {
                res = mid;
                right = mid - 1;
            }
            else
                left = mid + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        int n = s.nextInt();
        System.out.println(collect_balls(n));
        s.close();
    }
}
