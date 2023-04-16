import java.util.Scanner;

public class dp {
    public static Scanner s = new Scanner(System.in);

    private static int stairCase(int n, int[] dp)
    {
        if(dp[n] != -1)
            return dp[n];

        if(n == 0 || n == 1)
            return 1;
        if(n == 2)
            return 2;
        dp[n] = stairCase(n - 1, dp) + stairCase(n - 2, dp) + stairCase(n - 3, dp);
        return dp[n];
    }

    // Returns the total number of ways a toddler can climb the stairs. On each step he can jump either 1, 2 or 3 steps.
    public static int stairCase(int n)
    {
        int[] dp = new int[n + 1];
        for(int i = 0; i <= n; i++)
            dp[i] = -1;
        return stairCase(n, dp);
    }

    // Iterative DP code for staircase problem
    public static int stairCaseI(int n)
    {
        if(n == 0 || n == 1)
            return 1;
        if(n == 2)
            return 2;
        
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        return dp[n];
    }

    private static int minStepsToOne(int n, int[] dp)
    {
        if(dp[n] != -1)
            return dp[n];
        
        if(n == 1)
            return 0;
        int res = minStepsToOne(n - 1, dp) + 1;
        if(n%2 == 0)
            res = Math.min(res, minStepsToOne(n/2, dp) + 1);
        if(n%3 == 0)
            res = Math.min(res, minStepsToOne(n/3, dp) + 1);

        dp[n] = res;
        return dp[n];
    }

    // Returns the minimum steps needed for a number to reach 1. It has 3 options -
    // i) n -> n - 1
    // ii) n -> n/2 if n%2 == 0
    // iii) n -> n/3 if n%3 == 0
    public static int minStepsToOne(int n)
    {
        int[] dp = new int[n + 1];
        for(int i = 0; i <= n; i++)
            dp[i] = -1;
        return minStepsToOne(n, dp);
    }

    public static int minStepsToOneI(int n)
    {
        if(n == 1)
            return 0;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for(int i = 2; i <= n; i++)
        {
            dp[i] = dp[i - 1] + 1;
            if(i%2 == 0)
                dp[i] = Math.min(dp[i], dp[i/2] + 1);
            if(i%3 == 0)
                dp[i] = Math.min(dp[i], dp[i/3] + 1);
        }
        return dp[n];
    }

    public static int minSquares(int n, int[] dp)
    {
        if(dp[n] != -1)
            return dp[n];
        
        if(n == 0)
            return 0;
        
        int res = Integer.MAX_VALUE;
        for(int i = 1; i <= (Math.sqrt(n)); i++)
            res = Math.min(res, minSquares(n - (i*i), dp) + 1);

        dp[n] = res;
        return dp[n];
    }

    // Returns the minimum number of squares neeeded to represent a number
    public static int minSquares(int n)
    {
        int[] dp = new int[n + 1];
        for(int i = 0; i <= n; i++)
            dp[i] = -1;
        return minSquares(n, dp);
    }

    public static int minSquaresI(int n)
    {
        if(n == 0)
            return 0;

        int[] dp = new int[n + 1];
        for(int i = 0; i <= n; i++)
            dp[i] = Integer.MAX_VALUE;
        dp[0] = 0;

        for(int i = 1; i <= n; i++)
        {
            for(int j = 1; j <= Math.sqrt(i); j++)
                dp[i] = Math.min(dp[i], dp[i - (j*j)]);
            dp[i]++;
        }

        for(int x: dp)
            System.out.print(x + " ");
        return dp[n];
    }

    private static int bytelandian(int n, int[] dp)
    {
        if(dp[n] != -1)
            return dp[n];
        
        if(n == 0)
            return 0;
        
        int smallAns = bytelandian(n/2, dp) + bytelandian(n/3, dp) + bytelandian(n/4, dp);
        dp[n] = Math.max(n, smallAns);
        return dp[n];
    }

    // Bytelandian
    public static int bytelandian(int n)
    {
        int[] dp = new int[n + 1];
        for(int i = 0; i <= n; i++)
            dp[i] = -1;
        return bytelandian(n, dp);
    }

    public static int bytelandianI(int n)
    {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for(int i = 1; i <= n; i++)
            dp[i] = Math.max(i, dp[i/2] + dp[i/3] + dp[i/4]);
        return dp[n];
    }

    private static int loot(int[] houses, int i, int[] dp)
    {
        if(i >= houses.length)
            return 0;
        if(dp[i] != -1)
            return dp[i];

        dp[i] = Math.max(houses[i] + loot(houses, i + 2, dp), loot(houses, i + 1, dp));
        return dp[i];
    }

    // Loot houses. The robber can't loot two consecutive houses. Return the maximum money he can rob
    public static int loot(int[] houses)
    {
        int[] dp = new int[houses.length];
        for(int i = 0; i < houses.length; i++)
            dp[i] = -1;
        return loot(houses, 0, dp);
    }

    public static int lootI(int[] houses)
    {
        if(houses.length == 0)
            return 0;
        int n = houses.length;
        int[] dp = new int[n];
        dp[0] = houses[0];
        if(n >= 2)
            dp[1] = Math.max(houses[0], houses[1]);
        for(int i = 2; i < n; i++)
            dp[i] = Math.max(houses[i] + dp[i - 2], dp[i - 1]);
        return dp[n - 1];
    }

    private static Boolean coinTowerHelper(int n, int x, int y, int[] dp)
    {
        if(dp[n] != -1)
            return dp[n]==0?false:true;

        if(n == 0)
            return false;
        
        Boolean smallAns = coinTowerHelper(n - 1, x, y, dp);
        if(smallAns && x <= n)
            smallAns = coinTowerHelper(n - x, x, y, dp);
        if(smallAns && y <= n)
            smallAns = coinTowerHelper(n - y, x, y, dp);
        
        boolean res = !smallAns;
        dp[n] = (res==false?0:1);
        return res;
    }

    // Coin Tower. Beerus and Whis are playing a coin tower game. Both play on alternate turns. The player to turn last wins. We have to return the name of the player that wins the game. There are n coins in a coin tower. Beerus has the first turn. He can either remove 1 coin, X coins or Y coins. If both players make turns optimally, find out the player who wins the game.
    public static String coinTower(int n, int x, int y)
    {
        int[] dp = new int[n + 1];
        for(int i = 0; i <= n; i++) 
            dp[i] = -1;
        if(coinTowerHelper(n, x, y, dp))
            return "Beerus";
        else
            return "Whis";
    }

    public static String coinTowerI(int n, int x, int y)
    {
        Boolean[] dp = new Boolean[n + 1];
        if(n == 0)
            return "Whis";
        dp[0] = false;
        for(int i = 1; i <= n; i++)
        {
            dp[i] = !dp[i - 1];
            if(!dp[i] && x <= i)
                dp[i] = !dp[i - x];
            if(!dp[i] && y <= i)
                dp[i] = !dp[i - y];
        }

        if(dp[n])
            return "Beerus";
        else
            return "Whis";
    }

    private static int minCost(int[][] mat, int i, int j, Integer[][] dp)
    {
        if(i >= mat.length || j >= mat[0].length)
            return Integer.MAX_VALUE;
        if(dp[i][j] != null)
            return dp[i][j];
        if(i == mat.length - 1 && j == mat[0].length - 1)
            return mat[i][j];
        
        int res =  minCost(mat, i + 1, j + 1, dp);
        res = Math.min(res, minCost(mat, i + 1, j, dp));
        res = Math.min(res, minCost(mat, i, j + 1, dp));
        dp[i][j] = res + mat[i][j];
        return dp[i][j];
    }

    // Minimum cost path problem
    public static int minCost(int[][] mat)
    {
        Integer[][] dp = new Integer[mat.length][mat[0].length];
        return minCost(mat, 0, 0, dp);
    }

    public static int minCostI(int[][] mat)
    {
        int m = mat.length, n = mat[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = mat[0][0];
        for(int r = 1; r < m; r++)
            dp[r][0] = dp[r - 1][0] + mat[r][0];
        for(int c = 1; c < n; c++)
            dp[0][c] = dp[0][c - 1] + mat[0][c];
        for(int r = 1; r < m; r++)
        {
            for(int c = 1; c < n; c++)
                dp[r][c] = mat[r][c] + Math.min(dp[r - 1][c], Math.min(dp[r][c - 1], dp[r - 1][c - 1]));
        }
        return dp[m - 1][n - 1];
    }

    private static int longestCommonSubsequenceHelper(String M, String N, int i, int j, int[][] dp)
    {
        if(i >= M.length() || j >= N.length())
            return 0;
        if(dp[i][j] != -1)
            return dp[i][j];
        
        int res;
        if(M.charAt(i) == N.charAt(j))
            res = 1 + longestCommonSubsequenceHelper(M, N, i + 1, j + 1, dp); 
        else
        {
            int smallAns1 = longestCommonSubsequenceHelper(M, N, i + 1, j, dp);
            int smallAns2 = longestCommonSubsequenceHelper(M, N, i, j + 1, dp);
            res = (smallAns1 > smallAns2?smallAns1:smallAns2);  
        }
        dp[i][j] = res;
        return dp[i][j];
    }

    // Returns the longest common subsequence of two strings M and N.
    public static int longestCommonSubsequence(String M, String N)
    {
        int[][] dp = new int[M.length()][N.length()];
        for(int i = 0; i < M.length(); i++)
        {
            for(int j = 0; j < N.length(); j++)
                dp[i][j] = -1;
        }
        return longestCommonSubsequenceHelper(M, N, 0, 0, dp);
    }

    public static int longestCommonSubsequenceI(String M, String N)
    {
        if(M.length() == 0 || N.length() == 0)
            return 0;
        int[][] dp = new int[M.length() + 1][N.length() + 1];
        for(int i = 1; i <= M.length(); i++)
        {
            for(int j = 1; j <= N.length(); j++)
            {
                if(M.charAt(i-1) == N.charAt(j-1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[M.length()][N.length()];
    }

    private static int editDistance(String S, String T, int i, int j, int[][] dp)
    {
        if(i >= S.length())
            return T.length() - j;
        else if(j >= T.length())
            return S.length() - i;
        
        if(dp[i][j] != -1)
            return dp[i][j];
        
        int ans2 = editDistance(S, T, i + 1, j + 1, dp);
        if(S.charAt(i) == T.charAt(j))
            return ans2;
        int ans1 = editDistance(S, T, i + 1, j, dp);
        int ans3 = editDistance(S, T, i, j + 1, dp);
        int res = 1 + Math.min(ans1, Math.min(ans2, ans3));

        dp[i][j] = res;
        return res;
    }

    // Returns the edit distance between two strings S and T.
    public static int editDistance(String S, String T)
    {
        int m = S.length(), n = T.length();
        int[][] dp = new int[m][n];
        for(int i = 0; i < m;i++)
        {
            for(int j = 0; j < n; j++)
                dp[i][j] = -1;
        }
        return editDistance(S, T, 0, 0, dp);
    }

    public static int editDistanceI(String S, String T)
    {
        int m = S.length(), n = T.length();
        int[][] dp =  new int[m + 1][n + 1];

        for(int i = 0; i <= m; i++)
            dp[i][n] = m - i;
        for(int j = 0; j <= n; j++)
            dp[m][j] = n - j;

        for(int i = m - 1; i >= 0; i--)
        {
            for(int j = n - 1; j >= 0; j--)
            {
                if(S.charAt(i) == T.charAt(j))
                    dp[i][j] = dp[i + 1][j + 1];
                else
                    dp[i][j] = 1 + Math.min(dp[i + 1][j + 1], Math.min(dp[i + 1][j], dp[i][j + 1]));
            }
        }
        
        return dp[0][0];
    }

    private static int knapsack(int[] weights, int[] value, int i, int maxWeight, int currValue, int[][] dp)
    {
        
        if(i >= weights.length || maxWeight == 0)
            return currValue;
        if(dp[i][maxWeight] != -1)
            return dp[i][maxWeight];
        
        int ans1 = knapsack(weights, value, i + 1, maxWeight, currValue, dp);
        int ans2 = Integer.MIN_VALUE;
        if(weights[i] <= maxWeight)
            ans2 = knapsack(weights, value, i + 1, maxWeight - weights[i], currValue + value[i], dp);
        
        int res = Math.max(ans1, ans2);
        dp[i][maxWeight] = res;
        return dp[i][maxWeight];
    }

    //0-1 Knapsack problem
    public static int knapsack(int[] weights, int[] value, int maxWeight)
    {
        int[][] dp = new int[weights.length][maxWeight + 1];
        for(int i = 0; i < weights.length; i++)
        {
            for(int currWeight = 0; currWeight <= maxWeight; currWeight++)
                dp[i][currWeight] = -1;
        }
        return knapsack(weights, value, 0, maxWeight, 0, dp);
    }

    public static int knapsackI(int[] weights, int[] value, int maxWeight)
    {
        int n = weights.length;
        int[][] dp = new int[n + 1][maxWeight + 1];
        for(int i = 1; i <= n; i++)
        {
            for(int currWeight = 1; currWeight <= maxWeight; currWeight++)
            {
                int ans1 = -1;
                if(weights[i - 1] <= currWeight)
                    ans1 = value[i - 1] + dp[i - 1][currWeight - weights[i - 1]];
                int ans2 = dp[i - 1][currWeight];
                dp[i][currWeight] = Math.max(ans1, ans2);
            }
        }
        return dp[n][maxWeight];
    }

    private static int coinChange(int[] den, int i, int val, int[][] dp)
    {
        if(i >= den.length)
            return 0;
        if(val == 0)
            return 1;
        if(dp[i][val] != -1)
            return dp[i][val];

        int ans1 = 0;
        if(val >= den[i])
            ans1 = coinChange(den, i, val - den[i], dp);
        int ans2 = coinChange(den, i + 1, val, dp);
        int res = ans1 + ans2;
        dp[i][val] = res;
        return res;
    }

    // Returns the total number of ways a value can be converted into coins of denominations d0, d1, d2,...
    public static int coinChange(int[] den, int val)
    {
        int[][] dp = new int[den.length][val + 1];
        for(int i = 0; i < dp.length; i++)
        {
            for(int j = 0; j <= val; j++)
                dp[i][j] = -1;
        }
        return coinChange(den, 0, val, dp);
    }

    public static int coinChangeI(int[] den, int val)
    {
        int n = den.length;
        int[][] dp = new int[n + 1][val + 1];
        //dp[i][j] represents the number of total coin change possible if the length of denominations taken is i, and val = v

        for(int i = 1; i <= n; i++)
        {
            for(int v = 1; v <= val; v++)
            {
                dp[i][v] = dp[i - 1][v];
                if(v == den[i - 1])
                    dp[i][v] += 1;
                else if(v > den[i - 1])
                    dp[i][v] += dp[i][v - den[i - 1]];
            }
        }
        return dp[n][val];
    }

    // with Memoization
    private static Misc maximumZeroSubmatrixM(int[][] mat, int m, int n, int i, int j, Misc[][] dp)
    {
        if(i >= m || j >= n)
            return new Misc();

        if(dp[i][j] != null)
            return dp[i][j];
        
        Misc res = new Misc();
        Misc ans1 = maximumZeroSubmatrix(mat, m, n, i, j + 1);
        Misc ans2 = maximumZeroSubmatrix(mat, m, n, i + 1, j);
        Misc ans3 = maximumZeroSubmatrix(mat, m, n, i + 1, j + 1);

        if(mat[i][j] == 1)
        {
            res.cellMaxSquare = 0;
            res.matMaxSquare = Math.max(ans1.matMaxSquare, ans2.matMaxSquare);
        }
        else
        {
            if(ans1.cellMaxSquare == ans2.cellMaxSquare && ans2.cellMaxSquare == ans3.cellMaxSquare)
                res.cellMaxSquare = ans1.cellMaxSquare + 1;
            else
                res.cellMaxSquare = 1 + Math.min(ans1.cellMaxSquare, Math.min(ans2.cellMaxSquare, ans3.cellMaxSquare));

            res.matMaxSquare = Math.max(res.cellMaxSquare, Math.max(ans1.matMaxSquare, ans2.matMaxSquare));
        }
        dp[i][j] = res;
        return res;
    }

    private static Misc maximumZeroSubmatrix(int[][] mat, int m, int n, int i, int j)
    {
        Misc res = new Misc();
        if(i >= m || j >= n)
            return res;

        Misc ans1 = maximumZeroSubmatrix(mat, m, n, i, j + 1);
        Misc ans2 = maximumZeroSubmatrix(mat, m, n, i + 1, j);
        Misc ans3 = maximumZeroSubmatrix(mat, m, n, i + 1, j + 1);

        if(mat[i][j] == 1)
        {
            res.cellMaxSquare = 0;
            res.matMaxSquare = Math.max(ans1.matMaxSquare, ans2.matMaxSquare);
            return res;
        }
        else
        {
            
            res.cellMaxSquare = 1 + Math.min(ans1.cellMaxSquare, Math.min(ans2.cellMaxSquare, ans3.cellMaxSquare));
            res.matMaxSquare = Math.max(res.cellMaxSquare, Math.max(ans1.matMaxSquare, ans2.matMaxSquare));
            return res;
        }
    }

    // Returns the length of maximum square submatrix which consists of all zeroes.
    public static int maximumZeroSubmatrix(int[][] mat)
    {
        int m = mat.length;
        int n = 0;
        if(m > 0)
            n = mat[0].length;
        Misc[][] dp = new Misc[m][n];
        return maximumZeroSubmatrixM(mat, m, n, 0, 0, dp).matMaxSquare;
    }

    public static int maximumZeroSubmatrixI(int[][] mat)
    {
        int m = mat.length;
        int n = 0;
        if(m > 0)
            n = mat[0].length;
        int[][] dp = new int[m + 1][n + 1];
        int max = 0;
        for(int i = m - 1; i >= 0; i--)
        {
            for(int j = n - 1; j >= 0; j--)
            {
                if(mat[i][j] == 1)
                    dp[i][j] = 0;
                else
                    dp[i][j] = 1 + Math.min(dp[i + 1][j], Math.min(dp[i][j + 1], dp[i + 1][j + 1]));
                
                if(dp[i][j] > max)
                    max = dp[i][j];
            }
        }
        return max;
    }

    private static int smallestSuperSequence(String S, String T, int i, int j, int[][] dp)
    {
        if(i >= S.length())
            return T.length() - j;
        if(j >= T.length())
            return S.length() - i;

        if(dp[i][j] != -1)
            return dp[i][j];

        int res;
        if(S.charAt(i) == T.charAt(j))
            res = 1 + smallestSuperSequence(S, T, i + 1, j + 1, dp);
        else
            res = 1 + Math.min(smallestSuperSequence(S, T, i + 1, j, dp), smallestSuperSequence(S, T, i, j + 1, dp));

        dp[i][j] = res;
        return res;
    }

    // Returns the length of smallest super sequence.
    public static int smallestSuperSequence(String S, String T)
    {
        int m = S.length(), n = T.length();
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
                dp[i][j] = -1;
        }
        return smallestSuperSequence(S, T, 0, 0, dp);
    }

    public static int smallestSuperSequenceI(String S, String T)
    {
        int m = S.length(), n = T.length();
        int[][] dp = new int[m + 1][n + 1];

        for(int i = 0; i < m; i++)
            dp[i][n] = m - i;
        for(int j = 0; j < n; j++)
            dp[m][j] = n - j;

        for(int i = m - 1; i >= 0; i--)
        {
            for(int j = n - 1; j >= 0; j--)
            {
                if(S.charAt(i) == T.charAt(j))
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                else
                    dp[i][j] = 1 + Math.min(dp[i + 1][j], dp[i][j + 1]);
            }
        }
        return dp[0][0];
    }

    private static int magicGrid(int[][] grid, int i, int j, int[][] dp)
    {
        if(i >= grid.length || j >= grid[0].length)
            return Integer.MAX_VALUE;
        if(i == grid.length - 1 && j == grid[0].length - 1)
            return 1;
        if(dp[i][j] != -1)
            return dp[i][j];
        
        int ans1 = magicGrid(grid, i, j + 1, dp);
        int ans2 = magicGrid(grid, i + 1, j, dp);
    
        int res = Math.max(1, Math.min(ans1, ans2) - grid[i][j]);
        dp[i][j] = res;
        return res;
    }

    // Returns the minimum strength required to travel from [0, 0] to [R-1, C-1]
    public static int magicGrid(int[][] grid)
    {
        int r = grid.length, c = grid[0].length;
        int[][] dp = new int[r][c];
        for(int i = 0; i < r; i++)
        {
            for(int j = 0; j < c; j++)
                dp[i][j] = -1;
        }
        return magicGrid(grid, 0, 0, dp);
    }

    public static int magicGridI(int[][] grid)
    {
        int r = grid.length, c = grid[0].length;
        int[][] dp = new int[r + 1][c + 1];

        for(int i = 0; i <= r; i++)
            dp[i][c] = Integer.MAX_VALUE;
        for(int j = 0; j <= c; j++)
            dp[r][j] = Integer.MAX_VALUE;
        dp[r - 1][c] = 1;
        dp[r][c - 1] = 1;

        for(int i = r - 1; i >= 0; i--)
        {
            for(int j = c - 1; j >= 0; j--)
                dp[i][j] = Math.max(1, (Math.min(dp[i][j + 1], dp[i + 1][j]) - grid[i][j]));
        }
        return dp[0][0];
    }

    private static int lootHouses(int[] houses, int i, int[] dp)
    {
        if(i >= houses.length)
            return 0;
        if(dp[i] != -1)
            return dp[i];

        int ans1 = houses[i] + lootHouses(houses, i + 2, dp);
        int ans2 = lootHouses(houses, i + 1, dp);
        int res = Math.max(ans1, ans2);

        dp[i] = res;
        return res;
    }

    // Returns the max amount of money a robber can steal from a row of houses if the robber can't steal in two consecutive houses.
    public static int lootHouses(int[] houses)
    {
        int[] dp = new int[houses.length];
        for(int i = 0; i < houses.length; i++)
            dp[i] = -1;
        return lootHouses(houses, 0, dp);
    }

    public static int lootHousesI(int[] houses)
    {
        int n = houses.length;
        if(n == 0)
            return 0;
        int[] dp = new int[n + 1];
        dp[n] = 0;
        dp[n - 1] = houses[n - 1];
        for(int i = n - 2; i >= 0; i--)
            dp[i] = Math.max(dp[i + 2] + houses[i], dp[i + 1]);
        return dp[0];
    }

    public static boolean subsetSum(int[] arr, int k, int i, Boolean[][] dp)
    {
        if(k == 0)
            return true;
        if(i >= arr.length || k < 0)
            return false;
        if(dp[i][k] != null)
            return dp[i][k];

        boolean ans1 = subsetSum(arr, k - arr[i], i + 1, dp);
        boolean ans2 = subsetSum(arr, k, i + 1, dp);
        boolean res = ans1 || ans2;

        dp[i][k] = res;
        return res;
    }

    // Returns true if there exists a subset whose sum is equal to k.
    public static boolean subsetSum(int[] arr, int k)
    {
        Boolean[][] dp = new Boolean[arr.length][k + 1];
        boolean res = subsetSum(arr, k, 0, dp);
        return res;
    }

    public static boolean subsetSumI(int[] arr, int k)
    {
        int n = arr.length;
        boolean[][] dp = new boolean[n + 1][k + 1];
        for(int i = 0; i <= n; i++)
            dp[i][0] = true;
        for(int i = n - 1; i >= 0; i--)
        {
            for(int j = 1; j <= k; j++)
            {
                if(arr[i] <= j)
                    dp[i][j] = dp[i][j] || dp[i + 1][j - arr[i]];
                dp[i][j] = dp[i][j] || dp[i + 1][j];
            }
        }
        return dp[0][k];
    }

    // Returns the minimum number of chocolates required if the teacher wants to distribute them among students. Each student must get at least 1 chocolate and if two students are sitting consecutively in the row, then the student with higher rating should get more chocolates than the one with lower rating. It's not really a dynamic programming problem since there aren't any overlapping substructures. It resembles more to a greedy approach where we traverse in the array once from left, and once from right to make sure that the condition is fulfilled for each student.
    public static int minChocolates(int[] ratings)
    {
        int n = ratings.length;
        if(n == 1)
            return 1;
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i = 1; i < n ; i++)
        {
            if(ratings[i] > ratings[i - 1])
                dp[i] = dp[i - 1] + 1;
            else
                dp[i] = 1;
        }
        for(int i = n - 2; i >= 0; i--)
        {
            if(ratings[i] > ratings[i + 1])
                dp[i] = Math.max(dp[i], dp[i + 1] + 1);
        }
        int sum = 0;
        for(int x: dp)
            sum += x;
        return sum;
    }

    public static void main(String[] args)
    {
        int[] ratings = hashmaps.take_input();
        System.out.println(minChocolates(ratings));
    }
}