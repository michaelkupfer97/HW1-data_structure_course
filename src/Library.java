//Michael Kupfer
//ID 209493246
import java.awt.*;
import java.util.Objects;


/**
 * A class for managing a library. The underlying data structure for this class minimizes memory use.
 */
public class Library {

    private static int idx = 0;
    private DataStructure<Book> books;
    private DataStructure<Book> loanedBooks;

    /**
     * Constructor creates a brand-new library with no books.
     * create another empty array to the future borrowed books.
     */
    public Library() {
        this.books = new DataStructure<>();
        this.loanedBooks = new DataStructure<>();
    }

    /***
     * Adds a new book to the library with a copy quantity of one.
     * @param name  - the name of the book
     * @param author - the author of the book
     */
    public void addBook(String name, String author) {
        books.addToEnd(new Book(name, author));
    }

    /***
     * Removes a book from the library based on its unique ID.
     * @param uniqueID- the unique ID of the book to be removed.
     * @throws Exception if the book isn't in the library.
     */
    public void removeBooks(int uniqueID) throws Exception {
        boolean isDeleted = false;
        for (int i = 0; i < books.size() && !isDeleted; i++) {
            Book currentBook = books.get(i);
            if (currentBook.uniqueID == uniqueID) {
                books.delete(currentBook);
                isDeleted = true;
            }
        }
        if (!isDeleted) throw new Exception("The Book is not in the Library.");
    }

    /***
     * Borrows the book with the specified unique ID.
     * using a flag to stop when book found.
     * using a borrowed book to return to user if found and borrowed.
     * @param uniqueID - the unique ID of the book to be borrowed.
     * @return book that user want to borrow.
     * @throws Exception if the book isn't in the library.
     */
    public Book borrowBook(int uniqueID) throws Exception {
        for (int i = 0; i < books.size(); i++) {
            Book currentBook = books.get(i);
            if (currentBook.uniqueID == uniqueID) {
                loanedBooks.addToEnd(currentBook);
                return currentBook;
            }
        }
        return null; // Book not found
    }

    /***
     * method that let user borrow all the books of author in library
     * all the borrowed books will be added to the loaned book array.
     * @param author_name - The name of the author we would like to borrow all his books.
     * @return An array of all the books in the library of the author given.
     * @throws Exception if the author has no books in library.
     */
    public DataStructure<Book> borrowAllBooks(String author_name) throws Exception {
        DataStructure<Book> borrowedBooks = new DataStructure<>();
        for (int i = 0; i < books.size(); i++) {
            Book currentBook = books.get(i);
            if (currentBook.author.equals(author_name)) {
                borrowedBooks.addToEnd(currentBook);
                loanedBooks.addToEnd(currentBook);
            }
        }
        return borrowedBooks;
    }

    /***
     * The function receives an ID of a book and returns if its borrowed .
     * @param uniqueID - the unique ID of the book
     * @return If the book is borrowed the function will return true, otherwise it will return false
     * @throws Exception if the index of i of get function is out of bounds.
     */
    public boolean isBorrowed(int uniqueID) throws Exception {
        //check the loaned books array if the book we look is there.
        for (int i = 0; i < loanedBooks.size(); i++) {
            Book currentBook = loanedBooks.get(i);
            //check if the book unique id is the same.
            if (currentBook.uniqueID == uniqueID)
                return true;
        }
        return false;
    }

    /***
     * Returns the total number of books available for loan in the library.
     * @return the total number of books available in the library.
     */
    public int totalAvailableBooks() {
        return books.size() - loanedBooks.size();
    }

    /***
     * Returns the total number of books currently on loan in the library.
     * @return the total number of books currently on loan in the library.
     */
    public int totalLoanBooks() {
        return loanedBooks.size();
    }

    /***
     * Returns the total number of books in the library.
     * @return the total number of books in the library.
     */
    public int totalBooksInLibrary() {
        return books.size();
    }

    /***
     * The function receives an ID of a borrowed book and returns it to the library (makes it available again).
     * @param uniqueID - the unique ID of the book to be return
     * @return If the book was indeed borrowed and returned, the function will return true, otherwise it will return false
     * @throws Exception if put wrong index.
     */
    public boolean returnBook(int uniqueID) throws Exception {
        for (int i = 0; i < loanedBooks.size(); i++) {
            Book currentBook = loanedBooks.get(i);
            //check for the loaned unique id in the loaned array.
            if (currentBook.uniqueID == uniqueID){
                //if found, deletes the book from loaned books array and returns true.
                loanedBooks.delete(currentBook);
                return true;
            }
        }
        //didn't find the book in loaned books array.
        return false;
    }

    /***
     * return the name of the author who wrote the most books in the library.
     * if there is few authors with the same number the method will return the first one it finds with the most books in library.
     * @return the name of the author with most books in library.
     * @throws Exception if there is no author in the library or the index are wrong.
     */
    public String authorWithMostBooks() throws Exception {
        if (books.size() == 0) {
            return "No authors in the library.";
        }

        String authorWithMostBooks = null;
        int maxBookCount = 0;

        for (int i = 0; i < books.size(); i++) {
            String currentAuthor = books.get(i).author;
            int authorBookCount = countBooksByAuthor(currentAuthor);
            if (authorBookCount > maxBookCount) {
                maxBookCount = authorBookCount;
                authorWithMostBooks = currentAuthor;
            } else if (authorBookCount == maxBookCount && authorWithMostBooks != null) {
                // If there's a tie for the maximum count, append the author's name
                authorWithMostBooks += ", " + currentAuthor;
            }
        }

        if (authorWithMostBooks == null) {
            return "No authors with books in the library.";
        } else if (authorWithMostBooks.contains(",")) {
            // Multiple authors with the same maximum count
            return "Multiple authors with the same maximum count of books: " + authorWithMostBooks;
        } else {
            return authorWithMostBooks;
        }
    }

    // Helper method to count books by a specific author
    private int countBooksByAuthor(String author) throws Exception {
        int count = 0;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).author.equals(author)) {
                count++;
            }
        }
        return count;
    }

    /***
     * Sorting the books in the library according to the nunique ID
     * @throws Exception if index out of bound.
     */
    public void sortedByUniqueID() throws Exception {
        sortedByUniqueID(0, books.size() - 1);
    }

    /**
     * Sorts the books array by unique ID in ascending order recursively.
     * @param low  the index of the first element in the subarray
     * @param high the index of the last element in the subarray
     * @throws Exception if an error occurs during sorting
     */
    private void sortedByUniqueID(int low, int high) throws Exception {
        if (low < high) {
            int mid = (low + high) / 2;
            sortedByUniqueID(low, mid);// Sort the left half of the array
            sortedByUniqueID(mid + 1, high); // Sort the right half of the array
            mergeBooksByUniqueId(low, mid, high);// Merge the sorted halves
        }
    }
    /**
     * Merges two sorted subarrays of books by their unique IDs into a single sorted subarray.
     * @param low  the starting index of the first subarray
     * @param mid  the ending index of the first subarray and the starting index of the second subarray
     * @param high the ending index of the second subarray
     * @throws Exception if an error occurs during merging
     */
    private void mergeBooksByUniqueId(int low, int mid, int high) throws Exception {
        // Copying books into temporary arrays
        DataStructure<Book> left = new DataStructure<>();
        DataStructure<Book> right = new DataStructure<>();
        for (int i = low; i <= mid; i++)
            left.addToEnd(books.get(i));
        for (int i = mid + 1; i <= high; i++)
            right.addToEnd(books.get(i));

        int i = 0, j = 0, k = low;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).getUniqueId() <= right.get(j).getUniqueId()) {
                books.set(left.get(i), k);
                i++;
            } else {
                books.set(right.get(j), k);
                j++;
            }
            k++;
        }
        // Copy remaining books of left and right back into books array
        while (i < left.size()) {
            books.set(left.get(i), k);
            i++;
            k++;
        }
        while (j < right.size()) {
            books.set(right.get(j), k);
            j++;
            k++;
        }
    }
    /**
     * Returns a string representation of the Library object.
     * The string contains information about the books in the library and the loaned books.
     * @return A string representation of the Library object, including books in the library and loaned books.
     */
    @Override
    public String toString() {
        String libraryString = "Books in Library:\n" + books.toString() + "\n" +
                "Loaned Books:\n" + loanedBooks.toString() + "\n";
        return libraryString;
    }

        /***
         *class of books in the library.
         */
        public static class Book {

            //unique ID of the book.
            private final int uniqueID;
            //The name of the book.
            protected String name;
            //The author of the book.
            protected String author;

            /***
             * constructor of book class.
             * @param name the name of the book.
             * @param author the author of the book.
             */
            public Book(String name, String author) {
                this.author = author;
                this.name = name;
                //at the building of the book, the unique id is ++ itself.
                idx++;
                this.uniqueID = idx;
            }

            /***
             * Getter method for the unique ID of the book.
             * @return the unique ID of the book.
             */
            public int getUniqueId() {
                return uniqueID;
            }

            /***
             * toString method that return a string of the book to user.
             * @return a string that is represents the book.
             */
            @Override
            public String toString() {
                return uniqueID + ". Author: " + author + ", " + "Book name: " + name;
            }

            /***
             * equals method that check if 2 books are the same book by check the unique id.
             * @param o the book we want to check.
             * @return true if they are the same book we're looking by unique id , false if not.
             */
            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Book book = (Book) o;
                return uniqueID == book.uniqueID;
            }
        }

}
