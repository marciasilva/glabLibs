package week01;


import edu.duke.*;

import java.io.File;
import java.util.*;

public class WordsInFile {
	
	private HashMap <String, ArrayList<String>> mapFiles;
	
	public WordsInFile(){
		this.mapFiles = new HashMap <String, ArrayList<String>> ();
	}
	
	void addWordsFromFile(File f){
		FileResource fr = new FileResource(f);
		for (String word : fr.words()) {
			
			word = word.toLowerCase();
			if (!this.mapFiles.containsKey(word)){
				ArrayList<String> filesName = new ArrayList<String>();
				filesName.add(f.getName());
				this.mapFiles.put(word,filesName);
			}
			else {
				ArrayList <String> aux = this.mapFiles.get(word);
				if(!aux.contains(f.getName())){
					aux.add(f.getName());
				}
				this.mapFiles.put(word,aux);
			}
		}
	}
	
	private void buildWordFileMap(){
		this.mapFiles.clear();
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			addWordsFromFile(f);
		    // process each file in turn
		}
	}
	
	int maxNumber(){
		int max = 0;
		for(String key : this.mapFiles.keySet()){
			max = max < this.mapFiles.get(key).size() ? this.mapFiles.get(key).size(): max;
		}
		return max;
	}
	
	ArrayList<String> wordsInNumFiles(Integer number){
		ArrayList<String> words = new ArrayList<String>();
		for(String key : this.mapFiles.keySet()){
			if(this.mapFiles.get(key).size() == number){
				words.add(key);
			}
		}
		return words;
		
	}
	
	void printFilesIn(String word){
		ArrayList<String> t = this.mapFiles.get(word);
		for (String s : t) {
		    System.out.println(s);
		} 
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WordsInFile wf = new WordsInFile();
		wf.buildWordFileMap();
//		ArrayList<String> wordsInAllFiles = wf.wordsInNumFiles(5);
		ArrayList<String> wordsInFourFiles = wf.wordsInNumFiles(7);
//		
//		System.out.println("Count words in 5 files" + wordsInAllFiles.size());
		System.out.println("Count words in 7 files" + wordsInFourFiles.size());
//		
//		
//		ArrayList<String> notSea = wf.mapFiles.get("sea");
//		System.out.println(notSea);
		
//		ArrayList<String>  hasTree = wf.mapFiles.get("tree");
//		System.out.println(hasTree);
	}

}
