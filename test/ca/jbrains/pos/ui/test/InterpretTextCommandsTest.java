package ca.jbrains.pos.ui.test;

import java.io.StringReader;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import ca.jbrains.pos.ui.BarcodeScannedListener;
import ca.jbrains.pos.ui.TextProcessorAndCommandInterpreter;

public class InterpretTextCommandsTest
{
   @Rule
   public final JUnitRuleMockery context = new JUnitRuleMockery();

   @Test
   public void zero ()
      throws Exception
   {
      BarcodeScannedListener barcodeScannedListener = context.mock(BarcodeScannedListener.class);
      context.checking(new Expectations()
      {
         {
            never(barcodeScannedListener);
         }
      });

      new TextProcessorAndCommandInterpreter(barcodeScannedListener).process(new StringReader(""));
   }

   @Test
   public void oneBarcode ()
      throws Exception
   {
      BarcodeScannedListener barcodeScannedListener = context.mock(BarcodeScannedListener.class);
      context.checking(new Expectations()
      {
         {
            oneOf(barcodeScannedListener).onBarcode("::barcode::");
         }
      });

      new TextProcessorAndCommandInterpreter(barcodeScannedListener).process(new StringReader("::barcode::"));
   }

   @Test
   public void severalBarcode ()
      throws Exception
   {
      BarcodeScannedListener barcodeScannedListener = context.mock(BarcodeScannedListener.class);
      context.checking(new Expectations()
      {
         {
            oneOf(barcodeScannedListener).onBarcode("::barcode 1::");
            oneOf(barcodeScannedListener).onBarcode("::barcode 2::");
            oneOf(barcodeScannedListener).onBarcode("::barcode 3::");
         }
      });

      new TextProcessorAndCommandInterpreter(barcodeScannedListener).process(new StringReader("::barcode 1::\n::barcode 2::\n::barcode 3::\n"));
   }

   @Test
   public void severalBarcodesInterspersedWithEmptyLines ()
      throws Exception
   {

      BarcodeScannedListener barcodeScannedListener = context.mock(BarcodeScannedListener.class);
      context.checking(new Expectations()
      {
         {
            oneOf(barcodeScannedListener).onBarcode("::barcode 1::");
            oneOf(barcodeScannedListener).onBarcode("::barcode 2::");
            oneOf(barcodeScannedListener).onBarcode("::barcode 3::");
         }
      });

      new TextProcessorAndCommandInterpreter(barcodeScannedListener).process(new StringReader("\n::barcode 1::\n\n::barcode 2::\n\t\n::barcode 3::\n\n"));

   }

   @Test
   public void trimsBarcodes ()
      throws Exception
   {
      BarcodeScannedListener barcodeScannedListener = context.mock(BarcodeScannedListener.class);
      context.checking(new Expectations()
      {
         {
            oneOf(barcodeScannedListener).onBarcode("::barcode::");
         }
      });

      new TextProcessorAndCommandInterpreter(barcodeScannedListener).process(new StringReader("\t\t    \t::barcode::   \t   \t\n"));
   }

}
