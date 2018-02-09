

import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;



public class clientHandler extends Thread 
{
    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
    final DataInputStream inFromClient;
    final DataOutputStream outToClient;
    final Socket connectionSocket;
     
 
    // Constructor
    public clientHandler(Socket connectionSocket, DataInputStream inFromClient, DataOutputStream outToClient) 
    {
        this.connectionSocket = connectionSocket;
        this.inFromClient = inFromClient;
        this.outToClient = outToClient;
    }
 
    @Override
    public void run() 
    {
        String received;
        String toreturn;
        while (true) 
        {
            try {
 
                // Ask user what he wants
                outToClient.writeUTF("What do you want?[Date | Time]..\n"+
                            "Type Exit to terminate connection.");
                 
                // receive the answer from client
                received = inFromClient.readUTF();
                 
                if(received.equals("Exit"))
                { 
                    System.out.println("Client " + this.connectionSocket + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.connectionSocket.close();
                    System.out.println("Connection closed");
                    break;
                }
                 
                // creating Date object
                Date date = new Date();
                 
                // write on output stream based on the
                // answer from the client
                switch (received) {
                 	
                    case "Date" :
                        toreturn = fordate.format(date);
                        outToClient.writeUTF(toreturn);
                        break;
                         
                    case "Time" :
                        toreturn = fortime.format(date);
                        outToClient.writeUTF(toreturn);
                        break;
                         
                    default:
                        outToClient.writeUTF("Invalid input");
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
         
        try
        {
            // closing resources
            this.inFromClient.close();
            this.outToClient.close();
             
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}