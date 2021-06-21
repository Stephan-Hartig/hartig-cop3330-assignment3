package ex42;

/*
 *  UCF COP3330 Summer 2021 Assignment 3 Solution
 *  Copyright 2021 Stephan Hartig
 */

import lombok.val;
import shared.io.FileIO;
import shared.io.InputOutput;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class App {
   public static void main(String[] args) {
      try (InputOutput io = new InputOutput()) {
         /* Entry point. */
         
         val fmt = "%-15.15s%-15.15s%-6.6s";
         val titles = new String[] {"Last", "First", "Salary"};
         val dash = "-------------------------------------------";
         val dashes = new String[] {dash, dash, dash};
   
         /* Slurp input-file into a list and pad each line to form a table. */
         var table = FileIO
            .slurpLines("resources/exercise42_input.txt")
            .stream()
            .map(str -> String.format(fmt, str.split(",")))
            .collect(Collectors.toCollection(LinkedList::new));
         
         /* Add header info. */
         table.add(0, String.format(fmt, titles));
         table.add(1, String.format(fmt, dashes));
   
   
         /* Spit into file. */
         FileIO.spit("resources/exercise42_output.txt", table);
      } catch (IOException e) {
         System.err.println(e);
      }
   }
   
   /**
    * Honestly ^ is not THAT bad. At least it has actual pseudocode!
    */
   public static void mainAlt(String[] args) {
      try (InputOutput io = new InputOutput()) {

         /* Do everything. */
         FileIO.spit("resources/exercise44_input.json",
            CSV.csvToTable(
               new String[] {"Last", "First", "Salary"},
               new int[] {15, 15, 6},
               FileIO.slurpLines("resources/exercise45_input.txt")
            )
         );
      } catch (IOException e) {
         System.err.println(e);
      }
   }
}
