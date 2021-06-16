package ex43;

/*
 *  UCF COP3330 Summer 2021 Assignment 3 Solution
 *  Copyright 2021 Stephan Hartig
 */

import lombok.val;
//import lombok.var;
import shared.io.InputOutput;

import java.io.IOException;
import java.util.Locale;

public class App {
   public static void main(String[] args) {
      try (InputOutput io = new InputOutput()) {
         /* Entry point. */
   
         val name = io.promptTrimmed("Site name: ");
         val author = io.promptTrimmed("Author: ");
         val createJsFolder = io
            .promptTrimmed("Do you want a folder for JavaScript? ")
            .toLowerCase(Locale.ROOT)
            .equals("y");
         val createCssFolder = io
            .promptTrimmed("Do you want a folder for CSS? ")
            .toLowerCase(Locale.ROOT)
            .equals("y");
   
         val project = new WebsiteGenerator(name, author);
         if (createJsFolder)
            project.createDir("js");
         if (createCssFolder)
            project.createDir("css");

         project
            .getCreationLog()
            .stream()
            .forEach(str -> io.printf("Created %s\n", str));
         
      } catch (IOException e) {
         System.err.println(e);
      }
   }
}