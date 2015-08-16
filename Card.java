/**
 * Card.java
 * Name: Quang Nguyen Dang
 * Login ID: dangn1
 * This class is responsible to hold information about a card.
 */

public class Card implements Comparable<Card> {
	
	/**
	 * The purpose of this class is to provides a data type used to store
	 * all cards in the overall program.
	 * 
	 * This class holds two pieces of information:
	 * 
	 * 		Rank: The rank of a card.
	 * 
	 * 		Suit: The suit of a card.
	 * 
	 * Also, this class provides getter methods to access those information.
	 * 
	 * Finally, this class implements Comparable interface that allows an
	 * array of Card type to be sorted based on the rank order of cards in
	 * that array.
	 */
	
	// Instance variables
	private Rank rank;
	private Suit suit;


	// Constructor
	/**
	 * @param rank Rank object
	 * @param suit Suit object
	 */
	public Card(Rank rank, Suit suit) {
		super();
		this.rank = rank;
		this.suit = suit;
	}

	// Getters
	/**
	 * @return Rank object of current card
	 */
	public Rank getRank() {
		return this.rank;
	}

	/**
	 * @return Suit object of current card
	 */
	public Suit getSuit() {
		return this.suit;
	}

	/**
	 * This method is overridden from Comparable interface to allows a Card 
	 * object to be compared to another Card object based on their rank orders.
	 */
	@Override
	public int compareTo(Card compareCard) {
		int currentRankOrder = this.rank.getRankOrder();

		// Get the rank order of cards which this current card is compared to
		int compareRankOrder = ((Card) compareCard).getRank().getRankOrder();

		// Sort in ascending order
		// return currentRankOrder - compareRankOrder;

		// Sort in descending order
		return compareRankOrder - currentRankOrder;
	}
}
