import java.util.*;
public class Debug {

   public static void main(String[] args) {
      
   }
  
   public static List<String> relevantWords(String s){
      List<String> output = new ArrayList<>();
      LetterInventory inputInventory = new LetterInventory(s);
      for (String key : wordMap.keySet()){
         LetterInventory currentInventory = wordMap.get(key);
      if (inputInventory.subtract(currentInventory) != null) 
         output.add(key);
      }
      return output;
   }

}