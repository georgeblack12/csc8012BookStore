/*
 ** A class for the users of the library. The class extends the Person class. Adds the attribute booksChecked, which is
 * the number of books the user has checked out. The class also implements the Comparable interface, so it can be ordered
 * properly when added to a SortedArrayList. Also, the class contains a userEquals methods and methods to check if
 * a user can check out/return a book.
 *
 *
 */
public class User extends Person implements Comparable<User> {
    private int booksChecked;

    public User(String first, String last) {
        super(first, last);
        this.booksChecked = 0;
    }

    //in case a User ever gives a middle name/middle initial
    public User(String first, String middle, String last) {
        super(first, middle, last);
        this.booksChecked = 0;
    }

    public int getBooksChecked() {
        return booksChecked;
    }


    /*
     *
     * Return: boolean, Returns if a user can check out a book.
     *
     * A method that returns a boolean that is true if the user has checked out less than 3 books. (The boolean is false
     * if the user cannot check out a book.)  This will be used in the Library class when a user wants to check out a
     * book. It will specifically be used to see if the limit of books a user can check out will be exceeded if the user
     * were to check out another book.
     *
     */
    public boolean canCheckBook() {
        return this.booksChecked < 3;
    }

    /*
     *
     * A method that increases the number of books this User has checked out by one.
     *
     */
    public void increaseBooksChecked() {
        this.booksChecked++;
    }

    /*
     *
     * A method that decreases the number of books this User has checked out by 1.
     *
     */
    public void decreaseBooksChecked() {
        this.booksChecked--;
    }



    /*
     *
     * compareTo method , found here-
     * -https://campus.recap.ncl.ac.uk/Panopto/Pages/Viewer.aspx?id=8d3791f3-52d0-4790-b53c-ac4d008dd8e2
     *
     * Original Author: Marta Koutny
     * Modifying Author: George Black
     *
     * Params: User u- The user we want "this" user to be compared to.
     *
     * Return: int, Returns an int describing if "this" user is equal to, less than, or greater than User u.
     * (Negative if "this" is less than u, positive if "this" is greater than u)
     *
     * A method that compares "this" person's last name to Person p's last name. Will be used in the compareTo methods
     * in User and Books.
     *
     */
    @Override
    public int compareTo(User u) {
        int lnCmp = this.compareLastName(u);
        if (lnCmp != 0) {
            return lnCmp;

            //if last names are the same get ordering from first names
        } else {
            return this.getFirstNames().compareTo(u.getFirstNames());
        }
    }

    @Override
    public String toString() {
        return "Name: " + super.toString() + "\nBooks Checked Out: " + this.booksChecked;
    }

    /*
     *
     * Return: String, the name of "this" User.
     *
     * A method that returns the name of the User by using the Person's toString method.
     *
     */
    public String stringName() {
        return super.toString();
    }
}
