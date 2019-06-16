/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videopoker;

/**
 *
 * @author katazina
 */
public enum HandsRanking {
    UNKNOWN(0),
    Jacks(1), // Two cards of the same value.
    twoPairs(2), // Two different pairs.
    threeOfAKind(3), // Three cards of the same value.
    Straight(4), // All cards are consecutive values.
    Flush(6), // All cards of the same suit.
    fullHouse(9), // Three of a kind and a pair.
    fourOfAKind(25), // Four cards of the same value.
    straightFlush(50), // All cards are consecutive values of same suit.
    royalFlush(800); // Ten, Jack, Queen, King, Ace, in same suit.
// In the code above we multiply each enum id with 16 in order to create non-overlapping zones for each rank
// So that later numerical representation of rank for pair would never overlap with triplette and so on...
// In other words our numerical representation of  flush will be always bigger than representation of straight    
    
    
    int value;

    HandsRanking(int v) {
      value = v;
    }  
    
    int getValue(){
      return value;
    } 
}
