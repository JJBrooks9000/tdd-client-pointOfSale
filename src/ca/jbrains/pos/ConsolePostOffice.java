package ca.jbrains.pos;

public class ConsolePostOffice implements PostOffice
{
   @Override
   public void sendMessage (String text)
   {
      System.out.println(text);
   }
}
