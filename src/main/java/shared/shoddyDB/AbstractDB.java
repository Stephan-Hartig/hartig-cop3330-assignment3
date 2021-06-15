package shared.shoddyDB;

/*
 *  UCF COP3330 Summer 2021 Assignment 2 Solution
 *  Copyright 2021 Stephan Hartig
 */

import lombok.Getter;

import java.util.*;

abstract public class AbstractDB {
   
   private List<Map<String, String>> db;
   
   /**
    * Basic format:
    * column name, description, ... attributes
    *
    * Top-to-bottom order here corresponds to left-to-right order in a CSV file and in a String[] obj.
    */
   @Getter final private String[][] CSV_ORDER;
   @Getter final private int CSV_ORDER_ATTR_STARTIDX;
   
   public AbstractDB(int CSV_ORDER_ATTR_STARTIDX, String[][] CSV_ORDER) {
      this.CSV_ORDER_ATTR_STARTIDX = CSV_ORDER_ATTR_STARTIDX;
      this.CSV_ORDER = CSV_ORDER;
      this.db = new ArrayList<>();
   }
   
   public int size() {
      return this.db.size();
   }
   
   private boolean hasAttribute(String[] csv_order_row, String attr) {
      return Arrays
         .stream(Arrays.copyOfRange(csv_order_row, CSV_ORDER_ATTR_STARTIDX, csv_order_row.length))
         .anyMatch(attr::equals);
   }
   
   /**
    * Add entry to the database.
    *
    * @param row Row of data. SHOULD BE in order by `CSV_ORDER`'s standard.
    * @return Success.
    */
   public boolean addEntry(String[] row) {
      Hashtable<String, String> entry = new Hashtable<>();
      for (int i = 0; i < CSV_ORDER.length; i++) {
         // Null entries are empty "" strings. No truly null entries are allowed.
         if (row[i] == null)
            return false;
         if (!hasAttribute(CSV_ORDER[i], "nullable")) {
            if (row[i].isBlank())
               return false;
         }
         entry.put(CSV_ORDER[i][0], row[i]);
      }
      return this.db.add(entry);
   }
   
   public List<Map<String, String>> selectAll(Constraint constraint) {
      return constraint.filter(this.db);
   }
}
