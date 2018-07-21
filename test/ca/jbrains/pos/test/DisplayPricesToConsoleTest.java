package ca.jbrains.pos.test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import ca.jbrains.pos.Price;
import ca.jbrains.pos.ui.EnglishLanguageConsoleDisplay;

@RunWith(Parameterized.class)
public class DisplayPricesToConsoleTest
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

   private final int priceInCents;
   private final String expectedFormattedPrice;

   public DisplayPricesToConsoleTest (int priceInCents, String expectedFormattedPrice)
   {
      this.priceInCents = priceInCents;
      this.expectedFormattedPrice = expectedFormattedPrice;
   }

   @Parameterized.Parameters(name = "Monetary amount {0} displays as {1}")
   public static Collection<Object[]> data ()
   {
      return Arrays.asList(new Object[][] {{789, "$7.89"}, {520, "$5.20"}, {400, "$4.00"}, {0, "$0.00"}, {2, "$0.02"}, {37, "$0.37"}, {418976, "$4,189.76"}, {210832281, "$2,108,322.81"}});
   }

   @Test
   public void test ()
      throws Exception
   {
      EnglishLanguageConsoleDisplay consoleDisplay = new EnglishLanguageConsoleDisplay();

      ByteArrayOutputStream canvas = new ByteArrayOutputStream();
      System.setOut(new PrintStream(canvas));

      consoleDisplay.displayPrice(Price.cents(priceInCents));

      Assert.assertEquals(Arrays.asList(expectedFormattedPrice), TextUtilities.lines(canvas.toString("UTF-8")));
   }
}
