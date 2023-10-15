package mru.game.view;
import java.util.ArrayList;
import java.util.Scanner; //added
import mru.game.model.Player; 

/**
 * This class will be used to show the main menus and sub menus to the user
 * It also prompts the user for the inputs and validates them 
 * @return 
 */
public class AppMenu {
	Scanner input;
	
	/**
     * Constructs AppMenu object and initializes Scanner for user input
     */
	public AppMenu() {
		input = new Scanner(System.in);
	}
	
	/**
     * Displays main menu options P,S,T and prompts user to enter a choice
     *
     * @return user main menu choice
     */
	public char showMainMenu() {
		System.out.println("Select one of these options:\n");
		System.out.println("\t(P) Play game");
		System.out.println("\t(S) Search");
		System.out.println("\t(E) Exit\n");
		System.out.print("Enter a choice: ");
		char option = input.nextLine().toLowerCase().charAt(0);
		return option;
	}
	
	/**
     * Displays sub menu options T,N,B and prompts user to enter a choice
     *
     * @return user sub menu choice
     */
	public char showSubMenu() {
		System.out.println("Select one of these options:\n");
		System.out.println("\t(T) Top player (Most number of wins)");
		System.out.println("\t(N) Looking for a Name");
		System.out.println("\t(B) Back to Main menu\n");
		System.out.println("Enter a choice: ");
		char option = input.nextLine().toLowerCase().charAt(0);
		return option;
	}
	
	/**
     * Prompts for the name of user
     *
     * @return The name entered by user
     */
	public String promtName() {
		System.out.println("What is your name: ");
		String name = input.nextLine();
		return name;
	}
	
	 /**
     * Displays player information
     *
     * @param ply The player whose information will be displayed
     */
	public void showPlayer(Player ply) {
		System.out.println(ply);
	}

	/**
     * Prompts user for bet choice, player, banker, or tie
     *
     * @return user bet choice as a string, P, B, or T
     */
	public String promptBetChoice() {
        System.out.println("Who do you want to bet on?");
        System.out.println("(P) Player Wins");
        System.out.println("(B) Banker Wins");
        System.out.println("(T) Tie Game");
        System.out.print("Enter your choice: ");
        String betChoice = input.nextLine();
        return betChoice.toUpperCase(); 
    }
	
	 /**
     * Prompts user for their bet amount
     *
     * @return user bet amount as a double
     */
	public double promptBetAmount() {
	    System.out.print("How much do you want to bet this round? ");
	    double betAmount = input.nextDouble();
	    input.nextLine(); 
	    return betAmount;
	}
}