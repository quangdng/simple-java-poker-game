/**
 * PokerHand.java
 * Name: Quang Nguyen Dang
 * Login ID: dangn1
 * This class is responsible to provide classifications and convenient methods to 
 * classify a poker hand and compare between two hands.
 */

import java.util.ArrayList;
import java.util.Collections;

public enum PokerHand {

	/**
	 * The purpose of this class is to hold information about 9 classifications
	 * of poker hand, their orders (based on classification order, the higher the
	 * value is, the higher the classification is) and support main program.
	 * 
	 * This class provide two main methods to support main program:
	 * 
	 * 		1. checkHandClassification to return the actual classification of a
	 * 		player's hand.
	 * 
	 * 		2. compareTwoHands to determine the result of win or draw among two 
	 * 		poker hands.
	 * 
	 * The class assumes that the player's raw hand (array of cards generated
	 * from command line input) that it takes to process is sorted in descending
	 * order. Therefore, to make sure that all static methods in this class work
	 * properly, the given player's raw hand has to be sorted in descending
	 * order.
	 * 
	 * Additionally, there are two static variables that this class holds to
	 * support its methods:
	 * 
	 * 		1. Extra information: to support classification checking and comparing 
	 * 		by holding a number of ranks of a player's poker hand depending on
	 * 		actual hand classification of that player. The logic of extra information
	 * 		implemented in this class is to put the more decisive values (higher
	 * 		cards, rank of set, higher rank of pairs etc) first and they are sorted
	 * 		based on their importance (for example: rank of set is put before rank of
	 * 		pairs).
	 * 
	 * 		2. Player description: also support classification checking by
	 * 		determining player's hand description depending on actual hand
	 * 		classification of that player.
	 */

	HIGH_CARD(1), ONE_PAIR(2), TWO_PAIR(3), THREE_OF_A_KIND(4), STRAIGHT(5), FLUSH(
			6), FULL_HOUSE(7), FOUR_OF_A_KIND(8), STRAIGHT_FLUSH(9);

	// Instance variable
	private int classificationOrder; // Classification order

	// Static variables
	private static Rank[] extraInfo; // Extra information
	private static String playerDescription; // Description

	// Constructor
	/**
	 * @param classificationOrder Order of a hand classification
	 */
	private PokerHand(int classificationOrder) {
		this.classificationOrder = classificationOrder;
	}

	// Getter
	/**
	 * @return Order of a hand classification
	 */
	public int getClassificationOrder() {
		return this.classificationOrder;
	}

	// Static methods
	/**
	 * This method checks the classification of a player's raw hand, determines 
	 * the appropriate extra information needed to compare between two player's 
	 * poker hand and the description of a player's poker hand.
	 * @param p Player object
	 */
	public static void checkHandClassification(Player p) {
		// Store player's raw hand
		Card[] playerRawHand = p.getPlayerRawHand();

		// Store n-of-a-kind cards (if available)
		ArrayList<NOfAKind> checkNOfAKind = PokerHand
				.checkNOfAKind(playerRawHand);

		// Check straight flush
		if (PokerHand.checkStraightHand(playerRawHand)
				&& PokerHand.checkFlushHand(playerRawHand)) {

			// Set player hand
			p.setPlayerHand(PokerHand.STRAIGHT_FLUSH);

			// Extra information
			extraInfo = PokerHand.getRankArray(playerRawHand);
			p.setExtraInfo(extraInfo);

			// Set player's hand description
			playerDescription = "Player " + Integer.toString(p.getPlayerNo())
					+ ": " + extraInfo[0] + "-high straight flush";
			p.setDescription(playerDescription);

		}

		// Check four of a kind
		else if (checkNOfAKind.size() == 1
				&& checkNOfAKind.get(0).getDuplicateTimes() == 4) {

			p.setPlayerHand(PokerHand.FOUR_OF_A_KIND);

			p.setExtraInfo(extraInfo);

			playerDescription = "Player " + Integer.toString(p.getPlayerNo())
					+ ": " + "Four " + extraInfo[0] + "s";
			p.setDescription(playerDescription);

		}

		// Check full house
		else if (checkNOfAKind.size() == 2
				&& (checkNOfAKind.get(0).getDuplicateTimes() + checkNOfAKind
						.get(1).getDuplicateTimes()) == 5) {

			p.setPlayerHand(PokerHand.FULL_HOUSE);

			p.setExtraInfo(extraInfo);

			playerDescription = "Player " + Integer.toString(p.getPlayerNo())
					+ ": " + extraInfo[0] + "s full of " + extraInfo[1] + "s";
			p.setDescription(playerDescription);

		}

		// Check flush
		else if (PokerHand.checkFlushHand(playerRawHand)) {

			p.setPlayerHand(PokerHand.FLUSH);

			extraInfo = PokerHand.getRankArray(playerRawHand);
			p.setExtraInfo(extraInfo);

			playerDescription = "Player " + Integer.toString(p.getPlayerNo())
					+ ": " + extraInfo[0] + "-high flush";
			p.setDescription(playerDescription);

		}

		// Check straight
		else if (PokerHand.checkStraightHand(playerRawHand)) {

			p.setPlayerHand(PokerHand.STRAIGHT);

			extraInfo = PokerHand.getRankArray(playerRawHand);
			p.setExtraInfo(extraInfo);

			playerDescription = "Player " + Integer.toString(p.getPlayerNo())
					+ ": " + extraInfo[0] + "-high straight";
			p.setDescription(playerDescription);

		}

		// Check three of a kind
		else if (checkNOfAKind.size() == 1
				&& (checkNOfAKind.get(0).getDuplicateTimes() == 3)) {

			p.setPlayerHand(PokerHand.THREE_OF_A_KIND);

			p.setExtraInfo(extraInfo);

			playerDescription = "Player " + Integer.toString(p.getPlayerNo())
					+ ": Three " + extraInfo[0] + "s";
			p.setDescription(playerDescription);

		}

		// Check two pair
		else if (checkNOfAKind.size() == 2
				&& (checkNOfAKind.get(0).getDuplicateTimes() + checkNOfAKind
						.get(1).getDuplicateTimes()) == 4) {

			p.setPlayerHand(PokerHand.TWO_PAIR);

			p.setExtraInfo(extraInfo);

			playerDescription = "Player " + Integer.toString(p.getPlayerNo())
					+ ": " + extraInfo[0] + "s over " + extraInfo[1] + "s";
			p.setDescription(playerDescription);

		}

		// Check one pair
		else if (checkNOfAKind.size() == 1
				&& checkNOfAKind.get(0).getDuplicateTimes() == 2) {

			p.setPlayerHand(PokerHand.ONE_PAIR);

			p.setExtraInfo(extraInfo);

			playerDescription = "Player " + Integer.toString(p.getPlayerNo())
					+ ": Pair of " + extraInfo[0] + "s";
			p.setDescription(playerDescription);

		}

		// High card
		else {

			p.setPlayerHand(PokerHand.HIGH_CARD);

			extraInfo = PokerHand.getRankArray(playerRawHand);
			p.setExtraInfo(extraInfo);

			playerDescription = "Player " + Integer.toString(p.getPlayerNo())
					+ ": " + extraInfo[0] + "-high";
			p.setDescription(playerDescription);

		}
	}

	/**
	 * This method compares two poker hand to determine the winner among the
	 * two by first comparing their classification order and then the extra
	 * information if necessary. 
	 * @param p1 First player object
	 * @param p2 Second player object
	 * @return Null if two players draw or the winner player's object
	 */
	public static Player compareTwoHands(Player p1, Player p2) {	
		// First compare the classification order of two player's poker hand
		if (p1.getPlayerHand().getClassificationOrder() > 
				p2.getPlayerHand().getClassificationOrder()) {
			return p1;
		} else if (p1.getPlayerHand().getClassificationOrder() < 
				p2.getPlayerHand().getClassificationOrder()) {
			return p2;
		} else {

			// If two classification orders are same, compare the value 
			// inside extra information array
			Player winner = null;

			for (int i = 0; i < p1.getExtraInfo().length; i++) {
				if (p1.getExtraInfo()[i] != p2.getExtraInfo()[i]) {
					
					int rankOrderOfP1 = p1.getExtraInfo()[i].getRankOrder();
					int rankOrderOfP2 = p2.getExtraInfo()[i].getRankOrder();
					
					if (Math.max(rankOrderOfP1, rankOrderOfP2) == rankOrderOfP1) {
						winner = p1;
						break;
					} else {
						winner = p2;
						break;
					}
				}
			}
			
			// Return null if two players draw or the winner
			if (winner != null) {
				return winner;
			} else {
				return null;
			}
		}
	}

	/**
	 * This method checks whether a player's raw hand is straight or not.
	 * @param hand Array of cards in a hand
	 * @return true if a player's raw hand is straight and false if not
	 */
	public static boolean checkStraightHand(Card[] hand) {
		// Check whether the rank of cards in the hand are in consecutive order
		// or not
		for (int i = 0; i < hand.length - 1; i++) {
			if (Math.abs(hand[i + 1].getRank().getRankOrder()
					- hand[i].getRank().getRankOrder()) != 1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This method checks whether a player's raw hand is flush or not.
	 * @param hand Array of cards in a hand
	 * @return true if a player's raw hand is flush and false if not
	 */
	public static boolean checkFlushHand(Card[] hand) {
		// Check whether the suit of cards in the hand are the same or not
		for (int i = 1; i < hand.length; i++) {
			if (hand[0].getSuit() != hand[i].getSuit()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This method checks a player's raw hand to find out any duplication
	 * of card(s) (based on card rank only) in this hand. From that, we can 
	 * determine whether that/those duplication(s) mean(s) pair(s)/three of 
	 * a kind/four of a kind. Also, this method sets static extra information
	 * required for classification checking and comparing.
	 * @param hand A player's raw hand
	 * @return An ArrayList (of NOfAKind type) contains duplicated card(s)
	 * and how many times it/they is/are duplicated.
	 */
	public static ArrayList<NOfAKind> checkNOfAKind(Card[] hand) {
		// To store found duplicated cards in the given hand to avoid
		// determining those cards again
		ArrayList<Rank> duplicateList = new ArrayList<Rank>();

		// To store found non-duplicated cards in the given hand to separate
		// duplicated and non-duplicated cards to determine extra information
		// for classification checking and comparing.
		ArrayList<Rank> nonDuplicateList = new ArrayList<Rank>();

		// To store duplicated card(s) and how many times it/they is/are 
		// duplicated
		ArrayList<NOfAKind> finalList = new ArrayList<NOfAKind>();

		// Check the duplication of card(s) in given player's raw hand and
		// assign to final list
		for (int i = 0; i < hand.length; i++) {
			int duplicateTimes = PokerHand.getDuplicateTimes(hand, hand[i]);
			
			// Check if cards appear more than one and not in duplicate list yet
			if ( duplicateTimes > 1 
					&& !duplicateList.contains(hand[i].getRank())) {
				duplicateList.add(hand[i].getRank());
				finalList.add(new NOfAKind(hand[i].getRank(), duplicateTimes));
			}
			// Fulfil non duplicate list with cards appear only once
			else  {
				nonDuplicateList.add(hand[i].getRank());
			}
		}
		
		// Sort the final list based on duplicate times of card
		Collections.sort(finalList);

		// Get duplicate list from sorted final list
		duplicateList.clear();
		for (NOfAKind nOfAKind : finalList) {
			duplicateList.add(nOfAKind.getCardRank());
		}

		// Combine the duplicated list with non-duplicated list
		// to provide the extra information needed to n-of-a-kind
		// classification. The whole idea of this operation is to put
		// the duplicated card first (means rank of n-of-a-kind first)
		// to compare and then non-duplicated will be compared later
		// if necessary
		duplicateList.addAll(nonDuplicateList);
		
		extraInfo = PokerHand.convert(duplicateList);

		return finalList;
	}

	/**
	 * This method gets all card ranks from a given cards array and put them
	 * into another Rank array.
	 * @param hand Array of cards in a hand
	 * @return An array of ranks from given cards array
	 */
	public static Rank[] getRankArray(Card[] hand) {
		int s = hand.length;
		Rank[] rankArray = new Rank[s];
		for (int i = 0; i < s; i++) {
			rankArray[i] = hand[i].getRank();
		}
		return rankArray;
	}

	
	/**
	 * This method is used to convert a Rank ArrayList to a Rank array
	 * @param rankList A Rank ArrayList
	 * @return a Rank array
	 */
	public static Rank[] convert(ArrayList<Rank> rankList) {
		int s = rankList.size();
		Rank[] rankArray = new Rank[s];
		for (int i = 0; i < s; i++) {
			rankArray[i] = rankList.get(i);
		}
		return rankArray;
	}
	
	/**
	 * This method returns how many times an card appear in an array of
	 * cards.
	 * @param hand Array of cards in a hand
	 * @param card A card object
	 * @return
	 */
	private static int getDuplicateTimes(Card[] hand, Card card) {
		int duplicateTimes = 0;
		for (int i = 0; i < hand.length; i++) {
			if (hand[i].getRank() == card.getRank()) {
				duplicateTimes ++;
			}
		}
		return duplicateTimes;
	}
}
