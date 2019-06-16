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
public class Card  implements Comparable {

    private String card;

    private String suit;

    private CardValue value;

    public String getSuit() {
        return suit;
    }

    public CardValue getValue() {
        return value;
    }

    public Card(String card) {
        this.card = card;
        this.value = convertValue(this.card.substring(0, 1));
        this.suit = this.card.substring(1, 2);
    }

    public CardValue convertValue(String v) {

        switch (v) {
            case "2":
                return CardValue.TWO;
            case "3":
                return CardValue.THREE;
            case "4":
                return CardValue.FOUR;
            case "5":
                return CardValue.FIVE;
            case "6":
                return CardValue.SIX;
            case "7":
                return CardValue.SEVEN;
            case "8":
                return CardValue.EIGHT;
            case "9":
                return CardValue.NINE;
            case "T":
                return CardValue.TEN;
            case "J":
                return CardValue.JACK;
            case "Q":
                return CardValue.QUEEN;
            case "K":
                return CardValue.KING;
            case "A":
                return CardValue.ACE;
            default:
                return null;
        }

    }
   
    
    public String getCard() 
    {
        return card;
    }

    
    @Override
    public String toString() 
    {
        return this.card.toString();
    }

    @Override
    public int compareTo(Object otherCard) {
        int otherCardValue = ((Card)otherCard).getValue().getValue();
        /* For Ascending order*/
        return this.getValue().getValue() - otherCardValue;
    }

}
