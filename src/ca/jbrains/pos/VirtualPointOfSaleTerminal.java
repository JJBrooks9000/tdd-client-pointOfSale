package ca.jbrains.pos;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

import ca.jbrains.pos.ui.EnglishLanguageConsoleDisplay;
import ca.jbrains.pos.ui.TextProcessorAndCommandInterpreter;

public class VirtualPointOfSaleTerminal
{
   public static void main (String[] args)
      throws IOException
   {
      SaleController saleController = new SaleController(new EnglishLanguageConsoleDisplay(), new InMemoryCatalog(new HashMap<String, Price>()
      {
         {
            put("12345", Price.cents(795));
            put("23456", Price.cents(1250));
            put("036000216", Price.cents(379));
         }
      }));

      new TextProcessorAndCommandInterpreter(saleController).process(new StringReader("\n12345\n\n23456\n\t\n99999\n\n"));

      saleController.onBarcode("12345");
      saleController.onBarcode("23456");
      saleController.onBarcode("99999");
      saleController.onBarcode("");
   }
}
