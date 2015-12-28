package week01;

import edu.duke.*;
import java.util.*;

public class CodonCount {
	
	private HashMap <String, Integer> countDNA;
	
	public CodonCount(){
		this.countDNA = new HashMap<String, Integer> ();
	}
	
	
	//check if map is empty before
	void buildCodonMap(int start, String dna){
		for (int i = start; i < dna.length(); i= i + 3){
			if(i+3 <= dna.length()){
			String codon = dna.substring(i,i+3);
			if (!this.countDNA.containsKey(codon)){
				this.countDNA.put(codon,1);
			}
			else {
				this.countDNA.put(codon,this.countDNA.get(codon)+1);
			}
			}
		}
		int count = 0;
		for(String s : this.countDNA.keySet()){
			count += this.countDNA.get(s);
			System.out.println(s + " " + this.countDNA.get(s));
		}
		System.out.println("total " + count);
	}
	
	String getMostCommonCodon(){
		String common = new String();
		int commonValue = 0;
		for (String s : this.countDNA.keySet()) {
			if(commonValue < this.countDNA.get(s)){
				commonValue = this.countDNA.get(s);
				common = s;
			}			
		}
		return common ;
	}
	
	void printCodonCounts(int start, int end){
		for (String s : this.countDNA.keySet()){
			if (this.countDNA.get(s) >= start && this.countDNA.get(s) <= end){
				System.out.println(s + " " + this.countDNA.get(s));
			}
		}
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FileResource fr = new FileResource();
		String contents = fr.asString();
		
		CodonCount cc = new CodonCount();

//		cc.buildCodonMap(0, contents);
//		System.out.println("-------");
//		cc.countDNA.clear();
//
		cc.buildCodonMap(1, contents);
		System.out.println("------- " + cc.countDNA.size());
		
		
//		cc.countDNA.clear();
//		cc.buildCodonMap(2, contents);		
		
		cc.countDNA.clear();
		

	}

}
