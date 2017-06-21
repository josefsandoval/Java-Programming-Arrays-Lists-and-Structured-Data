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
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/short-test log.txt");
        la.printAll();
    }
    
    /*In the Tester class (or you can write a new class for testing) write the void method testUniqueIP
     * that has no parameters. This method should create a LogAnalyzer, read from the file short-test_log, 
     * and then test the method countUniqueIPs.*/
    public void testUniqueIP()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog-short_log.txt");
        int count = la.countUniqueIPs();
        
        System.out.println("There are " + count + " unique IP's in weblog-short_log.txt");
        
        la.printAllHigherThanNum(200);
    }
    
    public void testUniqueIPVisitsOnDay()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/weblog-short_log.txt");
        
        ArrayList<String> sep14 = la.uniqueIPVisitsOnDay("Sep 14");
        
        for(String s: sep14){
            System.out.println(s);
        }
        
        System.out.println("\n");
        ArrayList<String> sep30 = la.uniqueIPVisitsOnDay("Sep 30");
        
        for(String s1: sep30)
        {
            System.out.println(s1);
        }
    }
    
    public void testCountUniqueIPsInRange()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/short-test_log.txt");
        
        int test1 = la.countUniqueIPsInRange(200, 299);
        System.out.println("Expected: 4 Got: " + test1);
        
        int test2 = la.countUniqueIPsInRange(300, 399);
        System.out.println("Expected: 2 Got: " + test2);
    }
}

