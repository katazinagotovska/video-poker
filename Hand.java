/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videopoker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author katazina
 */
public class Hand implements Comparable {

    private ArrayList<Card> handCards = new ArrayList<Card>();

    public Hand(ArrayList<String> handCardsArr) {
        for (String handCardStr : handCardsArr) {
            handCards.add(new Card(handCardStr));
        }
        Collections.sort(handCards, Collections.reverseOrder());
    }

    public void setHandCards(ArrayList<Card> handCards) {
        this.handCards = handCards;
    }

    public ArrayList<Card> getHandCards() {
        return handCards;
    }

    private boolean isRoyalFlash() {
        List<CardValue> royaltyList = Arrays.asList(CardValue.TEN, CardValue.JACK, CardValue.QUEEN, CardValue.KING, CardValue.ACE);
        String currentSuit = "";
        for (Card currentCard : this.handCards) {
            if (currentSuit.equals("")) {
                currentSuit = currentCard.getSuit();
            } else if (!currentSuit.equals(currentCard.getSuit())) {
                return false;
            }
        }
        return (handCards.containsAll(royaltyList));
    }

    private boolean isStraightFlush() {

        return this.isStraight() && this.isFlush();
    }

    private boolean isStraight() {
        Integer currentValue = 0;
        for (Card currentCard : this.handCards) {
            if (currentValue == 0) {
                currentValue = currentCard.getValue().getValue();
            } else {
                if (currentValue - 1 != currentCard.getValue().getValue()) {
                    return false;
                }
                currentValue--;  // since our array has been sorted in Hand constructor each next card should have value one bigger or it is not straight flash            
            }
        }
        return true;
    }

    private boolean isFlush() {
        String currentSuit = "";
        for (Card currentCard : this.handCards) {
            if (currentSuit.equals("")) {
                currentSuit = currentCard.getSuit();
            } else if (!currentSuit.equals(currentCard.getSuit())) {
                return false;
            }
        }
        return true;
    }

    private boolean isFullHouse() {

        return this.isThree() && this.isPair();
    }

    private boolean isFour() {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (Card currentCard : this.handCards) {
            int currentCardValue = currentCard.getValue().getValue();

            if (hashMap.get(currentCardValue) == null) {
                hashMap.put(currentCardValue, 0);
            }

            if (hashMap.get(currentCardValue) + 1 == 4) {
                return true;
            }
            hashMap.put(currentCardValue, hashMap.get(currentCardValue) + 1);
            if (hashMap.size() > 2) // this meens we have third different card out of five
            {
                return false;
            }
        }
        return false;
    }

    private boolean isThree() {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (Card currentCard : this.handCards) {
            int currentCardValue = currentCard.getValue().getValue();

            if (hashMap.get(currentCardValue) == null) {
                hashMap.put(currentCardValue, 0);
            }

            if (hashMap.get(currentCardValue) + 1 == 3) {
                return true;
            }
            hashMap.put(currentCardValue, hashMap.get(currentCardValue) + 1);
            if (hashMap.size() > 3) // this meens we have fourth different card out of five
            {
                return false;
            }
        }
        return false;
    }

    private boolean isTwoPairs() {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        CardValue pairRank = CardValue.UNKNOWN;
        CardValue pair2Rank = CardValue.UNKNOWN;
        for (Card currentCard : this.handCards) {
            int currentCardValue = currentCard.getValue().getValue();
            if (hashMap.get(currentCardValue) == null) {
                hashMap.put(currentCardValue, 0);
            }

            if (hashMap.get(currentCard.getValue().getValue()) + 1 == 2) {
                if (pairRank.getValue() == 0) {
                    pairRank = currentCard.getValue();
                } else // this means we have two pairs in one hand (or even three and two, of course)
                {
                    pair2Rank = currentCard.getValue();
                }
            }
            hashMap.put(currentCard.getValue().getValue(), hashMap.get(currentCard.getValue().getValue()) + 1);
        }

        if (pairRank.getValue() != 0 && hashMap.get(pairRank.getValue()) > 2) //   this means three - not two pairs - not this case
        {
            return false;
        }

        if (pair2Rank.getValue() != 0 && hashMap.get(pair2Rank.getValue()) > 2) // this means three - not two pairs - not this case
        {
            return false;
        }

        if (pair2Rank.getValue() == 0 && pair2Rank.getValue() == 0) {
            return false;
        }

        return true;
    }

    private boolean isPair() {
        List<CardValue> royaltyList = Arrays.asList(CardValue.JACK, CardValue.QUEEN, CardValue.KING, CardValue.ACE);
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        CardValue pairRank = CardValue.UNKNOWN;
        for (Card currentCard : this.handCards) {
            int currentCardValue = currentCard.getValue().getValue();
            if(currentCardValue<CardValue.JACK.getValue()){
                continue;
            }
            if (hashMap.get(currentCardValue) == null) {
                hashMap.put(currentCardValue, 0);
            }

            if (hashMap.get(currentCard.getValue().getValue()) + 1 == 2) {
                if (pairRank.getValue() == 0) {
                    pairRank = currentCard.getValue();
                } else // this means we have two pairs in one hand (or even three and two, of course)
                {
                    return false;
                }
            }
            hashMap.put(currentCard.getValue().getValue(), hashMap.get(currentCard.getValue().getValue()) + 1);
        }

        if (pairRank.getValue() > 0 && hashMap.get(pairRank.getValue()) > 2) //   this means three - not pair
        {
            return false;
        }

        return (pairRank.getValue() > 0);
    }

    public int getGift() {

        if (this.isRoyalFlash()) {
            return HandsRanking.royalFlush.getValue();
        }

        if (this.isStraightFlush()) {
            return HandsRanking.straightFlush.getValue();
        }
        if (this.isFour()) {
            return HandsRanking.fourOfAKind.getValue();
        }
        if (this.isFullHouse()) {
            return HandsRanking.fullHouse.getValue();
        }
        if (this.isFlush()) {
            return HandsRanking.Flush.getValue();
        }
        if (this.isStraight()) {
            return HandsRanking.Straight.getValue();
        }
        if (this.isThree()) {
            return HandsRanking.threeOfAKind.getValue();
        }
        if (this.isTwoPairs()) {
            return HandsRanking.twoPairs.getValue();
        }
        if (this.isPair()) {
            return HandsRanking.Jacks.getValue();
        }

        return HandsRanking.UNKNOWN.getValue();
    }

    @Override
    public int compareTo(Object otherHand) {
        int consolidatedValue = (((Hand) otherHand).getGift());
        /* For Ascending order*/
        return this.getGift() - consolidatedValue;
    }

    public String ToString() {
        StringBuilder sb = new StringBuilder();
        for (Card s : this.handCards) {
            sb.append(s);
            sb.append("\t");
        }
//       System.out.println(sb.toString());
        return sb.toString();
    }

    public ArrayList<Card> getHand() {
        return handCards;
    }

    public void cardsChanger(int n, CardsDeck deck) {
        handCards.set(n - 1, new Card (deck.getCard()));
    }

}
