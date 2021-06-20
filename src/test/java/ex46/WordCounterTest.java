package ex46;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 */
public class WordCounterTest {
   private static Stream<Arguments> params_fromString() {
      return Stream.of(
         Arguments.of(
            Map.of(),
            ""
         ),
         Arguments.of(
            Map.of(
               "one", 1,
               "two", 1,
               "three", 1
            ),
            "one two three"
         ),
         Arguments.of(
            Map.of(
               "one", 1,
               "two", 1,
               "three", 1,
               "s", 3
            ),
            "one's two's three's"
         ),
         Arguments.of(
            Map.of(
               "one", 1,
               "two", 2,
               "three", 3
            ),
            "one two three, two three\nthree\n"
         ),
         Arguments.of(
            Map.of(
               "one", 1,
               "two", 2,
               "three", 3
            ),
            "one two three 3 3 three3, two three\nthree\n"
         )
      );
   }
   @ParameterizedTest
   @MethodSource("params_fromString")
   void test_sort(Map<String, Integer> expect, String input) {
      Assertions.assertEquals(expect, WordCounter.fromString(input));
   }
}
