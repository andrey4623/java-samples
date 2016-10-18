/*
 * Copyright (c) 2016, Andrey Kolchanov.
 *
 * The task:
 *
 * Given a large file that does not fit in memory (say 10GB), find the top 100000
 * most frequent phrases. The file has 50 phrases per line separated by a pipe (|).
 * Assume that the phrases do not contain pipe.
 *
 * Example line may look like: Foobar Candy | Olympics 2012 | PGA | CNET | Microsoft Bing ....
 *
 * The above line has 5 phrases in visible region.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class TopPhrases {

    /**
     * Returns a list of strings of the most frequent phrases in a text file.
     *
     * The idea of the algorithm:
     * 1. Read a file line by line and put all words to a tree map. A key is a word,
     * a value is number of occurrences of the current word in the file.
     * 2. Use treeMap.keySet() to get words (already) sorted by frequency.
     * 3. Return the first top words.
     *
     * The complexity of the algorithm is O(n*log(n)) because of sorting using a red-black tree.
     *
     * @param   inputFile     an input file
     * @param   top           find top the most frequent phrases
     * @param   delimiter     delimiter of words in the file
     * @return                list of strings
     */

    public static List<String> getMostFrequentPhrases(
                                    File inputFile,
                                    int top,
                                    final String delimiter)
            throws IOException {

        // Validate input parameters.
        if (top < 0)
            top = 0;

        if (top == 0)
            return new ArrayList<String>();

        if (!Files.exists(inputFile.toPath()))
            throw new FileNotFoundException();

        Map<String, Integer> phrases = new TreeMap<String, Integer>();
        BufferedReader reader = null;
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(inputFile);
        } catch (FileNotFoundException ex) {
            throw ex;
        }

        // Read line by line and add it to TreeMap.
        try {
            reader = new BufferedReader(fileReader);
            for (String line; (line = reader.readLine()) != null;) {
                StringTokenizer stringTokenizer = new StringTokenizer(line, delimiter);
                while (stringTokenizer.hasMoreTokens()) {
                    String phrase = stringTokenizer.nextToken();
                    Integer value = phrases.containsKey(phrase) ? phrases.get(phrase) : 0;
                    phrases.put(phrase, ++value);
                }
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                throw ex;
            }
        }

        // Keys in phrases.keySet() are already sorted.
        List<String> sortedKeySet = new LinkedList<String>(phrases.keySet());
        // An output ArrayList.
        ArrayList<String> result = new ArrayList<String>();

        // Select first top elements.
        int i = 0;
        ListIterator<String> listIterator = sortedKeySet.listIterator();
        while (listIterator.hasNext()) {
            if (i++ >= top)
                break;
            result.add(listIterator.next());
        }

        try {
            fileReader.close();
        } finally {
        }

        return result;
    }
}
