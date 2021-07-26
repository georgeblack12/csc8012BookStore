/*
 *
 * A class with the attributes of the first names (this includes middle initial/name) and last name of a person.
 * The class will be extended in the users class and used for the author attribute that is in
 * the books class.
 *
 */
public class Person {

    private String firstNames;
    private String lastName;

    public Person(String first, String last) {
        this.firstNames = first;
        this.lastName = last;
    }

    //needed if there is a middle initial/middle name (of the person) is given
    public Person(String first, String middle, String last) {
        this.firstNames = first+ " " +middle;
        this.lastName = last;
    }

    //setters not needed for this class

    public String getFirstNames() {
        return firstNames;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return this.firstNames + " " + this.lastName;
    }


    /*
     *
     * compareTo method that was used for an example found here-
     * -https://campus.recap.ncl.ac.uk/Panopto/Pages/Viewer.aspx?id=8d3791f3-52d0-4790-b53c-ac4d008dd8e2
     *
     * Author: Marta Koutny
     *
     * Params: Person p- The Person we want "this" Person's last name to be compared to.
     *
     * Return: int, Returns an int describing if "this" persons last is equal to, less than, or greater than the last
     * name of Person p.
     *
     * Used to compare "this" person's last name to Person p's last name. Will be used in the compareTo methods in
     * User and Books.
     *
     */
    public int compareLastName(Person p) {
        int lnCmp = lastName.compareTo(p.lastName);
        return lnCmp;
    }

    /*
     *
     * Params: Person p- the Person who's firstNames and lastName "this" Person's firstNames and lastName are going to
     * be compared to.
     *
     * Return: boolean, Returns if "this" person has the same firstNames and lastName as Person p
     *
     * This method checks to see if "this" person has the same firstNames and lastName as Person p. Since no two
     * distinct users or authors have the same firstNames and lastName if Person P and "this" person has the same
     * firstNames and lastName, they will have to be the same Person. This method will be used in the Library class and
     * the Book class.
     * IMPORTANT NOTE: I created the personEquals method instead of overriding the equals method because I did not want
     * to have to deal with overriding the hashcode. Dr. Koutny approved this approach.
     *
     */
    public boolean personEquals(Person p) {
        return (this.firstNames.equals(p.firstNames) && this.lastName.equals(p.getLastName()));
    }
}
