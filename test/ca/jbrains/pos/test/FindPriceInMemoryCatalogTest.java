package ca.jbrains.pos.test;

import java.util.Collections;
import java.util.HashMap;

import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.InMemoryCatalog;
import ca.jbrains.pos.Price;

public class FindPriceInMemoryCatalogTest extends FindPriceInCatalogContract
{

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
