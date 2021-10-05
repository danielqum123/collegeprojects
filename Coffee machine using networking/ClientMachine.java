package Htcpcp;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.InputMismatchException;

public class ClientMachine {

    private static String STATE = "INIT";
    private static String sendMSG;

    private static Socket_Instance sINST;

    public static String htcpcpRequest = "";
    public static String additionOne = "";
    public static String additionTwo = "";
    public static String additionThree = "";

    public static int addonOneVol;
    public static int addonTwoVol;
    public static int addonThreeVol;
    public static String proceed;
    public static String propProceed;

    ClientMachine(String inMSG) throws IOException {

        //switch between states based on server reply
        switch (STATE) {
            case "INIT":
                System.out.println("Welcome to HTCPCP 1.0");
                
                //220: server is ready for new client
                if (inMSG.contains("220")) {
                    System.out.println("Make a coffee request: Before making a cup of coffee, "
                            + "would you like to send a PROPFIND request to the server,"
                            + " to get a list of possible coffee additions? REPLY WITH YES or NO");
                    //keep prompting for correct input
                    while (true) {
                        Scanner propRequest = new Scanner(System.in);
                        String tofind = propRequest.nextLine();
                        if (tofind.equalsIgnoreCase("yes") || tofind.equalsIgnoreCase("no")) {
                            propProceed = tofind;
                            break;
                        } else {
                            System.out.println("PLEASE REPLY WITH YES or NO");
                        }
                    }

                    //conditionals for input given
                    if (propProceed.equalsIgnoreCase("no")) {

                        System.out.println("Make a coffee request: specify 3 coffee additions you would like to add");

                        Scanner request = new Scanner(System.in);
                        additionOne = request.nextLine().toLowerCase();
                        coffee.add(additionOne);

                        additionTwo = request.nextLine().toLowerCase();
                        coffee.add(additionTwo);

                        additionThree = request.nextLine().toLowerCase();
                        coffee.add(additionThree);

                        System.out.println("Specify the volume of the additions e.g 'Vanilla: 2'");

                        System.out.println("How much " + additionOne + " do you want? ");
                        System.out.println(additionOne + ": ");
                        while (true) {
                            try {
                                Scanner addonsRequest = new Scanner(System.in);
                                addonOneVol = addonsRequest.nextInt();
                                break;
                            } catch (InputMismatchException e) {
                                } System.out.println("Input only numbers");
                        }

                        System.out.println("How much " + additionTwo + " do you want? ");
                        System.out.println(additionTwo + ": ");
                        
                        while (true) {
                            try {
                                Scanner addonsRequest = new Scanner(System.in);
                                addonTwoVol = addonsRequest.nextInt();
                                break;
                            } catch (InputMismatchException e) {
                                    System.out.println("Input only numbers");
                                }
                            }
                     
                        System.out.println("How much " + additionThree + " do you want? ");
                        System.out.println(additionThree + ": ");
                        while (true) {
                            try {
                                Scanner addonsRequest = new Scanner(System.in);
                                addonThreeVol = addonsRequest.nextInt();
                                break;
                            } catch (InputMismatchException e) {
                                    System.out.println("Input only numbers");
                            }
                        }
                        //craft htcpcp request from provided inputs i.e 'the coffe additions'
                        htcpcpRequest = "coffee://localhost/"
                                + Client.portNumber + "?" + additionOne + ";" + addonOneVol + "#"
                                + additionTwo + ";" + addonTwoVol + "#"
                                + additionThree + ";" + addonThreeVol;

                        System.out.println(htcpcpRequest);
                        System.out.println("The above request would be sent to the htcpcp server");
                        System.out.println("would you like to change your coffee request? REPLY WITH YES or NO");

                        //keep prompting for correct input
                        while (true) {
                            Scanner proceedRequest = new Scanner(System.in);
                            String toProceed = proceedRequest.nextLine();
                            if (toProceed.equalsIgnoreCase("yes") || toProceed.equalsIgnoreCase("no")) {
                                proceed = toProceed;
                                break;
                            } else {
                                System.out.println("PLEASE REPLY WITH YES or NO");
                            }
                        }

                        //conditionals for the correct input given
                        if (proceed.equalsIgnoreCase("no")) {
                            if (!Arrays.toString((CoffeeServer.coffeeAddons)).contains(additionOne.toLowerCase())) {
                                System.out.println("***The requested coffee addition " + additionOne + " is not available");
                                new ClientMachine("220");
                            }

                            if (!Arrays.toString((CoffeeServer.coffeeAddons)).contains(additionTwo.toLowerCase())) {
                                System.out.println("***The requested coffee addition " + additionTwo + " is not available");
                                new ClientMachine("220");
                            }

                            if (!Arrays.toString((CoffeeServer.coffeeAddons)).contains(additionThree.toLowerCase())) {
                                System.out.println("***The requested coffee addition " + additionThree + " is not available");
                                new ClientMachine("220");
                            } else {
                                STATE = "BREW"; // change state
                            }

                        } else if (proceed.equalsIgnoreCase("yes")) {
                            new ClientMachine("220");
                        }

                        //craft htcpcp propfind request if the input given is 'yes'
                    } else if (propProceed.equalsIgnoreCase("yes")) {

                        htcpcpRequest = "propfind://localhost/";
                        System.out.println(htcpcpRequest);
                        System.out.println(inMSG);
                        if (inMSG.contains("220")) {
                            System.out.println(Arrays.toString(CoffeeServer.coffeeAddons) + "\r\n");
                            new ClientMachine("220");
                        }
                    }

                } else {
                    System.out.println("2.0 unexpected input in: " + STATE);
                }

                break;
            case "BREW":
                System.out.println("...STATE IS NOW IN: " + STATE + " STAGE");

                //craft htcpcp brew request from the given inputs 
                htcpcpRequest = "brew://localhost/"
                        + Client.portNumber + "?" + additionOne + ";" + addonOneVol + "#"
                        + additionTwo + ";" + addonTwoVol + "#"
                        + additionThree + ";" + addonThreeVol;
                System.out.println("2.1 Received response " + inMSG);
                System.out.println(htcpcpRequest);
                if (inMSG.contains("202")) {
                    STATE = "POST";
                    System.out.println(">>> Coffee Brewing!");
                }
                break;

            case "POST":
                System.out.println("2.2 STATE IS : " + STATE);

                //craft htcpcp post request from the given inputs
                htcpcpRequest = "post://localhost/"
                        + Client.portNumber + "?" + additionOne + ";" + addonOneVol + "#"
                        + additionTwo + ";" + addonTwoVol + "#"
                        + additionThree + ";" + addonThreeVol;
                System.out.println("2.2 received response " + inMSG);
                System.out.println(htcpcpRequest);
                if (inMSG.toUpperCase().contains("203")) {  //parse message
                    System.out.println(">>> Posting Coffee Request");
                    STATE = "FINAL";

                } else if (inMSG.toUpperCase().contains("551")) {
                    STATE = "QUIT";
                }
                break;

            case "FINAL":
                System.out.println("2.3 STATE IS : " + STATE);

                //send a 'final' message to the coffee server to prompt delivery of the COFFEE
                htcpcpRequest = "final";
                if (inMSG.toUpperCase().contains("204")) {  //parse message
                    System.out.println("2.3 Final Server Response: " + inMSG);
                STATE ="WAIT_QUIT_CONF";
                htcpcpRequest = "QUIT";
                } else if (inMSG.toUpperCase().contains("551")) { //don't send command again.
                    STATE = "QUIT";
                }
                break;

            case "QUIT":
                System.out.println("2.5. STATE IS : " + STATE);
                // start interaction with server
                htcpcpRequest = "QUIT";
                System.exit(0);
                break;

            case "WAIT_QUIT_CONF":
                System.out.println("STATE IS : " + STATE);
                //parse message
                    htcpcpRequest = "QUIT";
                    System.out.println("System is Closing");
                    //quiting through destructor = destruction connstructor
                    new Socket_Instance(sINST);
                   if (inMSG.toUpperCase().contains("500")) {
                    STATE = "QUIT";
                } else if (inMSG.toUpperCase().isEmpty()) {
                    System.out.println("Connection closed");
                }
                break;
            default:
                break;
        }// switch(STATE)

    }

    public String getMessage() {
        return sendMSG;
    }

    //accessible get method for the crafted htcpcp request that returns the request string
    public String getRequest() {
        return htcpcpRequest;
    }
    public static ArrayList<String> coffee = new ArrayList<String>();
}
