import java.util.Scanner;

public class strings {

    public static String reverse_string(String str)
    {
        String res = "";
        for(int i = 0; i < str.length(); i++)
        {
            res = str.charAt(i) + res;
        }
        return res;
    }

    public static int num_of_words(String str)
    {
        int count = 0;
        for(int i = 0; i < str.length(); i++)
        {
            if(str.charAt(i) == ' ')
            {
                count++;
            }
        }
        return count + 1;
    }

    public static void print_chars(String str)
    {
        for(int i = 0; i < str.length(); i++)
        {
            System.out.println(str.charAt(i));
        }
    }

    public static boolean is_palindrome(String str)
    {
        for(int i = 0; i < str.length()/2; i++)
        {
            if(str.charAt(i) != str.charAt(str.length() - 1 - i))
                return false;
        }
        return true;
    }

    public static void print_substrings(String str)
    {
        for(int i = 0; i < str.length(); i++)
        {
            for(int j = i + 1; j <= str.length(); j++)
            {
                System.out.println(str.substring(i, j));
            }
        }
    }

    public static String reverse_word_wise(String str)
    {
        StringBuffer res = new StringBuffer("");
        int word_end = str.length();
        for(int i = str.length() - 1; i >= 0; i--)
        {
            if(str.charAt(i) == ' ')
            {
                res.append(str.substring(i + 1, word_end));
                res.append(" ");
                word_end = i;
            }
        }
        res.append(str.substring(0, word_end));
        return res.toString();
    }

    public static boolean is_permutation(String str1, String str2)
    {
        if(str1.length() != str2.length())
            return false;
        int[] arr = new int[26];
        for(int i = 0; i < str1.length(); i++)
        {
            arr[str1.charAt(i) - 97]++;
            arr[str2.charAt(i) - 97]--;
        }
        for(int i = 0; i < 26; i++)
        {
            if(arr[i] != 0)
                return false;
        }
        return true;
    }

    public static String remove_consecutive_duplicates(String str)
    {
        StringBuffer res = new StringBuffer("");
        char prev = '_';
        for(int i  = 0; i < str.length(); i++)
        {
            if(str.charAt(i) != prev)
            {
                prev = str.charAt(i);
                res.append(prev);
            }
        }
        return res.toString();
    }

    public static String reverse_each_word(String str)
    {
        int start = 0;
        StringBuffer res = new StringBuffer("");
        for(int i = 0; i < str.length(); i++)
        {
            if(str.charAt(i) == ' ')
            {
                res.append(reverse_string(str.substring(start, i)));
                res.append(" ");
                start = i + 1;
            }
        }
        res.append(reverse_string(str.substring(start)));
        return res.toString();
    }

    public static String remove_character(String str, char x)
    {
        StringBuffer res = new StringBuffer("");
        for(int i = 0; i < str.length(); i++)
        {
            if(str.charAt(i) != x)
                res.append(str.charAt(i));
        }
        return res.toString();
    }

    public static char highest_occuring_cahracter(String str)
    {
        char res = ' ';
        int max_count = 0;
        int[] arr = new int[26];
        for(int i = 0; i < str.length(); i++)
        {
            arr[str.charAt(i) - 97]++;
        }
        for(int i = 0; i < str.length(); i++)
        {
            if(arr[str.charAt(i) - 97] > max_count)
            {
                max_count = arr[str.charAt(i) - 97];
                res = str.charAt(i);
            }
        }
        return res;
    }

    public static String compress_string(String str)
    {
        if(str.length() == 0)
            return "";
        char curr, prev = str.charAt(0);
        int count = 1;
        StringBuffer res = new StringBuffer();
        for(int i = 1; i < str.length(); i++)
        {
            curr = str.charAt(i);
            if(curr == prev)
                count++;
            else
            {
                res.append(prev);
                if(count > 1)
                    res.append(count);
                count = 1;
            }
            prev = curr;
        }
        res.append(prev);
        if(count > 1)
            res.append(count);
        return res.toString();
    }

    public static void main(String[] args) 
    {
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        System.out.println(compress_string(str));
        s.close();
    }
}