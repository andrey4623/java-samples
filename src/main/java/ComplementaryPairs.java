/*
 * Copyright (c) 2016, Andrey Kolchanov.
 *
 * The task:
 *
 * Write an efficient algorithm to find K-complementary pairs in a given array of
 * integers. Given Array A, pair (i, j) is K- complementary if K = A[i] + A[j].
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class ComplementaryPairs {

    public class Pair {
        private int x;
        private int y;
        private static final String FORMAT_STRING = "(%d,%d)";

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return String.format(FORMAT_STRING, getX(), getY());
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Pair other = (Pair) obj;
            if (x != other.x)
                return false;
            if (y != other.y)
                return false;
            return true;
        }
    }

    /**
     * Returns a list of complementary pairs in a given array.
     *
     * The idea of the algorithm:
     * 1. Put all integers from the array to a tree map. A key is an int,
     * a value is number of occurrences of the current integer in the original array.
     * 2. Use treeMap.keySet() to get sorted original array without duplicates.
     * 3. Run two pointers: from the beginning and from the end and calculate
     * the sum of integers. If the sum > k, move the right pointer to the left.
     * If the sum < k, move the left pointer to the right. If the sum == k,
     * we found an complimentary pair.
     *
     * The complexity of the algorithm is O(n*log(n)) because of sorting using a red-black tree.
     *
     * @param   a[]         int array
     * @param   k           complementary number
     * @return  List<Pair>  the list of Pair
     * @see                 Pair
     */

    public static List<Pair> getComplementaryPairs(final int[] a, final int k) {

        TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>();

        for (int i = 0; i < a.length; i++) {
            int tmpValue = treeMap.containsKey(a[i]) ? treeMap.get(a[i]) : 0;
            treeMap.put(a[i], ++tmpValue);
        }

        final Integer[] arr = new LinkedList<Integer>(treeMap.keySet()).toArray(new Integer[0]);
        List<Pair> result = new ArrayList<Pair>();
        int posLeft = 0;
        int posRight = arr.length - 1;

        while (posLeft < posRight) {
            if (arr[posLeft] + arr[posRight] == k) {
                result.add(new ComplementaryPairs().new Pair(arr[posLeft], arr[posRight]));
                posLeft++;
                posRight--;
            } else if (arr[posLeft] + arr[posRight] > k) {
                posRight--;
            } else if (arr[posLeft] + arr[posRight] < k) {
                posLeft++;
            }
        }

        return result;
    }
}
