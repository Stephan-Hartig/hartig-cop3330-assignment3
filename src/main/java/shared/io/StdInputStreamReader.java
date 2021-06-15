package shared.io;

import java.io.InputStreamReader;

/*
 *  UCF COP3330 Summer 2021 Assignment 2 Solution
 *  Copyright 2021 Stephan Hartig
 */
public class StdInputStreamReader extends InputStreamReader {
   public StdInputStreamReader() {
      super(System.in);
   }
   
   @Override
   public void close() { /* Do nothing. */ }
}
