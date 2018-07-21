package ca.jbrains.pos.ui.lcd.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class LearnUdpClientTest
{
   public static void main (String[] args)
      throws InterruptedException
   {
      String textToDisplay = "Hello, world. " + new Date();
      int port = 5358;
      String hostName = "localhost";
      DatagramSocket clientSocket = null;
      while (true) {
         Thread.sleep(500);
         Date now = new Date();
         System.out.println("Sending message at : " + now.toString());
         sendMessage(textToDisplay, port, hostName, clientSocket);
      }
   }

   public static class UdpPostOffice
   {
      public UdpPostOffice (int port, String hostName, String textEncoding)
      {
         this.port = port;
         this.hostName = hostName;
         this.textEncoding = textEncoding;
      }

      private int port;
      private String hostName;
      private String textEncoding;

      public void sendMessage (String textToDisplay,

                               DatagramSocket clientSocket)
      {
         try {

            clientSocket = new DatagramSocket()
            {
               /*@Override
               public void send (DatagramPacket p)
                  throws IOException
               {
                  throw new PortUnreachableException();
               }*/
            };

            InetAddress serverInetAddress = InetAddress.getByName(hostName);
            byte[] payload = textToDisplay.getBytes(textEncoding);
            DatagramPacket message = new DatagramPacket(payload, payload.length, serverInetAddress, 5358);
            clientSocket.send(message);

         } catch (IOException e) {
            throw new RuntimeException(String.format("Unable to send message '%s' by UDP to %s:%d", textToDisplay, hostName, port), e);
         } finally {
            if (clientSocket != null) {
               clientSocket.close();
            }
         }
      }
   }

   private static void sendMessage (String textToDisplay,
                                    int port,
                                    String hostName,
                                    DatagramSocket clientSocket)
   {
      new UdpPostOffice(port, hostName, "UTF-8").sendMessage(textToDisplay, clientSocket);
   }
}
