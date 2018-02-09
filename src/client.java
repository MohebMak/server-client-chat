import java.io.*; 
import java.net.*; 
class client { 

    public static void main(String argv[]) throws Exception 
    { 
    	
    	InetAddress ipAddress = InetAddress.getByName("localhost");
        Socket clientSocket = new Socket(ipAddress, 6789); 

        DataOutputStream outToServer = 
          new DataOutputStream(clientSocket.getOutputStream()); 

        DataInputStream inFromServer = 
                new DataInputStream(clientSocket.getInputStream()); 


        BufferedReader inFromUser = 
          new BufferedReader(new InputStreamReader(System.in)); 
    	while(true)
    	{
        String sentence; 

              System.out.println("From server : "+inFromServer.readUTF());
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