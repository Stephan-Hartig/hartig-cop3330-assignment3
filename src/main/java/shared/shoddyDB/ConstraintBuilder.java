package shared.shoddyDB;

/*
 *  UCF COP3330 Summer 2021 Assignment 2 Solution
 *  Copyright 2021 Stephan Hartig
 */

import lombok.val;

import java.util.*;
import java.util.function.Function;

public class ConstraintBuilder implements Constraint {
   
   private ArrayList<String> ordering;
   private ArrayList<Function<Map<String, String>, Boolean>> conditions;
   
   public ConstraintBuilder() {
      this.ordering = new ArrayList<>();
      this.conditions = new ArrayList<>();
   }
   
   public ConstraintBuilder orderBy(String attr) {
      this.ordering.add(attr);
      return this;
   }
   
   /**
    * Example usage:
    * @<code>cb.where(entry -> entry.get("fname").toLowerCase.equals("john"))</code>
    * @param condition
    * @return           Returns `this` to be further chained like a builder.
    */
   public ConstraintBuilder where(Function<Map<String, String>, Boolean> condition) {
      this.conditions.add(condition);
      return this;
   }
   
   /**
    * Example usage:
    * @<code>cb.where("fname", fname -> fname.toLowerCase.equals("john"))</code>
    * @param attr       Name of the attribute.
    * @param condition  Predicate that returns boolean.
    * @return           Returns `this` to be further chained like a builder.
    */
   public ConstraintBuilder where(String attr, Function<String, Boolean> condition) {
      this.conditions.add(entry -> condition.apply(entry.get(attr)));
      return this;
   }
   
   @Override
   public List<Map<String, String>> filter(List<Map<String, String>> coll) {
      List<Map<String, String>> processed = new ArrayList<>();
   
      // Select entries that satisfy every condition.
      outer:
      for (val entry : coll) {
         for (val condition : this.conditions) {
            if (!condition.apply(entry))
               continue outer;
         }
         processed.add(new HashMap<>(entry));
      }
      
      // Sort.
      Collections.sort(processed, new Comparator<>() {
         @Override public int compare(Map<String, String> entry1, Map<String, String> entry2) {
            int value = 0;
            for (String attr : ordering) {
               value = entry1.get(attr).compareTo(entry2.get(attr));
               if (value != 0)
                  return value;
            }
            return value;
         }
      });
      
      return processed;
   }
}
