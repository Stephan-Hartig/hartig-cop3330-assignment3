package ex43;

/*
 *  UCF COP3330 Summer 2021 Assignment 3 Solution
 *  Copyright 2021 Stephan Hartig
 */

import lombok.Getter;
import lombok.val;
import shared.io.FileIO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import static shared.io.FileIO.touch;

public class WebsiteGenerator {
   
   private static String DEFAULT_FILES = "./resources/web-default";
   
   @Getter private String name;
   @Getter private String author;
   private String rootPath;
   @Getter private List<String> creationLog;
   
   WebsiteGenerator(String name, String author) throws IOException {
      this.init("./", name, author);
   }
   
   WebsiteGenerator(String rootPath, String name, String author) throws IOException {
      this.init(rootPath, name, author);
   }
   
   private void init(String rootPath, String name, String author) throws IOException {
      this.rootPath = rootPath;
      this.name = name;
      this.author = author;
      this.creationLog = new LinkedList<>();
      
      this.createDir(name);
      this.rootPath = Paths.get(this.rootPath, name).toString();
      
      this.createFile("index.html");
   }
   
   private void appendCreationLog(String fullPath) {
      this.creationLog.add(fullPath);
   }
   
   private String getFullPath(String dir) {
      return Paths.get(this.rootPath, dir).toString();
   }
   
   private String getDefaultFile(String filename) {
      return Paths.get(DEFAULT_FILES, filename).toString();
   }
   
   public void createDir(String dir) throws IOException {
      val fullPath = this.getFullPath(dir);
      if (! new File(fullPath).mkdir())
         throw new IOException(String.format("Directory %s could not be created.", dir));
      this.appendCreationLog(fullPath);
   }
   
   public void createFile(String filename) throws IOException {
      /* Copy and paste the template, replacing any $VARIABLES. */
      val fullPath = this.getFullPath(filename);
      FileIO.spit(fullPath,
         FileIO
            .slurp(this.getDefaultFile(filename))
            .replaceAll("\\$AUTHOR", this.author)
            .replaceAll("\\$TITLE", this.name)
      );
      this.appendCreationLog(fullPath);
   }
}
