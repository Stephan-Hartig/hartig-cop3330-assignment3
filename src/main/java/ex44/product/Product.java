package ex44.product;

/*
 *  UCF COP3330 Summer 2021 Assignment 3 Solution
 *  Copyright 2021 Stephan Hartig
 */

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class Product {
   public String name;
   public float price;
   public int quantity;
   
   @Override
   public String toString() {
      return String.format("{ %s, %.2f, %d }",
         this.name,
         this.price,
         this.quantity
      );
   }
   
   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Product product = (Product) o;
      return Float.compare(product.price, price) == 0 && quantity == product.quantity && name.equals(product.name);
   }
   
   @Override
   public int hashCode() {
      return Objects.hash(name, price, quantity);
   }
}
