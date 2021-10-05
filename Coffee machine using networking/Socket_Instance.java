package Htcpcp;

import java.io.*;
import java.net.Socket;

// this method is used in order to hold the socket instace valu
public class Socket_Instance {
    public Socket soc = null;
    public DataInputStream SOCin = null;
    public DataOutputStream SOCout = null;
    
    // constructor, inititates the values of the object
    public Socket_Instance(Socket socket) throws IOException {
            soc = socket;
            SOCin = new DataInputStream(soc.getInputStream());
            SOCout = new DataOutputStream(soc.getOutputStream());
    }
    // derstructor = constructor of destruction of object's values
    public Socket_Instance(Socket_Instance s) throws IOException{
        try{
        s.SOCin.close();
        s.SOCout.close();
        s.soc.close();
        } catch(Exception e){
            System.out.println("Conection completed");
        }
    }
}    
