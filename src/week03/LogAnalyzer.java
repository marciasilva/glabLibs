package week03;

import java.util.*;
import edu.duke.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
    	 records = new ArrayList<LogEntry>();
     }	
        
     public void readFile(String filename) {
         // complete method
    	 FileResource fr = new FileResource(filename);
    	 for (String line : fr.lines()) {
    		 WebLogParser wlp = new WebLogParser();
    		 records.add(wlp.parseEntry(line));
    		    // process each line in turn
    	 }
    	 
     }
     
     private ArrayList<String> uniqueIPs(){
    	 ArrayList<String> uniqueIPs = new ArrayList<String>();
     	 for(LogEntry le : records){
     		String ipAddress = le.getIpAddress();
     		if(!uniqueIPs.contains(ipAddress))
     			 uniqueIPs.add(ipAddress);
     	 }
     	 return uniqueIPs;
     }
       
    private int countUniqueIPs(){
    	ArrayList<String> uniqueIPs = uniqueIPs();
    	return uniqueIPs.size();
    }
    
    
    public ArrayList<LogEntry> uniqueIPVisitsOnDay(String someday){
    	ArrayList<LogEntry> answer = new ArrayList<LogEntry>();
    	ArrayList<String> uniqueIPs = uniqueIPs();
    	for(LogEntry le : records){
       		String strDate = le.getAccessTime().toString();
    		String ipAddress = le.getIpAddress();
    		if( strDate.contains(someday)  && !uniqueIPs.contains(ipAddress)){
    			answer.add(le);
    		}
    	}
    	return answer;
    }
    
    
    public int countUniqueIPsInRange(int low, int high){
    	int count = 0;
    	ArrayList<String> uniqueIPs = new ArrayList<String>();
    	for(LogEntry le : records){
    		if(le.getStatusCode() > low && le.getStatusCode() < high){
    			if(!uniqueIPs.contains(le.getIpAddress())){
    				uniqueIPs.add(le.getIpAddress());
    				count++;
    			}
    		}  			
    	}
    	return count;
    	
    }
    
    public void printAllHigherThanNum(int num){
    	for(LogEntry le : records){
    		int status = le.getStatusCode();
    		if(status > num)
    			System.out.println(le);
    	}
    }
    
     
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     } 
     
     public void printUniqIPs(){
    	 System.out.println("Unique IPs number: " + countUniqueIPs());
     }
}
