package shared.io;

/*
 *  UCF COP3330 Summer 2021 Assignment 3 Solution
 *  Copyright 2021 Stephan Hartig
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.stream.Stream;


public class InputOutputTest {
   
   private static Stream<Arguments> params_getTrimmed() {
      return Stream.of(
         Arguments.of("one", "one\n"),
         Arguments.of("one", "   one   \n"),
         Arguments.of("one", "one   \n"),
         Arguments.of("one two", "one two\n"),
         Arguments.of("one  two", "   one  two    \n"),
         Arguments.of("one  two", "one  two   \n")
      );
   }
   @ParameterizedTest
   @MethodSource("params_getTrimmed")
   void test_getTrimmed(String expect, String input) {
      ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
      System.setIn(in);
      
      try (InputOutput io = new InputOutput()) {
         Assertions.assertEquals(expect, io.getTrimmed());
      }
      catch (IOException e) {
         assert false;
      }
   }
   
   
   @ParameterizedTest
   @MethodSource("params_getTrimmed")
   void test_getLine(String expect, String input) {
      ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
      System.setIn(in);
      
      try (InputOutput io = new InputOutput()) {
         Assertions.assertEquals(expect, io.getTrimmed());
      }
      catch (IOException e) {
         assert false;
      }
   }
   
   private static Stream<Arguments> params_demandInt() {
      return Stream.of(
         Arguments.of(5, "5\n"),
         Arguments.of(5, "  3  \n5\n"),
         Arguments.of(5, "3.0\n3f\n5\n"),
         Arguments.of(5, "0x03\n5\n"),
         Arguments.of(-5, "-5\n"),
         Arguments.of(55, "55\n"),
         Arguments.of(55, "\n55\n")
      );
   }
   @ParameterizedTest
   @MethodSource("params_demandInt")
   void test_demandInt(int expect, String input) {
      ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
      System.setIn(in);
   
      try (InputOutput io = new InputOutput()) {
         Assertions.assertEquals(expect, io.demandInt(""));
      }
      catch (IOException e) {
         assert false;
      }
   }
   
   private static Stream<Arguments> params_demandFloat() {
      return Stream.of(
         Arguments.of(5, "5\n"),
         Arguments.of(5, "5.0\n"),
         Arguments.of(5, "5f\n"),
         Arguments.of(5, "0x03\n5\n"),
         Arguments.of(-5, "-5\n"),
         Arguments.of(55, "55\n"),
         Arguments.of(55, "\n55\n")
      );
   }
   @ParameterizedTest
   @MethodSource("params_demandFloat")
   void test_demandFloat(float expect, String input) {
      ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
      System.setIn(in);
      
      try (InputOutput io = new InputOutput()) {
         Assertions.assertEquals(expect, io.demandFloat(""));
      }
      catch (IOException e) {
         assert false;
      }
   }
   
   private static Stream<Arguments> params_promptIntDefaulting() {
      return Stream.of(
         Arguments.of(5,   0, "5\n"),
         Arguments.of(0,   0, "5.0\n5\n"),
         Arguments.of(0,   0, "5f\n5\n"),
         Arguments.of(0,   0, "0x05\n5\n"),
         Arguments.of(-5,  0, "-5\n"),
         Arguments.of(55,  0, "55\n")
      );
   }
   @ParameterizedTest
   @MethodSource("params_promptIntDefaulting")
   void test_promptIntDefaulting(int expect, int _default, String input) {
      ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
      System.setIn(in);
      
      try (InputOutput io = new InputOutput()) {
         Assertions.assertEquals(expect, io.promptIntDefaulting("", _default));
      }
      catch (IOException e) {
         assert false;
      }
   }
   
   private static Stream<Arguments> params_promptFloatDefaulting() {
      return Stream.of(
         Arguments.of(5,   0, "5\n"),
         Arguments.of(5,   0, "5.0\n"),
         Arguments.of(5,   0, "5f\n"),
         Arguments.of(0,   0, "0x05\n5\n"),
         Arguments.of(-5,  0, "-5\n"),
         Arguments.of(55,  0, "55\n")
      );
   }
   @ParameterizedTest
   @MethodSource("params_promptFloatDefaulting")
   void test_promptFloatDefaulting(float expect, float _default, String input) {
      ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
      System.setIn(in);
      
      try (InputOutput io = new InputOutput()) {
         Assertions.assertEquals(expect, io.promptFloatDefaulting("", _default));
      }
      catch (IOException e) {
         assert false;
      }
   }
}