/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videopoker;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author katazina
 */
public class VideoPoker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CardsDeck deck = new CardsDeck();
        List<String> list = new ArrayList<>();
        list.add(deck.getCard());
        list.add(deck.getCard());
        list.add(deck.getCard());
        list.add(deck.getCard());
        list.add(deck.getCard());
        Hand hand = new Hand((ArrayList<String>) list);
        System.out.println("Hand " + hand.ToString());
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you like change the cards (Yes=>1; No=>0)");
        int change = 0;
        try {
            change = sc.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Bad number!");
        }
        if (change == 0) {
            System.out.println("Your gift is " + hand.getGift());
        } else {
            int changeCardsCount = 0;
            System.out.println("How many cards do you like change?");
            try {
                changeCardsCount = sc.nextInt();
                if (changeCardsCount<0 || changeCardsCount>5){
                   System.out.println("Bad number! It should be in range [0:5]");
                   return;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Bad number!");
            }
            for (int i = 1; i <= changeCardsCount; i++) {
                int cardIndex = -1;
                if (changeCardsCount < 5) {
                    System.out.println("Choose the card you want to change and press enter");
                    try {
                        cardIndex = sc.nextInt();
                    } catch (InputMismatchException ex) {
                        System.out.println("Bad number!");
                    }
                } else {
                    cardIndex = i;
                }
                hand.cardsChanger(cardIndex, deck);
            }
            System.out.println("New hand " + hand.ToString());
            System.out.println("Your gift is " + hand.getGift());
        }
    }

}
