import java.util.Random;

/**
 * Created by 844920234 on 2/2/2015.
 */
public class ExtraTest {

    // number of elements to use in the checkAdd and checkIndexOf tests
    public static final int BASIC_TEST_SIZE = 10;
    // number of elements to use in the checkSpeed test (should be very large)
    public static final int SPEED_TEST_SIZE = 100000;
    public static final int TEST_SIZE = 25;
    //the lower this is, the more duplicates there will be
    public static final int MAX_INT = 10;
    public static final int REPETITIONS = 9;
    // maximum list size to use in a test (start small, then increase it)
    public static final int MAX_TEST_SIZE = 99;
    // set to false if you don't want to see printlns for every passed test run
    public static final boolean VERBOSE = false;

    public static void test3() {
        checkAdd(BASIC_TEST_SIZE);
        checkIndexOf(BASIC_TEST_SIZE);
        checkSpeed(SPEED_TEST_SIZE);
        checkToString();
        checkMinMax();
        System.out.println("\nTest 3 Passed!\n");
    }

    public static void test4() {
        SortedIntList list;
        int testsFailed = 0;

        //test the first constructor
        System.out.println("Testing SortedIntList(true, " + 10 + ")");
        list = new SortedIntList(true, 10);
        fill(list, 18);
        if (!test(list, true)) {
            System.out.println("    Failed!");
            testsFailed++;
        } else {
            System.out.println("    Passed!");
        }

        System.out.println();

        //test the second constructor
        System.out.println("Testing SortedIntList(true, " + TEST_SIZE + ")");
        list = new SortedIntList(true, TEST_SIZE);
        fill(list, TEST_SIZE);
        if (!test(list, true)) {
            System.out.println("    Failed!");
            testsFailed++;
        } else {
            System.out.println("    Passed!");
        }

        System.out.println();

        //test the third constructor
        System.out.println("Testing SortedIntList(false, " + 10 + ")");
        list = new SortedIntList(false, 10);
        fill(list, SortedIntList.DEFAULT_CAPACITY);
        if (!test(list, false)) {
            System.out.println("    Failed!");
            testsFailed++;
        } else {
            System.out.println("    Passed!");
        }

        System.out.println();

        //test the forth constructor
        System.out.println("SortedIntList(false, " + TEST_SIZE + ")");
        list = new SortedIntList(false, TEST_SIZE);
        fill(list, TEST_SIZE);
        if (!test(list, false)) {
            System.out.println("    Failed!");
            testsFailed++;
        } else {
            System.out.println("    Passed!");
        }

        if (testsFailed > 0) {
            System.out.println("\n" + testsFailed + " tests failed!");
        } else {
            System.out.println("\nTest 4 passed!");
        }
    }

    public static void test5() {
        // the program performs 10 tests each for test sizes from 0 to 99
        for (int testSize = 0; testSize <= MAX_TEST_SIZE; testSize++) {
            for (int i = 0; i < REPETITIONS; i++) {
                SortedIntList list1 = new SortedIntList(false, testSize);
                testTrue(list1, testSize);
                SortedIntList list2 = new SortedIntList(false, 2 * testSize);
                testFalse(list2, testSize);
            }
        }
        System.out.println("\nTest 5 passed!");
    }

    // This method adds a series of numbers to the list and makes sure that
    // the size increases and that it produces a sorted list.
    public static void checkAdd(int testSize) {
        System.out.println("Beginning checkAdd basic add test of size " + testSize);
        SortedIntList list = new SortedIntList(false, testSize);
        Random r = new Random();
        for (int i = 0; i < testSize; i++) {
            // remember old list contents in case of a problem
            String old = list.toString();
            // pick a number between -testSize and +testSize, add to list
            int next = r.nextInt(2 * testSize + 1) - testSize;

            int expectedSize = i + 1;

            // add to end of list
            list.add(next);

            // check list size and make sure list is sorted
            if (list.size() != expectedSize) {
                throw new RuntimeException("\n\nafter adding " + expectedSize
                        + " items, size = " + list.size() + " (should be " + expectedSize + ")\n");
            } else if (!isSorted(list)) {
                throw new RuntimeException(
                        "\n\nadding " + next + " failed; list is not sorted.\n" +
                                "List before add = " + old + ";\nlist after  add = " + list + "\n");
            }
        }
        System.out.println("    Passed!\n");
    }

    // This method adds values to the list and uses a boolean array to keep
    // track of what values have been added.  It then checks often to make
    // sure that indexOf returns values that make sense given the set of
    // values that have been added to the list.
    public static void checkIndexOf(int testSize) {
        System.out.println("Beginning checkIndexOf basic indexOf test of size " + testSize);
        SortedIntList list = new SortedIntList(false, testSize);
        Random r = new Random();
        // numbers will be between -testSize and +testSize
        boolean[] chosen = new boolean[2 * testSize + 1];
        checkIndexes(testSize, chosen, list);
        for (int i = 0; i < testSize; i++) {
            // pick a number between -testSize and +testSize, add to list
            int next = r.nextInt(2 * testSize + 1) - testSize;
            list.add(next);
            chosen[next + testSize] = true;
            checkIndexes(testSize, chosen, list);
        }
        System.out.println("    Passed!\n");
    }

    // This method constructs a very large list of even numbers and calls
    // indexOf many times to make sure that the code is fast.
    public static void checkSpeed(int testSize) {
        System.out.println("Beginning checkSpeed indexOf test of size " + testSize);

        // keep track of starting time before constructing
        long start = System.currentTimeMillis();

        // fill up the list with even numbers
        int dot = testSize / 10;
        SortedIntList list = new SortedIntList(false, testSize);
        System.out.print("    Building list of the first " + testSize + " multiples of 2: ");
        boolean addTooSlow = false;
        for (int i = 0; i < testSize; i++) {
            list.add(2 * i);
            if (i % dot == 0) {
                System.out.print(".");
            }
            if (!addTooSlow && System.currentTimeMillis() >= start + testSize / 20) {
                System.out.print(" (add is too slow) ");
                addTooSlow = true;
            }
        }
        System.out.println();
        double elapsed = (System.currentTimeMillis() - start) / 1000.0;
        // System.out.println("    construction took " + elapsed + " seconds");

        // keep track of starting time before indexOf tests
        System.out.print("    Checking indexOf each element: ");
        start = System.currentTimeMillis();
        dot = testSize / 10;
        for (int i = 0; i < testSize; i++) {
            for (int j = 0; j < 100; j++) {
                int actualIndex = list.indexOf(2 * i);
                if (actualIndex != i) {
                    System.out.println();
                    throw new RuntimeException(
                            "\n\nindexOf " + 2 * i + " should have returned: " +
                                    i + ",\n  but your method actually returned: " +
                                    actualIndex + "\n");
                }
            }
            if (System.currentTimeMillis() >= start + testSize / 5) {
                System.out.println();
                throw new RuntimeException(
                        "\n\nindexOf appears to be running too slowly.\n");
            }
            if (i % dot == 0) {
                System.out.print(".");
            }
        }
        double elapsed2 = (System.currentTimeMillis() - start) / 1000.0;
        if (addTooSlow) {
            System.out.println();
            throw new RuntimeException(
                    "\n\nYour add method appears to have run too slowly.  (runtime: " + elapsed + " seconds)\n");
        }
        System.out.println("\n    Passed!  (runtime: " + elapsed2 + " seconds)\n");
    }

    // returns true if list is sorted, false otherwise
    public static boolean isSorted(SortedIntList list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    // Compares the boolean array to the SortedIntList to make sure that
    // they match.  If a number has not been chosen, then its index should be
    // reported as -1.  If it has been chosen, then it should have a
    // non-negative index and the number should actually be at that index.
    public static boolean checkIndexes(int testSize, boolean[] chosen, SortedIntList list) {
        for (int i = 0; i < chosen.length; i++) {
            int original = i - testSize;
            int index = list.indexOf(original);

            // start by assuming it's not bad
            boolean bad = false;

            // then check for bad cases
            if (chosen[i] && (index < 0 || index >= list.size()
                    || list.get(index) != original)) {
                bad = true;
            } else if (!chosen[i] && index >= 0) {
                bad = true;
            }
            if (bad) {
                String result = "\n\n" + original + " should not have index " + index
                        + "\nlist = " + list + "\n";
                throw new RuntimeException(result);
            }
        }
        return true;
    }

    // Briefly tests the toString method for unique and non-unique lists.
    public static void checkToString() {
        System.out.println("Beginning toString test (should be [-1, 2, 5, 17] and [3, 6, 8, 42])");
        SortedIntList list = new SortedIntList(5);
        list.add(5);
        list.add(-1);
        list.add(17);
        list.add(2);
        System.out.println("    " + list.toString());

        SortedIntList list2 = new SortedIntList(true);
        list2.add(6);
        list2.add(3);
        list2.add(6);
        list2.add(42);
        list2.add(8);
        System.out.println("    " + list2.toString());
        System.out.println();
    }

    // Briefly tests the min/max methods.
    public static void checkMinMax() {
        System.out.println("Beginning min/max test (should be -6 and 17)");
        SortedIntList list = new SortedIntList();
        list.add(5);
        list.add(-6);
        list.add(17);
        list.add(2);
        System.out.println("    min = " + list.min());
        System.out.println("    max = " + list.max());
        System.out.println();
    }

    //pre:  l is a SortedIntList, size will not exceed l's capacity
    //post: l is filled with TEST_SIZE random values
    public static void fill(SortedIntList list, int size) {
        Random rand = new Random();

        //fill l with size elements
        for (int i = 0; i < size; i++) {
            list.add(rand.nextInt(MAX_INT));
        }
    }

    //pre: l is a filled SortedIntList, unique should be true if all values
    //     should be unique
    //post: tests is l is a valid SortedIntList and prints errors
    public static boolean test(SortedIntList l, boolean unique) {
        boolean ret = true;
        String error = "";

        //loop through the list checking for order and duplicates
        for (int i = 1; i < l.size(); i++) {
            //check for duplicates if the list is supposed to be unique
            if (unique && l.get(i - 1) == l.get(i)) {
                ret = false;
                error += "\n\tDuplicate value " + l.get(i) + " found at indexes "
                        + (i - 1) + " and " + i;
            }
            //check for order
            if (l.get(i - 1) > l.get(i)) {
                ret = false;
                error += "\n\tNot in sorted order at indexes "
                        + (i - 1) + " and " + i;
            }
        }

        //print the error and the list if an error exists
        if (!ret) {
            System.out.println(error);
            System.out.println();
            System.out.println("\t" + l);
            System.out.println();
        }

        return ret;
    }

    // This method tests the case where unique is set to true most of the time
    public static void testTrue(SortedIntList list, int testSize) {
        if (VERBOSE) {
            System.out.println("Beginning testTrue basic getUnique/setUnique test of size " + testSize);
        }
        setAndTestUnique(list, true, testSize);
        boolean[] chosen = new boolean[testSize];
        int count = 0;
        Random r = new Random();

        // this loop generates random numbers until all test numbers have
        // been added to the list
        while (count < testSize) {
            int next = r.nextInt(testSize);
            list.add(next);
            if (!chosen[next]) {
                count++;
                chosen[next] = true;
            }
            checkSize("while adding initial numbers to list", list, count, testSize);
        }

        // make sure we can turn unique off and on again
        setAndTestUnique(list, false, testSize);
        setAndTestUnique(list, true, testSize);
        checkSize("after setting unique to false and then back to true", list, testSize, testSize);

        // batter the list with duplicates to make sure it works
        for (int i = 0; i < testSize * 10; i++) {
            int next = r.nextInt(testSize);
            list.add(next);
        }
        checkSize("after attempting to add many duplicates", list, testSize, testSize);

        if (VERBOSE) {
            System.out.println("    Passed!");
        }
    }

    // This method tests the case where unique is set to false most of the time
    public static void testFalse(SortedIntList list, int testSize) {
        if (VERBOSE) {
            System.out.println("Beginning testFalse basic getUnique/setUnique test of size " + testSize);
        }
        setAndTestUnique(list, false, testSize);
        boolean[] chosen = new boolean[testSize];
        int count = 0;
        Random r = new Random();

        // this loop will add each test number exactly once to the list
        while (count < testSize) {
            int next = r.nextInt(testSize);
            if (!chosen[next]) {
                list.add(next);
                count++;
                chosen[next] = true;
            }
            checkSize("after adding many distinct values to the list", list, count, testSize);
        }

        // then we add testSize duplicates to the list
        for (int i = 1; i <= testSize; i++) {
            int next = r.nextInt(testSize);
            list.add(next);
            checkSize("after adding a duplicate of value " + next, list, testSize + i, testSize);
        }

        // and make sure that they are removed when we set unique back to true
        setAndTestUnique(list, true, testSize);
        checkSize("after setting unique to true", list, testSize, testSize);

        // then add testSize more duplicates
        setAndTestUnique(list, false, testSize);
        for (int i = 1; i <= testSize; i++) {
            int next = r.nextInt(testSize);
            list.add(next);
            checkSize("after setting unique back to false and then adding a duplicate of value " + next, list, testSize + i, testSize);
        }

        // and again make sure they are removed when unique is set to false
        setAndTestUnique(list, true, testSize);
        checkSize("after setting unique back to true (duplicates should be removed)", list, testSize, testSize);

        if (VERBOSE) {
            System.out.println("    Passed!");
        }
    }

    // sets the unique property to the given value and makes sure that
    // getUnique returns the same value
    public static void setAndTestUnique(SortedIntList list, boolean unique,
                                        int testSize) {
        list.setUnique(unique);
        if (list.getUnique() != unique) {
            throw new RuntimeException(
                    "\n\ngetUnique = " + list.getUnique() +
                            " after setting it to " + unique +
                            " with test size = " + testSize + "\n");
        }
    }

    // Checks to see if the list has the appropriate size.
    public static void checkSize(String message, SortedIntList list, int size, int testSize) {
        if (list.size() != size) {
            throw new RuntimeException("\n\n" + message +
                    ",\nsize = " + list.size() + " when it should be " + size +
                    "\ntest size = " + testSize +
                    "\nyour list = " + list + "\n\n" +
                    "(if your size does match the number of elements in your list, \n" +
                    "this error probably means you have not properly added the \n" +
                    "number of elements requested by the test case.)\n");
        }
    }

    // Print the String and see if it is Unique or not.
    private static void printUnique(SortedIntList list) {
        System.out.println(list.getUnique() ? ("Is Unique") : ("Not Unique"));
        System.out.println("list = " + list);
        System.out.println(list.size());
    }
}
