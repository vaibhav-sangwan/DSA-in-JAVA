import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class priority_queue {

    public static Scanner s = new Scanner(System.in);

    // i is the index of element that we have to sink down. n is the size of our remaining heap.
    private static void sinkDown(int[] arr, int i, int n)
    {
        int c1 = (2 * i) + 1, c2 = (2 * i) + 2;
        if(c1 >= n)
            return;

        int min = i;
        if(arr[c1] < arr[min])
            min = c1;
        if(c2 < n && arr[c2] < arr[min])
            min = c2;

        if(min == i)
            return;
        int temp = arr[i];
        arr[i] = arr[min];
        arr[min] = temp;
        sinkDown(arr, min, n);
    }

    // Sorts the given array in decreasing order using in-place heap sort. Time Complexity for building the heap = O(n), Overall time complexity = O(n log n), Space Complexity = O(1). We have to return the sorted array in decreasing order, that's why we will use Min Heap.
    public static void heapSort(int[] arr)
    {
        int n = arr.length;

        // For building the heap in O(n) time complexity, we will sink down non leaf nodes. Non-leaf nodes start from (n/2) - 1 and go till 0.
        for(int i = (n/2) - 1; i >= 0; i--)
            sinkDown(arr, i, n);

        // We will remove the elements one by one from Heap and sink down one by one. This will result in a sorted array.
        for(int i = n - 1; i > 0; i--)
        {
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            sinkDown(arr, 0, i);
        }
        
    }
    // Returns an array of k smallest elements from the current array. Time Complexity - O(n log k), Space Complexity - O(k)
    public static int[] kSmallestElements(int[] arr, int k)
    {
        int[] res = new int[k];
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < k; i++)
            pq.add(arr[i]);
        for(int i = k; i < arr.length; i++)
        {
            if(arr[i] < pq.element())
            {
                pq.remove();
                pq.add(arr[i]);
            }
        }
        for(int i = 0; i < k; i++)
            res[k - 1 - i] = pq.poll();
        return res;
    }

    // Returns an array of k largest elements from the current array. Time Complexity - O(n log k), Space Complexity - O(k)
    public static int[] kLargestElements(int[] arr, int k)
    {
        int[] res = new int[k];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < k; i++)
            pq.add(arr[i]);
        for(int i = k; i < arr.length; i++)
        {
            if(arr[i] > pq.element())
            {
                pq.remove();
                pq.add(arr[i]);
            }
        }
        for(int i = 0; i < k; i++)
            res[i] = pq.poll();
        return res;
    }

    // Returns true if the given array represents a max heap. False otherwise
    public static boolean isMaxHeap(int[] arr)
    {
        int n = arr.length, i, c1, c2;
        for(i = 0; i < n; i++)
        {
            c1 = (2 * i) + 1;
            c2 = (2 * i) + 1;
            if(c1 < n && arr[c1] > arr[i])
                return false;
            if(c2 < n && arr[c2] > arr[i])
                return false; 
        }
        return true;
    }

    // Returns the kth largest element from the current array. Time Complexity - O(n log k), Space Complexity - O(k)
    public static int kthLargestElement(int[] arr, int k)
    {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < k; i++)
            pq.add(arr[i]);
        for(int i = k; i < arr.length; i++)
        {
            if(arr[i] > pq.element())
            {
                pq.poll();
                pq.add(arr[i]);
            }
        }
        return pq.remove();
    }

    // There's a queue for getting the tickets of a concert. If there's a person in the queue which has a higher priority than the person standing in front of the queue, he can ask the person in front of the queue to go to the end of the queue. Removing and Inserting in queue takes 0 minutes. Giving a ticket to the person standing in front of the queue takes 1 minute.
    public static int waitTime(int[] arr, int k)
    {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int n = arr.length;
        for(int i = 1; i < n; i++)
            pq.add(arr[i]);
        
        int res = 0;
        int i = 0;
        while(!(i == k && (arr[i] >= pq.peek())))
        {   
            if(i >= n)
                i = 0;
            if(arr[i] != -1 && arr[i] >= pq.peek())
            {
                res++;
                pq.remove();
                arr[i] = -1;
            }
            i++;
        }
        res++;
        return res;
    }

    public static void main(String[] args)
    {
        int[] arr = hashmaps.take_input();
        int k = s.nextInt();
        System.out.println(waitTime(arr, k));
    }
}

// Test class which packs a string as it's value and priority as it's priority in PQ.
class StringInteger {
    String val;
    int priority;

    public StringInteger(String val, int priority)
    {
        this.val = val;
        this.priority = priority;
    }
}

// A class which implements the comparator interface for StringInteger class
class priorityComparator implements Comparator<StringInteger> {
    @Override
    public int compare(StringInteger a, StringInteger b)
    {
        if(a.priority < b.priority)
            return -1;
        else if(a.priority == b.priority)
            return 0;
        else
            return 1;
    }
}


// Implementation of a Maximum Priority Queue using Complete Binary Tree.
class PQ<T> {
    private ArrayList<PQelement<T>> heap;

    public PQ()
    {
        heap = new ArrayList<>();
    }

    public int size()
    {
        return heap.size();
    }

    public boolean isEmpty()
    {
        return heap.size() == 0;
    }

    public T peek()
    {
        if(heap.size() == 0)
            throw new ArrayIndexOutOfBoundsException();
        return heap.get(0).val;
    }

    private void sinkDown(int i)
    {
        int c1 = (2 * i) + 1, c2 = (2 * i) + 2;
        if(i >= heap.size() || c1 >= heap.size())
            return;
        int max = i;
        if(heap.get(c1).priority > heap.get(max).priority)
            max = c1;
        if(c2 < heap.size() && heap.get(c2).priority > heap.get(max).priority)
            max = c2;
        if(max == i)
            return;
        PQelement<T> temp = heap.get(i);
        heap.set(i, heap.get(max));
        heap.set(max, temp);
        sinkDown(max);
    }

    public T remove()
    {
        if(heap.size() == 0)
            throw new ArrayIndexOutOfBoundsException();
        PQelement<T> res = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        sinkDown(0);
        return res.val;
    }

    private void bubbleUp(int i)
    {
        if(i <= 0)
            return;
        int parent = (i - 1)/2;
        if(heap.get(i).priority <= heap.get(parent).priority)
            return;
        PQelement<T> temp = heap.get(i);
        heap.set(i, heap.get(parent));
        heap.set(parent, temp);
        bubbleUp(parent);
    }

    public void add(T val, int priority)
    {
        PQelement<T> elem = new PQelement<>(val, priority);
        heap.add(elem);
        bubbleUp(heap.size() - 1);
    }
}

class PQelement<T> {
    T val;
    int priority;

    public PQelement(T val, int priority)
    {
        this.val = val;
        this.priority = priority;
    }
}