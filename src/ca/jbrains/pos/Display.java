package ca.jbrains.pos;

public interface Display
{
   void displayPrice (Price price);

   void displayEmptyBarcodeMessage ();

   void displayProductNotFoundMessage (String barcode);
}
