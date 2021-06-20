package ex46;

/*
 *  UCF COP3330 Summer 2021 Assignment 3 Solution
 *  Copyright 2021 Stephan Hartig
 */

import lombok.val;
import shared.io.FileIO;
import shared.io.InputOutput;

import java.io.IOException;

public class App {
   public static void main(String[] args) {
      try (InputOutput io = new InputOutput()) {
         /* Entry point. */
   
         long allStartTime = System.nanoTime();
   
         val text = FileIO.slurp("resources/macbeth.txt");
         
         long parseStartTime = System.nanoTime();
         
         val counts
            //= WordCounter.fromString(FileIO.slurp("resources/exercise46_input.txt"));
            = WordCounter.fromString(text);
         
         long endTime = System.nanoTime();
         long durationAll = (endTime - allStartTime);  //divide by 1000000 to get milliseconds.
         long durationParse = (endTime - parseStartTime);
   
         io.printf("There were %d unique words.\n", counts.size());
         
         counts.entrySet()
            .stream()
            .sorted((left, right) -> 0 - left.getValue().compareTo(right.getValue()))
            .forEach(pair -> {
               io.printf("%-10s  ", pair.getKey());
               io.println("*".repeat(pair.getValue()));
            });
   
         io.printf("It took %.2f milliseconds to slurp + parse.\n", durationAll / 1000000.0);
         io.printf("It took %.2f milliseconds to parse.\n", durationParse / 1000000.0);
      } catch (IOException e) {
         System.err.println(e);
      }
   }
}