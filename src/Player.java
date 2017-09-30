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
	
	/** 
	 * Creates the Player object.
	 * 
	 * @param imagePath  The path to the player's image.
	 * @param x          The x-coordinate of the sprite, in pixels.
	 * @param y          The x-coordinate of the sprite, in pixels.
	 */
	public Player(String imagePath, float x, float y) {
		super(imagePath, x, y);
	}
	
	
	/** 
	 * Update the player's movement based on keyboard presses.
	 *
	 * @param input  The Slick input object, used for getting keyboard input.
     * @param delta  Time passed since last frame (milliseconds).
	 */
	@Override
	public void update(Input input, int delta) {
		
		System.out.println("Delta = " + delta);
		
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			
			float newXPos = this.getX() - App.TILE_SIZE;
			
			if (!Loader.isBlocked(newXPos, this.getY())) {
				this.setX(newXPos);
			}
		}
		
		if (input.isKeyPressed(Input.KEY_UP)) {
			
			float newYPos = this.getY() - App.TILE_SIZE;
			
			if (!Loader.isBlocked(this.getX(), newYPos)) {
				this.setY(newYPos);
			}
		}
		
		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			
			float newXPos = this.getX() + App.TILE_SIZE;
			
			if (!Loader.isBlocked(newXPos, this.getY())) {
				this.setX(newXPos);
			}
		}
		
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			
			float newYPos = this.getY() + App.TILE_SIZE;
			
			if (!Loader.isBlocked(this.getX(), newYPos)) {
				this.setY(newYPos);
			}
		}
	}
}
