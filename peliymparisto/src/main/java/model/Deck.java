/** =============================== Javadoc ====================================
* @author Jere Lampola
*/

package model;

public class Deck {
  /* ============================= Variables ================================ */
  private int    size         = 52;
  private int    jokers       = 0;
  private Card[] deck         = new Card[size+1];
  private int    nextIndex    = 0;
  private int    shuffleSwaps = 10000;
  
  /* ============================ Constructors ============================== */
  public Deck(){
    fillDeckWithCards();
  }
  
  /* =========================== Public methods ============================= */
  public Card nextCard(){
    return deck[getNextIndex()];
  }
  
  public void shuffle(){
    for(int i=0;i<shuffleSwaps;i++){
      swapTwoCards();
    }
  }
  
  /* ========================== Private methods ============================= */
  private void fillDeckWithCards(){
    for(int i=0;i<size;i++){
      deck[i+1] = new Card(i/13%4,i%13+1);
    }
  }

  private int getNextIndex(){
    return nextIndex++%size+1; // Overflow on index (2^32 / 2 + 1)...
  }

  private void swapTwoCards(){
    int[]  indexes   = getTwoUniqueRandomNumbers(1, size);
    Card temporary   = deck[indexes[0]];
    deck[indexes[0]] = deck[indexes[1]];
    deck[indexes[1]] = temporary;
  }
  
  private int[] getTwoUniqueRandomNumbers(int min, int max){
    int firstRandom  = getRandomNumber(min, max);
    int secondRandom = firstRandom;
    while(secondRandom == firstRandom && min != max){
      secondRandom = getRandomNumber(min, max);
    }
    int[] twoRandoms = {firstRandom, secondRandom};
    return twoRandoms;
  }
  
  private int getRandomNumber(int min, int max){
    return (int)(Math.random()*(max-min+1)+min);
  }
}
