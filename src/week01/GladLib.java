package week01;

import edu.duke.*;
import java.util.*;

public class GladLib {
	
	
	private Random myRandom;
	
	//private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "D:/Personal/GlabLibs/data";
	private	HashMap<String, ArrayList<String>> mySource = new HashMap<String,ArrayList<String>>();
	private ArrayList<String> getCategories;

	
	public GladLib(){
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}
	
	public GladLib(String source){
		initializeFromSource(source);
		myRandom = new Random();
	}
	
	private void initializeFromSource(String source) {
		String [] labels  = {"adjective", "animal", "color", "country", "fruit", "name", "noum", "timeframe", "verb"};
		
		for (String s : labels){
			ArrayList <String> list = readIt(source + "/" + s + ".txt");
			mySource.put(s, list);
		}	
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		else{
			return randomFrom(this.mySource.get(label));
		}
	}
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		this.getCategories.add(w.substring(first+1, last));
		String sub = getSubstitute(w.substring(first+1,last));
		return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	public void makeStory(){
	    System.out.println("\n");
		String story = fromTemplate( dataSourceDirectory + "madtemplate.txt");
		printOut(story, 60);
	}
	
	int totalWordsInMap(){
		int count = 0;
		
		for (String s : this.mySource.keySet()) {
			count += this.mySource.get(s).size();
		    // process each key in turn 
		}
		return count;
	}

	int totalWordsConsidered(){
		int count = 0;
		for(String category : this.getCategories){
			count += this.mySource.get(category).size();
		}
		return count;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GladLib gl = new GladLib();
		gl.makeStory();
		System.out.println("Total words in map " + gl.totalWordsInMap());
		System.out.println("Total words considered " + gl.totalWordsConsidered());
	}


}

