package Htcpcp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


// Scope of this class is to implement the logic of the service, namely to serve the clients
// In our case to read input and reply back to client

public class Socket_Handler implements Runnable {
    public String sendMESSAGE="";
    public static String MESSAGE="";
    Socket_Instance sINST = null;
    ArrayList<Socket_Instance> cINlist = null;
    
    // constructor of the class, inititates the local variables
    public Socket_Handler (ArrayList<Socket_Instance> inArrayListVar, Socket_Instance inSocVar){
        sINST = inSocVar;
        cINlist = inArrayListVar;
        // server in init state
        // after connection establishment, server pass to HELO state        
    }
    public void run(){
        try{
            
            //CONNECTION ESTABLISHMENT  S: 220         
            System.out.println("1.0. SOCKET HANDLER: SERVER STATE: INIT" );
            sendMESSAGE = "220" + " " + "Coffee Service Ready"; 
            
            //F: 421  - if the server must go down and stop the service
            sINST.SOCout.writeUTF(sendMESSAGE);
         
            //flush any characters to output stream.
            sINST.SOCout.flush();


                String clientMSG = sINST.SOCin.readUTF();
                System.out.println("(*) SOCKET HANDLER: New Client at Port " + sINST.soc.getPort()); //read client socket
                //decryption
                Decryption_CaesarCipher dec = new Decryption_CaesarCipher(clientMSG, 13);
                String decryptedMSG = dec.get_decrypted(); //Decryption is URI
                CoffeeServer ss = new CoffeeServer(decryptedMSG);  //(clientMSG);
            
            // as long as the socket isntance is not closed
            while (!sINST.soc.isClosed()) {
                sendMESSAGE = ss.getMessage();
                System.out.println("1.2. SOCKET HANDLER: message " + sendMESSAGE); 

                Thread.sleep(3000); //waits a little bit between stages
                
                //encryption
                Encryption_CaesarCipher enc = new Encryption_CaesarCipher(sendMESSAGE, 13);
                String encryptedMSG = enc.get_encrypted();                
                
                sINST.SOCout.writeUTF(encryptedMSG);
                sINST.SOCout.flush();

                if(sendMESSAGE.contains("221")){
                    sINST.SOCin.close();
                    sINST.SOCout.close();
                    sINST.soc.close();
                    cINlist.remove(sINST);                    
                } else {
                    clientMSG = sINST.SOCin.readUTF();

                    //decryption
                    dec = new Decryption_CaesarCipher(clientMSG, 13);
                    decryptedMSG = dec.get_decrypted();                
                    
                    new CoffeeServer(decryptedMSG); //(clientMSG);
                }
                    
            }   //while socket NOT CLOSED
        }
        catch (IOException except){
            //Exception thrown (except) when something went wrong, pushing clientMSG to the console
            System.out.println("Error in Server Connection Handler --> " + except.getMessage());
        } catch (InterruptedException ex) {
            Logger.getLogger(Socket_Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void getMESSAGE(){
        //
    }
}

    