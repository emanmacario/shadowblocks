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
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends Movable {
	
	/** Player attributes.
	 */
	private int direction;
	private int moveCount;
	
	
	/** Creates the Player object.
	 * 
	 * @param x  The x-coordinate of the sprite, in pixels.
	 * @param y  The x-coordinate of the sprite, in pixels.
	 */
	public Player(float x, float y) {
		super("res/player.png", x, y);
		this.direction = DIR_NONE;
		this.moveCount = 0;
		this.addTag("Player");
	}
	
	
	/** Method that updates the state of the Player object 'on move'.
	 * 
	 * @param direction
	 * @param testX
	 * @param testY
	 */
	@Override
	public void onMove(float newX, float newY) {
		this.moveCount++;
		
		super.onMove(newX, newY);
	}
	
	
	/** Undo the most recent move for a player. 
	 */
	@Override
	public void undo() {
		
		if (this.moveCount > 0) {
			super.undo();
			this.moveCount--;
		}
	}
	
	
	@Override
	public void update(World world, int delta) {
		
		/* Firstly check if the player is 'dead'. 
		 */
		if (isPlayerDead(world)) {
			world.restartLevel();
		}
		
		/* Get the direction, and set it. 
		 */
		Input input = world.getInput();
		this.direction = inputToDirection(input);
		
		
		/* Relay if a player has moved. 
		 */
		if (this.direction != DIR_NONE) {
			
			/* If yes, update the history of
			 * movable objects.
			 */
			world.setPlayerMoved(true);
			world.updateMovableHistory();
			
		} else {
			
			/* Otherwise, nothing to do.
			 */
			world.setPlayerMoved(false);
			return;
		}
		
		/* Get new candidate position. */
		float testX = getTestX(this.getX(), this.direction);
		float testY = getTestY(this.getY(), this.direction);
		
		float blockX = getTestX(testX, this.direction);
		float blockY = getTestY(testY, this.direction);
		
		
		if (world.isBlocked(testX, testY)) {
			
			Sprite block = world.getSpriteOfType("Block", testX, testY);
           
            if (block == null) {
            	
            	this.moveToDestination(DIR_NONE);
            	
            } else if (block instanceof TNT && world.isBlocked(blockX, blockY)) {
                    
                Sprite crackedWall = world.getSpriteOfType("Blocked", blockX, blockY);
                
                if (crackedWall != null && crackedWall instanceof Cracked) {
                    
                	((TNT)block).setActivated(true);
              
                }
                    
            } else if (!world.isBlocked(blockX, blockY)) {
            	
            	this.moveToDestination(this.direction);
            	((Pushable)block).push(this.direction);
            	
            } else {
            	
            	this.moveToDestination(DIR_NONE);
            	
            }
            	
		} else {
			
			this.moveToDestination(this.direction);
		}	
		
	}
	
		
	/** Translates a key input on the keyboard, to
	 * an in-game direction.
	 * 
	 * @param input
	 * @return direction
	 */
	private int inputToDirection(Input input) {
		
		if (input.isKeyPressed(Input.KEY_W)) {
			
			return DIR_UP;
			
		} else if (input.isKeyPressed(Input.KEY_A)) {
			
			return DIR_LEFT;
			
		} else if (input.isKeyPressed(Input.KEY_S)) {
			
			return DIR_DOWN;
			
		} else if (input.isKeyPressed(Input.KEY_D)) {
			
			return DIR_RIGHT;
		}
		
		return DIR_NONE;
	}
	
	
	/** Checks if a player is 'dead', by checking
	 * if the player shares a position with an 'Enemy'.
	 * 
	 * @param world  The world the player sprite belongs to.
	 * @return True if player is dead, else false.
	 */
	private boolean isPlayerDead(World world) {
		
		if (world.getSpriteOfType("Enemy", 
					this.getX(), this.getY()) != null) {
			
			return true;
		}
		return false;
	}
	
	
	/** In addition to rendering the sprite, 
	 * also renders the player's current move count. 
	 * 
	 * @param g  The Slick graphics container object.
	 * @throws SlickException
	 * @return void
	 */
	@Override
	public void render(Graphics g) throws SlickException {
		super.render(g);
		
		/* Display current number of moves. 
		 */
		g.drawString("Moves: " + moveCount, 0, 0);
	}
}
