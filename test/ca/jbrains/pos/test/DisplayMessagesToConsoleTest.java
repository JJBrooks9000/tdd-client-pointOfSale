package ca.jbrains.pos.test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ca.jbrains.pos.ui.EnglishLanguageConsoleDisplay;

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

      new EnglishLanguageConsoleDisplay().displayProductNotFoundMessage("123456789");

      Assert.assertEquals(Arrays.asList("Product not found for 123456789"), TextUtilities.lines(canvas.toString("UTF-8")));
   }

   @Test
   public void emptyBarcodeMessage ()
      throws Exception
   {
      ByteArrayOutputStream canvas = new ByteArrayOutputStream();
      System.setOut(new PrintStream(canvas));

      new EnglishLanguageConsoleDisplay().displayEmptyBarcodeMessage();

      Assert.assertEquals(Arrays.asList("Scanning error: empty barcode"), TextUtilities.lines(canvas.toString("UTF-8")));
   }

   @Test
   public void multipleMEssages ()
      throws Exception
   {

      ByteArrayOutputStream canvas = new ByteArrayOutputStream();
      System.setOut(new PrintStream(canvas));

      EnglishLanguageConsoleDisplay consoleDisplay = new EnglishLanguageConsoleDisplay();
      consoleDisplay.displayProductNotFoundMessage("4545455454");
      consoleDisplay.displayEmptyBarcodeMessage();
      consoleDisplay.displayProductNotFoundMessage("123456789");
      consoleDisplay.displayEmptyBarcodeMessage();

      Assert.assertEquals(Arrays.asList("Product not found for 4545455454", "Scanning error: empty barcode", "Product not found for 123456789", "Scanning error: empty barcode"), TextUtilities.lines(canvas.toString("UTF-8")));
   }

}
