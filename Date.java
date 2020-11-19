package project3;

import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 * The Date class processes the date string and then parses it into the day, month, and year. Implements the comparable interface.
 * This validates that the date is within specified bounds.
 *
 *
 *
 * @author Reema Amhaz
 *
 */

public class Date implements Comparable<Date>
{
    public String date;
    private int year;
    private int month;
    private int day;

/**
 * The constructor should throw an instance of IllegalArgumentException if it is called with a null parameter, or a string
 *     that does not match either MM/DD/YYYY or MM/DD/YY pattern.
 * @param date- a string
 */
    public Date (String date) throws IllegalArgumentException
    {
        if (date == null)
            throw new IllegalArgumentException("Invalid date, please enter result other than null.");

        if (date.length() == 7 || date.length() == 9)
            throw new IllegalArgumentException("Invalid date format.");

        String[] minusTime = date.split(" ");
        date = minusTime[0];

        Scanner splitInput = new Scanner(date);

        try
        {
            splitInput.useDelimiter("/");

            month =  splitInput.nextInt();
            day = splitInput.nextInt();
            year  = splitInput.nextInt();

            validate(month, day, year);
        }
        catch (NoSuchElementException ex)
        {
            throw new IllegalArgumentException("Invalid date formatting.");
        }
    }
    /**
     * The constructor should throw an instance of IllegalArgumentException if it is called with a parameters that are considered bad values.
     *
     * @param month, day, year
     */
    public Date (int month, int day, int year) throws IllegalArgumentException
    {
        validate(month, day, year);
    }

    /**
     * This validates whether or not the dates entered are valid based on comparisons done for the month, day, and year and checks dates for leap years.
     *
     * @param month, day, year
     */
    public void validate (int month, int day, int year)
    {
        if (year >= 0 && year <= 25) // just add 2000 to the year
        {
            year = 2000 + year;
        }

        this.year = year;
        this.month = month;
        this.day  =  day;

        if (year < 2000 || year > 2025) {
            throw new IllegalArgumentException("Invalid value for year. " +
                    "Valid range is 2000-2025");
        }

        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid value for month. " +
                    "Valid range is 1-12");
        }

        if (month == 2)
        {
            if (year % 4 == 0)
            {
                if (day < 1 || day > 29)
                    throw new IllegalArgumentException("Invalid value for day. " +
                            "Valid range is 1-29");
            }
            else
            {
                if (day < 1 || day > 28)
                    throw new IllegalArgumentException("Invalid value for day. " +
                            "Valid range is 1-28");
            }
        }

        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
        {
            if (day < 1 || day > 31)
                throw new IllegalArgumentException("Invalid value for day. " +
                        "Valid range is 1-31");
        }

        if (month == 4 || month == 6 || month == 9 || month == 11)
        {
            if (day < 1 || day > 30)
                throw new IllegalArgumentException("Invalid value for day. " +
                        "Valid range is 1-30");
        }
    }
    /**
     * This method overrides the to string method by returning the results of the date using the mm/dd/yyyy format.
     * @return formatted string
     */
    @Override
    public String toString () {
        return String.format("%02d/%02d/%4d", month, day, year);
    }
    /**
     * This method overrides the compare to method by returning the results of comparing parts of the date, a non zero if the date is not equal.
     * @return int for equality of values
     *
     */
    @Override
    public int compareTo(Date d)
    {
        if (this.year  < d.year)
            return -1;
        if (this.year  > d.year)
            return +1;
        if (this.month < d.month)
            return -1;
        if (this.month > d.month)
            return +1;
        if (this.day   < d.day)
            return -1;
        if (this.day   > d.day)
            return +1;
        return 0;
    }
}