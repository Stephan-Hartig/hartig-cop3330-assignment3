package ex44.product;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 */
public class ProductParserTest {
   
   private static Product createProduct(String name, float price, int quantity) {
      Product product = new Product();
      
      product.name = name;
      product.price = price;
      product.quantity = quantity;
      
      return product;
   }
   
   private static Stream<Arguments> params_fromJson() {
      return Stream.of(
         Arguments.of(new HashMap<>(), "{ 'products': [ ] }"),
         Arguments.of(
            Map.of(
               "widget", createProduct("Widget",  25.00f, 5)
            ),
            "{ 'products': [ { 'name':  'Widget', 'price':  25.00, 'quantity':  5 } ] }"
         ),
         Arguments.of(
            Map.of(
               "a", createProduct("A",  1.00f, 1),
               "b", createProduct("B",  2.00f, 2)
            ),
            "{'products':[ {'name':'A','price':1.00,'quantity':1}, {'name':'B','price':2.00,'quantity':2} ]}"
         )
      );
   }
   @ParameterizedTest
   @MethodSource("params_fromJson")
   void test_fromJson(Map<String, Product> expect, String json) {
      ProductParser parser = new ProductParser();
      
      Assertions.assertDoesNotThrow(() -> Assertions.assertEquals(
         parser.fromJson(json),
         parser.fromJson(json)
      ));
   }
   
   @ParameterizedTest
   @MethodSource("params_fromJson")
   void test_toJson(Map<String, Product> db, String __) {
      ProductParser parser = new ProductParser();
      
      /* We don't care how the outputted JSON looks, just as long as it can be correctly parsed. */
      Assertions.assertDoesNotThrow(() -> Assertions.assertEquals(
         db,
         parser.fromJson(parser.toJson(db))
      ));
   }
}
