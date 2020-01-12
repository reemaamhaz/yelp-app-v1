package project3;

import java.util.ArrayList;
import java.util.Collections;

/**
 * RestaurantList class is used to store a collection of Restaurant objects by zipcode or name.
 * This class inherits all of its properties from an ArrayList<Restaurant>. It
 * allows search through functions that are filtered by zipcode
 * and by name.
 *
 *
 *
 * @author Reema Amhaz
 *
 */
public class RestaurantList extends LinkedList<Restaurant> //essentially becomes arraylist  and then adds functionality
{
    public void verifyNewRestaurant(Restaurant r) {
        for (int i = 0; i < size(); i++)
        {
            if (get(i).equals(r))
            {
                get(i).addInspection(r.getInspectionList().get(0)); //adds the inspection
            }
        }
        add(r); //add this restaurant if it doesn't exist
    }

    /**
     * This method adds matching restaurants to the list based on the keyword search query and then sorts the list and returns it to the user.
     * @param keyword - user input string
     * @return nameMatchedRestaurants (list of sorted restaurants)
     */
    public RestaurantList getMatchingRestaurants ( String keyword )
    {
        if (keyword == null) return null;
        if (keyword == "") return null;

        RestaurantList nameMatchedRestaurants = new RestaurantList();
        for (Restaurant r : this)
        {
            String restaurant = r.getName();
            if (restaurant == null)
                continue;
            if (restaurant.toLowerCase().contains( keyword.toLowerCase() )) // checks if the list contains that restaurant, if it does then it adds it to the matching arraylist
                nameMatchedRestaurants.add(r);
        }
        if (nameMatchedRestaurants.isEmpty()) {
            return null;
        }
        nameMatchedRestaurants.sort();
        return nameMatchedRestaurants;
    }

    /**
     * This method adds matching restaurants to the list based on the keyword zip search query and then sorts the list and returns it to the user.
     * @param keyword - user input string
     * @return zipMatchedRestaurants (list of sorted restaurants)
     */
    public RestaurantList getMatchingZip ( String keyword )
    {
        if (keyword == null) return null;
        if (keyword == "") return null;

        RestaurantList zipMatchedRestaurants = new RestaurantList();
        for (Restaurant r : this)
        {
            String restaurant = r.getZip();
            if (restaurant == null)
                continue;
            if (restaurant.contains(keyword)) // checks if the list contains that restaurant, if it does then it adds it to the matching arraylist
                zipMatchedRestaurants.add(r);
        }

        if (zipMatchedRestaurants.isEmpty()) {
            return null;
        }

        zipMatchedRestaurants.sort();
        return zipMatchedRestaurants;
    }

    /**
     * This method overrides the to string method by returning the restaurants separated by commas.
     * @return formatted string
     */
   @Override
   public String toString()
   {
       return String.format
               ("%s, %s, %s", get(0).name, get(1).name, get(2).name);
   }

}