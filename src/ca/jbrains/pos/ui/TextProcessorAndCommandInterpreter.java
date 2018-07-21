package ca.jbrains.pos.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.stream.Stream;

public class TextProcessorAndCommandInterpreter
{

   public TextProcessorAndCommandInterpreter (BarcodeScannedListener barcodeScannedListener)
   {
      this.barcodeScannedListener = barcodeScannedListener;
   }

   private BarcodeScannedListener barcodeScannedListener;

   public void process (Reader reader)
      throws IOException
   {
      interpretCommandsFromTextInput(reader);

   }

   private void interpretCommandsFromTextInput (Reader reader)
      throws IOException
   {
      try (BufferedReader commandReader = new BufferedReader(reader)) {
         sanitizeThenInterpretLines(commandReader.lines());
      }
   }

   private void sanitizeThenInterpretLines (Stream<String> lines)
   {
      interpretLines(sanitizeLines(lines));
   }

   private void interpretLines (Stream<String> lines)
   {
      sanitizeLines(lines).filter( (line) -> !line.isEmpty()).forEach( (line) -> interpretLine(line));
   }

   private Stream<String> sanitizeLines (Stream<String> lines)
   {
      return lines.map( (line) -> line.trim());
   }

   private void interpretLine (String line)
   {
      barcodeScannedListener.onBarcode(line);
   }
}
