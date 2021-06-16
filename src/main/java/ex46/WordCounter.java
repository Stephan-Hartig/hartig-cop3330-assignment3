package ex46;

/*
 *  UCF COP3330 Summer 2021 Assignment 3 Solution
 *  Copyright 2021 Stephan Hartig
 */

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class WordCounter {
   public static Map<String, Integer> fromString(String str) {
      Map<String, Integer> counts = new HashMap<>();
   
      Pattern.compile("\\b[a-zA-Z]+\\b")
         .matcher(str.toLowerCase(Locale.ROOT))
         .results()
         .map(MatchResult::group)
         .forEach(word -> {
            counts.put(word, (counts.get(word) == null) ? 1 : counts.get(word) + 1);
         });
      
      return counts;
   }
}
