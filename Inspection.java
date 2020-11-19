package project3;

/**
 * The Inspection class processes the data fields of the inspection and create inspection objects. Implements the comparable interface.
 * This class validates that the input (ie date, score, violation, and risk) are within specified bounds.
 *
 *
 *
 * @author Reema Amhaz
 *
 */

public class Inspection implements Comparable<Inspection>
{
    public Date date;
    public int score;
    public String violation;
    public String risk;

    /**
     * This constructor is passed parameters date, score, violation, and risk and determines validity based on project specifications.
     * @param date, score, violation, and risk
     * @throws IllegalArgumentException
     */
    public Inspection (Date date, int score, String violation, String risk) throws IllegalArgumentException
    {

        this.date = date;
        this.score = score;
        this.violation = violation;
        this.risk = risk;


        if (date == null)
        {
            throw new IllegalArgumentException("Invalid value for date, no null dates.");
        }
        if (score < 0 || score > 100)
        {
            throw new IllegalArgumentException("Invalid value for score, valid range is 0-100.");
        }
    }

    // getter methods for the data fields

    /**
     * This method is a getter method for the date.
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * This method is a getter method for the violation.
     * @return violation
     */
    public String getViolation() {
        return violation;
    }

    /**
     * This method is a getter method for the score.
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * This method is a getter method for the risk.
     * @return risk
     */
    public String getRisk() {
        return risk;
    }

    /**
     * This method overrides the compare to method by returning the results of comparing two inspections to determine which is higher.
     * @return int for equality of values
     * @param r for inspection objects
     */
    public int compareTo(Inspection r)
    {
        if (this.score > r.score)
        {
            return -1;
        }
        else if (this.score < r.score)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}
