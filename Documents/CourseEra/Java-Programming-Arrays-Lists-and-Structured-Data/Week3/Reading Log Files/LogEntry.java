import java.util.*;
//CourseEra Week 3
public class LogEntry 
{
    private String ipAddress;
    private Date accessTime;
    private String request;
    private int statusCode;
    private int bytesReturned;
    
    public LogEntry(String ipAddress, Date accessTime, String request, int statusCode,int bytesReturned)
    {
        this.ipAddress = ipAddress;
        this.accessTime = accessTime;
        this.request = request;
        this.statusCode = statusCode;
        this.bytesReturned = bytesReturned;
    }
    
    public String getIpAddress()
    {
        return this.ipAddress;
    }
    
    public Date getAccessTime()
    {
        return this.accessTime;
    }
    
    public String getRequest()
    {
        return this.request;
    }
    
    public int getStatusCode()
    {
        return this.statusCode;
    }
    
    public int getBytesReturned()
    {
        return this.bytesReturned;
    }
    
    /*
    //Override Objects toString method. Write in Apache 2.4 format
    @Override
    public String toString()
    {
        return this.ipAddress + " - - [" + this.accessTime + "] " + "\"GET " + this.request + "\" " + this.statusCode + " " + this.bytesReturned;
    }
    */
   
    //Testing out toString method
    /*
    public static void main(String []args)
    {
        Date d = new Date();
        LogEntry le = new LogEntry("127.0.0.1",d,"/apache_pb.gif HTTP/1.0",200,2326);
        System.out.println(le);
    }
    */
   
    @Override
    public String toString()
    {
        return this.ipAddress + " " + this.accessTime + " " + this.request + " " + this.statusCode + " " + this.bytesReturned;
    }
}
