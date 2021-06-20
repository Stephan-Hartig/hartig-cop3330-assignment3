package ex44.product;

/*
 *  UCF COP3330 Summer 2021 Assignment 3 Solution
 *  Copyright 2021 Stephan Hartig
 */

import lombok.Getter;
import shared.io.FileIO;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public class ProductDB {
   
   @Getter private Map<String, Product> products;
   private ProductParser parser;
   @Getter private String filename;
   
   public ProductDB(String filename) throws IOException {
      this.filename = filename;
      this.parser = new ProductParser();
      this.read();
   }
   
   public void put(Product product) {
      this.products.put(product.name.toLowerCase(Locale.ROOT), product);
   }
   
   public Product get(String name) {
      return this.products.get(name.toLowerCase(Locale.ROOT));
   }
   
   public boolean has(String name) {
      return this.products.get(name.toLowerCase(Locale.ROOT)) != null;
   }
   
   public void read() throws IOException {
      this.products = parser.fromJson(FileIO.slurp(this.filename));
   }
   
   public void write() throws IOException {
      FileIO.spit(this.filename, parser.toJson(this.products));
   }
}
