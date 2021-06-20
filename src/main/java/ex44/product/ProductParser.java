package ex44.product;

/*
 *  UCF COP3330 Summer 2021 Assignment 3 Solution
 *  Copyright 2021 Stephan Hartig
 */

import com.google.gson.Gson;
import com.google.gson.stream.MalformedJsonException;
import shared.io.FileIO;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ProductParser {
   
   private class RawJsonInput {
      public Product[] products;
   }
   
   private Gson gson;
   
   public ProductParser() {
      this.gson = new Gson();
   }
   
   public Map<String, Product> fromJson(String json) throws MalformedJsonException  {
      Gson gson = new Gson();
      Map<String, Product> products = new HashMap<>();
      
      Arrays.stream(gson.fromJson(json, RawJsonInput.class).products)
         .forEach(product -> {
            products.put(product.name.toLowerCase(Locale.ROOT), product);
         });
      
      return products;
   }
   public String toJson(Map<String, Product> products) {
      RawJsonInput wrapper = new RawJsonInput();
      wrapper.products = products.values().toArray(new Product[0]);
      Gson gson = new Gson();
      return gson.toJson(wrapper);
   }
}
