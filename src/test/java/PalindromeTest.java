/*
 * Copyright (c) 2016, Andrey Kolchanov.
 */

import static org.junit.Assert.*;
import org.junit.Test;

public class PalindromeTest {
    // Test words.
    private static final String EVEN_PALINDROME = "alla";
    private static final String ODD_PALINDROME = "civic";
    private static final String EVEN_NOT_PALINDROME = "spring";
    private static final String ODD_NOT_PALINDROME = "cat";

    // Error messages.
    private static final String IS_PALINDROME_MESSAGE = "\"%s\" is a palindrome.";
    private static final String IS_NOT_PALINDROME_MESSAGE = "\"%s\" is not a palindrome.";
    private static final String EMPTY_STRING_IS_PALINDROME_MESSAGE =
            "An empty string is a palindrome";

    @Test
    public void testEmptyString() {
        assertTrue(EMPTY_STRING_IS_PALINDROME_MESSAGE, Palindrome.isPalindrome(""));
    }

    @Test
    public void testPalindromeEvenLength() {
        assertTrue(
                String.format(IS_PALINDROME_MESSAGE, EVEN_PALINDROME),
                Palindrome.isPalindrome(EVEN_PALINDROME));
    }

    @Test
    public void testPalindromeOddLength() {
        assertTrue(
                String.format(IS_PALINDROME_MESSAGE, ODD_PALINDROME),
                Palindrome.isPalindrome(ODD_PALINDROME));
    }

    @Test
    public void testNotPalindromeEvenLength() {
        assertFalse(
                String.format(IS_NOT_PALINDROME_MESSAGE, EVEN_NOT_PALINDROME),
                Palindrome.isPalindrome(EVEN_NOT_PALINDROME));
    }

    @Test
    public void testNotPalindromeOddLength() {
        assertFalse(
                String.format(IS_NOT_PALINDROME_MESSAGE, ODD_NOT_PALINDROME),
                Palindrome.isPalindrome(ODD_NOT_PALINDROME));
    }
}
