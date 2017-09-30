/* SWEN20003 Object Oriented Software Development
 * 
 * Author: Emmanuel Macario <macarioe>
 * Student Number: 831659
 * Email: macarioe@student.unimelb.edu.au
 * 
 * This file contains the Wall class, which is
 * responsible for blocking the player from leaving
 * the game map bounds.
 */


public class Wall extends Sprite {
	
	/** 
	 * Creates the Wall object.
	 */
	public Wall(String imagePath, float x, float y) {
		super(imagePath, x, y);
	}
}
