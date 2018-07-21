package ca.jbrains.pos.test;

import java.util.Arrays;
import java.util.List;

public class TextUtilities
{
   public static List<String> lines (String string)
   {
      String[] lines = string.split("\\n");
      for (int i = 0; i < lines.length; i++) {
         lines[i] = lines[i].trim();
      }
      return Arrays.asList(lines);
   }
}
