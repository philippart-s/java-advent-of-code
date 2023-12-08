package com.ovhcloud.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;
import org.apache.commons.lang3.StringUtils;
import com.ovhcloud.java.util.FileOperations;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "day7", mixinStandardHelpOptions = true)
public class Day7 implements Callable<Integer> {

  private final static Integer FIVE_CARDS = 7;
  private final static Integer FOUR_CARDS = 6;
  private final static Integer FULL_HOUSE = 5;
  private final static Integer THREE_CARDS = 4;
  private final static Integer TWO_PAIRS = 3;
  private final static Integer ONE_PAIR = 2;
  private final static Integer ONE_CARD = 1;

  public static boolean isJocker = false;

  @Option(names = {"-part1"})
  private boolean puzzleOne;

  @Option(names = {"-part2"})
  private boolean puzzleTwo;

  @Override
  public Integer call() throws Exception {
    if (puzzleOne) {
      isJocker = false;
      puzzle1("/input-day7-1.txt");
    }
    if (puzzleTwo) {
      isJocker = true;
      puzzle2("/input-day7-2.txt");
    }

    return 0;
  }

  int puzzle1(String input) {
    int count = 0;

    Scanner scanner = new Scanner(FileOperations.loadInputs(input));

    String currentLine;
    ArrayList<Hand> arrayOfHands = new ArrayList<>();
    while (scanner.hasNextLine()) {
      currentLine = scanner.nextLine();
      arrayOfHands
          .add(new Hand(currentLine.split(" ")[0], Integer.parseInt(currentLine.split(" ")[1])));
    }
    scanner.close();

    arrayOfHands.sort(null);

    for (int i = 0; i < arrayOfHands.size(); i++) {
      count += arrayOfHands.get(i).bid * (i + 1);
    }

    System.out.println("Result: " + count);
    return count;
  }

  int puzzle2(String input) {
    int count = 0;
    Scanner scanner = new Scanner(FileOperations.loadInputs(input));

    String currentLine;
    ArrayList<Hand> arrayOfHands = new ArrayList<>();
    while (scanner.hasNextLine()) {
      currentLine = scanner.nextLine();
      arrayOfHands
          .add(new Hand(currentLine.split(" ")[0], Integer.parseInt(currentLine.split(" ")[1])));
    }
    scanner.close();

    arrayOfHands.sort(null);

    System.out.println("isJocker: " + isJocker);

    for (int i = 0; i < arrayOfHands.size(); i++) {
      // System.out.println("hand: " + arrayOfHands.get(i));
      count += arrayOfHands.get(i).bid * (i + 1);
    }

    System.out.println("Result: " + count);
    return count;

  }

  public static Integer classifyPokerHand(String pokerHand) {
    String[] cards = pokerHand.split("");
    int numberOfJ = StringUtils.countMatches(pokerHand, "J");
    // System.out.println("pokerHand: " + pokerHand);
    // System.out.println("numberOfJ: " + numberOfJ);

    if (isJocker && numberOfJ != 0) {
      if (numberOfJ == 5 || numberOfJ == 4) {
        return FIVE_CARDS;
      }
      if (numberOfJ == 3) {
        if (isOnePair(cards)) {
          return FIVE_CARDS;
        }
        return FOUR_CARDS;
      }

      if (numberOfJ == 2) {
        if (isThreeOfAKind(cards)) {
          return FIVE_CARDS;
        }
        if (isOnePair(cards)) {
          return FOUR_CARDS;
        }
        return THREE_CARDS;
      }

      if (numberOfJ == 1) {
        if (isFourOfAKind(cards)) {
          return FIVE_CARDS;
        }
        if (isThreeOfAKind(cards)) {
          return FOUR_CARDS;
        }
        if (isTwoPairs(cards)) {
          return FULL_HOUSE;
        }
        if (isOnePair(cards)) {
          return THREE_CARDS;
        }
        if (isNumberOfAKind(cards, 1)) {
          return ONE_PAIR;
        }
      }
    }  

    if (isFiveOfAKind(cards)) {
      return FIVE_CARDS;
    } else if (isFourOfAKind(cards)) {
      return FOUR_CARDS;
    } else if (isFullHouse(cards)) {
      return FULL_HOUSE;
    } else if (isThreeOfAKind(cards)) {
      return THREE_CARDS;
    } else if (isTwoPairs(cards)) {
      return TWO_PAIRS;
    } else if (isOnePair(cards)) {
      return ONE_PAIR;
    } else {
      return ONE_CARD;
    }


  }

  private static boolean isFiveOfAKind(String[] cards) {
    return isNumberOfAKind(cards, 5);
  }

  private static boolean isFourOfAKind(String[] cards) {
    return isNumberOfAKind(cards, 4);
  }

  private static boolean isThreeOfAKind(String[] cards) {
    return isNumberOfAKind(cards, 3);
  }

  private static boolean isFullHouse(String[] cards) {
    boolean isAPair = false;
    boolean isABrelan = false;

    Map<Character, Integer> valueCount = new HashMap<>();

    for (String card : cards) {
      char value = card.charAt(0);
      if (value != 'J') {
        valueCount.put(value, valueCount.getOrDefault(value, 0) + 1);
      }
    }

    for (Integer value : valueCount.values()) {
      if (value != 'J') {
        if (value == 2) {
          isAPair = true;
        }
        if (value == 3) {
          isABrelan = true;
        }
        if (isAPair && isABrelan) {
          return true;
        }
      }
    }

    return false;
  }

  private static boolean isTwoPairs(String[] cards) {
    int pairCount = 0;
    Map<Character, Integer> valueCount = new HashMap<>();

    for (String card : cards) {
      char value = card.charAt(0);
      if (value != 'J') {
        valueCount.put(value, valueCount.getOrDefault(value, 0) + 1);
        if (valueCount.get(value) == 2) {
          pairCount++;
        }

      }
    }

    return pairCount == 2;
  }

  private static boolean isOnePair(String[] cards) {
    return isNumberOfAKind(cards, 2);
  }

  private static boolean isNumberOfAKind(String[] cards, int n) {
    Map<Character, Integer> valueCount = new HashMap<>();

    for (String card : cards) {
      char value = card.charAt(0);
      if (value != 'J') {
        valueCount.put(value, valueCount.getOrDefault(value, 0) + 1);
        if (valueCount.get(value) == n) {
          return true;
        }
      }
    }

    return false;
  }

  record Hand(String cards, Integer bid) implements Comparable {

    @Override
    public int compareTo(Object o) {
      Hand handToCompare = (Hand) o;
      if (classifyPokerHand(cards) > classifyPokerHand(handToCompare.cards)) {
        return 1;
      } else if (classifyPokerHand(cards) < classifyPokerHand(handToCompare.cards)) {
        return -1;
      }

      for (int i = 0; i < cards.length(); i++) {
        if (getIntForACard(cards.charAt(i)) > getIntForACard(handToCompare.cards.charAt(i))) {
          return 1;
        } else if (getIntForACard(cards.charAt(i)) < getIntForACard(
            handToCompare.cards.charAt(i))) {
          return -1;
        }
      }

      return 0;
    }

  }

  private static int getIntForACard(char card) {
    switch (card) {
      case 'A':
        return 14;
      case 'K':
        return 13;
      case 'Q':
        return 12;
      case 'J':
        return 0;
      case 'T':
        return 10;
      case '9':
        return 9;
      case '8':
        return 8;
      case '7':
        return 7;
      case '6':
        return 6;
      case '5':
        return 5;
      case '4':
        return 4;
      case '3':
        return 3;
      case '2':
        return 2;
      default:
        return card;
    }
  }

}

