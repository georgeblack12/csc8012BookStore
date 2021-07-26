/*
 *
 * A utility class that contains methods that are used in multiple classes throughout the program.
 *
 */
public class Helper {

    /*
     *
     * Params: String StringUsed - The String that is to have the capitalization of all the words in the string changed.
     *
     * Return: String, The String that was entered given in the method parameter with the first letter of all the words
     * in the String capitalized, and all the other letters in the String set to their lowercase values.
     *
     * A method that takes a String and capitalizes the first letter of all the words in the String and sets all the
     * other letters in the String to their lowercase values. The String that was given in the method parameter with the
     * capitalization changes stated above is returned.
     *
     * NOTE: For this method, define a word as part of the string that does not contain any space.
     *
     */
    public static String capitalizeString(String stringUsed) {
        String[] tmp = stringUsed.split(" ");
        String capitalString = "";

        for (String word : tmp) {
            if (word.equals(tmp[tmp.length - 1]))
                capitalString = capitalString + capitalizeWord(word);
            else
                capitalString = capitalString + capitalizeWord(word) + " ";
        }
        return capitalString;
    }

    /*
     * capitalizeWord example, found below
     * - https://stackoverflow.com/questions/3904579/how-to-capitalize-the-first-letter-of-a-string-in-java
     *
     * Original Author: Jens Piegsa
     * Modifying Author: George Black
     *
     * Params: word-The word to have the capitalization of its characters to be changed.
     *
     * Return: String, The word given in the parameter of this method with the first letter being capitalized and all
     * the other letters of the word set to lowercase.
     *
     * The purpose of this method is to take the single word given in the parameter and alter it so the first letter of
     * the given parameter is capitalized, and all the letters in the parameter are set to lowercase.
     *
     */
    private static String capitalizeWord(String word) {
        String firstLetter = String.valueOf(word.charAt(0)).toUpperCase();
        String restOfLetters = word.substring(1).toLowerCase();
        return firstLetter + restOfLetters;
    }


    /*
     *
     * Params: String stringForPerson - The String that is to be used to create a person.
     *
     * Returns: Person, A Person that is created by the words in the String given in the parameter of the Method
     *
     * Takes a string made up of two or three words and creates a person with the words in the String. If the String has
     * two words, the Person's  firstNames is set to the first word  in the String, and the Person's lastName is set to the
     * second Word in the String. If the String contains three words, the first two words in the String are set to the Peron's firstNames, and the third word in the string is set to the Person's last name.
     *
     * NOTE: For this method level comment, define a word to be part of the String that does not contain any space.
     *
     */
    public static Person stringToPerson(String stringForPerson) {
        String eachName[] = stringForPerson.split(" ");

        if (eachName.length == 3) {
            return new Person(eachName[0], eachName[1], eachName[2]);
        } else { //has a length of 2
            return new Person(eachName[0], eachName[1]);
        }
    }
}
