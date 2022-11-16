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
        for (String word : list) 
            wordMap.put(word, new LetterInventory(word));
    }


    // This is your method that will use recursive backtracking to find 
    // combinations of words that have the same letters as the given 
    // string. It should print to System.out all combinations of words 
    // from the dictionary that are anagrams of s and that include at 
    // most max words (or unlimited number of words if max is 0). It 
    // should throw an IllegalArgumentException if max is less than 0.

    public void print(String s, int max) {
        if (max < 0)
            throw new IllegalArgumentException();
        List<String> possibleWords = relevantWords(s);
        if (max == 0)
            max = possibleWords.size();
        print(new LetterInventory(s), max, possibleWords, new Stack<>());

    }


    private void print(LetterInventory currInv, int remaining, List<String> words, Stack<String> considering) {
        if (currInv != null){
            if (currInv.size() == 0 && remaining >= 0) 
                System.out.println(considering);
            else {
                for (String word : words) {
                    considering.push(word);
                    print(currInv.subtract(wordMap.get(word)), remaining - 1, words, considering);
                    considering.pop();
                }
            }
        }

    }


    private List<String> relevantWords(String s){
        List<String> output = new ArrayList();
        LetterInventory inputInventory = new LetterInventory(s);
        for (String key : wordMap.keySet()){
            LetterInventory currentInventory = wordMap.get(key);
            if (inputInventory.subtract(currentInventory) != null) 
                output.add(key);
        }
        return output;
    }
}

