package Htcpcp;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class CoffeeServer {
    private static String STATE = "COFFEE";
    private static String messageIN;
    private static String messageOUT;

    public static String coffeeAddons[] = {"cinnamon", "butter", "sugar", "ice-cream", "salt", "almonds", 
                    "milk", "cream", "egg", "vodka", "coconut-oil", "vanilla", "ginger", "bourbon"};
    public static String coffee;

    public static String MYdomain = "localhost";
    
    //URI: coffee://localhost/5000?cinnamon ;3#sugar;3#vanilla;3
    
    public CoffeeServer(String clientMSG) throws InterruptedException {
        messageIN = clientMSG;
        
        //switch between states inorder to send appropriate message codes to the client machine
        switch(STATE){
            // INIT state is accomplished with socket connection establishment
            case "COFFEE":
                System.out.println(">>>" + STATE + " " + messageIN);
                
                Thread.sleep(3000); //wait a bit between states
                
                // parsing packet
                if(messageIN.length()>512) {            // line too long
                     messageOUT = "500 command not recognized"; //500: server encoutered error
                }
                else if(messageIN.contains("coffee://")){
                    coffee = messageIN;
                    
                    messageOUT = "201, COFFEE"; //201: sucess
                    STATE = "BREW";
                    new CoffeeServer("");
                    }
                else if (messageIN.contains("propfind://")) {
                    STATE = "PROPFIND";
                    new CoffeeServer("");
                }
                break;
                
                case "PROPFIND":
                System.out.println(">>>}}" + STATE + " " + messageIN);
                
                Thread.sleep(3000);
                
                // parsing packet
                if (messageIN.length() > 512) {            // line too long
                    messageOUT = "500 command not recognized";
                } else if (messageIN.contains("propfind://")) {
                    
                    STATE = "COFFEE";
                    
                    new CoffeeServer("");
                }

                break;
                
            case "BREW":
                System.out.println(">>>" + STATE + " " + messageIN);
                
                Thread.sleep(3000);
                
                // parsing packet
                if(messageIN.length()>512) {            // line too long
                     messageOUT = "500 command not recognized";
                }  else if(messageIN.contains("brew://")) {
                    messageOUT = "202, BREW"; //202: accept status
                    STATE = "POST";
                    
                }
                break;
                
                case "POST":
                System.out.println(">>>" + STATE + " " + messageIN);
                Thread.sleep(3000);
                
                // parsing packet
                if (messageIN.length() > 512) {            // line too long
                    messageOUT = "500 command not recognized";
                } else if (messageIN.contains("post://")) {
                    messageOUT = "203, POST"; 

                    STATE = "FINAL";
                    
                }
                
                case "FINAL":
                // parsing packet
                if (messageIN.length() > 512) {            // line too long
                    messageOUT = "500 command not recognized";
                } else if (messageIN.equals("final")) { 
                    //extracts the coffee composition from the htcpcp coffee uri scheme
                     messageOUT = "204" + " here's your coffee: " + "COFFEE " 
                             + "\r\n" + coffee.substring(coffee.indexOf("?")+1)
                             .replaceAll(";", ":")
                             .replaceAll("#", "\r\n");
                     STATE = "QUIT"; 
                     ImageIcon image2 = new ImageIcon("C:\\Users\\hp\\Desktop\\coffeefrapp.jpg");
					JOptionPane.showMessageDialog(null,null, "Coffee Server", 
                   		    JOptionPane.PLAIN_MESSAGE, image2 );
                }
                break;

                case "QUIT":
                System.out.println("**Client is Exiting**");
                Thread.sleep(3000);
                
                if (messageIN.length() > 500) {            // line too long
                    messageOUT = "500 command not recognized";
                } else if (messageIN.contains("QUIT")) {
                    messageOUT = "221 quiting"; 
                }

                break;
                
            default:
                System.out.println("STUCK AT State " + STATE);
        }
    }
    
    //accesible get method for the server messages to be sent to the client machine
    public String getMessage(){
        return messageOUT;
    }
    
}
