package ex42;

/*
 *  UCF COP3330 Summer 2021 Assignment 3 Solution
 *  Copyright 2021 Stephan Hartig
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CSV {
   /**
    * Create a printable table out of a list of CSV data.
    *
    * @param titles     Titles for the table.
    * @param paddings   Paddings for the table. Will truncate overflowing data.
    * @param raw        CSV data, commas still intact, each String is a row.
    * @return           List where each String is a row to print out.
    */
   public static List<String> csvToTable(String[] titles, int[] paddings, List<String> raw) {
      final int maxPadWidth = Arrays.stream(paddings).reduce(0, Math::max);
      
      assert titles.length == paddings.length;
      
      /* Create `fmt`: "%-x.xs%-y.ys%-z.zs..." */
      String fmt = Arrays.stream(paddings)
         .mapToObj(x -> String.format("%%-%d.%ds", x, x))
         .collect(Collectors.joining());
      String dash = "-".repeat(maxPadWidth);
      String[] dashes = new String[titles.length];
      Arrays.fill(dashes, dash);
      
      
      /* Pad each row according to `fmt`. */
      var table = raw
         .stream()
         .map(str -> String.format(fmt, str.split(",")))
         .collect(Collectors.toCollection(LinkedList::new));
      
      /* Add title. */
      table.add(0, String.format(fmt, titles));
      table.add(1, String.format(fmt, dashes));
      
      return table;
   }
}
