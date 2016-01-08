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
    		 this.records.add(wlp.parseEntry(line));
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
     
     public Integer mostNumberVisitsByIP(HashMap<String, Integer> myMap){
    	 Integer ipsVisited = 0;
    	 for(Integer v : myMap.values()){
    		 if(v > ipsVisited) ipsVisited = v;
    	 }
    	 return ipsVisited;
     }
     
     public ArrayList<String> IPsMostVisits(HashMap<String, Integer> myMap){
    	 ArrayList<String> answer = new ArrayList<String>();
    	 
    	 Integer maxV = mostNumberVisitsByIP(myMap);
    	 
    	 for(String ip : myMap.keySet()){
    		 if(myMap.get(ip) >= maxV){
    			 answer.add(ip);
    		 }
    	 }
    	 return answer;
     }
     
     //returns a list  IP addresses that occurred on that day
     public HashMap<String, ArrayList<String>> iPsForDays(){
    	 HashMap<String, ArrayList<String>> answer = new HashMap<String, ArrayList<String>>();

    	 for(LogEntry le : this.records){
    		 String date =  new SimpleDateFormat("MMM dd").format( le.getAccessTime());
    		 String ip = le.getIpAddress();
    		 
    		if(!answer.containsKey(date)){
    	    	 ArrayList<String> ips = new ArrayList<String>();
    			ips.add(ip);
    			 answer.put(date, ips);
    		 }
    		else{
    			ArrayList<String> aux = answer.get(date);
    			aux.add(ip);
    			answer.put(date, aux);
    			
    		}
    	 }
    	 
    	 return answer;
     }
     
     //returns the day that has the most IP address visits
     public String dayWithMostIPVisits(HashMap <String, ArrayList<String>> myMap){
    	 String day = new String();
    	 int vOnDay = 0;
    	 
    	 for(String key : myMap.keySet()){
    		 int size = myMap.get(key).size();
    		 if(size > vOnDay){
    			 vOnDay = size;
    			 day = key;
    		 
    		 }
    		 else if(size == vOnDay){
    			 return "Two days had the same number of visits";
    		 }
    	 }
    	 return day;
     }
     
     
     //returns a list of IP addresses that had the most accesses on the given day.
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> myMap, String date){
    	HashMap<String, Integer> visits = new HashMap<String, Integer>();
    
    	ArrayList<String> ips = myMap.get(date);
       	try{
    	for(String ip : ips){
    	    if(visits.containsKey(ip)){
    	    	visits.put(ip, visits.get(ip) + 1);
    	    }
    	    else{
    	    	visits.put(ip, 1);
    	    }
    	}
    	}catch(Exception e){
    		System.out.println("Erro ao usar chave " + date);
    	}
    	return IPsMostVisits(visits);
     }
     
     
    // returns a list with the number of times this IP address visited the website
     public HashMap<String, Integer> countVisitsPerIP(){
    	 HashMap<String, Integer> visits = new HashMap<String, Integer>();
    	 
    	 for(LogEntry le : records){
    		 String ip = le.getIpAddress();
    		 if(visits.containsKey(ip)){
    			 visits.put(ip, visits.get(ip) + 1);
    		 }
    		 else{
    			 visits.put(ip, 1);
    		 }
    	 }
    	 
    	 return visits;
     }
     
   
}
