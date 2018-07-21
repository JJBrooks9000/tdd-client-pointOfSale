package ca.jbrains.pos.test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LearnHoToHijackSystemOutTest
{
   private PrintStream productionSystemOut;

   @Before
   public void rememberSystemOut ()
   {
      productionSystemOut = System.out;
   }

   @After
   public void restoreSystemOut ()
   {
      System.setOut(productionSystemOut);
   }

   @Test
   public void singleLineOfText ()
      throws Exception
   {
      System.out.println("one line of text.");
      ByteArrayOutputStream canvas = new ByteArrayOutputStream();
      System.setOut(new PrintStream(canvas));
      System.out.println("Hello, world.");
      Assert.assertEquals(Collections.singletonList("Hello, world."), TextUtilities.lines(canvas.toString("UTF-8")));
   }

   @Test
   public void severalLinesOfText ()
      throws Exception
   {
      System.out.println("several lines of text.");
      ByteArrayOutputStream canvas = new ByteArrayOutputStream();
      System.setOut(new PrintStream(canvas));
      for (int i = 1; i <= 3; i++) {
         System.out.println("Line" + i);
      }

      Assert.assertEquals(Arrays.asList("Line1", "Line2", "Line3"), TextUtilities.lines(canvas.toString("UTF-8")));
   }

}
