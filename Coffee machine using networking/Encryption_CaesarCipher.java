package Htcpcp;

public class Encryption_CaesarCipher {
    private String plainMessage = "";
    private String encryptedMessage = "";
    private int key;
    
    public Encryption_CaesarCipher(String message, int KEY){
        plainMessage = message;
        key = KEY;
        char ch;
        
        //System.out.println("message to encrypt "+plainMessage + " of length " + plainMessage.length());
        
        for(int i = 0; i < plainMessage.length(); ++i){
            ch = plainMessage.charAt(i);
            if(ch >= 'a' && ch <= 'z'){
                ch = (char)(ch + key);
                if(ch > 'z'){
                    ch = (char)(ch - 'z' + 'a' -1);
                }
	        encryptedMessage += ch;
	    } else if(ch >= 'A' && ch <= 'Z'){
                ch = (char)(ch + key);
	        if(ch > 'Z'){
	            ch = (char)(ch - 'Z' + 'A' -1);
	        }
	        encryptedMessage += ch;
	    } else {
                encryptedMessage += ch;
	    }
	}
	//.out.println("Encrypted Message = " + encryptedMessage);
    }
    public String get_encrypted(){
        return encryptedMessage;
    }
}
