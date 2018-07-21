package ca.jbrains.post.test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FindPriceInMemoryCatalogTest extends FindPriceInCatalogContract
{
   public static class InMemoryCatalog implements Catalog
   {
      private Map<String, Price> pricesByBarcode;

      public InMemoryCatalog (Map<String, Price> pricesByBarcode)
      {
         this.pricesByBarcode = pricesByBarcode;
      }

      public Price findPrice (String barcode)
      {
         return pricesByBarcode.get(barcode);
      }

   }

   @Override
   protected Catalog catalogWith (String barcode,
                                  Price price)
   {
      return new InMemoryCatalog(new HashMap<String, Price>()
      {
         {
            put("definately not " + barcode, Price.cents(0));
            put(barcode, price);
            put("once again, definately not " + barcode, Price.cents(1000000000));
         }
      });
   }

   @Override
   protected Catalog catalogWithout (String barcodeToAvoid)
   {
      return new InMemoryCatalog(Collections.singletonMap("anything but " + barcodeToAvoid, Price.cents(0)));
   }
}
