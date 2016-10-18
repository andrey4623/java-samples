/*
 * Copyright (c) 2016, Andrey Kolchanov.
 *
 * The task:
 *
 * Write an efficient algorithm to check if a string is a palindrome. A string is a
 * palindrome if the string matches the reverse of string.
 * Example: 1221 is a palindrome but not 1121.
 */

public class Palindrome {

    /**
     * Checks is the given string a palindrome.
     *
     * The complexity of the algorithm is O(n).
     *
     * @param   s        String
     * @return  boolean  The given string is a palindrome
     */

    public static boolean isPalindrome(String s) {
        int len = s.length();
        for (int i = 0, n = len / 2; i < n; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1))
                return false;
        }
        return true;
    }
}
