package ex41;

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
         
         int a = 3;
         val b = a;
         
         val sorted = FileIO
               .slurpLines("resources/exercise41_input.txt")
               .stream()
               .sorted()
               .toList();
         
         //sorted.add(0, String.format("Total of %d names\n-----------------\n", sorted.size()));
   
         FileIO.spit("resources/exercise41_output.txt", sorted);
         
         io.println("foobar");
         
      } catch (IOException e) {
         System.err.println(e);
      }
   }
}