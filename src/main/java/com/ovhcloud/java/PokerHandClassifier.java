package com.ovhcloud.java;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PokerHandClassifier {
    public static void main(String[] args) {
        String pokerHand = "2H 3H 4H 5H 6H"; // Replace with your poker hand

        String result = classifyPokerHand(pokerHand);
        System.out.println("Poker Hand: " + pokerHand);
        System.out.println("Classification: " + result);
    }

    public static String classifyPokerHand(String pokerHand) {
        String[] cards = pokerHand.split(" ");
        Arrays.sort(cards);

        if (isStraightFlush(cards)) {
            return "Straight Flush";
        } else if (isFourOfAKind(cards)) {
            return "Four of a Kind";
        } else if (isFullHouse(cards)) {
            return "Full House";
        } else if (isFlush(cards)) {
            return "Flush";
        } else if (isStraight(cards)) {
            return "Straight";
        } else if (isThreeOfAKind(cards)) {
            return "Three of a Kind";
        } else if (isTwoPairs(cards)) {
            return "Two Pairs";
        } else if (isOnePair(cards)) {
            return "One Pair";
        } else {
            return "High Card";
        }
    }

    private static boolean isStraightFlush(String[] cards) {
        return isStraight(cards) && isFlush(cards);
    }

    private static boolean isFourOfAKind(String[] cards) {
        return isNOfAKind(cards, 4);
    }

    private static boolean isFullHouse(String[] cards) {
        return isNOfAKind(cards, 3) && isNOfAKind(cards, 2);
    }

    private static boolean isFlush(String[] cards) {
        char suit = cards[0].charAt(1);
        for (String card : cards) {
            if (card.charAt(1) != suit) {
                return false;
            }
        }
        return true;
    }

    private static boolean isStraight(String[] cards) {
        for (int i = 1; i < cards.length; i++) {
            if (getCardValue(cards[i]) - getCardValue(cards[i - 1]) != 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean isThreeOfAKind(String[] cards) {
        return isNOfAKind(cards, 3);
    }

    private static boolean isTwoPairs(String[] cards) {
        int pairCount = 0;
        Map<Character, Integer> valueCount = new HashMap<>();

        for (String card : cards) {
            char value = card.charAt(0);
            valueCount.put(value, valueCount.getOrDefault(value, 0) + 1);
            if (valueCount.get(value) == 2) {
                pairCount++;
            }
        }

        return pairCount == 2;
    }

    private static boolean isOnePair(String[] cards) {
        return isNOfAKind(cards, 2);
    }

    private static boolean isNOfAKind(String[] cards, int n) {
        Map<Character, Integer> valueCount = new HashMap<>();

        for (String card : cards) {
            char value = card.charAt(0);
            valueCount.put(value, valueCount.getOrDefault(value, 0) + 1);
            if (valueCount.get(value) == n) {
                return true;
            }
        }

        return false;
    }

    private static int getCardValue(String card) {
        char value = card.charAt(0);
        if (Character.isDigit(value)) {
            return Character.getNumericValue(value);
        } else {
            switch (value) {
                case 'T':
                    return 10;
                case 'J':
                    return 11;
                case 'Q':
                    return 12;
                case 'K':
                    return 13;
                case 'A':
                    return 14;
                default:
                    throw new IllegalArgumentException("Invalid card value: " + value);
            }
        }
    }
}

