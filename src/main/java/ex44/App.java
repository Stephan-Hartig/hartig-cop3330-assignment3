package ex44;

/*
 *  UCF COP3330 Summer 2021 Assignment 3 Solution
 *  Copyright 2021 Stephan Hartig
 */

import com.google.gson.stream.MalformedJsonException;
import ex44.product.Product;
import ex44.product.ProductDB;
import shared.io.InputOutput;

import java.io.IOException;


public class App {
   public static void main(String[] args) {
      try (InputOutput io = new InputOutput()) {
         /* Entry point. */

         /* Init. */
         ProductDB db = new ProductDB("resources/exercise44_input.json");
   
         /* Loop until the user asks for an existing product. */
         Product product;
         while (true) {
            String input = io.promptTrimmed("What is the product name? ");
            if (db.has(input)) {
               product = db.get(input);
               break;
            }
            io.println("Sorry, that product was not found in our inventory.");
         }
   
         /* Print info about the product. */
         io.printf("Name: %s\nPrice: %.2f\nQuantity: %d\n",
            product.name,
            product.price,
            product.quantity
         );
   
      }
      catch (IOException e) {
         System.err.println(e);
      }
   }
}
