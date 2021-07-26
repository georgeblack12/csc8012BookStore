import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 *
 * The class that handles all the input from the librarian. This is the driver class that contains the main method for
 * the program
 *
 * */
public class LibraryRunning {
    static Scanner scan = new Scanner(System.in);

    /*
     *
     * The program's main method. Displays a menu for the librarian to select a task they want to perform. The librarian
     * can: Display information about all the Users in the Library, Display information about all the Books in the
     * Library, attempt to have a user check out a book, or attempt to have a book returned by a User. After an option
     * is chosen and completed, the menu is displayed again. This process is repeated until the user enters the
     * character f.
     *
     */
    public static void main(String[] args) throws FileNotFoundException {
        Library ourLibrary = new Library("booksAndUsers.txt");
        PrintWriter outFile = new PrintWriter("returnBookMessages.txt");

        char choice;
        do {
            displayMenu();
            choice = Character.toLowerCase(scan.next().charAt(0));
            scan.nextLine(); //avoids possible error of not reading the next line entered

            switch (choice) {
                case 'f':
                    System.out.println("Goodbye!");
                    break;
                case 'b':
                    displaySortedArray(ourLibrary.getLibraryBooks());
                    break;
                case 'u':
                    displaySortedArray(ourLibrary.getLibraryUsers());
                    break;
                case 'i':

                    //gets the index of the User in ourLibrary
                    //SortedArrayList of User that was entered by the librarian. Does the same with the Book entered
                    //by the librarian (if the librarian entered is in ourLibrary.libraryUsers).
                    int[] userAndBookLoan = checkProperValues(ourLibrary);

                    int userLInt = userAndBookLoan[0];
                    int bookLInt = userAndBookLoan[1];

                    //if the User entered is a User in ourLibrary and the Book Entered is a Book in ourLibrary,
                    //try to have the book be checked out by the User.
                    if (userAndBookLoan[0] != -1 && userAndBookLoan[1] != -1) {
                        ourLibrary.checkOutBook(ourLibrary.getLibraryUsers().get(userLInt),
                                ourLibrary.getLibraryBooks().get(bookLInt), outFile);
                    }
                    break;

                case 'r':
                    //gets the index of the User in ourLibrary
                    //SortedArrayList of User that was entered by the librarian. Does the same with the Book entered
                    //by the User (if the User entered is in ourLibrary.libraryUsers).
                    int[] userAndBookReturn = checkProperValues(ourLibrary);

                    int userRInt = userAndBookReturn[0];
                    int bookRInt = userAndBookReturn[1];

                    //If the User entered is a User in ourLibrary and the Book Entered is a Book in ourLibrary,
                    //try to have the book returned by the User.
                    if (userAndBookReturn[0] != -1 && userAndBookReturn[1] != -1) {
                        ourLibrary.returnBook(ourLibrary.getLibraryUsers().get(userRInt),
                                ourLibrary.getLibraryBooks().get(bookRInt));
                    }
                    break;
                default:
                    System.out.println("Error! Invalid entry, try again.\nNote: The character entered must be lowercase");
            }
        } while (choice != 'f');
        outFile.close();
    }

    /*
     *
     * menuOptions (menu) example, found below
     * - https://campus.recap.ncl.ac.uk/Panopto/Pages/Viewer.aspx?id=782984e3-89cc-4d39-89d9-ac4c00e27c1f
     *
     * Original Author: Marta Koutny
     * Modifying Author: George Black
     *
     * The main menu options shown throughout the main method.
     */
    private static void displayMenu() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("MENU");
        System.out.println("f -to finish running the program.");
        System.out.println("b -to display on the screen the information about all the books in the library.");
        System.out.println("u -to display on the screen the information about all the users.");
        System.out.println("i -to update the stored data when a book is issued to a user.");
        System.out.println("r -to update the stored data when a user returns a book to the library.");
        System.out.println("--------------------------------------------------------------------------------");
    }

    /*
     *
     * Params: SortedArrayList<E> mySorted - A SortedArrayList of type E whose values are to be displayed.
     *
     * A general method that displays all the values in a SortedArrayList one at a time with a space between each value.
     * NOTE: Because we were advised only to have one method in our SortedArrayList class, I put this method in the
     * driver class instead of the SortedArrayList class.
     *
     * */
    private static <E extends Comparable<E>> void displaySortedArray(SortedArrayList<E> mySorted) {
        for (E e : mySorted) {
            System.out.println(e + "\n");
        }
    }

    /*
     *
     * Params: Library lib- The library who's Users and Books will be set as the acceptable Users and Books.
     *
     * Returns: int[], an array whose first number is the index of the User in lib.libraryUsers that was entered by
     * the librarian. The second number of the array is the index of the Book in lib.libraryBooks that was entered by
     * the librarian.
     *
     * A method begins by displaying a message telling the librarian to enter the User. It then saves the index of the
     * User in lib.libraryUsers that was entered by the librarian. Then, the method displays a message telling the
     * librarian to enter the Book. The index of the Book in lib.LibraryBooks that was entered by the librarian is
     * saved. If the librarian enters a User that is not one of the Users in lib, -1 is set as the value
     * for both indices and an appropriate message is displayed. If the librarian enters a Book that is not one of the
     * Books in Library lib. -1 is set as the second index, and an appropriate message is displayed.
     * No matter what the values are for the indices, they are returned at the end of the method.
     *
     */
    private static int[] checkProperValues(Library lib) {
        System.out.println("What is the user's name?");
        displayPersonEntryRules("user");
        Person checkPerson = Helper.stringToPerson(Helper.capitalizeString(scan.nextLine()));

        int userNumber = lib.getUserNumber(checkPerson);
        int bookNumber = -1;

        if (userNumber != -1) { //(if it is a User in the Library lib)
            System.out.println("What is the name of the book?");
            String checkTitle = Helper.capitalizeString(scan.nextLine());

            System.out.println("- Who is the book's author?");
            displayPersonEntryRules("author");
            Person checkAuthor = Helper.stringToPerson(Helper.capitalizeString(scan.nextLine()));
            Book checkBook = new Book(checkTitle, checkAuthor);
            bookNumber = lib.getBookNumber(checkBook);

            if (bookNumber == -1) {
                System.out.println("Sorry, that is not a book in our Library");
            }

        } else {
            System.out.println("Sorry, that is not a user in our Library");
        }
        return new int[]{userNumber, bookNumber};
    }

    /*
     *
     * Params: String - personType. A string that specifies exactly what the Person is we are having the librarian enter.
     *
     * A method that displays the list of rules that must be followed when the librarian enters (the name of) a Person.
     *
     */
    private static void displayPersonEntryRules(String personType) {
        System.out.println("- Must at least enter the " + personType + "'s" + " first initial and last name.");
        System.out.println("- You can include a max of three names for the " + personType + " (first, middle, and last)");
        System.out.println("- If an initial is used it must be followed by a period");
        System.out.println("- The name must be entered exactly the same as it saved in our system, although " +
                "capitalization does not matter. ");
    }
}
