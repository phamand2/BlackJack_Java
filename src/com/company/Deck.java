package com.company;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    ArrayList<Card> cards;

    public Deck(){
        this.cards = new ArrayList<>();
    }

    public void createFullDeck(){

        for(Card.Suit cardSuit : Card.Suit.values()){
            for(Card.Value cardValue : Card.Value.values()){
                this.cards.add(new Card(cardSuit, cardValue));
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(this.cards);
    }

    public void removeCard(int index){
        cards.remove(index);
    }


    public Card getCard(int i){
        return this.cards.get(i);
    }

    public void addCard(Card addCard){
        this.cards.add(addCard);
    }


    // Draws from the deck
    public void draw(Deck comingFrom){
        this.cards.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }

    public void resetDeck(Deck deck){
        int deckSize = this.cards.size();

        for (int i = 0; i < deckSize ; i++) {
            deck.cards.add(this.cards.get(i));
        }

        for (int i = 0; i < deckSize ; i++) {
            this.removeCard(0);
        }
    }





    public int getDeckSize(){
        return cards.size();
    }

    public int cardsValue(){
        int value = 0;
        int aces = 0;

        for (Card card: cards) {

            switch(card.getValue()){
                case ACE: aces++; break;
                case TWO: value+=2; break;
                case THREE: value+=3; break;
                case FOUR: value+=4; break;
                case FIVE: value+=5; break;
                case SIX: value+= 6; break;
                case SEVEN: value+= 7;break;
                case EIGHT: value+= 8; break;
                case NINE: value+= 9; break;
                case TEN:
                case JACK:
                case QUEEN:
                case KING:
                    value+= 10; break;
            }

                for (int i = 0; i < aces ; i++) {
                    if(value <= 10){
                        value+= 11;
                    } else {
                        value += 1;
                    }
            }
        }
                return value;
    }

    @Override
    public String toString() {
      String cardListOutput = "";
      for(Card aCard : this.cards){
          cardListOutput += "\n " + aCard.toString();
      }
      return cardListOutput;
    }
}
