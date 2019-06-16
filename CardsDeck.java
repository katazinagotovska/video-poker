/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videopoker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author katazina
 */
public class CardsDeck {

    private ArrayList<String> cardsDeck = new ArrayList<>(Arrays.asList("2H", "2S", "2C", "2D", "3H", "3S", "3C", "3D", "4H", "4S", "4C", "4D", "5H", "5S", "5C", "5D", "6H", "6S", "6C", "6D", "7H", "7S", "7C", "7D", "8H", "8S", "8C", "8D", "9H", "9S", "9C", "9D", "TH", "TS", "TC", "TD", "JH", "JS", "JC", "JD", "QH", "QS", "QC", "QD", "KH", "KS", "KC", "KD", "AH", "AS", "AC", "AD"));

    public CardsDeck() {
        ArrayList<String> cardsDeckCopy = new ArrayList<>(); 
        cardsDeckCopy = (ArrayList<String>)this.cardsDeck.clone();
        Collections.shuffle(cardsDeck);
    }
    
    public String getCard() {
        String x = "";
        Iterator itr = cardsDeck.iterator();
        if(!itr.hasNext())
        {
           ArrayList<String> cardsDeckCopy = new ArrayList<>(); 
           cardsDeckCopy = (ArrayList<String>)this.cardsDeck.clone();  
           Collections.shuffle(cardsDeck);
                    }
        
            x = (String) itr.next();
            itr.remove();
        
        return x;
    }
    
}
