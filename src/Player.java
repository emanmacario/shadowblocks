/* SWEN20003 Object Oriented Software Development
 * 
 * Author: Emmanuel Macario <macarioe>
 * Student Number: 831659
 * Email: macarioe@student.unimelb.edu.au
 * 
 * This file contains the Player class, which controls
 * movement of the player in the program.
 */

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;


public class Player extends Movable {
	
	public static final String PLAYER = "res/player.png";
	
	private int dir;
	private int moveCount;
	
	/** 
	 * Creates the Player object.
	 * 
	 * @param x          The x-coordinate of the sprite, in pixels.
	 * @param y          The x-coordinate of the sprite, in pixels.
	 */
	public Player(float x, float y) {
		super(PLAYER, x, y);
		this.moveCount = 0;
		this.addTag("Player");
	}
	
	
	/** 
	 * Update the player's movement based on keyboard presses.
	 *
	 * @param input  The Slick input object, used for getting keyboard input.
     * @param delta  Time passed since last frame (milliseconds).
	 */
	@Override
	public void update(Input input, int delta) {
		int direction = DIR_NONE;
		
		if (input.isKeyPressed(Input.KEY_W)) {
			direction = DIR_UP;
		} else if (input.isKeyPressed(Input.KEY_A)) {
			direction = DIR_LEFT;
		} else if (input.isKeyPressed(Input.KEY_S)) {
			direction = DIR_DOWN;
		} else if (input.isKeyPressed(Input.KEY_D)) {
			direction = DIR_RIGHT;
		}
		
		super.moveToDestination(direction);
	}
	
	
	
	/** 
	 * @param g  The Slick graphics container object.
	 * @throws SlickException
	 * @return void
	 */
	@Override
	public void render(Graphics g) throws SlickException {
		
		/* Display current number of moves. */
		g.drawString("Moves: " + moveCount, 0, 0);
		
		super.render(g);
	}
}
