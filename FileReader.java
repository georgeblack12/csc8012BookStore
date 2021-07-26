import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
 *
 * A class that takes a file in the format given in the assignment specifications and uses the data from the file to
 * to add Users and Books to this.users and this.books respectively.
 *
 */
public class FileReader {
    private SortedArrayList<User> users = new SortedArrayList<>();
    private SortedArrayList<Book> books = new SortedArrayList<>();

    public SortedArrayList<User> getUsers() {
        return this.users;
    }

    public SortedArrayList<Book> getBooks() {
        return this.books;
    }

    public FileReader(String fileName) throws FileNotFoundException {
        grabUsersAndBooksFromFile(fileName);
    }

    /*
     *
     * Params: String-fileName. The name of the file to be retrieved and have its data used to add Users and Books
     * to this.users and this.books respectively.
     *
     * Takes a given fileName and adds the file from the src. Once this is done, the data from the file is used to add
     * Users and Books to this.users and this.books respectively.
     *
     */
    private void grabUsersAndBooksFromFile(String fileName) throws FileNotFoundException {
        ArrayList<String> myFileLines = getLines((getFile(fileName)));
        int[] amountsForSorted = findAmounts(myFileLines);
        parser(myFileLines, amountsForSorted[0], amountsForSorted[1]);
    }


    /*
     *
     * Params: String fileName - the name of the file to be retrieved.
     * Return: File, returns the file from the src with the name given as the fileName parameter.
     *
     * The purpose of this method in relation to the class is to locate and return a file from the src with the
     * name given as the fileName parameter.
     *
     */
    private File getFile(String fileName) {
        File ourFile = new File(FileReader.class.getResource
                (fileName).getFile());
        return ourFile;
    }


    /*
     * readCSV example, found here
     * - https://nucode.ncl.ac.uk/scomp/msc-computer-science/csc8011/code-examples-from-videos/csc8011-video-code-examples/blob/master/src/filereader/FileReaderExample.java
     *
     * Original Author: Jordan Barnes
     * Modifying Author: George Black
     *
     * Params: File f - file to have information pulled out of.
     * Return: ArrayList<String>, returns an ArrayList of Strings where each string is a line from theFile.
     *
     * The purpose of this method in relation to class is  to get each line from a File (theFile) and add them to an
     *  ArrayList of Strings. Once this is complete, the ArrayList is returned. This will be used in findAmounts.
     *
     */
    private ArrayList<String> getLines(File f) throws FileNotFoundException {
        Scanner scanFile = new Scanner(f);
        ArrayList<String> fileLines = new ArrayList<>();

        while (scanFile.hasNext()) {

            //Makes it, so every word in the string begins with a capital letter and then has it where all other letters
            //are lowercase. This will allow for proper ordering of users/books that are created by these string values
            //when they are entered into their respected SortedArrayList.
            String entry = Helper.capitalizeString(scanFile.nextLine());
            fileLines.add(entry);
        }
        return fileLines;

    }


    /*
     *
     * Params: ArrayList<String> fileLines- The individual lines from the file.
     *
     * Return: int[], Returns an array of integers that contains two numbers. The first number in this array
     *  is the number of Strings in the ArrayList (given in as the method's parameter) that give Book information.
     * The second number in the array is the number of Strings in the ArrayList that give User information.
     *
     * A method that locates the number of Strings in fileLines that give Book information and the number of Strings in fileLines
     * that list User information. These two numbers are put into an array, which is the array is returned.
     *
     */
    private int[] findAmounts(ArrayList<String> fileLines) {
        int usersAmount = 0;
        int booksAmount = 0;
        boolean gotFirstNumber = false;

        for (String line : fileLines) {

            //The first number will specify how many Books there are in fileLines.
            if (line.matches("[0-9]+") && !gotFirstNumber) {
                //For every book in filelines, there are two Strings in fileLines dedicated to it.
                //One String for the title of the Book and a second String giving the Book's author. Therefore if there
                // n books in fileLines there are 2 Strings in fileLines that give book information about the book.
                booksAmount = 2 * Integer.parseInt(line);
                gotFirstNumber = true;

                //The second number specifies how many Users there are in fileLines.
            } else if (line.matches("[0-9]+") && gotFirstNumber) {
                usersAmount = Integer.parseInt(line);
            }
        }
        return new int[]{booksAmount, usersAmount};
    }


    /*
     *
     * Params: ArrayList<String> fileLines - ArrayList of strings that contains the individual lines from the file.
     * int booksAmount - the number of Strings in fileLines that give book information.
     * int userAmount - the number of Strings in fileLines that give user information.
     *
     * A method that goes through the Strings in the ArrayList(given in the parameter) and creates users/books from them
     * (with the help of the two int parameters). Once a User/Book is created from some of the Strings in fileLines,
     * the Book/User is added to its respected SortedArrayList attribute.
     *
     */
    private void parser(ArrayList<String> fileLines, int booksAmount, int usersAmount) {

        //Does not include the first String at index 0 because that is the number specifying how many books there are.
        //Uses two String to create a single book.
        for (int i = 1; i <= booksAmount; i = i + 2) {
            Book bookToEnter = new Book(fileLines.get(i), Helper.stringToPerson(fileLines.get(i + 1)));
            books.insertion(bookToEnter);
        }

        int usersStart = booksAmount + 2; //+2 because we need to skip over the Strings that are numbers.
        int usersEnd = usersStart + usersAmount - 1;

        for (int i = usersStart; i <= usersEnd; i++) {
            //create Person to be able to easily grab firstNames and last Name to be entered.
            Person userNames = Helper.stringToPerson(fileLines.get(i));
            users.insertion(new User(userNames.getFirstNames(), userNames.getLastName()));
        }
    }
}

