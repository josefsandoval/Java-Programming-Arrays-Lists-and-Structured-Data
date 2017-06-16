import java.util.*;
import edu.duke.*;
import java.io.*;

public class WordsInFiles
{
    HashMap<String,ArrayList<String>> map;
    
    public WordsInFiles()
    {
        map = new HashMap<>();
    }
    
    /*
       Write a private void method named addWordsFromFile that has one parameter f of type File. 
       This method should add all the words from f into the map. If a word is not in the map, then
       you must create a new ArrayList of type String with this word, and have the word map to this
       ArrayList. If a word is already in the map, then add the current filename to its ArrayList, 
       unless the filename is already in the ArrayList. You can use the File method getName to get
       the filename of a file.
    */
    private void addWordsFromFile(File f)
    {
        FileResource fr = new FileResource(f.toString());
        
        for(String s: fr.words())
        {
            if(!(map.containsKey(s)))
            {
                ArrayList<String> newArrayList = new ArrayList<>();
                map.put(s,newArrayList);
                map.get(s).add(f.getName());
            }else
            {
                map.get(s).add(f.getName());
            }
        }
        
    }
    
    /*
       Write a void method named buildWordFileMap that has no parameters. This method first clears 
       the map, and then uses a DirectoryResource to select a group of files. For each file, it puts 
       all of its words into the map by calling the method addWordsFromFile. 
       The remaining methods to write all assume that the HashMap has been built.
    */
    private void buildWordFileMap()
    {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        
        for(File f: dr.selectedFiles())
        {
            //adds all the words from files to the hashmap 
            //using addWordsFromFile method.
            this.addWordsFromFile(f);
        }
        
    }
    
    /*
       Write the method maxNumber that has no parameters. This method returns the maximum number 
       of files any word appears in, considering all words from a group of files. In the example above,
       there are four files considered. No word appears in all four files. Two words appear in three 
       of the files, so maxNumber on those four files would return 3. This method assumes that the
       HashMap has already been constructed.
    */
   private int maxNumber()
   {
       int max = 0;
       int current = 0;
       
       for(String s: map.keySet())
       {
           current = map.get(s).size();
           if(max < current)
           {
               max = current;
           }
       }
       return max;
   }
   
   /*
      Write the method wordsInNumFiles that has one integer parameter called number. This method returns
      an ArrayList of words that appear in exactly number files. In the example above, the call wordsInNumFiles(3)
      would return an ArrayList with the words “cats” and “and”, and the call wordsInNumFiles(2) would return an 
      ArrayList with the words “love”, “are”, and “dogs”, all the words that appear in exactly two files.
   */
  
  private ArrayList<String> wordsInNumFiles(int number)
  {
      int nFilesIn = 0;
      ArrayList<String> list = new ArrayList<>();
      
      for(String word: map.keySet())
      {
        //get number of files that contains the current word
        nFilesIn = map.get(word).size();
        
        if(nFilesIn == number)
        {
            list.add(word);
        }
      }
  
      return list;
  }
  
  /*
     Write the void method printFilesIn that has one String parameter named word. This method prints the names
     of the files this word appears in, one filename per line. For example, in the example above, the call printFilesIn(“cats”)
     would print the three filenames: brief1.txt, brief3.txt, and brief4.txt, each on a separate line.
  */
 
 private void printFilesIn(String word)
 {
    for(String s: map.keySet())
    {
        if(s.equals(word))
        {
            for(String currentWord: map.get(s))
            {
                System.out.println(currentWord);
            }
        }
    }
 }
 
 /*
    Write the void method tester that has no parameters. This method should call buildWordFileMap to select a group of files 
    and build a HashMap of words, with each word mapped to an ArrayList of the filenames this word appears in, determine the 
    maximum number of files any word is in, considering all words, and determine all the words that are in the maximum number 
    of files and for each such word, print the filenames of the files it is in. (optional) If the map is not too big, then you
    might want to print out the complete map, all the keys, and for each key its ArrayList. This might be helpful to make sure
    the map was built correctly.
 */

    //SAMPLE OUTPUT TO ACHIEVE
    /*
     * The greatest number of files a word appears in is three, and there are two such words: “cats” and “and”.
     * 
     * “cats” appears in the files: brief1.txt, brief3.txt, brief4.txT
     * 
     * “and” appears in the files: brief1.txt, brief3.txt, brief4.txt
    */
    public void tester()
    {
        buildWordFileMap();
        int greatest = this.maxNumber();
        
        ArrayList<String> fileNames = this.wordsInNumFiles(greatest);
        System.out.print("The greatest number of files a word appears in is " +  greatest + ", and there are " + fileNames.size() + " such words: " );
        for(String s: fileNames)
        {
            System.out.print('"' + s + '"' + " ");
        }
        System.out.println();
         for(String s: fileNames)
         {
             System.out.print('"' + s + '"' + " appears in the files: ");
             
             for(String word: map.get(s))
             {
                 System.out.print(word + " " );
             }
             
             System.out.println();
        }
        
        
    }
}
