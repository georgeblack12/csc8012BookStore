import java.io.FileNotFoundException;
import java.io.PrintWriter;

/*
 *
 * A class that contains two attributes: A SortedArrayList of books and a SortedArrayList of users. The
 * class mostly serves to check if actions can be done between libraryUsers and libraryBooks and then performing
 * the action if it can be done.
 * The class also handles sending messages to an outputFile. Additionally, this class has the ability to get the
 * numberIDs (the location in the SortedArrayList) of books and users.
 *
 * NOTE: I was a little conflicted if this class should have been created, but it seemed appropriate to have a library
 * class that handles sending out messages and making sure actions between Users and Books are performed appropriately.
 * Furthermore, it seemed appropriate to have a class that holds all the Books And Users.
 *
 */
public class Library {
    private SortedArrayList<Book> libraryBooks = new SortedArrayList<>();
    private SortedArrayList<User> libraryUsers = new SortedArrayList<>();


    /*
     *
     * Params: String- fileName. The name of the file that we will get the Users and Books for our library from.
     *
     * The constructor for the class has its attributes set by the data from a  file with the fileName given in the
     * parameter.
     *
     */
    public Library(String fileName) throws FileNotFoundException {
        FileReader libraryFileReader = new FileReader(fileName);
        libraryBooks = libraryFileReader.getBooks();
        libraryUsers = libraryFileReader.getUsers();
    }

    public SortedArrayList<Book> getLibraryBooks() {
        return libraryBooks;
    }

    public SortedArrayList<User> getLibraryUsers() {
        return libraryUsers;
    }

    /*
     *
     * Params: User u- The User who wants to check out Book b
     * Book b- The Book User u wishes to check out
     * PrintWriter f- The file a message (saying to return the book) will be sent to if Book b is currently checked out.
     *
     * A method that sees if a User can check out a book. If the User does not already have three books checked out
     * and the Book is currently not being checked out, the Book is set to where the Book is being loaned by them and the
     * User has the number of Books they have checked out increased by one. If the above conditions are not met, an
     * appropriate message is displayed. If the book is being checked out by someone else, a message is sent to
     * PrintWriter f. (The message sent to PrintWriter f tells the User who currently has Book b to return it.)
     *
     */
    public void checkOutBook(User u, Book b, PrintWriter f) {
        if (u.canCheckBook()) {
            if (!b.isLoaned()) {
                b.setBookLoan(u); //sets b.LoanedBy=u and makes u.booksChecked++
                System.out.println("The book has been successfully checked out.");


            } else {
                System.out.println("Sorry, this book is currently being loaned by " + b.getLoanedBy().stringName() +
                        ". A message will be sent to " + b.getLoanedBy().stringName() + ", telling them to return the "
                        + "book.");
                sendReturnMessage(b, f);
            }
        } else {
            System.out.println("Sorry, " + u.stringName() + " has 3 books checked out. They must return a book before " +
                    "they " + "can check out" + " another book.");
        }
    }

    /*
     *
     * Params: Book b - The book that needs to be returned.
     * PrintWriter f - the file the message will be sent to
     *
     * A method that sends a message to printWriter f telling the User who currently has Book b to return it because someone
     * wants it. This method is used in the CheckOutBook method in this class.
     *
     */
    private void sendReturnMessage(Book b, PrintWriter f) {
        f.println("Dear " + b.getLoanedBy().stringName() + ",\n" +
                "Someone wishes to check out " + b.titleAndAuthorString() + ". Please return this book at your convenience.\n" +
                "Thank you.\n");
    }

    /*
     *
     * Params: User u - The User who wants to return Book b
     * Book b - The Book User u wishes to return
     *
     * A method that sees if a User can return a book. If the User currently has Book b checked out, the Book is set to
     * being loaned by no one(an empty default User). Additionally, the User who returned the book has
     * the number of books they have checked out decreased by one.
     * If User u does not have Book b checked out, a message is displayed saying they do not have the book checked out.
     *
     */
    public void returnBook(User u, Book b) {
        if (b.getLoanedBy().personEquals(u)) {
            b.setReturnBook(u); // sets b.loanedBy to empty default User and u.booksChecked--
            System.out.println("The book has been successfully returned");
        } else {
            System.out.println(u.stringName() + " has not checked out " + b.titleAndAuthorString() + ", therefore, " +
                    "they cannot be returning the book.");
        }
    }

    /*
     *
     * Params: Person uCheck- the Person to be checked if has the same name as a User in libraryUsers.
     *
     * Returns: int, Returns the index in libraryUsers where the User with the same name as Person
     * uCheck is located.
     *
     * A method that gets the index in libraryUsers where the User with the same name as Person uCheck is located. If there
     * is not a User in libraryUsers with the same name as Person uCheck, -1 is returned signify this Person is not a
     * LibraryUsers.
     * NOTE: (Assuming no two people have the same name) If uCheck has the same name as a User in libraryUsers, then
     * uCheck is that User in libraryUsers.
     *
     */
    public int getUserNumber(Person uCheck) {
        int userNumber = -1;
        for (User u : libraryUsers) {
            if (u.personEquals(uCheck)) {
                userNumber = libraryUsers.indexOf(u);
            }
        }
        return userNumber;
    }

    /*
     *
     * Params: Book bCheck- the Book to be checked if it is in libraryBooks
     *
     * Returns: int, Returns the index in libraryBooks where the Book bCheck is located.
     *
     * A method that gets the index in libraryBooks where the Book bCheck is located. If Book bCheck is not in
     * libraryBooks, -1 is returned signify this bCheck is not a LibraryBooks.
     *
     */
    public int getBookNumber(Book bCheck) {
        int bookNumber = -1;
        for (Book b : libraryBooks) {
            if (b.bookEquals(bCheck)) {
                bookNumber = libraryBooks.indexOf(b);
            }
        }
        return bookNumber;
    }
}
