/**
 * NOfAKind.java
 * Name: Quang Nguyen Dang
 * Login ID: dangn1
 * This class is responsible to hold rank and duplicate times of a card.
 */

public class NOfAKind implements Comparable<NOfAKind> {

	/**
	 * The purpose of this class is to provide a convenient data type that can
	 * be used particularly to check n-of-a-kind classification of a hand.
	 * 
	 * This class holds two pieces of information: 
	 * 
	 * 		Rank: The rank of a card.
	 * 
	 * 		Duplicate times: How many times a card is repeated in a hand.
	 * 
	 * Also, this class provides getter methods to access those information.
	 * 
	 * Finally, this class implements Comparable interface that allows an array
	 * of NOfAKind type to be sorted based on the the duplicate times of NOfAKind
	 * objects in that array.
	 */

	// Instance variables
	private Rank cardRank;
	private int duplicateTimes;

	// Constructor
	/**
	 * @param cardRank Rank object of a card
	 * @param duplicateTimes Duplicate times of a card
	 */
	public NOfAKind(Rank cardRank, int duplicateTimes) {
		this.cardRank = cardRank;
		this.duplicateTimes = duplicateTimes;
	}

	// Getters
	/**
	 * @return Rank object of current NOfAKind
	 */
	public Rank getCardRank() {
		return this.cardRank;
	}

	/**
	 * @return Duplicate times of current NOfAKind
	 */
	public int getDuplicateTimes() {
		return this.duplicateTimes;
	}

	/**
	 * This method is overridden from Comparable interface to allows a NOfAKind 
	 * object to be compared to another NOfAKind object based on their duplicate 
	 * times.
	 */
	@Override
	public int compareTo(NOfAKind compareNOfAKind) {
		// Get the duplicate times of current NOfAKind
		int currentDuplicateTimes = this.duplicateTimes;

		// Get the duplicate times of current NOfAKind which this current 
		// NOfAKind is compared to
		int compareDuplicateTimes = ((NOfAKind) compareNOfAKind)
				.getDuplicateTimes();

		// Sort in ascending order
		// return currentDuplicateTimes - compareDuplicateTimes;

		// Sort in descending order
		return compareDuplicateTimes - currentDuplicateTimes;
	}
}
