import java.util.*;
import edu.duke.*;
public class LogAnalyzer 
{
    private ArrayList<LogEntry> records;
    
    public LogAnalyzer()
    {
        this.records = new ArrayList<>();
    }
    
    public void readFile(String fileName)
    {
        FileResource fr = new FileResource(fileName);
        
        for(String line: fr.lines())
        {
            records.add(WebLogParser.parseEntry(line));
        }
    }
    
    public void printAll()
    {
        for(LogEntry le: this.records)
        {
            System.out.println(le);
        }
    }
    
    //Find Unique IP addresses 
    /*In the LogAnalyzer class, write the method countUniqueIPs that has no parameters. This method 
     * should return an integer representing the number of unique IP addresses. It should also assume
     * that the instance variable records already has its ArrayList of Strings read in from a file, and 
     * should access records in computing this value. For help, refer to the lectures in this lesson
     * on the unique IP algorithm and code*/
    public int countUniqueIPs()
    {
        ArrayList<String> unique = new ArrayList<>();
        
        for(LogEntry le: this.records)
        {
            String ipAddress = le.getIpAddress();
            if(!(unique.contains(ipAddress)))
            {
                unique.add(ipAddress);
            }
        }
        
        return unique.size();
    }
    
     /*In the LogAnalyzer class, write the void method printAllHigherThanNum that has one integer 
     * parameter num. This method should examine all the web log entries in records and print those
     * LogEntrys that have a status code greater than num. Be sure to add code in the Tester class
     * to test out this method with the file short-test_log.*/
    public void printAllHigherThanNum(int num)
    {
        for(LogEntry le: this.records)
        {
            if(le.getStatusCode() > num)
            {
                System.out.println(le);
                System.out.println();
                System.out.println(le.getAccessTime().toString());
            }
        }
    }
    
    /*In the LogAnalyzer class, write the method uniqueIPVisitsOnDay that has one String parameter named 
    * someday in the format “MMM DD” where MMM is the first three characters of the month name with the 
    * first letter capitalized and the others in lowercase, and DD is the day in two digits 
    * (examples are “Dec 05” and “Apr 22”). This method accesses the web logs in records and returns an 
    * ArrayList of Strings of unique IP addresses that had access on the given day. (Note that the dates
    * in LogEntrys are stored as a Date object, but using toString will allow you to access the characters
    * in the Date. For example, consider that d is a Date. String str = d.toString(); allows you to now 
    * use a String representation of the date.) Be sure to test your program with code in the Tester class. 
    * Using the file weblog-short_log you should see that the call to uniqueIPVisitsOnDay(“Sep 14”) returns
    * an ArrayList of 2 items and uniqueIPVisitsOnDay(“Sep 30”) returns an ArrayList of 3 items.*/
    public ArrayList<String> uniqueIPVisitsOnDay(String someday)
    {
        ArrayList<String> uniqueIPVisit = new ArrayList<>();
        
        //visit all Log Entries 
        for(LogEntry le: this.records)
        {
            //Store current log entries day as a String
            String currentDay = le.getAccessTime().toString();
            
            //if current log entries day contains String someday
            //add it to uniqueIPVisit if only it is already not in the ArrayList
            if(currentDay.contains(someday))
            {
                if(!uniqueIPVisit.contains(le.getIpAddress()))
                {
                    uniqueIPVisit.add(le.getIpAddress());
                }
            }
        }
       return uniqueIPVisit;
    }
    
     /*In the LogAnalyzer class, write the method countUniqueIPsInRange that has two integer parameters 
     * named low and high. This method returns the number of unique IP addresses in records that have a 
     * status code in the range from low to high, inclusive. Be sure to test your program on several ranges. 
     * For example, using the file short-test_log, the call countUniqueIPsInRange(200,299) returns 4, as
     * there are four unique IP addresses that have a status code from 200 to 299. The call countUniqueIPsInRange(300,399) returns 2. 
     * In this case, note that there are three entries in the file that have a status code in the 300 range, but two of them have the same IP address.*/
    public int countUniqueIPsInRange(int low, int high)
    {
        ArrayList<String> uniqueIPs = new ArrayList<>();
        
        for(LogEntry le: this.records)
        {
            if(le.getStatusCode() <= high && le.getStatusCode() >= low)
            {
                if(!uniqueIPs.contains(le.getIpAddress()))
                {
                    uniqueIPs.add(le.getIpAddress());
                }
            }
        }
        return uniqueIPs.size();
    }
}
