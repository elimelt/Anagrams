// Name: Elijah Melton
// TA: James Hu
// HW: #6

import java.util.*;

// This class takes a dictionary of words and uses it to find all of the possible
// anagrams of a given word that can be constructed from words in our input dict.
public class AnagramSolver {
    private Map<String, LetterInventory> wordMap;
    private List<String> dict;


    // takes List<String> list parameter and uses it as a dictionary of
    // possible words to create an anagram with.  
    public AnagramSolver(List<String> list) {
        wordMap = new HashMap<>();
        for (String word : list) 
            wordMap.put(word, new LetterInventory(word));
        this.dict = list; //get rid of this!
    }


    // takes inputs String s and int max. prints all possible anagrams of 
    // s that can be made from the user provided dictionary that are less 
    // or equal to max words in length. if max is zero, allows any length 
    // anagram to be printed. if max < 0, throws IllegalArgumentException.
    public void print(String s, int max) {
        if (max < 0)
            throw new IllegalArgumentException();
        List<String> possibleWords = relevantWords(s);
        if (max == 0)
            max = possibleWords.size();
        print(new LetterInventory(s), max, possibleWords, new Stack<>());

    }

    // takes parameters: 
    //   LetterInventory currInv - the LetterInventory we are currently finding anagrams for, 
    //   int max - the maximum number of words allowed in for an anagram, 
    //   List<String> words - the words that could possibly be part of an anagram for currInv
    //   Stack<String> considering - the current anagram being considered
    private void print(LetterInventory currInv, int max, List<String> words, Stack<String> considering) {
        if (currInv != null){
            if (currInv.size() == 0 && max >= considering.size()) 
                System.out.println(considering);
            else {
                for (String word : words) {
                    considering.push(word);
                    print(currInv.subtract(wordMap.get(word)), max, words, considering); 
                    considering.pop();
                }
            }
        }
    }

    // takes parameter String s and finds all of the words in the our dictionary that
    // could possibly be in an anagram for s. 
    private List<String> relevantWords(String s){
        List<String> output = new ArrayList<>();
        LetterInventory inputInventory = new LetterInventory(s);
        for (String key : this.dict){
            LetterInventory currentInventory = wordMap.get(key);
            if (inputInventory.subtract(currentInventory) != null) 
                output.add(key);
        }
        return output;
    }
}

