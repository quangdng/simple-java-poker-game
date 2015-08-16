/**
 * Poker.java
 * Name: Quang Nguyen Dang
 * Login ID: dangn1
 * This class acts as entry point for the Poker program, takes the input from
 * command line arguments and produces player's poker hand description and winner
 * result accordingly.
 */

import java.util.ArrayList;
import java.util.Arrays;

public class Poker {

	/**
	 * The purpose of the program is to simulates traditional famous Poker game 
	 * that we can see in every single casino. Basically, the program takes command
	 * line arguments input and correctly classify a poker hand based on the given
	 * lists of hand generated from command line input. If there are more than one
	 * hand, the program will also decide the result of the game (win or draw).
	 * 
	 * There are several assumptions for the program:
	 * 		
	 * 		1. Each argument in command line input represents one card. Each argument
	 * 		contains 2 characters (not case sensitive) : first is the rank of card,
	 * 		second is the suit of card.
	 * 
	 * 		2. The program expects multiple of 5 input arguments.
	 * 
	 * 		3. Ace is the highest rank.
	 */
	
	// Constant variables
	private static final String[] LIST_OF_RANKS = { "2", "3", "4", "5", "6",
			"7", "8", "9", "T", "J", "Q", "K", "A" };
	private static final String[] LIST_OF_SUITS = { "H", "C", "D", "S" }; 

	// Entry point
	/**
	 * This is the entry point of the program. The method takes command line
	 * arguments and process them.
	 * @param args Command line input arguments
	 */
	public static void main(String[] args) {
		// Check if valid input before processing
		if (Poker.checkHandInput(args)) {
			// Create card array to hold cards generated from hand input
			Card[] cardArray = new Card[args.length];

			// Create player array to hold 5 cards for each player
			Player[] playerArray = new Player[cardArray.length / 5];

			// Generate cards and assign cards to cardArray
			for (int i = 0; i < args.length; i++) {
				String rankChar = args[i].substring(0, 1);

				String suitChar = args[i].substring(1, 2);

				// Match rank & suit character with appropriate rank & suit
				Rank cardRank = Poker.matchRank(rankChar);
				Suit cardSuit = Poker.matchSuit(suitChar);

				// Create a new card base on generated rank & suit
				Card newCard = new Card(cardRank, cardSuit);

				// Assign new card to cardArray
				cardArray[i] = newCard;
			}

			// Assign player number, player's raw hand for each player
			// and put all player object to playerArray
			for (int i = 0; i < args.length / 5; i++) {
				Player player = new Player();

				player.setPlayerNo(i + 1);

				Card[] playerRawHand = Arrays.copyOfRange(cardArray, i * 5,
						(i + 1) * 5);
				
				// Sort player's raw hand in descending order
				Arrays.sort(playerRawHand);
				player.setPlayerRawHand(playerRawHand);
				
				playerArray[i] = player;
			}

			// Check player(s)'s hand classification and print out player(s)'s
			// description
			for (Player p : playerArray) {
				PokerHand.checkHandClassification(p);
				System.out.println(p.getDescription());
			}

			// Compare players's hand and decide game result (if more than 1 player)
			if (playerArray.length > 1) {
				Poker.gameResult(playerArray);
			}
		}
	}

	// Static methods
	/**
	 * This method checks the validity of input taken from command line arguments
	 * and print out error message if necessary. 
	 * @param cmdInput Input taken from command line arguments
	 * @return - true if all input are valid and false if not
	 */
	private static boolean checkHandInput(String[] cmdInput) {
		// Result of the input check
		boolean result = true;

		// Check if hands input is a multiple of five or not
		if (cmdInput.length % 5 != 0) {
			System.out
					.println("Error: wrong number of arguments; must be a multiple of 5");

			result = false;
		}
		else {
			for (String card : cmdInput) {
				String rankChar = card.substring(0, 1);
				
				String suitChar = card.substring(1, 2);

				// Check input validity and print out error message in case
				// of invalid input
				if (!(Arrays.asList(LIST_OF_RANKS).contains(
						rankChar.toUpperCase()) && Arrays.asList(LIST_OF_SUITS)
						.contains(suitChar.toUpperCase()))) {

					System.out.println("Error: invalid card name '" + rankChar
							+ suitChar + "'");

					result = false;
				}
			}	
		}
		return result;
	}

	/**
	 * This method checks the result of the game if there are more than 1 player
	 * and print out the result accordingly.
	 * @param playerArray An array of players in the game
	 */
	private static void gameResult(Player[] playerArray) {
		// Assume the first player is the winner
		Player winner = playerArray[0];

		boolean draw = false;

		// Compare other players and assign the winner and draw boolean based on
		// the comparison
		for (int i = 1; i < playerArray.length; i++) {
			if (PokerHand.compareTwoHands(winner, playerArray[i]) == null) {
				draw = true;
			} else if (PokerHand.compareTwoHands(winner, playerArray[i]) == playerArray[i]) {
				winner = playerArray[i];
				draw = false;
			}
		}
		
		// Contains multiple draw players
		ArrayList<Player> drawArray = new ArrayList<Player>();

		// Assign player with the same highest extra information to drawArray
		for (Player p : playerArray) {
			if (Arrays.equals(p.getExtraInfo(), winner.getExtraInfo())) {
				drawArray.add(p);
			}
		}

		// Print out the result
		if (draw == false) {
			System.out.println("Player " + winner.getPlayerNo() + " wins.");
		} else {
			System.out.print("Players ");

			int s = drawArray.size();

			for (int i = 0; i < s; i++) {
				if (i == s - 2)
					System.out.print(drawArray.get(i).getPlayerNo());
				else if (i == s - 1)
					System.out.print(" and " + drawArray.get(i).getPlayerNo()
							+ " ");
				else
					System.out.print(drawArray.get(i).getPlayerNo() + ", ");
			}
			System.out.print("draw.");
		}
	}
	
	/**
	 * This method match Rank character taken from command line arguments with
	 * correct Rank type.
	 * @param rankChar Rank character from command line argument
	 * @return Rank type based on rank character
	 */
	private static Rank matchRank(String rankChar) {
		rankChar = rankChar.toUpperCase();

		if (rankChar.equals("2")) {
			return Rank.TWO;
		} else if (rankChar.equals("3")) {
			return Rank.THREE;
		} else if (rankChar.equals("4")) {
			return Rank.FOUR;
		} else if (rankChar.equals("5")) {
			return Rank.FIVE;
		} else if (rankChar.equals("6")) {
			return Rank.SIX;
		} else if (rankChar.equals("7")) {
			return Rank.SEVEN;
		} else if (rankChar.equals("8")) {
			return Rank.EIGHT;
		} else if (rankChar.equals("9")) {
			return Rank.NINE;
		} else if (rankChar.equals("T")) {
			return Rank.TEN;
		} else if (rankChar.equals("J")) {
			return Rank.JACK;
		} else if (rankChar.equals("Q")) {
			return Rank.QUEEN;
		} else if (rankChar.equals("K")) {
			return Rank.KING;
		} else {
			return Rank.ACE;
		}
	}

	/**
	 * This method match Suit character taken from command line arguments with
	 * correct Suit type.
	 * @param suitChar Suit character from command line argument
	 * @return Suit type based on suit character
	 */
	private static Suit matchSuit(String suitChar) {
		suitChar = suitChar.toUpperCase();

		if (suitChar.equals("H")) {
			return Suit.HEART;
		} else if (suitChar.equals("C")) {
			return Suit.CLUB;
		} else if (suitChar.equals("D")) {
			return Suit.DIAMOND;
		} else {
			return Suit.SPADE;
		}
	}
}
