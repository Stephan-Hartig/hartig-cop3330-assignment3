package shared.shoddyDB;

import java.util.List;
import java.util.Map;

/*
 *  UCF COP3330 Summer 2021 Assignment 2 Solution
 *  Copyright 2021 Stephan Hartig
 */
public interface Constraint {
   /**
    * Its up to implementations to ensure that the user is returned DUPLICATE data rather than the original.
    * @param coll The database. It may be original, depending on the implementation.
    * @return     Filtered data from the database.
    */
   public List<Map<String, String>> filter(List<Map<String, String>> coll);
}
