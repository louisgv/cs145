
import java.util.*;
/**
 * An Integer Specific data type which sort itself.
 *
 * <ul>
 * <li> Name: SortedIntList.java
 * <li> Description: Sorted Int List
 * <li> Class: Java 145
 * <li> Instructor: Ken Hang
 * <li> Date: Jan 28 2015
 * </ul>
 *
 * @author  Hai H Nguyen (Bill)
 * @version Winter 2015
 */
public class SortedIntList {

    public static final int DEFAULT_CAPACITY = 99;

    private int[] elementData;  // list of integers

    private int size;           // current number of elements in the list

    private boolean unique;     // indicate whether there should be duplications or not

    /**
     * Full Constructor, take a capacity and an indicator, then call Main Constructor.
     * @param unique        Indicate whether there should be duplications or not
     * @param capacity      Replacement for the DEFAULT_CAPACITY
     */
    public SortedIntList(boolean unique, int capacity){
        this(capacity);

        this.unique = unique;
    }

    /**
     * Main Constructor, take in a capacity and instantiate an array based on it.
     * @param capacity      Replacement for the DEFAULT_CAPACITY
     */
    public SortedIntList(int capacity){
        if (capacity < 0) {
            throw new IllegalArgumentException("Invalid capacity: " + capacity);
        }

        elementData = new int[capacity];

        size = 0;
    }

    /**
     * Sub Constructor, take an indicator, call Full Constructor with DEFAULT_CAPACITY
     * @param unique        Indicate whether there should be duplicates or not
     */
    public SortedIntList(boolean unique) {
        this(unique, DEFAULT_CAPACITY);
    }

    /**
     * Default Constructor, Call the Main Constructor with DEFAULT_CAPACITY
     */
    public SortedIntList(){
        this(DEFAULT_CAPACITY);
    }

    /**
     * @return          The maximum value from the List
     */
    public int max(){
        if (size == 0){
            throw new NoSuchElementException("Size: " + size);
        }

        return elementData[size-1];
    }

    /**
     * @return          The minimum value from the List
     */
    public int min(){
        if (size == 0){
            throw new NoSuchElementException("Size: " + size);
        }

        return elementData[0];
    }

    /**
     * @return          The value of the Unique Flag
     */
    public boolean getUnique(){
        return unique;
    }

    /**
     * Set the Value of the Unique Flag
     * @param value     The Flag to switch to
     */
    public void setUnique (boolean value){
        if ((unique = value) && size > 1){
            removeDuplicates();
        }
    }

    /**
     * @param index     Index to get Value
     * @return          Value at Index
     */
    public int get(int index) {
        checkIndex(index);

        return elementData[index];
    }

    /**
     * @return          The Size Field
     */
    public int size() {
        return size;
    }

    /**
     * Add the passed value into the elementData.
     * @param value     Element to be added
     */
    public void add(int value){
        int index = indexOf(value);

        if (!(index >= 0 && unique)){
            ensureCapacity(size + 1);

            if (index == -1){
                index = getInsertIndex (value);
            }

            insert(index,value);
        }
    }

    /**
     * @param value     Value looking for index
     * @return          Return the index to Insert the passed Value
     */
    private int getInsertIndex(int value){
        return -(Arrays.binarySearch(elementData, 0, size, value)+1);
    }

    /**
     * Insert the value at the index indicated
     * @param index     Index to insert the value
     * @param value     Value to be inserted
     */
    private void insert(int index, int value){
        for (int i = size; i > index; --i) {
            elementData[i] = elementData[i - 1];
        }

        elementData[index] = value;

        ++size;
    }

    /**
     * Remove all Duplicates
     */
    public void removeDuplicates(){
        int previousSize = size + 1;    // Ensure Space

        int [] tempData = new int[previousSize];

        size = 1;

        tempData[0] = elementData[0];

        for (int i = 0 ; i < previousSize-1; ++i){
            if (tempData[size-1] != elementData[i]){
                tempData[(++size)-1] = elementData[i];
            }
        }

        elementData = tempData;
    }

    /**
     * Remove the element at passed index
     * @param index     Index of Element to be Deleted
     */
    public void remove(int index) {
        checkIndex(index);

        --size;

        for (int i = index; i < size; i++) {
            elementData[i] = elementData[i + 1];
        }
    }

    /**
     * Set the Size to 0, thus negate the use of other functions.
     */
    public void clear() {
        size = 0;
    }

    /**
     * @param value     The value to check for index
     * @return          Index of the value given. Else return -1
     */
    public int indexOf(int value) {
        int index = Arrays.binarySearch(elementData, 0, size, value);

        if (index >= 0){
            return index;
        } else {
            return -1;
        }
    }

    /**
     * @param value     The value to check for existence
     * @return          True if elementData contains it, False otherwise
     */
    public boolean contains(int value) {
        return indexOf(value) >= 0;
    }

    /**
     * Check if index is within the size
     * @param index     Index to be checked
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
    }

    /**
     * @return      True if size is 0, False otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Check the capacity of the current list. If needed, double it.
     * @param capacity      Passed size to check
     */
    public void ensureCapacity(int capacity) {
        if (capacity > elementData.length) {
            int newCapacity = elementData.length * 2 + 1;

            if (capacity > newCapacity) {
                newCapacity = capacity;
            }

            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    /**
     * @return          Comma-separated, bracketed version of the list
     */
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            String out = "[" + elementData[0];

            for (int i = 1; i < size; i++) {
                out += ", " + elementData[i];
            }

            out += "]";

            return out;
        }
    }
}