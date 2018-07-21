package ca.jbrains.pos.ui.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LearningCueCatTest
{
   /**
    * This test shows us that CuCat behaves just like a keyboard device.
    * Run this program, then enter barcodes into stdin or type on the keyboard, then press ENTER.  
    * This program will echo each line to stdout
    * @param args
    * @throws IOException
    */
   public static void main (String[] args)
      throws IOException
   {
      try (BufferedReader stdinLineReader = new BufferedReader(new InputStreamReader(System.in))) {
         stdinLineReader.lines().forEach( (line) -> System.out.println(line));
      }
   }
}
