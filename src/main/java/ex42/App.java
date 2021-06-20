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
   
         var table = FileIO
            .slurpLines("resources/exercise42_input.txt")
            .stream()
            .map(str -> String.format(fmt, str.split(",")))
            .collect(Collectors.toCollection(LinkedList::new));
         
         table.add(0, String.format(fmt, titles));
         table.add(1, String.format(fmt, dashes));
   
   
         FileIO.spit("resources/exercise42_output.txt", table);
      } catch (IOException e) {
         System.err.println(e);
      }
   }
   
}