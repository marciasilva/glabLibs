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
    
    
    public HashSet<String> readDictionary(FileResource fr){
    	HashSet<String> myDict = new HashSet<String>();
    	for(String line : fr.lines()){
    		myDict.add(line.toLowerCase());
    	}
    	return myDict;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
    	int numberW = 0;
    	for(String word : message.split("\\W")){
    		if(dictionary.contains(word.toLowerCase())) numberW ++;
    	}
    	
    	return numberW;
    }
   
    public void breakForLanguage(String encrypted, HashSet<String> dictionary){
    	int maxRealW = 0;
    	String realDecrypted = new String();
    	for (int i = 1; i <= 100; i++){
    		int keys [] = tryKeyLength(encrypted, i, 'e');
        	VigenereCipher vc = new VigenereCipher(keys);
        	String decrypted = vc.decrypt(encrypted);
        	int numRealW = countWords(decrypted, dictionary);
        	if(maxRealW < numRealW){
        		maxRealW = numRealW;
        		realDecrypted =  decrypted;
        	}	
    	}
    	System.out.println("Decrypted message \n" + realDecrypted);	
    	System.out.println("\nTotal valid words " + maxRealW);	
    }
    
    public void breakVigenere () {
    	FileResource fr = new FileResource();
    	String message = fr.asString();
    	
    	FileResource dictFile = new FileResource();
    	HashSet <String> dictionary = readDictionary(dictFile);
    	
    	breakForLanguage(message, dictionary);
    	
    	//int keys [] = tryKeyLength(message, 4, 'e'); known key
    	//VigenereCipher vc = new VigenereCipher(keys);
    	//System.out.println(vc.decrypt(message));
    }
    
}

