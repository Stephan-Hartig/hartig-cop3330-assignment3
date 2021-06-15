package shared.io;

/*
 *  UCF COP3330 Summer 2021 Assignment 3 Solution
 *  Copyright 2021 Stephan Hartig
 */

import lombok.val;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileIO {
   
   public static String slurp(String path, Charset encoding) throws IOException {
      byte[] encoded = Files.readAllBytes(Paths.get(path));
      return new String(encoded, encoding);
   }
   
   public static String slurp(String path) throws IOException {
      byte[] encoded = Files.readAllBytes(Paths.get(path));
      return new String(encoded, StandardCharsets.UTF_8);
   }
   
   public static List<String> slurpLines(String path) throws IOException {
      return Files.readAllLines(Paths.get(path));
   }
   
   public static void spit(String path, String contents) throws IOException {
      BufferedWriter out = new BufferedWriter(new FileWriter(path));
      out.write(contents);
      out.close();
   }
   
   public static void spit(String path, List<String> contents) throws IOException {
      BufferedWriter out = new BufferedWriter(new FileWriter(path));
      for (val line : contents) {
         out.write(line);
         out.newLine();
      }
      out.close();
   }
   
}
