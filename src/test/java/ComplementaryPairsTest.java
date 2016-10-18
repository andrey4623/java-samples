/*
 * Copyright (c) 2016, Andrey Kolchanov.
 */

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class ComplementaryPairsTest {

    private static final String INCORRENT_PAIRS_AMOUNT = "Wrong number of pairs.";
    private static final String PAIR_NOT_FOUND = "The pair %s is not found.";

    @Test
    public final void testGetComplementaryPairs() {
        final int[] a = { 1, 2, 3, 6, -100, 500, 10, 5 };
        final int k = 8;

        ArrayList<ComplementaryPairs.Pair> result = (ArrayList<ComplementaryPairs.Pair>)
                ComplementaryPairs.getComplementaryPairs(a, k);

        ComplementaryPairs.Pair pair1 = new ComplementaryPairs().new Pair(2, 6);
        ComplementaryPairs.Pair pair2 = new ComplementaryPairs().new Pair(3, 5);

        assertTrue(INCORRENT_PAIRS_AMOUNT, result.size() == 2);
        assertTrue(String.format(PAIR_NOT_FOUND, pair1), result.contains(pair1));
        assertTrue(String.format(PAIR_NOT_FOUND, pair2), result.contains(pair2));
    }

    @Test
    public final void testOnlyComplementaryPairs() {
        final int[] a = { 1, 2, 3, 8, 7, 6 };
        final int k = 9;

        ArrayList<ComplementaryPairs.Pair> result = (ArrayList<ComplementaryPairs.Pair>)
                ComplementaryPairs.getComplementaryPairs(a, k);

        assertTrue(INCORRENT_PAIRS_AMOUNT, result.size() == 3);
    }

    @Test
    public final void testNoComplementaryPairs() {
        final int[] a = { 1, 2, 3, 6, -100, 500, 10, 5 };
        final int k = Integer.MAX_VALUE;

        ArrayList<ComplementaryPairs.Pair> result = (ArrayList<ComplementaryPairs.Pair>)
                ComplementaryPairs.getComplementaryPairs(a, k);

        assertTrue(result.size() == 0);
    }

    @Test
    public final void testOnlyOneValue() {
        final int[] a = { 1 };
        final int k = Integer.MAX_VALUE;

        ArrayList<ComplementaryPairs.Pair> result = (ArrayList<ComplementaryPairs.Pair>)
                ComplementaryPairs.getComplementaryPairs(a, k);

        assertTrue(result.size() == 0);
    }

    @Test
    public final void testEmptyArray() {
        final int[] a = {};
        final int k = 8;

        ArrayList<ComplementaryPairs.Pair> result = (ArrayList<ComplementaryPairs.Pair>)
                ComplementaryPairs.getComplementaryPairs(a, k);

        assertTrue(result.size() == 0);
    }
}
