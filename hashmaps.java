import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public class hashmaps {

    public static Scanner s = new Scanner(System.in);

    public static int[] take_input()
    {
        int n = s.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++)
            arr[i] = s.nextInt();
        return arr;
    }

    // Maintains the order of elements in which they occur in the array but removes duplicates elements from it.
    public static ArrayList<Integer> remove_duplicates(int[] arr)
    {
        HashMap<Integer, Boolean> map = new HashMap<>();
        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 0; i < arr.length; i++)
        {
            if(!map.containsKey(arr[i]))
            {
                map.put(arr[i], true);
                res.add(arr[i]);
            }
        }
        return res;
    }

    // Returns the maximum frequency number present in the array
    public static int maximum_freq_number(int[] arr)
    {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++)
        {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        int res = 0, max_count = Integer.MIN_VALUE;
        for(Integer k: map.keySet())
        {
            if(map.get(k) > max_count)
            {
                max_count = map.get(k);
                res = k;
            }
        }
        return res;
    }

    // Prints the intersection of two arrays
    public static void print_intersection(int[] arr1, int[] arr2)
    {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int x: arr1)
            map.put(x, map.getOrDefault(x, 0) + 1);
        for(int x: arr2)
        {
            if(map.containsKey(x) && map.get(x) > 0)
            {
                System.out.print(x + " ");
                map.put(x, map.get(x) - 1);
            }
        }
        System.out.println();
    }

    // Returns the count of total number of pairs in an array which sum upto 0.
    public static int pair_sum_0(int[] arr)
    {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int x: arr)
            map.put(x, map.getOrDefault(x, 0) + 1);
        int res = 0;
        for(Integer k: map.keySet())
        {
            if(k == 0) // when k = 0, -k will become 0 too, and we need to handle that case specially.
            {
                res += map.get(k) * (map.get(k) - 1)/2;
                map.put(k, 0);
                continue;
            }
            if(map.containsKey(-k))
                res += (map.get(k) * map.get(-k));
            map.put(k, 0);
        }
        return res;
    }

    // Given a string str, this function returns a unique string with non duplicate characters.
    public static String remove_duplicates_from_string(String str)
    {
        HashMap<Character, Boolean> map = new HashMap<>();
        String res = "";
        for(int i = 0; i < str.length(); i++)
        {
            if(!map.containsKey(str.charAt(i)))
            {
                res += str.charAt(i);
                map.put(str.charAt(i), true);
            }
        }
        return res;
    }

    // Finds out the longest consecutive sequence in an unsorted array of integers and return the result as an array
    public static int[] longestConsecutieSequence(int[] arr)
    {
        if(arr == null)
            return null;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int x: arr)
            map.put(x, 0);
           
        int max = 0;
        int[] res = new int[2];
        for(int k: arr)
        {
            int curr = 1, temp = k + 1;
            while(map.containsKey(temp))
            {
                if(map.get(temp) == 0)
                {
                    map.put(temp, 1);
                    curr++;
                    temp++;
                }
                else
                {
                    curr += map.get(temp);
                    break;
                }
            }
            map.put(k, curr);
            if(curr > max)
            {
                res[0] = k;
                res[1] = k + curr - 1;
                max = curr;
            }
        }
        return res;
    }

    // Returns the count of total number of pairs in an array whose absolute difference results in K.
    public static int pairDifferenceCount(int[] arr, int k)
    {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int x: arr)
            map.put(x, map.getOrDefault(x, 0) + 1);
        int res = 0;
        if(k == 0)
        {
            for(int key: map.keySet())
                res += map.get(key) * (map.get(key) - 1)/2;
        }
        else
        {
            for(int key: map.keySet())
            {
                res += (map.get(key) * map.getOrDefault(k + key, 0));
                res += (map.get(key) * map.getOrDefault(-k + key, 0));
                map.put(key, 0);
            }
        }
        return res;
    }

    // Returns the length of the largest subarray whose sum is 0
    public static int largestSubsetZeroSum(int[] arr)
    {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for(int i = 1; i < arr.length; i++)
            arr[i] += arr[i - 1];
        int res = 0;
        for(int i = 0; i < arr.length; i++)
        {
            if(!map.containsKey(arr[i]))
                map.put(arr[i], i);
            else
            {
                if(i - map.get(arr[i]) > res)
                    res = i - map.get(arr[i]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = take_input();
        System.out.println(largestSubsetZeroSum(arr));
    }
}

class Map<K, V> {
    private int bucketSize, count;
    private ArrayList<MapNode<K, V>> arr;

    public Map()
    {
        bucketSize = 5;
        arr = new ArrayList<>();
        for(int i = 0; i < bucketSize; i++)
            arr.add(null);
    }

    public Map(int size)
    {
        bucketSize = size;
        arr = new ArrayList<>();
        for(int i = 0; i < bucketSize; i++)
            arr.add(null);
    }

    public void insert(K key, V value)
    {
        if(((float)count/bucketSize) >= 0.7)
            rehash();
        int index = key.hashCode()%bucketSize;
        MapNode<K, V> head = arr.get(index);
        while(head != null)
        {
            if(head.key.equals(key))
            {
                head.value = value;
                return;
            }
            head = head.next;
        }
        MapNode<K, V> node = new MapNode<>(key, value);
        node.next = arr.get(index);
        arr.set(index, node);
        count++;
    }

    private void rehash()
    {
        ArrayList<MapNode<K, V>> temp = arr;
        arr = new ArrayList<>();
        count = 0;
        bucketSize *= 2;
        for(int i = 0; i < bucketSize; i++)
            arr.add(null);
        MapNode<K, V> head = null;
        for(int i = 0; i < temp.size(); i++)
        {
            head = temp.get(i);
            while(head != null)
            {
                insert(head.key, head.value);
                head = head.next;
            }
        }
    }

    public V get(K key)
    {
        int index = key.hashCode()%bucketSize;
        MapNode<K, V> node = arr.get(index);
        while(node != null)
        {
            if(node.key.equals(key))
                return node.value;
            node = node.next;
        }
        throw new NullPointerException();
    }

    public boolean contains(K key)
    {
        int index = key.hashCode()%bucketSize;
        MapNode<K, V> node = arr.get(index);
        while(node != null)
        {
            if(node.key.equals(key))
                return true;
            node = node.next;
        }
        return false;
    }

    public V delete(K key)
    {
        int index = key.hashCode()%bucketSize;
        MapNode<K, V> node = arr.get(index), prev = node;
        while(node != null)
        {
            if(node.key.equals(key))
            {
                if(prev != node)
                    prev.next = node.next;
                else
                    arr.set(index, node.next);
                count--;
                return node.value;
            }
            prev = node;
            node = node.next;
        }
        return null;
    }

    public int size()
    {
        return count;
    }
}

class MapNode<K, V> {
    K key;
    V value;
    MapNode<K, V> next;

    public MapNode(K key, V value)
    {
        this.key = key;
        this.value = value;
    }
}