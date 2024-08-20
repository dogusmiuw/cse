#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Oct  3 23:22:50 2018

@author: kostya
"""

from random import shuffle

class CrazyEights:
    """Create a class to store all methods and objects"""

    def __init__(self):
        self.suits = ['h','d','s','c']
        self.cards = ['2','3','4','5','6','7','8','9','10','J','Q','K','A']
        self.value = [2,3,4,5,6,7,50,9,10,10,10,10,10]
        self.stock = list((x,y) for x in self.suits for y in self.cards)
        self.player_hand = []
        self.dealer_hand = []
        self.discard_pile = 0
        self.new_suit = ''
    
    def deal_cards(self):
        shuffle(self.stock)
        for i in range(7):
            self.player_hand.append(self.stock.pop())
            self.dealer_hand.append(self.stock.pop())
        self.discard_pile = self.stock.pop()
    
    def card_value(self, card):
        return self.value[self.cards.index(card[1])]
    
    def player_discard(self, inpt):
        """
        The function determines whether player's selected card qualifies for
        discard or not
        
        """
        
        if inpt.isdigit() == False:
            return 0
        if int(inpt) > len(self.player_hand):
            print("\nNumber of card entered is greater than number of cards")
            print("Please try again \n")
            return 0
        if self.player_hand[int(inpt)-1][1] == '8':
            self.discard_pile = self.player_hand.pop(int(inpt)-1)
            self.new_suit = ''
            while self.new_suit not in ['h','d','s','c']:
                self.new_suit = input("Please enter new suit: h, d, s, c\n")
            print("\nNew suit is: ", self.new_suit)
            return 1
        if self.new_suit != '':
            if self.player_hand[int(inpt)-1][0] == self.new_suit:
                self.discard_pile = self.player_hand.pop(int(inpt)-1)
                self.new_suit = ''
                return 1
            else:
                print("\nYou need to match new suit")
                print("Please try again\n")
                return 0
        if self.new_suit == '':
            if self.player_hand[int(inpt)-1][0] == self.discard_pile[0] or \
            self.player_hand[int(inpt)-1][1] == self.discard_pile[1]:
                self.discard_pile = self.player_hand.pop(int(inpt)-1)
                return 1
            else:
                print("\nYou need to match discard pile card suit or rank")
                print("Please try again\n")
                return 0
    
    def player_play(self):
        disc = 0
        inpt = ''
        print("\nPlayer's turn")
        
        while disc == 0 and len(self.stock) > 0:
            print("Player's cards: \n", self.player_hand)
            print("Discard pile: ", self.discard_pile)
            inpt = input("Please enter 0 if you need another card or number "\
                              "of card to discard\n")
            if inpt == '0':
                self.player_hand.append(self.stock.pop())
            else:
                disc = self.player_discard(inpt)
        if len(self.player_hand) == 0:
            print("Player is out of cards")
            return
        elif len(self.stock) == 0:
            print("No cards in stock pile")
            return
        else:
            print("------------------------------------------------")
            self.dealer_play()
            
    def dealer_matching(self):
        """
        The function determines whether dealer has a card that would qualify for
        discard or not
        
        """
        if len([card for card in self.dealer_hand if card[1] == '8']) > 0:
            self.discard_pile = [card for card in self.dealer_hand if card[1] == '8'][0]
            self.dealer_hand.remove(self.discard_pile)
            dealer_suits = [card[0] for card in self.dealer_hand]
            self.new_suit = max(set(dealer_suits), key=dealer_suits.count)
            print("\nNew suit is :", self.new_suit)
            return 1
        if self.new_suit != '':
            matching = []
            for card in self.dealer_hand:
                if card[0] == self.new_suit:
                    matching.append(card)
            if len(matching) > 0:
                matching_values = list(map(self.card_value, matching))
                self.discard_pile = matching[matching_values.index(max(matching_values))]
                self.dealer_hand.remove(self.discard_pile)
                self.new_suit = ''
                return 1
            else:
                return 0
        if self.new_suit == '':
            matching = []
            for card in self.dealer_hand:
                if card[0] == self.discard_pile[0] or card[1] == self.discard_pile[1]:
                    matching.append(card)
            if len(matching) > 0:
                matching_values = list(map(self.card_value, matching))
                self.discard_pile = matching[matching_values.index(max(matching_values))]
                self.dealer_hand.remove(self.discard_pile)
                return 1
            else:
                return 0
        
    def dealer_play(self):
         disc = 0
         print("\nDealer's turn")
         
         i = 0
         while disc == 0 and len(self.stock) > 0:
             #print("Dealer's cards: \n", self.dealer_hand)
             #print("Discard pile: ", self.discard_pile)
            
             disc = self.dealer_matching()
             if disc == 0:
                 self.dealer_hand.append(self.stock.pop())
                 i +=1
         print("Dealer drew {} cards".format(i))
         if len(self.dealer_hand) == 0:
             print("Dealer is out of cards")
             return
         elif len(self.stock) == 0:
             print("No cards in stock pile")
             return
         else:
             print("------------------------------------------------")
             self.player_play()
    
    def calc_points(self):
        player_points = 0
        dealer_points = 0
        for card in self.player_hand:
            player_points += self.card_value(card)
        for card in self.dealer_hand:
            dealer_points += self.card_value(card)
        
        print("\nPlayer points: ", player_points)
        print("Dealer points: ", dealer_points)
        if player_points < dealer_points:
            print("Player wins")
        else:
            print("Dealer wins") 
            
    def start(self):
        self.deal_cards()
        self.player_play()
        self.calc_points()


game = CrazyEights()
game.start()













