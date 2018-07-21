package ca.jbrains.pos.ui;

import ca.jbrains.pos.ConsolePostOffice;
import ca.jbrains.pos.Display;
import ca.jbrains.pos.PostOffice;
import ca.jbrains.pos.Price;

public class EnglishLanguageConsoleDisplay implements Display

{
   private static final String DISPLAY_FORMAT = "$%,.2f";
   private static final String SCANNING_ERROR_MESSAGE_FORMAT = "Scanning error: empty barcode";
   private static final String PRODUCT_NOT_FOUND_MESSAGE_FORMAT = "Product not found for %s";

   private final PostOffice consolePostOffice;

   public EnglishLanguageConsoleDisplay ()
   {
      consolePostOffice = new ConsolePostOffice();
   }

   private void displayMessage (String messageTemplate,
                                Object... placeholders)
   {
      //consolePostOffice.sendMessage(messageTemplate);
      sendMessage(messageTemplate, placeholders);
   }

   private void sendMessage (String messageTemplate,
                             Object... placeholders)
   {
      System.out.println(messageFormat(messageTemplate, placeholders));
   }

   private String messageFormat (String messageTemplate,
                                 Object... placeholders)
   {
      return String.format(messageTemplate, placeholders);
   }

   @Override
   public void displayPrice (Price price)
   {
      displayMessage(DISPLAY_FORMAT, price.dollarValue());

   }

   @Override
   public void displayEmptyBarcodeMessage ()
   {
      displayMessage(SCANNING_ERROR_MESSAGE_FORMAT);
   }

   @Override
   public void displayProductNotFoundMessage (String barcode)
   {
      displayMessage(PRODUCT_NOT_FOUND_MESSAGE_FORMAT, barcode);
   }

}
