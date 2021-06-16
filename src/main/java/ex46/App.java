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
   
         val counts
            //= WordCounter.fromString(FileIO.slurp("resources/exercise46_input.txt"));
            = WordCounter.fromString(FileIO.slurp("resources/macbeth.txt"));
   
         io.printf("There were %d unique words.\n", counts.size());
         
         counts.entrySet()
            .stream()
            .sorted((left, right) -> 0 - left.getValue().compareTo(right.getValue()))
            .forEach(pair -> {
               io.printf("%-10s  ", pair.getKey());
               for (int i = 0; i < pair.getValue(); i++)
                  io.print("*");
               io.println();
            });
         
      } catch (IOException e) {
         System.err.println(e);
      }
   }
}