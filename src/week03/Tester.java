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
    	la.readFile("D:/Personal/GlabLibs/testFiles/weblog3-short_log.txt");
    	
    	
    	HashMap<String, Integer> visitsPerIp = la.countVisitsPerIP();
    	System.out.println("Visits per Ip");
    	System.out.println(visitsPerIp);
    	
    	System.out.println("Most number visits by Ip " + la.mostNumberVisitsByIP(visitsPerIp));
    	
    	System.out.println("Ips Most visited");
    	ArrayList<String> mostVisIps = la.IPsMostVisits(visitsPerIp);
    	System.out.println(mostVisIps);
    	
    	System.out.println("Ips for day");
    	HashMap<String, ArrayList<String>>  ipsForDay = la.iPsForDays();
    	System.out.println(ipsForDay);
    	
    	System.out.println("Day with most visits " + la.dayWithMostIPVisits(ipsForDay));
    	
    	System.out.println("Ips with most visits on Sep 30");
    	ArrayList<String> list = la.iPsWithMostVisitsOnDay(ipsForDay, "set 30");
    	System.out.println(list);
    	
//    	la.printAll();
//    	la.printUniqIPs();
//    	la.printAllHigherThanNum(200);
    	
//    	ArrayList<LogEntry> visitDay = la.uniqueIPVisitsOnDay("Sep 30");
//    	for(LogEntry le : visitDay)
//    		System.out.println(le);
//    	
//    	System.out.println("UniqueIPsinRange " + la.countUniqueIPsInRange(300,399));
    }
    
    public static void main(String args[]){
    	Tester t = new Tester();
    	t.testLogAnalyzer();
    }
}
