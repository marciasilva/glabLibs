package week04;

import edu.duke.*;
import java.util.*;

public class VigenereCipher {
    CaesarCipher[] ciphers;
    
    public VigenereCipher(int[] key) {
        ciphers = new CaesarCipher[key.length];
        for (int i = 0; i < key.length; i++) {
            ciphers[i] = new CaesarCipher(key[i]);
        }
    }
    
    public String encrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.encryptLetter(c));
            i++;
        }
        return answer.toString();
    }
    
    public String decrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.decryptLetter(c));
            i++;
        }
        return answer.toString();
    }
    
    public String toString() {
        return Arrays.toString(ciphers);
    }
    
    public static void main(String args[]){
    	int keys [] = {17,14,12,4};
    	
    	VigenereCipher vc = new VigenereCipher(keys);
    	VigenereBreaker vb = new VigenereBreaker();
    	
    	FileResource fr = new FileResource();
    	String mFile = fr.asString();
    	
    	System.out.println("Encrypted message: ");
    	System.out.println(vc.encrypt(mFile));
    	
    	System.out.println("Decrypted message: ");
    	System.out.println(vc.decrypt(vc.encrypt(mFile)));

    	
//    	String message = "Coal-black is better than another hue, In that it scorns to bear another hue; "
//    			+ "For all the water in the ocean Can never turn the swan's black legs to white,"
//    			+ "Although she lave them hourly in the flood.";
//    	System.out.println(vb.sliceString(message, 0, 3));
//    	System.out.println(vb.sliceString(message, 1, 3));
//    	System.out.println(vb.sliceString(message, 2, 3));
//    	
//    	//test with Portuguese alphabet
//    	CaesarCracker cc = new CaesarCracker('a');
    
//    	System.out.println(cc.decrypt(mFile));
    	
    	
    	//sliceString
    	
    }
    
}

