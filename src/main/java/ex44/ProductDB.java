package ex44;

/*
 *  UCF COP3330 Summer 2021 Assignment 3 Solution
 *  Copyright 2021 Stephan Hartig
 */

import com.google.gson.Gson;
import lombok.Getter;
import lombok.val;
import shared.io.FileIO;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ProductDB {
   
   @Getter private Map<String, Product> products;
   private Gson gson;
   private String inputFilename;
   
   ProductDB(String inputFilename) throws IOException {
      this.inputFilename = inputFilename;
      this.gson = new Gson();
      this.read();
   }
   
   public void add(Product product) {
      this.products.put(product.name.toLowerCase(Locale.ROOT), product);
   }
   
   public Product get(String name) {
      return this.products.get(name.toLowerCase(Locale.ROOT));
   }
   
   public boolean has(String name) {
      return this.products.get(name.toLowerCase(Locale.ROOT)) != null;
   }
   
   public void read() throws IOException {
      this.products = new HashMap<>();
      
      Arrays
         .stream(
            this.gson
               .fromJson(FileIO.slurp(this.inputFilename), JsonInput.class)
               .products)
         .forEach(product -> this.products.put(product.name.toLowerCase(Locale.ROOT), product));
   }
   
   public void write() throws IOException {
      JsonInput obj = new JsonInput();
      obj.products = this.products.values().toArray(new Product[0]);
      String json = this.gson.toJson(obj);
      FileIO.spit(this.inputFilename, json);
   }
}
