package mru.game.application;

import mru.game.controller.GameManager;

/**
 * This class represents the main entry point for the app
 * @author shaira and mohamed
 */

public class AppDriver {

	/**
	 * This method initiates the game
	 */
	
	public static void main(String[] args) throws Exception {
	
		// Calls method from GameManager class to start the app
		
		new GameManager();
	}
}
