package ex44;

/*
 *  UCF COP3330 Summer 2021 Assignment 3 Solution
 *  Copyright 2021 Stephan Hartig
 */

import lombok.val;
import shared.io.FileIO;
import shared.io.InputOutput;

import java.io.IOException;
import java.util.Arrays;

import com.google.gson.Gson;


public class App {
   public static void main(String[] args) {
      try (InputOutput io = new InputOutput()) {
         /* Entry point. */
         ProductDB db = new ProductDB("resources/exercise44_input.json");
         
         Product product;
         
         while (true) {
            String input = io.promptTrimmed("What is the product name? ");
            if (db.has(input)) {
               product = db.get(input);
               break;
            }
            io.println("Sorry, that product was not found in our inventory.");
         }
         
         io.printf("Name: %s\nPrice: %.2f\nQuantity: %d\n",
            product.name,
            product.price,
            product.quantity
         );
         
      } catch (IOException e) {
         System.err.println(e);
      }
   }
}