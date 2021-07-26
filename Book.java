
/*
 * A class for a book with the attributes, title, author, loaned, and loanedBy. If a book is not being
 * loaned, the User who is used for loanedBy (the person who loans the book) is set to a blank value User
 * (a user value with "" for its firstNames and lastName). If a book has it where the loanedBy attribute is set to the
 * blank value User, it implies there is no one loaning the book. Furthermore, if the blank value User is the value of
 * the loanedBy attribute, then the loaned attribute will be false. (The loaned attribute is a boolean that explicitly
 * states if a book is being loaned.)
 * When a book object is created, the loanedBy is set to this blank value User, and the loaned attribute is set to false.
 * A method in the class is used to set the loaned attribute to true and set the loanedBy attribute to a nonblank User
 * value.
 * This class does not check if a book is already loaned or not before someone is set to loan it, as that will be done
 * in the library class.
 *
 */
public class Book implements Comparable<Book> {
    private String title;
    private Person author;
    private boolean loaned;
    private User loanedBy;

    public Book(String title, Person author) {
        this.title = title;
        this.author = author;
        loaned = false;
        loanedBy = new User("", ""); //default user that means no user has this book
    }

    public String getTitle() {
        return this.title;
    }

    public Person getAuthor() {
        return this.author;
    }

    public boolean isLoaned() {
        return this.loaned;
    }

    public User getLoanedBy() {
        return this.loanedBy;
    }

    /*
     *
     * Params: User u- the user who is going to loan the book.
     *
     * Sets User u as the person who is loaning the book and increases the number of books the user has checked out by
     * one. Also, sets the loaned boolean to true since there is now a User loaning the bookThis class is like a setter
     * for the attribute loanedBy.
     *
     */
    public void setBookLoan(User u) {
        this.loanedBy = u;
        u.increaseBooksChecked();
        this.loaned = true;

    }

    /*
     *
     * Params: User u- the User who is going to loan the book.
     *
     * Sets the User loaning the book back to a blank value User and sets the loaned boolean to false since
     * there is now technically not a real User loaning the book. Additionally, the user who returned the book (User u)
     * has the number of books that have checked out decreased by one.
     *
     */
    public void setReturnBook(User u) {
        this.loanedBy = new User("", "");
        this.loaned = false;
        u.decreaseBooksChecked();

    }

    /*
     *
     * Params: Book b- the book whose title and author will be compared with "this" book's title and author.
     *
     * Return: boolean, Returns if Book b's title and Author are the same values as the "this" book's title and
     * author.
     *
     * A method that checks to see if Book b's title and Author are the same values as the "this" book's title and
     * author. If a book has the same title and author, it is safe to assume that they are the same book; therefore, it
     * is true that Book b and "this" book are equal.
     * IMPORTANT NOTE: I created the bookEquals method instead of overriding the equals method because I did not want
     * to have to deal with overriding the hashcode. Dr. Koutny approved this approach.
     *
     */
    public boolean bookEquals(Book b) {
        //When comparing the authors, we are using the personEquals method created in the Person class.
        return (this.title.equals(b.title) && this.author.personEquals(b.author));
    }


    /*
     *
     * compareTo method, found here-
     * -https://campus.recap.ncl.ac.uk/Panopto/Pages/Viewer.aspx?id=8d3791f3-52d0-4790-b53c-ac4d008dd8e2
     *
     * Author: Marta Koutny
     *
     * Params: Book b- The book we want "this" user to be compared to.
     *
     * Return: int, Returns an int describing if "this" book is equal to, less than, or greater than Book b.
     * (Negative if "this" is less than b, positive if "this" is greater than b)
     *
     * Used to compare "this" book's Author's lastName to Book b's author's lastName. This is done completely by using
     * the compareLastName method created in the Person class. We are only comparing books by their last name because
     * their order for a SortedArrayList only needs to be based on the Book's Author's lastName.
     *
     */
    @Override
    public int compareTo(Book b) {
        return this.author.compareLastName(b.author);
    }

    @Override
    public String toString() {
        if (isLoaned())
            return this.titleAndAuthorString() + "\nCurrently rented by: " + this.loanedBy.stringName();
        else
            return this.titleAndAuthorString() + "\nThis book is currently not being rented.";
    }

    /*
     *
     * Return: String, Returns the title and author of "this" Book.
     *
     * A method that returns a string for the title and author of the Book.
     *
     */
    public String titleAndAuthorString() {
        return this.title + ", By: " + this.author;
    }
}
