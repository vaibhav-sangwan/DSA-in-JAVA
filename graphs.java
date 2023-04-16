import java.util.Scanner;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;

public class graphs {

    public static Scanner s = new Scanner(System.in);

    // Takes the number of vertices, number of edges and all edges as input from the user and returns the graph in the form of adjacency matrix.
    // This function takes the input of an undirected graph.
    public static int[][] adjGraphInput()
    {
        int n = s.nextInt();
        int e = s.nextInt();
        int[][] mat = new int[n][n];
        int v1, v2;
        for(int i = 0; i < e; i++)
        {
            v1 = s.nextInt();
            v2 = s.nextInt();
            mat[v1][v2] = 1;
            mat[v2][v1] = 1; // Remove this statement(or comment it out) if you want to take input for a directed graph.
        }
        return mat;
    }

    // Displays a matrix
    public static void printMat(int[][] mat)
    {
        int m = mat.length;
        int n = 0;
        if(m > 0)
            n = mat[0].length;
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
                System.out.print(mat[i][j] + " ");
            System.out.println();
        }
    }

    private static void dft(int[][] graph, int i, boolean[] visited)
    {
        if(visited[i])
            return;
        System.out.print(i + " ");
        visited[i] = true;
        for(int j = 0; j < graph.length; j++)
        {
            if(graph[i][j] == 1)
                dft(graph, j, visited);
        }
    }

    // Depth First Traversal of a graph.
    public static void dft(int[][] graph)
    {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++)
        {
            if(!visited[i])
            {
                dft(graph, i, visited);
                System.out.println();
            }
        }
    }

    private static void bft(int[][] graph, int sourceVertex, boolean[] visited)
    {
        Queue<Integer> q = new LinkedList<>();
        visited[sourceVertex] = true;
        q.add(sourceVertex);
        int front;
        while(!q.isEmpty())
        {
            front = q.remove();
            System.out.print(front + " ");
            for(int i = 0; i < graph.length; i++)
            {
                if(graph[front][i] == 1 && (!visited[i]))
                {
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    // Breadth First Traversal of a graph.
    public static void bft(int[][] graph)
    {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++)
        {
            if(!visited[i])
            {
                bft(graph, i, visited);
                System.out.println();
            }
        }
    }

    // Returns true if the given graph has a path from node v1 to node v2. False otherwise.
    public static boolean hasPathBFS(int[][] graph, int v1, int v2)
    {
        Queue<Integer> q = new LinkedList<>();
        int n = graph.length;
        boolean[] visited = new boolean[n];
        q.add(v1);
        visited[v1] = true;
        int currVertex;
        while(!q.isEmpty())
        {
            currVertex = q.remove();
            if(currVertex == v2)
                return true;
            for(int i = 0; i < n; i++)
            {
                if(graph[currVertex][i] == 1 && (!visited[i]))
                {
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
        return false;
    }

    private static boolean hasPathDFS(int[][] graph, int v1, int v2, boolean[] visited)
    {
        if(v1 == v2)
            return true;
        visited[v1] = true;
        for(int i = 0; i < graph.length; i++)
        {
            if(graph[v1][i] == 1 && (!visited[i]))
            {
                if(hasPathDFS(graph, i, v2, visited))
                    return true;
            }
        }
        return false;
        
    }

    // Returns true if the given graph has a path from node v1 to node v2. False otherwise.
    public static boolean hasPathDFS(int[][] graph, int v1, int v2)
    {
        int n = graph.length;
        if(v1 >= n || v2 >= n)
            return false;
        
        boolean[] visited = new boolean[n];
        return hasPathDFS(graph, v1, v2, visited);
    }

    // Returns the shortest path between v1 and v2 in graph if it exists, in the form of an ArrayList. BFS implementation.
    public static ArrayList<Integer> getPathBFS(int[][] graph, int sv, int ev)
    {
        int n = graph.length;
        if(sv >= n || ev >= n)
            return null;
        
        // We could also keep the track of parents(or what node was responsible for adding a certain node in the queue) using a hashmap too, but for now we will maintain an array.
        int[] parents = new int[n];
        for(int i = 0; i < n; i++)
            parents[i] = -1;
        
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        visited[sv] = true;
        q.add(sv);
        
        while(!q.isEmpty())
        {
            int front = q.remove();
            if(front == ev)
                break;
            for(int  i = 0; i < n; i++)
            {
                if(graph[front][i] == 1 && (!visited[i]))
                {
                    q.add(i);
                    visited[i] = true;
                    parents[i] = front;
                }
            }
        }

        if(parents[ev] == -1)
            return null;
        
        ArrayList<Integer> res = new ArrayList<>();
        int curr = ev;
        while(curr != sv)
        {
            res.add(curr);
            curr = parents[curr];
        }
        res.add(curr);
        return res;
    }

    private static ArrayList<Integer> getPathDFS(int[][] graph, int sv, int ev, boolean[] visited)
    {
        if(ev == sv)
        {
            ArrayList<Integer> res = new ArrayList<>();
            res.add(ev);
            return res;
        }
        ArrayList<Integer> res;
        for(int i = 0; i < graph.length; i++)
        {
            if(graph[sv][i] == 1 && (!visited[i]))
            {
                visited[i] = true;
                res = getPathDFS(graph, i, ev, visited);
                if(res != null)
                {
                    res.add(sv);
                    return res;
                }
            }
        }
        return null;
    }

    // Returns the path between v1 and v2. Implementation using DFS. Doesn't need to be the shortest path.
    public static ArrayList<Integer> getPathDFS(int[][] graph, int sv, int ev)
    {
        int n = graph.length;
        if(sv >= n || ev >= n)
            return null;
        boolean[] visited = new boolean[n];
        visited[sv] = true;
        return getPathDFS(graph, sv, ev, visited);
    }

    // Returns true if the given graph is connected. False otherwise. BFS implementation.
    public static boolean isConnectedBFS(int[][] graph)
    {
        int n = graph.length;
        if(n == 0)
            return true;
        
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        visited[0] = true;
        q.add(0);

        while(!q.isEmpty())
        {
            int front = q.remove();
            for(int i = 0; i < n; i++)
            {
                if(graph[front][i] == 1 && (!visited[i]))
                {
                    q.add(i);
                    visited[i] = true;
                }
            }
        }

        for(boolean x: visited)
        {
            if(x == false)
                return false;
        }
        return true;
    }

    private static void isConnectedDFS(int[][] graph, int node, boolean[] visited)
    {
        if(visited[node])
            return;
        visited[node] = true;
        for(int i = 0; i < graph.length; i++)
        {
            if(graph[node][i] == 1 && (!visited[i]))
                isConnectedDFS(graph, i, visited);
        }
    }

    // Returns true if the given graph is connected. False otherwise. DFS implementation.
    public static boolean isConnectedDFS(int[][] graph)
    {
        int n = graph.length;
        if(n == 0)
            return true;
        boolean[] visited = new boolean[n];
        isConnectedDFS(graph, 0, visited);
        for(boolean x: visited)
        {
            if(x == false)
                return false;
        }
        return true;
    }

    // Takes input for a weighted undirected graph and returns it as an Edge Array
    public static Edge[] edgeListInput()
    {
        int e = s.nextInt();
        Edge[] res = new Edge[e];
        for(int i = 0; i < e; i++)
        {
            int v1 = s.nextInt();
            int v2 = s.nextInt();
            int weight = s.nextInt();
            res[i] = new Edge(v1, v2, weight);
        }
        return res;
    }

    // Finds and returns the parent of a given vertex. Used in the Union Find Algorithm for cycle detection in Kruskal's algorithm
    private static int parent(int vertex, int[] parent)
    {
        while(vertex != parent[vertex])
            vertex = parent[vertex];
        return vertex;
    }

    // Finds and returns the Minimum Spanning Tree using Kruskal's Algorithm
    public static Edge[] findMstUsingKruskals(Edge[] edgeList, int n)
    {
        Arrays.sort(edgeList);
        
        int[] parent = new int[n];
        for(int i = 0; i < n; i++)
            parent[i] = i;

        Edge[] res = new Edge[n - 1];
        int count = 0, i = 0;
        while(count < n - 1)
        {
            int v1 = edgeList[i].v1;
            int v2 = edgeList[i].v2;
            int p1 = parent(v1, parent);
            int p2 = parent(v2, parent);
            if(p1 != p2)
            {
                res[count] = edgeList[i];
                parent[p1] = p2;
                count++;
            }
            i++;
        }
        return res;
    }

    // Takes input from the user for a weighted graph and returns it as an adjacency matrix
    public static int[][] WeightedAdjGraphInput()
    {
        int n = s.nextInt();
        int e = s.nextInt();
        int[][] graph = new int[n][n];
        for(int i = 0; i < e; i++)
        {
            int v1 = s.nextInt();
            int v2 = s.nextInt();
            int weight = s.nextInt();
            graph[v1][v2] = weight;
            graph[v2][v1] = weight;
        }
        return graph;
    }

    // Finds and Returns the vertex having minimum weight from the list of unvisited vertices.
    private static int findUnvisitedVertexWithMinWeight(int[] weights, boolean[] visited)
    {
        int minWeightVertex = -1;
        for(int i = 0; i < weights.length; i++)
        {
            if((!visited[i]) && (minWeightVertex == -1 || (weights[i] < minWeightVertex)))
                minWeightVertex = i;
        }
        return minWeightVertex;
    }

    // Finds and returns the Minimum Spanning Tree using Prim's Algorithm
    public static Edge[] findMstUsingPrims(int[][] graph)
    {
        int n = graph.length;
        int[] parents = new int[n];
        int[] weights = new int[n];
        boolean[] visited = new boolean[n];
        for(int i = 1; i < n; i++)
            weights[i] = Integer.MAX_VALUE;
        
        for(int i = 0; i < n; i++)
        {
            int vertex = findUnvisitedVertexWithMinWeight(weights, visited);
            visited[vertex] = true;
            for(int j = 0; j < n; j++)
            {
                if(graph[vertex][j] != 0 && (!visited[j]))
                {
                    if(weights[j] > graph[vertex][j])
                    {
                        weights[j] = graph[vertex][j];
                        parents[j] = vertex;
                    }
                }
            }
        }

        Edge[] res = new Edge[n - 1];
        for(int i = 1; i < n; i++)
            res[i - 1] = new Edge(i, parents[i], weights[i]);
        return res;
    }

    // Finds and Returns the vertex having minimum distance from the list of unvisited vertices.
    private static int findUnvisitedVertexWithMinDistance(int[] distance, boolean[] visited)
    {
        int minVertex = -1;
        for(int i = 0; i < visited.length; i++)
        {
            if(!visited[i] && (minVertex == -1 || distance[i] < distance[minVertex]))
                minVertex = i;
        }
        return minVertex;
    }

    // Finds and returns the shortest path from source node to all other nodes using Djikstra's algorithm. The answer is returned as an int[] where res[i] denotes the total length of ith node from source vertex.
    public static int[] djikstra(int[][] graph, int source)
    {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        int[] distance = new int[n];
        for(int i = 0; i < n; i++)
            distance[i] = Integer.MAX_VALUE;
        distance[source] = 0;

        for(int i = 0; i < n - 1; i++)
        {
            int mv = findUnvisitedVertexWithMinDistance(distance, visited);
            visited[mv] = true;
            for(int j = 0; j < n; j++)
            {
                if(!visited[j] && graph[mv][j] != 0 && graph[mv][j] < Integer.MAX_VALUE)
                {
                    int distViaMV = distance[mv] + graph[mv][j];
                    if(distViaMV < distance[j])
                        distance[j] = distViaMV;
                }
            }
        }
        return distance;
    }

    public static void numOfIslandGroupsHelper(int[][] graph, int vertex, boolean[] visited)
    {
        visited[vertex] = true;
        for(int j = 0; j < graph.length; j++)
        {
            if(graph[vertex][j] == 1 && !visited[j])
                numOfIslandGroupsHelper(graph, j, visited);
        }
    }

    // Returns the number of groups of connected islands
    public static int numOfIslandGroups(int[][] graph)
    {
        int count = 0;
        int n = graph.length;
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++)
        {
            if(!visited[i])
            {
                count++;
                numOfIslandGroupsHelper(graph, i, visited);
            }
        }
        return count;
    }

    public static boolean isPath(char[][] mat, int r, int c, String str, int i, int[][] neighborDistance, boolean[][] currentlyVisited)
    {
        if(i >= str.length())
        {
            return true;
        }
        if(r < 0 || c < 0 || r >= mat.length || c >= mat[0].length || mat[r][c] != str.charAt(i) || currentlyVisited[r][c])
            return false;
        
        currentlyVisited[r][c] = true;
        for(int[] x: neighborDistance)
        {
            if(isPath(mat, r + x[0], c + x[1], str, i + 1, neighborDistance, currentlyVisited))
            {
                return true;
            }
        }
        currentlyVisited[r][c] = false;
        return false;
    }

    // Returns 1 if any path in the matrix can form the string str. Else 0. Neighbors are those which share edge or a corner with the current cell.
    public static int isPath(char[][] mat, String str)
    {
        int m = mat.length, n = mat[0].length;
        int[][] neighborDistance = new int[][]{{1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}};
        boolean[][] currentlyVisited = new boolean[m][n];
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if(mat[i][j] == 'C')
                {
                    if(isPath(mat, i, j, str, 0, neighborDistance, currentlyVisited))
                        return 1;
                }
            }
        }
        return 0;
    }

    private static boolean isCycle(char[][] graph, int r, int c, int pr, int pc, boolean[][] visited, int[][] neighbors)
    {
        if(r < 0 || c < 0 || r >= graph.length || c >= graph[0].length || graph[r][c] != graph[pr][pc])
            return false;
        
        if(visited[r][c])
            return true;

        visited[r][c] = true;
        for(int[] x: neighbors)
        {
            int newR = r + x[0], newC = c + x[1];
            if(newR == pr && newC == pc)
                continue;
            if(isCycle(graph, newR, newC, r, c, visited, neighbors))
                return true;
        }
        return false;
    }

    // Returns true if there is a cycle of same colored dots with length > 4. Otherwise, returns false. Cells sharing an edge with the current cell are considered neighbors.
    public static boolean connectingDots(char[][] graph)
    {
        int m = graph.length, n = graph[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] neighbors = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if(!visited[i][j] && (isCycle(graph, i, j, i, j, visited, neighbors)))
                    return true;
            }
        }
        return false;
    }

    private static int biggestPiece(int[][] graph, int r, int c, boolean[][] visited, int[][] neighbors)
    {
        if(r < 0 || c < 0 || r >= graph.length || c >= graph.length || visited[r][c] ||graph[r][c] == 0)
            return 0;
        
        visited[r][c] = true;
        int res = 1;
        for(int[] x: neighbors)
        {
            res += biggestPiece(graph, r + x[0], c + x[1], visited, neighbors);
        }
        return res;
    }

    // Returns the size of biggest piece of cake which contains of all 1's. The whole cake(graph) is square. Cells sharing an edge with the current cell are considered neighbors.
    public static int biggestPiece(int[][] graph)
    {
        int n = graph.length;
        boolean[][] visited = new boolean[n][n];
        int[][] neighbors = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int res = 0;
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if(!visited[i][j] && graph[i][j] == 1)
                    res = Math.max(res, biggestPiece(graph, i, j, visited, neighbors));
            }
        }
        return res;
    }

    private static int findThreeCycles(int[][] graph, int source, int vertex, int remainingMoves, boolean[] visited)
    {
        if(remainingMoves <= 0 && vertex == source)
            return 1;
        if(remainingMoves <= 0)
            return 0;
        
        int res = 0;
        for(int i = 0; i < graph.length; i++)
        {
            if(graph[vertex][i] == 1 && !visited[i])
                res += findThreeCycles(graph, source, i, remainingMoves - 1, visited);
        }
        return res;
    }

    // Finds and returns the number of 3 cycles present in the graph.
    public static int findThreeCycles(int[][] graph)
    {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        int res = 0;
        for(int i = 0; i < n; i++)
        {
            res += findThreeCycles(graph, i, i, 3, visited);
            visited[i] = true;
        }
        return res/2;
    }

    public static void main(String[] args)
    {
        int[][] graph = adjGraphInput();
        System.out.println(findThreeCycles(graph));
    }
}

class Edge implements Comparable<Edge> {
    int v1;
    int v2;
    int weight;

    public Edge()
    {

    }

    public Edge(int v1, int v2, int weight)
    {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge b)
    {
        return this.weight - b.weight;
    }
}