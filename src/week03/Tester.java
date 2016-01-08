package week03;

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile("D:/Personal/GlabLibs/testFiles/weblog2_log.txt");
    	
    	System.out.println("Unique Ips " + la.countUniqueIPs());
    	
    	System.out.println("Unique Ips visited in sep 27: " + la.uniqueIPVisitsOnDay("set 27").size());
    	
    	System.out.println("Ips in Range " + la.countUniqueIPsInRange(200, 299));
    	
    	
    	HashMap<String, Integer> visitsPerIp = la.countVisitsPerIP();  	
    	System.out.println("Most number visits by Ip " + la.mostNumberVisitsByIP(visitsPerIp));
    	
    	System.out.println("Ips Most visited");
    	ArrayList<String> mostVisIps = la.IPsMostVisits(visitsPerIp);
    	System.out.println(mostVisIps);
    	

    	HashMap<String, ArrayList<String>>  ipsForDay = la.iPsForDays();  	
    	System.out.println("Day with most visits " + la.dayWithMostIPVisits(ipsForDay));
    	
       	System.out.println("Ips with most visits on Sep 30");
    	ArrayList<String> list = la.iPsWithMostVisitsOnDay(ipsForDay, "set 29");
    	System.out.println(list);
    	

    }
    
    public static void main(String args[]){
    	Tester t = new Tester();
    	t.testLogAnalyzer();
    }
}
