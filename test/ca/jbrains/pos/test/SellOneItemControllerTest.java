package ca.jbrains.pos.test;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.Display;
import ca.jbrains.pos.Price;
import ca.jbrains.pos.SaleController;

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

}
