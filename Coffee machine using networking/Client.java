package Htcpcp;

import java.io.*;
import java.net.*;

// this is a plain implementantion of client

public class Client {
    static int portNumber = 5000;
    static int SECportNumber = 6000;
    static String serverIP = "localhost";    
    static DataInputStream SOCin = null;
    static DataOutputStream SOCout = null;
    static String KEY = "";
    
    public static void main(String[] args) {  
        String BYTESin = "";
        String msgToServer = "";
        try{      
            // security oriented KEY distribution
            // scope of this part is to obtain security key from the server
            System.out.println("0.0. Client KEY exchange start");
            Socket  secSOC = new Socket("localhost", SECportNumber);
            SOCout = new DataOutputStream(secSOC.getOutputStream());
            SOCout.writeUTF("KEY request");
            SOCout.flush();
            
            SOCin = new DataInputStream(secSOC.getInputStream());
            KEY = SOCin.readUTF();
            System.out.println("0.0. Client KEY exchange end " + Integer.parseInt(KEY));
            // close sockets
            SOCout.close();
            SOCin.close();
            secSOC.close();

            
            // SMTP oriented operation     
            Socket s = new Socket(serverIP, portNumber);
            Socket_Instance socINST = new Socket_Instance(s);
            System.out.println("1.1. Client started");
            ClientMachine csm = new ClientMachine (socINST.SOCin.readUTF());

            while(!s.isClosed()){
                msgToServer = csm.getRequest();     

                //encryption
                Encryption_CaesarCipher enc = new Encryption_CaesarCipher(msgToServer, Integer.parseInt(KEY));
                String encryptedMSG = enc.get_encrypted();
                
                socINST.SOCout.writeUTF(encryptedMSG);  //(msgToServer);
                socINST.SOCout.flush();               
                //System.out.println("1.4. message SENT -" +  msgToServer+ "-" + encryptedMSG);
                BYTESin = socINST.SOCin.readUTF();
                //System.out.println("1.5 message RECV -" +  BYTESin + "-");

                //decryption
                Decryption_CaesarCipher dec = new Decryption_CaesarCipher(BYTESin, 13);
                String decryptedMSG = dec.get_decrypted();                
                    
                csm = new ClientMachine(decryptedMSG); //(BYTESin);  //FSM
            }             
        }
        catch(Exception e){System.out.println("Connection Aborted");}  
    }  

}
