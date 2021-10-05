package Htcpcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

// this method is realised as a thread in order not to mix server sockets
// its purpose is to distribute encryption KEY on a given algorithm, in our case Ceasar Cipher

public class KEY_distributor implements Runnable {
    private ServerSocket secSOC;
    private int SECportNumber = 6000;
    private String KEY = "13";
    
    public KEY_distributor() throws IOException, Exception {
        secSOC = new ServerSocket(SECportNumber);   // create a server socket instance for KEY distribution

        // security key exchange
        System.out.println("KEY Exchange");
    }
    
    public void run(){
        try{

            while (true)
            {
                
                //client connected
                Socket cSOC = secSOC.accept();                        // accept incoming requests
                Socket_Instance inSOC = new Socket_Instance(cSOC);  // store socket instance values 
                System.out.println("KEY Socket accepted at " + cSOC.getLocalPort());
                System.out.println(KEY.toString());
                inSOC.SOCout.writeUTF(KEY);
                inSOC.SOCout.flush();
                
                inSOC.SOCin.close();
                inSOC.SOCout.close();
                inSOC.soc.close();
                System.out.println("KEY Socket closed");
            }
        }
        catch (IOException except){
            //Exception thrown (except) when something went wrong, pushing clientMSG to the console
            System.out.println("Error in Server Connection Handler --> " + except.getMessage());
        }
    }

}

    