package ca.jbrains.post.test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DisplayMessagesToConsoleTest
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
   public void productNotFoundMessage ()
      throws Exception
   {
      ByteArrayOutputStream canvas = new ByteArrayOutputStream();
      System.setOut(new PrintStream(canvas));

      new ConsoleDisplay().productNotFoundMessage("123456789");

      Assert.assertEquals(Arrays.asList("Product not found for 123456789"), TextUtilities.lines(canvas.toString("UTF-8")));
   }

   @Test
   public void emptyBarcodeMessage ()
      throws Exception
   {
      ByteArrayOutputStream canvas = new ByteArrayOutputStream();
      System.setOut(new PrintStream(canvas));

      new ConsoleDisplay().displayEmptyBarcodeMessage();

      Assert.assertEquals(Arrays.asList("Scanning error: empty barcode"), TextUtilities.lines(canvas.toString("UTF-8")));
   }

   @Test
   public void multipleMEssages ()
      throws Exception
   {

      ByteArrayOutputStream canvas = new ByteArrayOutputStream();
      System.setOut(new PrintStream(canvas));

      ConsoleDisplay consoleDisplay = new ConsoleDisplay();
      consoleDisplay.productNotFoundMessage("4545455454");
      consoleDisplay.displayEmptyBarcodeMessage();
      consoleDisplay.productNotFoundMessage("123456789");
      consoleDisplay.displayEmptyBarcodeMessage();

      Assert.assertEquals(Arrays.asList("Product not found for 4545455454", "Scanning error: empty barcode", "Product not found for 123456789", "Scanning error: empty barcode"), TextUtilities.lines(canvas.toString("UTF-8")));
   }

   public static class ConsoleDisplay
   {

      public void productNotFoundMessage (String barcodeNotFound)
      {
         System.out.println(String.format("Product not found for %s", barcodeNotFound));
      }

      public void displayEmptyBarcodeMessage ()
      {
         System.out.println("Scanning error: empty barcode");
      }

   }
}
