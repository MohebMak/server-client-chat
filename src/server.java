import java.io.*; 
import java.net.*; 

class server { 

	public static void main(String[] args) throws IOException 
    { 
      String clientSentence; 

      ServerSocket welcomeSocket = new ServerSocket(6789); 
  
      while(true) { 
  
            Socket connectionSocket = null;
            		try {
            			connectionSocket = welcomeSocket.accept();
            			System.out.println("new client is connected   :" +connectionSocket);
            		
            		
            			 DataInputStream inFromClient = 
            		              new DataInputStream(connectionSocket.getInputStream()); 

            		           
            		           DataOutputStream  outToClient = 
            		                   new DataOutputStream(connectionSocket.getOutputStream()); 



            		                  Thread clienthandler = new clientHandler(connectionSocket , inFromClient , outToClient);
            		                  clienthandler.start();
            		
            		}
            catch (Exception e) {
				// TODO: handle exception
            	connectionSocket.close();
            	e.printStackTrace();
			}

          
              } 
          } 
      } 
