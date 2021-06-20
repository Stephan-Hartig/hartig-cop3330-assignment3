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
   public static List<String> csvToTable(String[] titles, int[] paddings, List<String> raw) {
      final int maxPadWidth = Arrays.stream(paddings).reduce(0, Math::max);
      
      assert titles.length == paddings.length;
      
      String fmt = Arrays.stream(paddings)
         .mapToObj(x -> String.format("%%-%d.%ds", x, x))
         .collect(Collectors.joining());
      String dash = "-".repeat(maxPadWidth);
      String[] dashes = new String[titles.length];
      Arrays.fill(dashes, dash);
      
      
      var table = raw
         .stream()
         .map(str -> String.format(fmt, str.split(",")))
         .collect(Collectors.toCollection(LinkedList::new));
      
      table.add(0, String.format(fmt, titles));
      table.add(1, String.format(fmt, dashes));
      
      return table;
   }
}
