package ex45;

/*
 *  UCF COP3330 Summer 2021 Assignment 3 Solution
 *  Copyright 2021 Stephan Hartig
 */

import shared.io.FileIO;
import shared.io.InputOutput;

import java.io.IOException;
import java.nio.file.Paths;

public class App {
   public static void main(String[] args) {
      try (InputOutput io = new InputOutput()) {
         /* Entry point. */
         FileIO.spit(
            Paths.get("resources", io.promptTrimmed("Output file name? ")).toString(),
            FileIO.slurp("resources/exercise45_input.txt")
               .replaceAll("utilize", "use") // Utilize "\\butilize\\b" to _only_ replace whole words.
         );
         
      } catch (IOException e) {
         System.err.println(e);
      }
   }
}
