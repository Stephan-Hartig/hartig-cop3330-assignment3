package shared.io;

/*
 *  UCF COP3330 Summer 2021 Assignment 3 Solution
 *  Copyright 2021 Stephan Hartig
 */

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class FileIOTest {
   
   final private String outputFilename = "test/tmp";
   
   
   
   private static Stream<Arguments> params_slurp() {
      return Stream.of(
         Arguments.of("test/countdown3_multiLine_noTrailingNewline", "three\ntwo\none"),
         Arguments.of("test/countdown3_multiLine_trailingNewline", "three\ntwo\none\n"),
         Arguments.of("test/countdown3_padded3_multiLine_trailingNewline", "   three\n   two\n   one\n"),
         Arguments.of("test/countdown3_multiLine_emptyLineBetween_trailingNewline", "three\n\ntwo\n\none\n"),
         Arguments.of("test/countdown3_singleLine_noTrailingNewline", "three two one"),
         Arguments.of("test/countdown3_singleLine_trailingNewline", "three two one\n"),
         Arguments.of("test/empty3", "\n\n")
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
   
   private static Stream<Arguments> params_slurpLines() {
      return Stream.of(
         Arguments.of("test/countdown3_multiLine_noTrailingNewline",    new String[] {"three", "two", "one"}),
         Arguments.of("test/countdown3_multiLine_trailingNewline",      new String[] {"three", "two", "one"}),
         Arguments.of("test/countdown3_padded3_multiLine_trailingNewline",          new String[] {"   three", "   two", "   one"}),
         Arguments.of("test/countdown3_multiLine_emptyLineBetween_trailingNewline", new String[] {"three", "", "two", "", "one"}),
         Arguments.of("test/countdown3_singleLine_noTrailingNewline",   new String[] {"three two one"}),
         Arguments.of("test/countdown3_singleLine_trailingNewline",     new String[] {"three two one"}),
         Arguments.of("test/empty3", new String[] {"", ""})
      );
   }
   @ParameterizedTest
   @MethodSource("params_slurpLines")
   void test_slurpLines(String filename, String[] expect) {
      try {
         List<String> fileContents = FileIO.slurpLines(filename);
         
         Assertions.assertEquals(expect.length, fileContents.size());
         Assertions.assertEquals(Arrays.stream(expect).toList(), fileContents);
      }
      catch (IOException e) {
         Assertions.assertEquals("", "IOException thrown");
      }
   }
   
   private static Stream<Arguments> params_spit() {
      return Stream.of(
         Arguments.of("foobar\n"),
         Arguments.of("foo\nbar\n"),
         Arguments.of("foobar\n\n"),
         Arguments.of("foo\n\nbar\n\n")
      );
   }
   @ParameterizedTest
   @MethodSource("params_spit")
   void test_spit(String expect) {
      try {
         FileIO.spit(outputFilename, expect);
         Assertions.assertEquals(expect, FileIO.slurp(outputFilename));
      }
      catch (IOException e) {
         Assertions.assertEquals("", "IOException thrown");
      }
   }
   
   private static Stream<Arguments> params_spit_list() {
      return Stream.of(
         Arguments.of(Arrays.stream(new String[] { "foo", "bar" }).toList()),
         Arguments.of(Arrays.stream(new String[] { "foo", "", "", "bar" }).toList()),
         Arguments.of(Arrays.stream(new String[] { "foo  ", "bar" }).toList()),
         Arguments.of(Arrays.stream(new String[] { "foo\n", "bar", "", "" }).toList()),
         Arguments.of(Arrays.stream(new String[] { "foo\n", "bar", "", "\n"}).toList()),
         Arguments.of(Arrays.stream(new String[] { "foo", "", "bar" }).toList())
      );
   }
   @ParameterizedTest
   @MethodSource("params_spit_list")
   void test_spit(List<String> input) {
      try {
         FileIO.spit(outputFilename, input);
         
         StringBuilder sb = new StringBuilder();
         for (String s : input)
            sb.append(s + "\n");
         
         String expect = sb.toString();
         
         Assertions.assertEquals(expect, FileIO.slurp(outputFilename));
      }
      catch (IOException e) {
         Assertions.assertEquals("", "IOException thrown");
      }
   }
}
