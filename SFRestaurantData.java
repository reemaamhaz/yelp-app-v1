package project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;

/**
 * SFRestaurant Data class is used to open the data files provided by user and take in keywords provided by their input.
 * This class handles errors and performs data validation.
 *
 *
 *
 * @author Reema Amhaz
 *
 */

public class SFRestaurantData
{
    /**
     * This method retrieves the data from the parsed input file and assigns the data to the appropriate classes
     * @param args
     */
    public static void main(String[] args)
    {
        if (args.length == 0) // this exits the system when there is no command line arg provided
        {
            System.err.println("Error: the program expects a file name to be provided as an argument.\n");
            System.exit(1);
        }

        File file = new File(args[0]);
        if (!file.exists()) // if the file doesn't exist then it throws this error
        {
            System.err.println("Error: the file: " + file.getAbsolutePath() + " does not exist.\n");
            System.exit(1);
        }
        else if (!file.canRead()) // if the file can't be read then it throws this error
        {
            System.err.println("Error: the file: " + file.getAbsolutePath() + " cannot be opened for reading.\n");
            System.exit(1);
        }

        /*
            This takes the command line file after determining that the compiler can read the file
         */
        Scanner sc = null;
        String textLine = null;

        try
        {
            sc = new Scanner(file); //takes in the file in the command line
        }
        catch (FileNotFoundException e) //throws the exception error if you can't read the file
        {
            System.err.println("Error: the file: " + file.getAbsolutePath() + " cannot be opened for reading.\n");
            System.exit(1);
        }

        RestaurantList list = new RestaurantList();
        Restaurant current = null;
        Inspection currInspect = null;
        Date currDate = null;
        String line = null;
        Scanner parseLine = null;
        String zip = null;
        String name = null;
        String address = null;
        String phone = null;
        String date = null;
        int score = 0;
        String violation = null;
        String risk = null;

        ArrayList<String> entries = null;
        while(sc.hasNextLine())
        {
            try
            {
                textLine = sc.nextLine();
                entries = splitCSVLine(textLine);
                name = entries.get(1);
                address = entries.get(2);
                zip = entries.get(5);
                phone = entries.get(9);
                date = entries.get(11);
                score = Integer.parseInt(entries.get(12));
                violation = entries.get(14);
                risk = entries.get(15);
            }
            catch (NoSuchElementException ex)
            {
                //caused by an incomplete or miss-formatted line in the input file
                continue;
            }
            catch (NumberFormatException ex)
            {
                //caused by an incomplete or miss-formatted line in the input file
                continue;
            }


            try
            {
                if (address == null || phone == null)
                {
                    current = new Restaurant(name, zip);
                    currDate = new Date(date);
                    currInspect = new Inspection(currDate, score, violation, risk);
                    current.addInspection(currInspect);
                    list.verifyNewRestaurant(current);
                }
                else if (address != null || phone != null)
                {
                    current = new Restaurant(name, zip, address, phone);
                    currDate = new Date(date);
                    currInspect = new Inspection(currDate, score, violation, risk);
                    current.addInspection(currInspect);
                    list.verifyNewRestaurant(current);
                }
            }
            catch (IllegalArgumentException ex )
            {
                //ignore this exception and skip to the next line
            }
        }

        Scanner userInput  = new Scanner (System.in); // creates a scanner for user input
        String userSearch = "";
        int counter = 0;

        do
        {
            if (counter == 0)
            {
                System.out.println("Search the database by matching keywords to titles or actor names.\n" +
                        "   To search for matching restaurant names, enter\n" +
                        "       name KEYWORD\n" +
                        "   To search for restaurants in a zip code, enter\n" +
                        "       zip KEYWORD\n" +
                        "   To finish the program, enter\n" +
                        "       quit\n\n\n" +
                        "Enter your search query:\n");
                counter++;
            }
            else if(counter > 0)
            {
                System.out.println("Enter your search query:");
            }


            userSearch = userInput.nextLine(); // gets input from user


            if (!userSearch.equalsIgnoreCase("quit"))
            {
                String[] splitInput = userSearch.split(" ", 2);

                SFRestaurantData match = new SFRestaurantData();

                if (splitInput[0].equals("name"))
                {
                    if(splitInput[1].isEmpty())
                    {
                        System.err.println("This is not a valid query. Try again.\n");
                    }
                    System.out.println(list.getMatchingRestaurants(splitInput[1]));
                    //call some method that matches the restaurant names to something in the list provided if no match then prompt user for another query
                }
                else if (splitInput[0].equals("zip"))
                {
                    if(splitInput[1].isEmpty())
                    {
                        System.err.println("This is not a valid query. Try again.\n");
                    }
                    System.out.println(list.getMatchingZip(splitInput[1]));
                    //call some method that matches the restaurant zips to something in the list provided if no match then prompt user for another query
                }
                else
                {
                    System.err.println("This is not a valid query. Try again.\n");
                }
            }
        }
        while (!userSearch.equalsIgnoreCase("quit"));

        userInput.close();


    }
    public static ArrayList<String> entries =  null;
    /**
     * Splits the given line of a CSV file according to commas and double quotes
     * (double quotes are used to surround multi-word entries so that they may contain commas)
     * @author Joanna Klukowska
     * @param textLine	a line of text to be passed
     * @return an Arraylist object containing all individual entries found on that line
     */
    public static ArrayList<String> splitCSVLine(String textLine){

        entries = new ArrayList<String>();
        int lineLength = textLine.length();
        StringBuffer nextWord = new StringBuffer();
        char nextChar;
        boolean insideQuotes = false;
        boolean insideEntry= false;

        // iterate over all characters in the textLine
        for (int i = 0; i < lineLength; i++) {
            nextChar = textLine.charAt(i);

            // handle smart quotes as well as regular quotes
            if (nextChar == '"' || nextChar == '\u201C' || nextChar =='\u201D') {

                // change insideQuotes flag when nextChar is a quote
                if (insideQuotes) {
                    insideQuotes = false;
                    insideEntry = false;
                }else {
                    insideQuotes = true;
                    insideEntry = true;
                }
            } else if (Character.isWhitespace(nextChar)) {
                if ( insideQuotes || insideEntry ) {
                    // add it to the current entry
                    nextWord.append( nextChar );
                }else { // skip all spaces between entries
                    continue;
                }
            } else if ( nextChar == ',') {
                if (insideQuotes){ // comma inside an entry
                    nextWord.append(nextChar);
                } else { // end of entry found
                    insideEntry = false;
                    entries.add(nextWord.toString());
                    nextWord = new StringBuffer();
                }
            } else {
                // add all other characters to the nextWord
                nextWord.append(nextChar);
                insideEntry = true;
            }

        }
        // add the last word ( assuming not empty )
        // trim the white space before adding to the list
        if (!nextWord.toString().equals("")) {
            entries.add(nextWord.toString().trim());
        }
        return entries;
    }
}