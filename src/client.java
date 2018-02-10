import java.io.*; 
import java.net.*; 
class client { 

    public static void main(String args[]) throws Exception 
    { 
    	
    	InetAddress ipAddress = InetAddress.getByName("localhost");
        Socket clientSocket = new Socket(ipAddress, 6789); 

        DataOutputStream outToServer = 
          new DataOutputStream(clientSocket.getOutputStream()); 

        DataInputStream inFromServer = 
                new DataInputStream(clientSocket.getInputStream()); 


            	while(true)
    	{
        String sentence; 

        BufferedReader inFromUser = 
          new BufferedReader(new InputStreamReader(System.in));
              System.out.println(inFromServer.readUTF());
              sentence = inFromUser.readLine();  

              outToServer.writeUTF(sentence); 


              if(sentence.equalsIgnoreCase("BYE"))
              {
            	 
              clientSocket.close(); 
              inFromServer.close();
              outToServer.close();
              break;
              }
    	}
    	
          } 
      } 