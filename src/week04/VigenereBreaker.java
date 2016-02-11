package week04;

import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
    	//use string builder 
        StringBuilder answer = new StringBuilder();
    	for (int i = whichSlice; i < message.length(); i += totalSlices){
    		char charSlice = message.charAt(i);
    		answer.append(charSlice);
    	}
    	
        return answer.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for(int i = 0; i < klength; i++){
        	key[i] = cc.getKey(sliceString(encrypted,i,klength));
        }
        return key;
    }

    public void breakVigenere () {
    	FileResource fr = new FileResource();
    	String message = fr.asString();
    	int keys [] = tryKeyLength(message, 6, 'e');
    	VigenereCipher vc = new VigenereCipher(keys);
    	System.out.println(vc.decrypt(message));
    }
    
}

