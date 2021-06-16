package ex41;

/*
 *  UCF COP3330 Summer 2021 Assignment 3 Solution
 *  Copyright 2021 Stephan Hartig
 */

import lombok.val;
import shared.io.FileIO;
import shared.io.InputOutput;

import java.io.IOException;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class App {
   public static void main(String[] args) {
      try (InputOutput io = new InputOutput()) {
         /* Entry point. */
         
         var sorted = FileIO
               .slurpLines("resources/exercise41_input.txt")
               .stream()
               .sorted()
               .collect(Collectors.toCollection(LinkedList::new));
         
         sorted.add(0, String.format("Total of %d names\n-----------------", sorted.size()));
   
         FileIO.spit("resources/exercise41_output.txt", sorted);
         
      } catch (IOException e) {
         System.err.println(e);
      }
   }
}