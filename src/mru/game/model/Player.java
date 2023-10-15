package mru.game.model;

/**
 * This class represent each player record in the Database
 * It is basically a model class for each record in the txt file
 */
public class Player {
	
	 String name;
	 double balance;
	 int numOfWins;
	
	 /**
	     * Constructs Player object with name, balance, and number of wins
	     *
	     * @param name      player name
	     * @param balance   player balance
	     * @param numOfWins total player wins
	     */
	public Player(String name, double balance, int numOfWins) {
		this.name = name;
		this.balance = balance;
		this.numOfWins = numOfWins;
		}
	
	 /**
     * Sets name of player
     *
     * @param name The name to set.
     */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
     * Gets name of player
     *
     * @return name of player
     */
	public String getName() {
		return name;
	}
	
	/**
     * Sets balance of player
     *
     * @param balance The balance to set
     */
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	/**
     * Gets player balance
     *
     * @return player balance
     */
	public double getBalance() {
		return balance;
	}
	
	/**
     * Sets number of player wins
     *
     * @param numOfWins The number of wins to set
     */
	public void setNumOfWins(int numOfWins) {
		this.numOfWins = numOfWins;
	}
	
	/**
     * Gets number of player wins
     *
     * @return number of player wins
     */
	public int getNumOfWins() {
		return numOfWins;
	}
	
	/**
     * Returns string representation of player
     *
     * @return empty string
     */
	public String toString() {
		return "";
	}
	
	 /**
     * Formats player data as string to store in text file
     *
     * @return string in the format name,balance,numOfWins
     */
	public String format() {
		return name+","+balance+","+numOfWins;
	}
}
