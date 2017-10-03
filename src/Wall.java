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
	
	public static final String WALL = "res/wall.png";
	
	/** 
	 * Creates the Wall object.
	 */
	public Wall(float x, float y) {
		super(WALL, x, y);
		this.addTag("Blocked");
	}
}
