// Name       : Elijah Melton
// Section    : CSE 143 AE
// TA         : James Hu
// Assignment : HW1 LetterInventory
//
// LetterInventory stores a count of each letter in the alphabet for a given string. 
// Also includes several methods to edit or view the count of a specific character, as well as
// methods to create new LetterInventories from two existing inventories.


public class LetterInventory {
   public final int ALPHA_LENGTH = 26; //length of the English Alphabet
   private int[] counts; //holds the counts of each 26 possible letters for the given string
   private int size; //total number of valid letters in inventory
   
   //constructs new LetterInventory object with input String s. Initializes all fields.
   public LetterInventory(String s) {
      this.counts = new int[ALPHA_LENGTH];
      this.size = 0;
      for (int i = 0; i < s.length(); i++) {
         char c = s.charAt(i);
         if(Character.isLetter(c)) {
            counts[charToAlphaIndex(c)]++;
            this.size++;
         }
      }
   }
   
   //given a parameter character c, this method returns the frequency of this letter
   //in the inventory. If c isn't a letter, throws IllegalArgumentException. 
   public int get(char c) {
      if (!Character.isLetter(c)) throw new IllegalArgumentException();
      return counts[charToAlphaIndex(Character.toLowerCase(c))];
   }
   
   //given parameters char letter, int value, this method sets the count of letter to value.
   //if letter isnt in the English alphabet, throws IllegalArgumentException.
   public void set(char letter, int value) {
      if (!Character.isLetter(letter)) throw new IllegalArgumentException();
      this.size = this.size - this.counts[charToAlphaIndex(letter)] + value;
      this.counts[charToAlphaIndex(letter)] = value;
   }
   
   //returns the int size field that tracks the number of letters in the inventory
   public int size() {
      return this.size;
   }
   
   //returns true if the LetterInventory is empty (no letters), and false otherwise.
   public boolean isEmpty() {
      return size == 0;
   }
   
   // returns the String representation of LetterInventory which consists of all the 
   // letters in the inventory surrounded by brackets ([]).
   public String toString() {
      String out = "[";
      for (int i = 0; i < ALPHA_LENGTH; i++) {
         char currLetter = alphaIndexToChar(i);
         for (int j = 0; j < counts[i]; j++) {
            out+= "" + currLetter;
         }
      }
      return out + "]";
   }
   
   // given parameter LetterInventory other, this method returns a new LetterInventory that 
   // is the result of adding each element of this and other LetterInventory and storing it in
   // the new LetterInventory.
   public LetterInventory add(LetterInventory other) {
      LetterInventory output = new LetterInventory("");
      for (int i = 0; i < ALPHA_LENGTH; i++) {
         output.set(alphaIndexToChar(i), this.counts[i] + other.get(alphaIndexToChar(i)));
      }
      
      return output;
   }
   
   // given parameter LetterInventory other, this method returns a new LetterInventory that 
   // is the result of subtracting each element of other from this LetterInventory and storing it 
   // in the new LetterInventory. If any resulting element of the output would be <0, returns null.
   public LetterInventory subtract(LetterInventory other) {
      LetterInventory output = new LetterInventory("");
      for (int i = 0; i < ALPHA_LENGTH; i++) {
         int result = this.counts[i] - other.get(alphaIndexToChar(i));
         if (result < 0) return null;
         output.set(alphaIndexToChar(i), result);
      }
      return output;
   }
   
   /*------------------------------------------------------/
   PLEASE DONT MARK ME DOWN FOR HAVING SINGLE LINE METHODS
   /------------------------------------------------------*/
   
   // given parameter char c, returns the int corresponding to the zero-indexed alphabet.
   // c must be part of the English alphabet for the mthod to work as intended. 
   private int charToAlphaIndex(char c) {
      return (int) (Character.toLowerCase(c)) - (int) 'a';
   }
   
   // given parameter int i, returns char corresponding to the character in the alphabet at that
   // index starting from 0 (ie. a:0, b:1...z:25). 0 <= i < 26 for the method to work as intended. 
   private char alphaIndexToChar(int i) {
      return (char) (i + (int) 'a');
   }
}