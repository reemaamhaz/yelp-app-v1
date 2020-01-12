package project3;

import java.util.*;
import java.util.ArrayList;
import java.lang.Character;
import java.lang.String;

/**
 * The Restaurant class processes the data fields of the restaurant and create restaurant objects.
 * This class validates that the input (ie zip, name, address, and phone) are within specified bounds.
 *
 *
 *
 * @author Reema Amhaz
 *
 */

public class Restaurant implements Comparable<Restaurant>
{
    public String name  = null;
    private String zip = null;
    private String address = null;
    private String phone = null;
    ArrayList<Inspection> inspectionList = new ArrayList<Inspection>();

    /**
     * This constructor is passed a valid name and zip based on project specifications.
     * @param name and zip
     * @throws IllegalArgumentException
     */
    public Restaurant(String name, String zip) throws IllegalArgumentException
    {
        this.name = name;
        this.zip = zip;

        if (name == null)
            throw new IllegalArgumentException("Invalid value for name, no null names.");
        if (name == "")
            throw new IllegalArgumentException("Invalid value for name, no empty strings for names.");
        if (zip.length() != 5)
            throw new IllegalArgumentException("Invalid length. Zip format should be five numbers");
        else
            for (int i = 0; i < zip.length(); i++)
            {
                if (!Character.isDigit(zip.charAt(i))) {
                    throw new IllegalArgumentException("Invalid zip format, zip contains non-digits.");
                }
            }
    }

    /**
     * This constructor is passed a valid name, zip, phone, and address based on project specifications.
     * @param name,phone, address, zip
     * @throws IllegalArgumentException
     */
    public Restaurant(String name, String zip, String address, String phone) throws IllegalArgumentException
    {
        this.name = name;
        this.zip = zip;
        this.address = address;
        this.phone = phone;

        if (name == null)
            throw new IllegalArgumentException("Invalid value for name, no null names.");
        if (name == "")
            throw new IllegalArgumentException("Invalid value for name, no empty strings for names.");
        if (zip.length() != 5)
            throw new IllegalArgumentException("Invalid length. Zip format should be five numbers.");
        else
            for (int i = 0; i < zip.length(); i++)
            {
                if (!Character.isDigit(zip.charAt(i))) {
                    throw new IllegalArgumentException("Invalid zip format, zip contains non-digits.");
                }
            }
        if (address == "")
            throw new IllegalArgumentException("Invalid address. No empty addresses");
    }

   // getter methods for the data fields
    /**
     * This method is a getter method for the address.
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method is a getter method for the zip.
     * @return zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * This method is a getter method for the phone number.
     * @return phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method is a getter method for the name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * This method adds the inspection to the inspection list for each restaurant given an inspection parameter is passed.
     * @param inspect for the inspection
     * @throws IllegalArgumentException
     */
    public void addInspection(Inspection inspect) throws IllegalArgumentException
    {
        if (inspect == null)
            throw new IllegalArgumentException("Invalid inspection data, no null inspections.");
        if (!(inspect == null))
        {
            try
            {
                inspectionList.add(inspect);
            }
            catch (IllegalArgumentException ex ) {
                //ignore this exception and skip to the next line
            }
        }

    }

    public ArrayList<Inspection> getInspectionList() {
        return inspectionList;
    }

    /**
     * This method overrides the compare to method by returning the results of comparing two restaurants by name and zip to determine if they are the same.
     * @return int for equality of values
     * @param r for restaurant objects
     */
    @Override
    public int compareTo(Restaurant r)
    {
        if (this.name.equalsIgnoreCase(r.name))
        {
            return(this.zip.compareTo(r.zip));
        }
        else
            {
            return(this.name.compareToIgnoreCase(r.name));
        }
    }

    /**
     * This method overrides the equals method by returning the results of comparing two restaurants by name first and zip to determine if they are the same.
     * @return boolean if they are equal
     * @param obj for each object
     */
    //@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Restaurant))
            return false;

        Restaurant res = (Restaurant) obj;

        if (name == null)
        {
            if (res.name != null)
                return false;
        }
        else if (!name.equalsIgnoreCase(res.name))
        {
            return false;
        }

        if (zip != res.zip)
        {
            return false;
        }

        return true;
    }

    /**
     * This method overrides the to string method by returning the restaurant in a table format indicated below.
     * @return formatted string
     */
    @Override
    public String toString()
    {
        return String.format
                ("\n%s\n" +
                "------------------------------------\n" +
                "address                         : %s\n" +
                "zip                             : %s\n" +
                "phone                           : %s\n" +
                "recent inspection results:\n" +
                "%s, %s, %s, %s", name, address, zip, phone, inspectionList.get(0).score, inspectionList.get(0).date, inspectionList.get(0).risk, inspectionList.get(0).violation);
    }

}