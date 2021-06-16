package ex44;

/*
 *  UCF COP3330 Summer 2021 Assignment 3 Solution
 *  Copyright 2021 Stephan Hartig
 */

import shared.io.InputOutput;

import java.io.IOException;

public class App {
   public static void main(String[] args) {
      try (InputOutput io = new InputOutput()) {
         /* Entry point. */
         
      } catch (IOException e) {
         System.err.println(e);
      }
   }
}