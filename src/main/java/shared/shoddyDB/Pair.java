package shared.shoddyDB;

import lombok.Getter;

/*
 *  UCF COP3330 Summer 2021 Assignment 2 Solution
 *  Copyright 2021 Stephan Hartig
 */
public class Pair<L, R> {
   @Getter private L left;
   @Getter private R right;
   public Pair(L left, R right) {
      this.left  = left;
      this.right = right;
   }
}
