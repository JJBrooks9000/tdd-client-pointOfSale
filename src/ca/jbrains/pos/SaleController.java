package ca.jbrains.pos;

import ca.jbrains.pos.ui.BarcodeScannedListener;

public class SaleController implements BarcodeScannedListener
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
