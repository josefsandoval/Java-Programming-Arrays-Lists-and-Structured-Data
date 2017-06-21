
/**
 * Write a description of LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
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
}
