package ex42;

import ex42.App;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

/**
 */
public class CSVTest {
   private static Stream<Arguments> params_csvToTable() {
      return Stream.of(
         Arguments.of(
            List.of("One  Two  Three", "---------------", "w    o    w!   ", "omgz omgz omgz "),
            new String[] { "One", "Two", "Three" },
            new int[] { 5, 5, 5 },
            List.of("w,o,w!", "omgz,omgz,omgz")
         ),
         Arguments.of(
            List.of("One  Two Thr", "------------", "w    o   w! ", "omgz omgzomg"),
            new String[] { "One", "Two", "Three" },
            new int[] { 5, 4, 3 },
            List.of("w,o,w!", "omgz,omgz,omgz")
         )
      );
   }
   @ParameterizedTest
   @MethodSource("params_csvToTable")
   void test_csvToTable(List<String> expect, String[] titles, int[] paddings, List<String> raw) {
      Assertions.assertEquals(expect, CSV.csvToTable(titles, paddings, raw));
   }
}
