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
    	HashMap<String, HashSet<String>> allLanguages = new HashMap<String, HashSet<String>>();
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
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<String,Integer> mostCommon = new HashMap<String,Integer>();
        int qtdMostCommonChar = 0;
        char mostCommonChar = 'z';
    	for (String	str : dictionary) {
    		for(int i = 0; i < str.length(); i++){
    			String c = Character.toString(str.charAt(i));
    			if(mostCommon.containsKey(c)){
    				Integer n = mostCommon.get(c);
    				if(n > qtdMostCommonChar){
    					qtdMostCommonChar = n;
    					mostCommonChar = c.charAt(0);
    				}
    				mostCommon.put(c, n + 1);
    			}
    			else{
    				mostCommon.put(c, 1);
    			}
    		}
    	}
    	
    	mostCommon.clear();
    	return mostCommonChar;
    }
    
    public void breakForAllLanguages(String encrypted, HashMap<String, HashSet<String>> languages){
    	int maxRealW =0;
    	String realDecrypted = new String();
    	String realLanguage = new String();
    	Set<String> myLanguages = languages.keySet();
    	    	
    	for(String language : myLanguages){
    		HashSet<String> dict = languages.get(language);
    		System.out.println("Trying language " + language);
    		String decrypted = breakForLanguage(encrypted, dict);
    		int numRealW = countWords(decrypted, dict);
        	if(maxRealW < numRealW){
        		maxRealW = numRealW;
        		realDecrypted =  decrypted;
        		realLanguage = language;
        	}
    	}
    	
    	System.out.println("\nTotal valid words " + maxRealW);	
    	System.out.println("The language used was: " + realLanguage);
    	System.out.println("Decrypted message \n" + realDecrypted);
    }
   
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
    	int maxRealW = 0;
    	String realDecrypted = new String();
    	for (int i = 1; i <= 100; i++){
    		int keys [] = tryKeyLength(encrypted, i, mostCommonCharIn(dictionary));
        	VigenereCipher vc = new VigenereCipher(keys);
        	String decrypted = vc.decrypt(encrypted);
        	int numRealW = countWords(decrypted, dictionary);
        	if(maxRealW < numRealW){
        		maxRealW = numRealW;
        		realDecrypted =  decrypted;
        	}	
    	}
    	
    	return realDecrypted;    	
    }
    
        
    public void breakVigenere () {
    	FileResource fr = new FileResource();
    	String message = fr.asString();
    	
    	String languages [] = {"Danish","Dutch", "English","French", "German","Italian","Portuguese","Spanish"};
    	
    	HashMap<String,HashSet<String>> allDicts = new HashMap<String, HashSet<String>>();
    	for(int i = 0; i < languages.length; i++){
    		FileResource dictFile = new FileResource();
            HashSet <String> dictionary = readDictionary(dictFile);
            allDicts.put(languages[i], dictionary);
            System.out.println("Most common char in " + languages[i]  + " is " + mostCommonCharIn(dictionary));
    	}
    	
    	
    	breakForAllLanguages(message,allDicts);
    
    	//breakForLanguage(message, dictionary);
    	
    	/* Known key, and English language 'e'
    	 * int keys [] = tryKeyLength(message, 4, 'e');
    	 * VigenereCipher vc = new VigenereCipher(keys);
    	 * System.out.println(vc.decrypt(message));    	
    	*/
    }
    
}

