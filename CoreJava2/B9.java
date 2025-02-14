import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
public class B9 {
    public static void main(String[] args) {
       
    }

    public static Map<String, Integer>  wordCount(File file){
        Map<String, Integer> wordCount = new HashMap<String, Integer>();
        try{
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()){
                String word = scanner.next();
                if(wordCount.containsKey(word)){
                    wordCount.put(word, wordCount.get(word) + 1);
                }else{
                    wordCount.put(word, 1);
                }
            }
            scanner.close();
        }catch(Exception e){
            e.printStackTrace();  
        }
        return wordCount;
    }
}