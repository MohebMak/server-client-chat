

import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;



public class clientHandler extends Thread implements Runnable
{
    
     DataInputStream inFromClient;
     DataOutputStream outToClient;
     Socket connectionSocket;
     Socket otherSocket;
     DataInputStream inFromClient1;
     DataOutputStream outToClient1;
     
 
    // Constructor
    public clientHandler(Socket connectionSocket, DataInputStream inFromClient, DataOutputStream outToClient , Socket otherSocket  ,   DataInputStream inFromClient1, DataOutputStream outToClient1) 
    {
        this.connectionSocket = connectionSocket;
        this.inFromClient = inFromClient;
        this.outToClient = outToClient;
        this.otherSocket = otherSocket;
        this.inFromClient1=inFromClient1;
        this.outToClient1 = outToClient1;

    }
 
    @Override
    public void run() 
    {
    	
        while (true) 
        {
            try {
 
    if(otherSocket!=null)
    {
	   String ll = inFromClient1.readUTF();
	   outToClient.writeUTF(ll);
	   String lll = inFromClient.readUTF();
	   outToClient1.writeUTF(lll);
    }
       if(inFromClient.readUTF().equals("bye")&&inFromClient1.readUTF().equals("bye"))
       { 
           System.out.println("Client " + this.connectionSocket + " sends exit...");
           System.out.println("Closing this connection.");
           this.connectionSocket.close();
           this.inFromClient.close();
           this.outToClient.close();
           this.inFromClient1.close();
           this.outToClient1.close();
           System.out.println("Connection closed");
       }
}
                 
                
             catch (IOException e) {
                e.printStackTrace();
            }
        }
         
        
       
    }
}