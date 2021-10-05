
package Htcpcp;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    
    public static void main(String[] args) {
        int portNumber = 5000;              //listening port
        
        try {
            ServerSocket sSOC = new ServerSocket(portNumber);   // create a server socket instance
            
            // + add an array list that hold the incoming requrests so as to keep track on them
            ArrayList<Socket_Instance> active_clients = new ArrayList<Socket_Instance>();

            KEY_distributor kdstr = new KEY_distributor();
            Thread kdstrThread = new Thread(kdstr);                 // create a thread type instance
            kdstrThread.start();             
            
            // infinetelly loop over
            while (true)
            {
                System.out.println("0.0. SERVER IN LISTEN STATE ...");
                Socket cSOC = sSOC.accept();                        // accept incoming requests
                Socket_Instance inSOC = new Socket_Instance(cSOC);  // store socket instance values 
                active_clients.add(inSOC);        
                System.out.println("0.1. Socket accepted at " + cSOC.getLocalPort());
                
                // create a server thread for handling each incoming client connection        
                // at first create a variable that shall keep the values of socket
                Socket_Handler sch = new Socket_Handler(active_clients, inSOC);
                Thread schThread = new Thread(sch);                 // create a thread type instance
                schThread.start();                                  // start the thread
                // the thread does the rest of the job
                System.out.println("0.2. Thread " + schThread.getId() + " started ");
            }   
        }   // this part of the code is rquired in order to handle error situations without forcing the program execution to exit
        catch(Exception e){
            //Exception thrown (except) when something went wrong, pushing message to the console
            System.out.println("99. Error Server --> " + e.getMessage());
        }
    }
}