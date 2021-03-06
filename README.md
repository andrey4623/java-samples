# Java Samples

This repo has some algorithms implemented in Java.

Please see source files for algorithms' ideas and explanations.

### The list of algorithms:

**1. Palindrome**

Write an efficient algorithm to check if a string is a palindrome. A string is a palindrome if the string matches the reverse of string. Example: 1221 is a palindrome but not 1121.

**2. K-complementary pairs**

Write an efficient algorithm to find K-complementary pairs in a given array of
integers. Given Array A, pair (i, j) is K- complementary if K = A[i] + A[j].

**3. The most frequent phrases**

Given a large file that does not fit in memory (say 10GB), find the top 100000
most frequent phrases. The file has 50 phrases per line separated by a pipe (|).
Assume that the phrases do not contain pipe.
Example line may look like: Foobar Candy | Olympics 2012 | PGA | CNET | Microsoft Bing ....

The above line has 5 phrases in visible region.

## Requirements

- Java 1.8 or newer
- Apache Maven 3.3.9 or newer

## Building

```sh
$ mvn clean install
```

## License

MIT
