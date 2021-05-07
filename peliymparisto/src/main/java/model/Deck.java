/** =============================== Javadoc ====================================
* Class contains a number of card objects that can be used in games. Has basic
* methods needed for shuffling
* @author Jere Lampola
* @version 1.1 2021-05-07
*/

package model;

public class Deck {
  /* ============================= Variables ================================ */
  private int    size         = 52;
  private Card[] deck         = new Card[size];
  private int    nextIndex    = 0;
  private int    shuffleSwaps = 10000;
  
  /* ============================ Constructors ============================== */
  /**
   * Constructor, the deck is filled with cards during creation.
   */
  public Deck(){
    fillDeckWithCards();
  }
  
  /* =========================== Public methods ============================= */
  /**
   * Method gives the card object at the next index
   * @return next card in the deck
   */
  public Card nextCard(){
    return deck[getNextIndex()];
  }
  
  /**
   * Method for shuffling the card array.
   */
  public void shuffle(){
    for(int i=0;i<shuffleSwaps;i++){
      swapTwoCards();
    }
  }
  
  /* ========================== Private methods ============================= */
  /**
   * Fills the deck, number of cards depends on the size variable
   */
  private void fillDeckWithCards(){
    for(int i=0;i<size;i++){
      deck[i] = new Card(i/13%4,i%13+1);
    }
  }

  /**
   * Increases the current index and returns it. Index will be limited to size
   * with modulo operator.
   * @return
   */
  private int getNextIndex(){
    return nextIndex++%size; // Overflow on index (2^32 / 2 + 1)...
  }

  /**
   * Takes two cards randomly in the deck and changes their places head to head.
   */
  private void swapTwoCards(){
    int[]  indexes   = getTwoUniqueRandomNumbers(0, size - 1);
    Card temporary   = deck[indexes[0]];
    deck[indexes[0]] = deck[indexes[1]];
    deck[indexes[1]] = temporary;
  }
  
  /**
   * A method for generating two unique random numbers
   * @param min : Lower bound for random numbers
   * @param max : Upper bound for random numbers
   * @return integer array containing the two numbers
   */
  private int[] getTwoUniqueRandomNumbers(int min, int max){
    int firstRandom  = getRandomNumber(min, max);
    int secondRandom = firstRandom;
    while(secondRandom == firstRandom && min != max){
      secondRandom = getRandomNumber(min, max);
    }
    int[] twoRandoms = {firstRandom, secondRandom};
    return twoRandoms;
  }
  
  /**
   * Generates a random within the limits
   * @param min : lower limit
   * @param max : upper limit
   * @return the random integer
   */
  private int getRandomNumber(int min, int max){
    return (int)(Math.random()*(max-min+1)+min);
  }
}
