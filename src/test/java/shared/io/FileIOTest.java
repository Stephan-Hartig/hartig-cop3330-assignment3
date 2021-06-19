package shared.io;

/*
 *  UCF COP3330 Summer 2021 Assignment 3 Solution
 *  Copyright 2021 Stephan Hartig
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

public class FileIOTest {
   private static Stream<Arguments> params_slurp() {
      return Stream.of(
         Arguments.of("test/countdown3_multiLine_noTrailingNewline", "three\ntwo\none"),
         Arguments.of("test/countdown3_multiLine_trailingNewline", "three\ntwo\none\n"),
         Arguments.of("test/countdown3_singleLine_noTrailingNewline", "three two one"),
         Arguments.of("test/countdown3_singleLine_trailingNewline", "three two one\n")
      );
   }
   @ParameterizedTest
   @MethodSource("params_slurp")
   void test_slurp(String filename, String expect) {
      try {
         Assertions.assertEquals(expect, FileIO.slurp(filename));
      }
      catch (IOException e) {
         Assertions.assertTrue(false);
      }
   }
}
