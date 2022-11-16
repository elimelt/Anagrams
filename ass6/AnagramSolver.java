import java.util.*;

public class AnagramSolver {
    private Map<String, LetterInventory> wordMap;



    // This method constructs an anagram solver that will use the 
    // given list as its dictionary. You should not change the list in any 
    // way. You may assume that the dictionary is a nonempty 
    // collection of nonempty sequences of letters, that it contains no 
    // duplicates, and that it doesn’t change in state as the program 
    // executes.
    public AnagramSolver(List<String> list) {
        wordMap = new HashMap<>();
            for (String word : list) wordMap.put(word, new LetterInventory(word));
    }
}

