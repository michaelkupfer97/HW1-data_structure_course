//Michael Kupfer
import java.util.Arrays;

/**
 * Represents a singly data structure.
 *
 * @param <T> the type of elements stored in the data structure **/

public class DataStructure<T> {

    //array to store the data.
    private T[] arr;
    //size to know how many data stored in the array.
    private int size;


    /**
     * Default constructor for the HW1.DataStructure class.
     */
    public DataStructure() {
        this.size = 0;
        arr = (T[]) new Object[10];
    }

    /**
     * Inserts a new node in the index location of the data structure.
     * This should be done in O(n) operations.
     *
     * @param data  the data to be inserted
     * @param index the index the item should be inserted at.
     */
    public void add(T data, int index) throws Exception {
        arr = capCheck(arr);

        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index" + index + "is out of bounds.");

        for (int i = size; i > index; i--)
            arr[i + 1] = arr[i];

        arr[index] = data;
        size++;
    }

    /**
     * Inserts a new node at the end of the data structure.
     *
     * @param data the data to be inserted
     */
    public void addToEnd(T data) {
        arr= capCheck(arr);
        arr[size] = data;
        size++;
    }

    /**
     * Check if the array is full and if so, resize it.
     */
    private T[] capCheck(T[] arr) {
        if (size == arr.length) {
            return Arrays.copyOf(arr, arr.length * 2);
        } else {
            return arr;
        }
    }

    /**
     * Deletes a node with the given data from the data structure.
     * Deletes the first node with the given data in the data structure.
     * Print to screen if data deleted, if not, throw exception.
     * This should be done in O(n) operations.
     *
     * @param data the data to be deleted
     */
    public void delete(T data) throws Exception {
        boolean isDeleted = false;
        for (int i = 0; i < arr.length && !isDeleted; i++) {
            if (arr[i].equals(data)) {
                isDeleted = true;
                for (int j = i; j < size; j++) {
                    arr[j] = arr[j + 1];
                }
                arr[size] = null;
                size--;
                System.out.println("The Data has deleted");
            }
        }
        if (!isDeleted) throw new Exception("The data is not in the data structure.");
    }

    /**
     * Searches for a node with the given data in the data structure.
     * This should be done in O(n) operations.
     *
     * @param data the data to be searched
     * @return true if the data is found, false otherwise
     */
    public boolean contains(T data) {
        for (int i = 0; i < size ; i++)
            if (arr[i].equals(data)) return true;
        return false;
    }


    /**
     * Gets the size of the data structure.
     *
     * @return the number of elements in the data structure
     */
    public int size() {
        return size;
    }

    /***
     * Method that return the data in given index
     * @param index the index user want to get data from.
     * @return data from the index in array.
     * @throws Exception id the index is bigger then the size of the array.
     */
    public T get(int index)throws Exception{
        if (index>size)throw new IndexOutOfBoundsException("index out of bounds");
        return arr[index];
    }

    public void set(T data,int index ){
        arr[index]=data;
    }
    /**
     * Return the string value of the elements in a data structure from beginning to end, separated by commas
     */
    @Override
    public String toString() {
        String strArr = "[ ";
        for (int i = 0; i < size; i++) {
            strArr = strArr + arr[i].toString();
            if (i < size - 1)
                strArr += ", ";
        }
        strArr += "]";
        return strArr;
    }
}