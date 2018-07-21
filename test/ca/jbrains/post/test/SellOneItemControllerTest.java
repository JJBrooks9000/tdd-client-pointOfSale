package ca.jbrains.post.test;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class SellOneItemControllerTest
{
   @Rule
   public final JUnitRuleMockery context = new JUnitRuleMockery();

   @Test
   public void productFound ()
      throws Exception
   {
      final Catalog catalog = context.mock(Catalog.class);
      Display display = context.mock(Display.class);
      final Price irrelevantPrice = Price.cents(795);
      context.checking(new Expectations()
      {
         {
            allowing(catalog).findPrice(with("::product found::"));

            will(returnValue(irrelevantPrice));
            oneOf(display).displayPrice(with(irrelevantPrice));
         }
      });

      SaleController saleController = new SaleController(display, catalog);
      saleController.onBarcode("::product found::");

      //Rule verifies internal rules
   }

   @Test
   public void productNotFound ()
      throws Exception
   {
      final Catalog catalog = context.mock(Catalog.class);
      Display display = context.mock(Display.class);
      context.checking(new Expectations()
      {
         {
            allowing(catalog).findPrice(with("::product not found::"));
            will(returnValue(null));

            oneOf(display).displayProductNotFoundMessage(with("::product not found::"));
         }
      });

      SaleController saleController = new SaleController(display, catalog);
      saleController.onBarcode("::product not found::");
   }

   @Test
   public void emptyBarcode ()
      throws Exception
   {
      Display display = context.mock(Display.class);
      context.checking(new Expectations()
      {
         {
            oneOf(display).displayEmptyBarcodeMessage();
         }
      });

      SaleController saleController = new SaleController(display, null);
      saleController.onBarcode("");
   }

   public interface Display
   {
      void displayPrice (Price price);

      void displayEmptyBarcodeMessage ();

      void displayProductNotFoundMessage (String barcode);
   }
   public static class SaleController

   {
      public SaleController (Display display, Catalog catalog)
      {
         this.display = display;
         this.catalog = catalog;
      }

      private Display display;
      private Catalog catalog;

      public void onBarcode (String barcode)
      {
         if ("".equals(barcode)) {
            display.displayEmptyBarcodeMessage();
            return;
         }
         Price price = catalog.findPrice(barcode);
         if (price == null) {
            display.displayProductNotFoundMessage(barcode);
         } else {
            display.displayPrice(catalog.findPrice(barcode));
         }
      }
   }

}
