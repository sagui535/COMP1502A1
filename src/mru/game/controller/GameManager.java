package mru.game.controller;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList; //added
import java.util.List;
import java.util.Scanner;	//added
import mru.game.model.Player;	//added
import mru.game.view.AppMenu;

/**
 * This class manages the game
 * Contains methods to play game, load and save player data
 * Search for players, and find top players
 */

public class GameManager {
	//File path to save and load player data
	private final String FILE_PATH = "res/CasinoInfo.txt";
	ArrayList<Player> players;
	AppMenu appMen;
	private Scanner enter;
	
	/**
     * Initializes the player list, main menu, and scanner for user input
     * Calls the loadData method to load existing players
     * 
     * @throws Exception if there is an error during initialization
     */
	
	public GameManager() throws Exception {
		players = new ArrayList<>();
		appMen = new AppMenu();
		enter = new Scanner(System.in);
		loadData();
		launchApp();
	}
	
	 /**
     * Launches the game
     * Displays main menu and process user input.
     *
     * @throws Exception if there is an error during the game execution.
     */
	
	private void launchApp() throws Exception {
		boolean flag = true;
		
		while (flag) {
			char option = appMen.showMainMenu();
			
			switch (option) {
			case 'p':
				playGame();
				break;
			case 's':
				Search();
				break;
			case 'e':
				flag = false;
				Save();
				break;
			default:
				System.out.println("Invalid choice. Try again!");
			}
		}
	}
	
	 /**
     * Plays game, including bet choice and bet amount
     * game execution, and updating player data
     */
	private void playGame() {
	    String name = appMen.promtName();
	    Player currentPlayer = findPlayerByName(name);

	    if (currentPlayer != null) {
	    	System.out.println("*****************************************************");
	        System.out.println("*** Welcome back " + name.toUpperCase() + " — Your balance is: " + currentPlayer.getBalance() + " $ ***");
	        System.out.println("*****************************************************");
	    } else {
	        currentPlayer = makePlayer(name);
	        System.out.println("*****************************************************");
	        System.out.println("*** Welcome " + name.toUpperCase() + " — Your initial balance is: 100 $ ***");
	        System.out.println("*****************************************************");
	    }

	    if (currentPlayer.getBalance() == 0) {
	        System.out.println("Your balance is 0. You cannot play. Returning to the main menu.");
	    } else {
	        // Prompt for the bet choice
	        String betChoice = appMen.promptBetChoice();

	        // Prompt for the bet amount
	        double betAmount = appMen.promptBetAmount();

	        // Ensure the player has enough balance
	        if (betAmount > currentPlayer.getBalance()) {
	            System.out.println("You cannot bet more than your current balance.");
	        } else {
	            // Proceed with the game
	        	 PuntoBancoGame puntoBancoGame = new PuntoBancoGame();
	             puntoBancoGame.play(currentPlayer, betChoice, betAmount);
	             
	          // Get the player and banker cards and their totals
	             List<Card> playerCards = puntoBancoGame.getPlayerCards();
	             List<Card> bankerCards = puntoBancoGame.getBankerCards();
	             int playerTotal = puntoBancoGame.getPlayerTotal();
	             int bankerTotal = puntoBancoGame.getBankerTotal();
	             
	          // Display the game result with player and banker cards
	                System.out.println("	     -PUNTO BANCO-");
	                System.out.println("+==================+==================+");
	                System.out.println("| PLAYER           | BANKER           |");
	                System.out.println("+==================+==================+");
	                for (int i = 0; i < 2; i++) {
	                    System.out.println("| " + playerCards.get(i) + "     | " + bankerCards.get(i) + "     |");
	                }
	                System.out.println("+--------------------------------+----+");
	                System.out.println("| PLAYER POINTS: " + playerTotal + " | BANKER POINTS: " + bankerTotal + " |");
	                System.out.println("+==================+==================+");

	                // Determine the winner and adjust balance
	                String result = currentPlayer.getBalance() > 100 ? "WON" : "LOST"; // Replace 100 with the actual balance condition
	                System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
	                System.out.println("$        PLAYER " + result + " " + betAmount + "            $");
	                System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
	             
	             System.out.print("Do you want to play again? (Y/N)?: ");
	             String playAgainChoice = enter.nextLine().toUpperCase();

	             if (playAgainChoice.equals("Y")) {
	                 // Plays another round
	                 playGame();
	             } else {
	                 // Returns to main menu
	            	 System.out.println("Returning to the main menu.");
	             }
	        }
	    }
	}

	/**
     * Searches player name
     */
	private Player findPlayerByName(String name) {
	    for (Player player : players) {
	        if (player.getName().equalsIgnoreCase(name)) {
	            return player;
	        }
	    }
	    return null; // Player not found
	}

	/**
     * Manage search sub menu including finding top players,
     * searching name and returning to main menu
     */
	private void Search() {
	        char option = appMen.showSubMenu();

	        switch (option) {
	            case 't':
	                FindTopPlayer();
	                break;
	            case 'n':
	                Player ply = searchByName();
	                appMen.showPlayer(ply);
	                break;
	            case 'b':
	            	break;
	            default:
					System.out.println("Invalid choice. Try again!");
					Search();
	        }
	}
	
	/**
     * Creates a new player and adds to player list
     * @return new player
     */
	public Player makePlayer(String name) {
		Player newPlayer = new Player(name, 100, 0);
		players.add(newPlayer);
		return newPlayer;
	}

	/**
     * Searches player name and displays wins and balance
     */
	private Player searchByName() {
		String name = appMen.promtName().toLowerCase();
		Player ply = null;
		
		for (Player p: players) {
			if (p.getName().toLowerCase().equals(name)) {
				ply = p;
				break;
			}
		}
		if (ply != null) {
	        System.out.println("   		    -PLAYER INFO-");
	        System.out.println("+==================+==================+==================+");
	        System.out.println("|NAME              |# WINS            |BALANCE           |");
	        System.out.println("+==================+==================+==================+");
	        System.out.println("|" + formatString(ply.getName(), 18) + "|" +
	                formatString(String.valueOf(ply.getNumOfWins()), 18) + "|" +
	                formatString(String.valueOf(ply.getBalance()), 18) + "|");
	        System.out.println("+==================+==================+==================+");
	  
	        System.out.println("Press \"Enter\" to continue...");
		    enter.nextLine();
		    
		
		} else {
	        System.out.println("Player does not exist. Try again!");
	    }
		return ply;
	}

	/**
     * Searches and display top players
     */
	private void FindTopPlayer() {
		// not from video
		if(players.isEmpty()) {
			System.out.println("Player not found. Try again!");
		}
		
		int maxWins = Integer.MIN_VALUE;
		ArrayList<Player> topPlayers = new ArrayList<>();
		
		for (Player p: players) {
			if (p.getNumOfWins() > maxWins) {
				maxWins = p.getNumOfWins();
				topPlayers.clear();
				topPlayers.add(p);
			} else if (p.getNumOfWins() == maxWins) {
				topPlayers.add(p);
			}
		}
		
		System.out.println("	     -TOP PLAYERS-");
	    System.out.println("+==================+==================+");
	    System.out.println("|NAME              |# WINS            |");
	    System.out.println("+==================+==================+");
	    
	    for (Player topPlayer : topPlayers) {
	    	System.out.println("|" + formatString(topPlayer.getName(), 18) + "|" +
	    	formatString(String.valueOf(topPlayer.getNumOfWins()), 18) + "|");
	        System.out.println("+==================+==================+");
	    }
	    
	    System.out.println("Press \"Enter\" to continue...");
	    enter.nextLine();  
	}
	
	/**
     * Formats string to specific length for top player and search name
     *
     * @param input  The input string to be formatted
     * @param length The desired length of the formatted string
     * @return The formatted string
     */
	private String formatString(String input, int length) {
	    if (input.length() >= length) {
	        return input.substring(0, length);
	    } else {
	        StringBuilder formattedString = new StringBuilder(input);
	        while (formattedString.length() < length) {
	            formattedString.append(" ");
	        }
	        return formattedString.toString();
	    }
	}
	
	 /**
     * Saves player data to text file
     *
     * @throws IOException if error during the save operation.
     */
	private void Save() throws IOException {
		System.out.println("Saving...");
		
		
		File db = new File(FILE_PATH);
		PrintWriter pw = new PrintWriter(db);
		
		for (Player p: players) {
			pw.println(p.format());
		}
		pw.close();
		System.out.println("Done! Please visit us again!");
	}

	 /**
     * Loads player data from text file
     *
     * @throws Exception if error during the load operation
     */
	private void loadData() throws Exception {
		File db = new File(FILE_PATH);
		String currentLine;
		String[] splitLine;
		
		
		if (db.exists()) {
			Scanner fileReader = new Scanner(db);
			while (fileReader.hasNextLine()) {
				currentLine = fileReader.nextLine();
				splitLine = currentLine.split(",");
				Player p = new Player(splitLine[0], Double.parseDouble(splitLine[1]), Integer.parseInt(splitLine[2]));
				players.add(p);
			}
		fileReader.close();
		}
		
	}
}
