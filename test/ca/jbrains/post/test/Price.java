package ca.jbrains.post.test;

public class Price
{
   public static Price cents (int centsValue)
   {
      return new Price();

   }

   @Override
   public String toString ()
   {
      return "a Price";
   }
}
