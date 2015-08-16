/**
 * Player.java
 * Name: Quang Nguyen Dang
 * Login ID: dangn1
 * This class is responsible to hold all information of a player.
 */

public class Player {
	
	/**
	 * The purpose of this class is to hold all information of a player in the 
	 * game, including:
	 * 
	 * 		Player number: The order of player in the game.
	 * 
	 * 		Player raw hand: Store all cards belongs to current player that are 
	 * 		generated from command line input.
	 * 
	 * 		Player hand: Store the actual classification of player's raw hand.
	 * 
	 * 		Extra information: An array of integer to store a number of card ranks
	 * 		in player hand to compare his hand with another player's hand.
	 *		
	 * 		Description: The description of player's hand.		
	 * 
	 * Also, this class provides getter and setters methods to access those
	 * information.
	 */
	
	// Instance variables
	private int playerNo; // Player number
	private Card[] playerRawHand; // Player raw hand
	private PokerHand playerHand; // Player hand
	private Rank[] extraInfo; // Extra information
	private String description; // Description
	
	// Getters
	/**
	 * @return current Player number
	 */
	public int getPlayerNo() {
		return this.playerNo;
	}
	
	/**
	 * @return A card array that contains all cards generated from command line
	 * arguments
	 */
	public Card[] getPlayerRawHand() {
		return this.playerRawHand;
	}
	
	/**
	 * @return An actual classification of a hand
	 */
	public PokerHand getPlayerHand() {
		return this.playerHand;
	}
	
	/**
	 * @return Extra information of player's hand
	 */
	public Rank[] getExtraInfo() {
		return this.extraInfo;
	}
	
	/**
	 * @return Description of player's hand
	 */
	public String getDescription() {
		return this.description;
	}
	
	// Setters
	
	/**
	 * @param playerNo Player number
	 */
	public void setPlayerNo(int playerNo) {
		this.playerNo = playerNo;
	}
	
	/**
	 * @param playerRawHand A card array that contains all cards generated from 
	 * command line arguments
	 */
	public void setPlayerRawHand(Card[] playerRawHand) {
		this.playerRawHand = playerRawHand;
	}
	
	/**
	 * @param playerHand An actual classification of a hand
	 */
	public void setPlayerHand(PokerHand playerHand) {
		this.playerHand = playerHand;
	}
	
	/**
	 * @param extraInfo Extra information of player's hand
	 */
	public void setExtraInfo(Rank[] extraInfo) {
		this.extraInfo = extraInfo;
	}
	
	/**
	 * @param description Description of player's hand
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
}
