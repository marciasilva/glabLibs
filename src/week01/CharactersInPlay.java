package week01;

import edu.duke.*;

import java.util.ArrayList;

public class CharactersInPlay {
	
	  
	private ArrayList<String> characters;
	private ArrayList<Integer> myFreqs;
	
	public CharactersInPlay(){
		characters = new ArrayList<String>();
	     myFreqs = new ArrayList<Integer>();
	}
	
	private void update(String person){
		int index = this.characters.indexOf(person);
        if (index == -1){
        	this.characters.add(person);
            myFreqs.add(1);
        }
        else {
            int freq = myFreqs.get(index);
            myFreqs.set(index,freq+1);
        }
	}
	
	private void findAllCharacters(){
		FileResource fr = new FileResource();
		for(String line : fr.lines()){
			int index = line.indexOf('.'); 
			if(index != -1 && index < 30){
				String actor = line.substring(0, index);
				update(actor);
			}
		}
		
		for (int i = 0; i < this.characters.size(); i++){
        	System.out.println(this.myFreqs.get(i) + " " + this.characters.get(i));
		}
	}
	
	private void charactersWithNumParts(int num1, int num2){
		for (int i = 0; i < this.myFreqs.size(); i++){
			if(this.myFreqs.get(i) >= num1 && this.myFreqs.get(i) < num2){
				System.out.println(this.characters.get(i) + " : " + this.myFreqs.get(i));
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CharactersInPlay cp = new CharactersInPlay();
		cp.findAllCharacters();
		cp.charactersWithNumParts(10, 15);
	}

}
