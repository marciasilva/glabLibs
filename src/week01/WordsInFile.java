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

}
