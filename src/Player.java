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

public class Player extends Movable {
	
	public static final String PLAYER = "res/player.png";
	
	private int direction;
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
	
	/* Direction setter. */
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	/* Direction getter. */
	public int getDirection() {
		return this.direction;
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
