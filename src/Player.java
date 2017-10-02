/* SWEN20003 Object Oriented Software Development
 * 
 * Author: Emmanuel Macario <macarioe>
 * Student Number: 831659
 * Email: macarioe@student.unimelb.edu.au
 * 
 * This file contains the Player class, which controls
 * movement of the player in the program.
 */

import org.newdawn.slick.Input;


public class Player extends Sprite {
	
	public static final String PLAYER = "res/player.png";
	
	/** 
	 * Creates the Player object.
	 * 
	 * @param x          The x-coordinate of the sprite, in pixels.
	 * @param y          The x-coordinate of the sprite, in pixels.
	 */
	public Player(float x, float y) {
		super(PLAYER, x, y);
	}
	
	
	/** 
	 * Update the player's movement based on keyboard presses.
	 *
	 * @param input  The Slick input object, used for getting keyboard input.
     * @param delta  Time passed since last frame (milliseconds).
	 */
	@Override
	public void update(Input input, int delta) {
		
	}
}
