package ex41;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

/**
 * Testing Java's standard library stuff to make sure they work :kekw:
 */
public class AppTest {
   private static Stream<Arguments> params_sort() {
      return Stream.of(
         Arguments.of(List.of("alpha"), List.of("alpha")),
         Arguments.of(List.of("alpha", "beta"), List.of("beta", "alpha")),
         Arguments.of(List.of("alpha", "beta", "gamma"), List.of("alpha", "beta", "gamma")),
         Arguments.of(List.of("alpha", "beta", "delta", "gamma"), List.of("alpha", "beta", "gamma", "delta"))
      );
   }
   @ParameterizedTest
   @MethodSource("params_sort")
   void test_sort(List<String> expect, List<String> input) {
      Assertions.assertEquals(expect, input.stream().sorted().toList());
   }
}
