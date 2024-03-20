

//import HW1.Library;
//import HW1.DataStructure;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        testAddBook(library); 
        testRemoveBook(library);
        testBorrowBook(library); 
        testBorrowAllBooks(library); 
        testReturnBook(library); 
        testIsBorrowed(library); 
        testTotalBooksInLibrary(library); 
        testTotalAvailableBooks(library); 
        testTotalLoanBooks(library); 
        testAuthorWithMostBooks(library); 
    }

    // Adding a book to the library
    private static void testAddBook(Library library) {
        try {
            library.addBook("Book1", "Author1");
            System.out.println("Test Add Book: PASSED");
        } catch (Exception e) {
            System.out.println("Test Add Book: FAILED");
        }
    }

    // Removing a book from the library
    private static void testRemoveBook(Library library) {
        try {
            library.removeBooks(1);
            System.out.println("Test Remove Book: PASSED");
        } catch (Exception e) {
            System.out.println("Test Remove Book: FAILED");
        }
    }

    // Borrowing a book from the library
    private static void testBorrowBook(Library library) {
        try {
            library.borrowBook(2);
            System.out.println("Test Borrow Book: PASSED");
        } catch (Exception e) {
            System.out.println("Test Borrow Book: FAILED");
        }
    }

    // Borrowing all books by an author (no books by specified author)
    private static void testBorrowAllBooks(Library library) {
        try {
            library.borrowAllBooks("NonExistentAuthor");
            System.out.println("Test Borrow All Books: PASSED");
        } catch (Exception e) {
            System.out.println("Test Borrow All Books: FAILED");
        }
    }

    // Returning a borrowed book
    private static void testReturnBook(Library library) {
        try {
            library.returnBook(2);
            System.out.println("Test Return Book: PASSED");
        } catch (Exception e) {
            System.out.println("Test Return Book: FAILED");
        }
    }

    //Checking if a book is borrowed
    private static void testIsBorrowed(Library library) {
        try {
            boolean isBook1Borrowed = library.isBorrowed(1);
            boolean isBook2Borrowed = library.isBorrowed(2);
            System.out.println("Test Is Borrowed: PASSED");
        } catch (Exception e) {
            System.out.println("Test Is Borrowed: FAILED");
        }
    }

    //Getting total number of books in the library
    private static void testTotalBooksInLibrary(Library library) {
        try {
            int totalBooks = library.totalBooksInLibrary();
            System.out.println("Test Total Books In Library: PASSED");
        } catch (Exception e) {
            System.out.println("Test Total Books In Library: FAILED");
        }
    }

    //Getting total number of available books in the library
    private static void testTotalAvailableBooks(Library library) {
        try {
            int totalAvailableBooks = library.totalAvailableBooks();
            System.out.println("Test Total Available Books: PASSED");
        } catch (Exception e) {
            System.out.println("Test Total Available Books: FAILED");
        }
    }

    //Getting total number of loaned books in the library
    private static void testTotalLoanBooks(Library library) {
        try {
            int totalLoanBooks = library.totalLoanBooks();
            System.out.println("Test Total Loan Books: PASSED");
        } catch (Exception e) {
            System.out.println("Test Total Loan Books: FAILED");
        }
    }

    //Getting author with the most books in the library
    private static void testAuthorWithMostBooks(Library library) {
        try {
            String authorWithMostBooks = library.authorWithMostBooks();
            System.out.println("Test Author With Most Books: PASSED");
        } catch (Exception e) {
            System.out.println("Test Author With Most Books: FAILED");
        }
    }
}
