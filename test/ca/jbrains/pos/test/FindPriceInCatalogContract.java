package ca.jbrains.pos.test;

import org.junit.Assert;
import org.junit.Test;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.Price;

public abstract class FindPriceInCatalogContract
{

   public FindPriceInCatalogContract ()
   {
      super();
   }

   @Test
   public void productFound ()
      throws Exception
   {
      Price foundPrice = Price.cents(1250);
      Catalog catalog = catalogWith("12345", foundPrice);
      Assert.assertEquals(foundPrice, catalog.findPrice("12345"));
   }

   protected abstract Catalog catalogWith (String barcode,
                                           Price foundPrice);

   @Test
   public void productNotFound ()
      throws Exception
   {
      Catalog catalog = catalogWithout("12345");
      Assert.assertEquals(null, catalog.findPrice("12345"));
   }

   protected abstract Catalog catalogWithout (String barcodeToAvoid);

}