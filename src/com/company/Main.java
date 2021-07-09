package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Deck playingDeck = generateDeck();

    // Create a deck for the player
        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();

        double playerMoney = 100.00;

        Scanner userInput = new Scanner(System.in);


        // Game Loop
        while(playerMoney > 0){
            // Play on!
            //Take the player bet
            System.out.println("You have $" + playerMoney + ", how much would you like to bet?");
            double playerBet = userInput.nextDouble();

            if(playerBet > playerMoney){
                System.out.println("You cannot bet more than you have. Please leave.");
                break;
            }

            boolean endRound = false;

            // Start Dealing
            // PLayer gets two cards
            playerDeck.draw(playingDeck);
            playerDeck.draw(playingDeck);

            // Dealer gets two cards
            dealerDeck.draw(playingDeck);
            dealerDeck.draw(playingDeck);

            while(true) {
                System.out.println("Your hand: ");
                System.out.println(playerDeck.toString());
                //cardsValue() returns the total value of the two cards in hand
                System.out.println("Your hand is valued at: " + playerDeck.cardsValue());

                System.out.println("================");

                // Display Dealer Hand
                // Only show the first card
                System.out.println("Dealer hand: " + dealerDeck.getCard(0).toString() + "[Hidden]");

                // What does the player want to do?
                System.out.println("Would you like to (1)HIT or (2) STAND?");
                int response = userInput.nextInt();

                // They Hit
                if (response == 1) {
                    playerDeck.draw(playingDeck);
                    // get the value of the last card
                    System.out.println("You draw a: " + playerDeck.getCard(playerDeck.getDeckSize() - 1).toString());

                    // Bust if > 21
                    if (playerDeck.cardsValue() > 21) {
                        System.out.println("Bust. Currently valued at: " + playerDeck.cardsValue());
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }
                }

                if (response == 2) {
                    break;
                }
            }

                // Reveal Dealer Cards
                System.out.println("Dealer Cards: " + dealerDeck.toString());
                //See if dealer has more points than player
                if(dealerDeck.cardsValue() > playerDeck.cardsValue() && !endRound){
                    System.out.println("Dealer beats you!");
                    playerMoney -= playerBet;
                    endRound = true;
                }

                // Dealer draws at 16, stand at 17
                while(dealerDeck.cardsValue() < 17 && endRound == false){
                    dealerDeck.draw(playingDeck);
                    System.out.println("Dealer draws: " + dealerDeck.getCard(dealerDeck.getDeckSize() -1).toString());
                }

                //Display Total Value for Dealer
                System.out.println("Dealer hand is valued at: " + dealerDeck.cardsValue());
                // Determine if dealer busted
                if(dealerDeck.cardsValue() > 21 && !endRound){
                    System.out.println("Dealer busts!, You win.");
                    playerMoney += playerBet;
                    endRound = true;
                }


                // Determine if push
                if(playerDeck.cardsValue() == dealerDeck.cardsValue() && endRound == false){
                    System.out.println("Push");
                    endRound = true;
                }

                if(playerDeck.cardsValue() > dealerDeck.cardsValue() && endRound == false){
                    System.out.println("You win the hand!");
                    playerMoney += playerBet;
                } else if(!endRound){
                    System.out.println("You lost the hand.");
                    playerMoney -= playerBet;
                }

                reset(playerDeck, dealerDeck, playingDeck);

                System.out.println("End of hand.");
            }

        System.out.println("Game over!, You are out of money");

        }

        public static Deck generateDeck(){
            System.out.println("Welcome to Blackjack!");

            Deck playingDeck = new Deck();
            playingDeck.createFullDeck();
            playingDeck.shuffle();
            return playingDeck;
        }

        public static void reset(Deck playerDeck, Deck dealerDeck, Deck playingDeck){
            playerDeck.resetDeck(playingDeck);
            dealerDeck.resetDeck(playingDeck);
        }



}
