/* The text file (1.13 GB) was used for test purposes. The size of the file is
 * enough for testing, since the Java max heap size is 1024m. For example, reading this file
 * into a string throws java.lang.OutOfMemoryError.
 *
 * The proposed algorithm successfully works with this file and shows a valid output.
 *
 * All tests below only check the basic functionality of the algorithm.
 * They don't use large text files.
 */

/*
 * Copyright (c) 2016, Andrey Kolchanov.
 */

import static org.junit.Assert.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class TopPhrasesTest {

    // Error messages.
    private final static String IOEXCEPTION_RAISED = "IOException was raised.";
    private final static String IOEXCEPTION_WAS_NOT_RAISED = "IOException was not raised.";
    private final static String EXPECTED_N_BUT_RECEIVED_M_RESULTS =
            "Expected %d results, but received %d.";
    private final static String EXPECTED_BUT_RECEIVED =
            "Wrong result at line %d: expected %s, but received %s.";
    private final static String UNABLE_TO_PREPARE_FILE =
            "Unable to write sample content to tmp file";
    private final static String CANNOT_CREATE_TEMPORARY_FILE =
            String.format("Unable to create a temporary file");
    private final static String NULL_PARAMETERS = "Parameters are null";

    //Test data.
    private final static String SOME_FILE_NOT_EXIST = "abc";
    private final static String TEMPORARY_FILE = "tmp.txt";
    private final static String FILE_CONTENT =
            "Foobar Candy | Apple | Olympics 2012 | Apple | CNET | PGA | "
                    + "CNET | Apple | Microsoft Bing ";
    private final static String DELIMITER = "|";
    private final static Integer TOP = 2;
    private final static ArrayList<String> EXPECTED_RESULTS = new ArrayList<String>();

    static {
        EXPECTED_RESULTS.add(" Apple ");
        EXPECTED_RESULTS.add(" CNET ");
    }

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public final void testFileNotExist() {
        File file = new File(SOME_FILE_NOT_EXIST);
        try {
            TopPhrases.getMostFrequentPhrases(file, 1, DELIMITER);
            fail(IOEXCEPTION_WAS_NOT_RAISED);
        } catch (IOException ioexception) {
        }
    }

    @Test
    public final void testEmptyFile() {
        File createdFile = prepareTemporaryFile(TEMPORARY_FILE, "");
        if (createdFile == null)
            fail(CANNOT_CREATE_TEMPORARY_FILE);

        ArrayList<String> top = null;

        try {
            top = (ArrayList<String>) TopPhrases.getMostFrequentPhrases(createdFile, 0, DELIMITER);
        } catch (IOException e) {
            e.printStackTrace();
            fail(IOEXCEPTION_RAISED);
        }

        verifyResults(top, new ArrayList<String>());
    }

    @Test
    public final void testGetMostFrequentPhrases() {
        File createdFile = prepareTemporaryFile(TEMPORARY_FILE, FILE_CONTENT);
        if (createdFile == null)
            fail(CANNOT_CREATE_TEMPORARY_FILE);

        ArrayList<String> top = null;

        try {
            top = (ArrayList<String>) TopPhrases.getMostFrequentPhrases(createdFile, TOP, DELIMITER);
        } catch (IOException e) {
            fail(IOEXCEPTION_RAISED);
        }

        verifyResults(top, EXPECTED_RESULTS);
    }

    @Test
    public final void testGetMostFrequentPhrasesBadArgument() {
        File createdFile = prepareTemporaryFile(TEMPORARY_FILE, FILE_CONTENT);
        if (createdFile == null)
            fail(CANNOT_CREATE_TEMPORARY_FILE);

        ArrayList<String> top = null;
        final int someNegativeNumber = -1;

        try {
            top = (ArrayList<String>) TopPhrases.getMostFrequentPhrases(createdFile, someNegativeNumber, DELIMITER);
        } catch (IOException e) {
            fail(IOEXCEPTION_RAISED);
        }

        verifyResults(top, new ArrayList<String>());
    }

    private final File prepareTemporaryFile(String path, String content) {
        File createdFile = null;
        try {
            createdFile = folder.newFile(path);
        } catch (IOException e) {
            e.printStackTrace();
            fail(CANNOT_CREATE_TEMPORARY_FILE);
        }
        if (createdFile == null)
            fail(CANNOT_CREATE_TEMPORARY_FILE);

        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(createdFile));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            fail(UNABLE_TO_PREPARE_FILE);
        }
        return createdFile;
    }

    private final void verifyResults(List<String> results, List<String> expectedResults) {
        if ((results == null) || (expectedResults == null))
            fail(NULL_PARAMETERS);

        if (results.size() != expectedResults.size())
            fail(String.format(
                    EXPECTED_N_BUT_RECEIVED_M_RESULTS,
                    expectedResults.size(), results.size()));

        for (int i = 0; i < expectedResults.size(); i++)
            if (!expectedResults.get(i).equals(results.get(i)))
                fail(String.format(
                        EXPECTED_BUT_RECEIVED,
                        (i + 1), expectedResults.get(i), results.get(i)));
    }
}
