/**
 * Rank.java
 * Name: Quang Nguyen Dang
 * Login ID: dangn1
 * This class is responsible to hold information about the rank of a card.
 */

public enum Rank {
	
	/**
	 * The purpose of this class is to hold all information about rank of a card
	 * in the game, from two to Ace with their rank order to support classifying 
	 * process.
	 * 
	 * Also, this class provides getter method to access those information.
	 * 
	 * Furthermore, by implementing enum type, this class guarantees no other 
	 * types of rank could be assigned to a card except those predefined ones.
	 * 
	 * Finally, this class also able to output appropriate description of a rank 
	 * according to its order by overriding toString method.
	 */
	
	TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(
			10), JACK(11), QUEEN(12), KING(13), ACE(14);

	// Instance variable
	private int rankOrder; // The higher the value is, the higher the rank is

	// Constructor
	/**
	 * @param rankOrder Order of a rank
	 */
	private Rank(int rankOrder) {
		this.rankOrder = rankOrder;
	}

	// Getter
	/**
	 * @return Order of current rank
	 */
	public int getRankOrder() {
		return this.rankOrder;
	}

	/**
	 * This method is overridden to out put appropriate description of a rank 
	 * according to its order to satisfy program purpose.
	 */
	@Override
	public String toString() {
		// Return the integer description of a rank if its order less than 11
		if (this.rankOrder < 11) {
			return Integer.toString(this.rankOrder);
			
		// Return the text description of a rank
		} else {
			String nameOfRank = this.name();
			nameOfRank = nameOfRank.toLowerCase();
			return Character.toUpperCase(nameOfRank.charAt(0))
					+ nameOfRank.substring(1);
		}
	}
}
