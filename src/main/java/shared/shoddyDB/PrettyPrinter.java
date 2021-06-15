package shared.shoddyDB;

import com.google.common.collect.Streams;
import lombok.val;
import shared.io.InputOutput;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 *  UCF COP3330 Summer 2021 Assignment 2 Solution
 *  Copyright 2021 Stephan Hartig
 */
public class PrettyPrinter {
   private static String padRightToWidth(String str, int n, char c) {
      StringBuilder sb = new StringBuilder(str);
      
      for (int i = str.length(); i < n; i++)
         sb.append(c);
      
      return sb.toString();
   }
   
   public static void prettyPrintCustom(List<Map<String, String>> selected, List<Pair<String, Function<Map<String, String>, String>>> columnData) {
      try (InputOutput io = new InputOutput()) {
         // The following was inspired by Mekromic <3
         
         val headers = columnData
            .stream()
            .map(pair -> pair.getLeft())
            .collect(Collectors.toList());
         
         val columns = columnData
            .stream()
            .map(pair -> pair.getRight())
            .collect(Collectors.toList());
         
         val maxWidths = columnData
            .stream()
            .map(pair -> selected
               .stream()
               .map(pair.getRight())
               .map(String::length)
               .reduce(pair.getLeft().length(), Math::max))
            .toList();
   
   
         io.println(
            Streams
               .zip(
                  headers.stream(),
                  maxWidths.stream(),
                  (header, maxWidth) -> " " + padRightToWidth(header, maxWidth, ' ') + " ")
               .collect(Collectors.joining("|"))
         );
         io.println(
            maxWidths
               .stream()
               .map(maxWidth -> "-" + padRightToWidth("", maxWidth, '-') + "-")
               .collect(Collectors.joining("+"))
         );
         for (val row : selected) {
            io.println(
               Streams
                  .zip(
                     columns.stream(),
                     maxWidths.stream(),
                     (column, maxWidth) -> " " + padRightToWidth(column.apply(row), maxWidth, ' ') + " ")
                  .collect(Collectors.joining("|"))
            );
         }
      }
      catch (IOException e) {
         System.err.println(e);
      }
   }
   
   
   
   
   
   
   
   /*
    * The following functions are yucky pieces of doo doo that exist only to remind
    * myself of how I tried so hard, and got so far, but in the end ^ only matters.
    *
    * Keep scrolling at your own risk.
    */
   
   
   
   
   
   
   
   
   
   
   
   
   
   public static void prettyPrintLegacy(List<Map<String, String>> selected) {
      try (InputOutput io = new InputOutput()) {
         // The following was inspired by Mekromic <3
         
         final Function<Character, Function<Integer, Function<String, String>>> paddingBuilder = c -> length -> value -> {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            for (int i = sb.length(); i < length; i++) sb.append(c);
            return sb.toString();
         };
         
         final Function<String, Function<Function<Map<String, String>, Integer>, Integer>> getMax = str -> fn -> {
            return selected.stream().map(fn).reduce(str.length(), Math::max);
         };
         
         final String nameHeader = "Name";
         final String posHeader = "Position";
         final String sepHeader = "Separation Date";
         
         final int nameSize = getMax.apply(nameHeader).apply(e -> e.get("fname").length() + 1 + e.get("lname").length());
         final int posSize = getMax.apply(posHeader).apply(e -> e.get("position").length());
         final int sepSize = getMax.apply(sepHeader).apply(e -> e.get("separation_date").length());
         
         val padDashes = paddingBuilder.apply('-').apply(6 + nameSize + posSize + sepSize + 2);
         
         val padName = paddingBuilder.apply(' ').apply(2 + nameSize);
         val padPos = paddingBuilder.apply(' ').apply(2 + posSize);
         val padSep = paddingBuilder.apply(' ').apply(2 + sepSize);
         
         
         io.println(String.format(" %s | %s | %s ",
            padName.apply(nameHeader),
            padPos.apply(posHeader),
            padSep.apply(sepHeader)
         ));
         io.println(padDashes.apply(""));
         for (val entry : selected) {
            io.println(String.format(" %s | %s | %s ",
               padName.apply(entry.get("fname") + " " + entry.get("lname")),
               padPos.apply(entry.get("position")),
               padSep.apply(entry.get("separation_date"))
            ));
         }
      }
      catch (IOException e) {
         System.err.println(e);
      }
   }
   
   public static void prettyPrintYucky(List<Map<String, String>> selected, List<Pair<String, Function<Map<String, String>, String>>> columnData) {
      try (InputOutput io = new InputOutput()) {
         // The following was also inspired by Mekromic <3
         // WARNING: The following is horrific (BUT IT WORKS).
         // WARNING: I also realized how horrifically inefficient this is.
         
         /* Header and raw column data. */
         Supplier<Stream<String>> columnHeaders = () -> columnData.stream().map(pair -> pair.getLeft());
         Supplier<Stream<Function<Map<String, String>, String>>> columnExtractors = () -> columnData.stream().map(pair -> pair.getRight());
         
         
         
         /* Column max width. */
         final Function<String, Function<Function<Map<String, String>, Integer>, Integer>> getMax = str -> fn -> {
            return selected.stream().map(fn).reduce(str.length(), Math::max);
         };
         Supplier<Stream<Integer>> columnSizes = () -> columnData.stream().map(pair -> getMax.apply(pair.getLeft()).apply(e -> pair.getRight().apply(e).length()));
         
         
         /* Column padding. */
         final Function<Character, Function<Integer, Function<String, String>>> paddingBuilder = c -> length -> value -> {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            for (int i = sb.length(); i < length; i++) sb.append(c);
            return sb.toString();
         };
         
         Supplier<Stream<Function<String, String>>> columnPaddings = () -> columnSizes.get().map(i -> paddingBuilder.apply(' ').apply(2 + i));
         
         
         val padDashes = paddingBuilder.apply('-').apply(3 * (int)columnSizes.get().count() - 1 + columnSizes.get().mapToInt(Integer::intValue).sum());
         
         
         /* Priiiiint. */
         io.println(Streams.zip(columnHeaders.get(), columnPaddings.get(), (h, pad) -> pad.apply(" " + h + " "))
            .collect(Collectors.joining("|")));
         io.println(padDashes.apply(""));
         for (val entry : selected) {
            io.println(Streams.zip(columnExtractors.get(), columnPaddings.get(), (ex, pad) -> pad.apply(" " + ex.apply(entry) + " "))
               .collect(Collectors.joining("|")));
         }
      }
      catch (IOException e) {
         System.err.println(e);
      }
   }
}
