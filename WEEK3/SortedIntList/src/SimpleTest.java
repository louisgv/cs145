
import java.util.Random;
/**
 * An Updated simple test for SortedIntList.
 *
 * <ul>
 * <li> Name: SimpleTest.java
 * <li> Description: Sorted Int List Simple Test
 * <li> Class: Java 145
 * <li> Instructor: Ken Hang
 * <li> Date: Feb 2 2015
 * </ul>
 *
 * @author  Hai H Nguyen (Bill)
 * @version Winter 2015
 */
public class SimpleTest {

    //Random Duplicate Indicator
    public static final int MAX_INT = 9;

    public static void main(String[] args) {
        test1();

        test2();

        //ExtraTest.test3();	//These invoke  

        //ExtraTest.test4();  //test cases extracted

        //ExtraTest.test5();  //from model tests on canvas
    }
    
    //First Test
    //Test Uniqueness and Sort order 
    public static void test1() {
        boolean unique = false;

        SortedIntList list = new SortedIntList(unique);

        fill(list, 18);

        list.setUnique(unique = true);

        fill(list, 18);

        boolean failFlag = ((list.getUnique() != unique) || (!isSorted(list)));

        list.clear();

        failFlag = failFlag || (!list.toString().equals("[]"));

        if (failFlag){
            System.out.println("\nTest 1 Failed!\n");
        } else{
            System.out.println("\nTest 1 Passed!\n");
        }
    }
   
    //Second Test
    //Test toString, min, max and size
    public static void test2() {
        if (checkToString() && checkMinMax() && checkSize()) {
            System.out.println("\nTest 2 Passed!");
        } else {
            System.out.println("\nTest 2 Failed!");
        }
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

    // Test the toString Method.
    public static boolean checkToString() {
        SortedIntList list1 = new SortedIntList(10);

        fillNine(list1);

        list1.add(0);

        String test1 =
                "[-9, -8, -7, -6, -5, -4, -3, -2, -1, " +
                "0, 1, 2, 3, 4, 5, 6, 7, 8, 9]";

        SortedIntList list2 = new SortedIntList(true);

        for (int i = 9; i > 0; --i) {
            for (int j = 0; j < 9; ++j) {
                list2.add(i * 9);
            }
        }

        String test2 = "[9, 18, 27, 36, 45, 54, 63, 72, 81]";

        boolean failFlag = !( test1.equals(list1.toString()) && 
                              test2.equals(list2.toString()));

        System.out.println(failFlag ?
                "toString Failed!" : "toString Ok!");

        return (!failFlag);
    }

    // Test the min/max methods.
    public static boolean checkMinMax() {
        //Should be -9 and 9
        
        SortedIntList list = new SortedIntList();

        fillNine(list);

        boolean failFlag = list.min() != -9 && list.max() != 9;

        System.out.println(failFlag ?
                "min and max Failed!" : "min and max Ok!");

        return (!failFlag);
    }

    // Checks to see if the list has the appropriate size.
    public static boolean checkSize() {
        SortedIntList list = new SortedIntList();

        fillNine(list);

        boolean failFlag = list.size() != 18;

        System.out.println(failFlag ?
                "size Failed!" : "size Ok!");

        return (!failFlag);
    }

    //pre: list is a SortedIntList, size will not exceed l's capacity
    //post: list is filled with TEST_SIZE random values
    public static void fill(SortedIntList list, int size) {
        Random rand = new Random();

        list.clear();

        for (int i = 0; i < size; i++) {
            list.add(rand.nextInt(MAX_INT));
        }
    }

    // Add integers from -9 to 9 into the list
    public static void fillNine(SortedIntList list){
        for(int i = 9; i > 0; --i){
            list.add(i);

            list.add(-i);
        }
    }
}