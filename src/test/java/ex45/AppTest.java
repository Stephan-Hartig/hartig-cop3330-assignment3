package ex45;

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
   private static Stream<Arguments> params_String_replaceAll() {
      return Stream.of(
         Arguments.of("two two three", "one two three", "one", "two"),
         Arguments.of("two\ntwo\nthree\n", "one\ntwo\nthree\n", "one", "two"),
         Arguments.of("woop woop yeahs", "yeah yeah yeahs", "\\byeah\\b", "woop"),
         Arguments.of("woop woop woops", "yeah yeah yeahs", "yeah", "woop")
      );
   }
   @ParameterizedTest
   @MethodSource("params_String_replaceAll")
   void test_String_replaceAll(String expect, String input, String regex, String substitute) {
      Assertions.assertEquals(
         expect,
         input.replaceAll(regex, substitute)
      );
   }
}
