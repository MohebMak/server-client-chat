import java.io.*; 
import java.net.*;
import java.util.HashMap;
import java.util.Set; 

class server { 

	public static void main(String[] args) throws IOException 
    { 
      String clientSentence; 

      ServerSocket welcomeSocket = new ServerSocket(6789); 
      HashMap<String, Socket> usersList = new HashMap<String, Socket>();
  
      while(true) { 
  
          Socket connectionSocket = null;
          		try {
           			connectionSocket = welcomeSocket.accept();
            			
           			 DataInputStream inFromClient = 
        		     new DataInputStream(connectionSocket.getInputStream()); 

           		           
                     DataOutputStream  outToClient = 
                     new DataOutputStream(connectionSocket.getOutputStream()); 

           			System.out.println("new client is connected   :" + connectionSocket);
            			
           		    outToClient.writeUTF("Please enter your name .");
            		    
           		    String clientName = inFromClient.readUTF();

          		    usersList.put(clientName, connectionSocket);
            	   
            		System.out.println(usersList.toString());
            		
            		if(usersList.size()<2)
            		{
            			outToClient.writeUTF("none is online now");
           			 Thread clienthandler = new clientHandler(connectionSocket , inFromClient , outToClient, null ,  null , null);

            		}
            		else
            		{
            			String k = "online users:"+"\n";
            		      HashMap<String, Socket> usersList1 = usersList;
            		      usersList1.remove(clientName);
              			Set<String>clients = usersList.keySet();

            			for(String s: clients)

        
            			{
     			k   =    k+"\n"+s;
            			}
            			outToClient.writeUTF(k + "\n" + "please enter the name you want to chat with");
            			
             			 String othername = inFromClient.readUTF();

             			 DataInputStream inFromClient1 = 
              		              new DataInputStream(usersList.get(othername).getInputStream()); 

              		           
              		           DataOutputStream  outToClient1 = 
              		                   new DataOutputStream(usersList.get(othername).getOutputStream()); 
            			 Thread clienthandler = new clientHandler(connectionSocket , inFromClient , outToClient, usersList.get(othername) ,  inFromClient1 , outToClient1);
   	                  clienthandler.start();

            		}
            		
            		
          
            		

	                  
        		   
            		
            		}
            catch (Exception e) {
				// TODO: handle exception
            	connectionSocket.close();
            	e.printStackTrace();
			}

          
              } 
          } 
      } 
