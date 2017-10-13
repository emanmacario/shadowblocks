/* SWEN20003 Object Oriented Software Development
 * Project 2 - Shadow Blocks
 * 
 * Author: Emmanuel Macario <macarioe>
 * Student Number: 831659
 * Email: macarioe@student.unimelb.edu.au
 * 
 * This file contains the Rogue class which 
 * is a passive unit, that can kill the Player.
 */

public class Rogue extends Movable {
	
	/** Rogue attributes.
	 */
	private int direction;
	
	
	/** Creates a new Rogue, with a default 
	 * direction of left.
	 * 
	 * @param x  The sprite's x-coordinate, in pixels.
	 * @param y  The sprite's y-coordinate, in pixels.
	 */
	public Rogue(float x, float y) {
		super("res/rogue.png", x, y);
		this.direction = DIR_LEFT;
		this.addTag(Constant.ENEMY_TAG);
	}
	
	
	@Override
	public void update(World world, int delta) {
		
		/* Firstly, check if the player moved.
		 */
		if (!world.getPlayerMoved()) {
			return;
		}
		
		/* Get new candidate position. 
		 */
		float testX = getTestX(this.getX(), this.direction);
		float testY = getTestY(this.getY(), this.direction);
		
		
		/* Get adjacent position of candidate position,
		 * in the respective direction. 
		 */
		float blockX = getTestX(testX, this.direction);
		float blockY = getTestY(testY, this.direction);
		
		/* Either move, move and push a block, or reverse direction. 
		 */
		if (world.isBlocked(testX, testY)) {
						
			Sprite block = world.getSpriteOfType(Constant.BLOCK_TAG, 
														testX, testY);
           
            if (block == null) {
            	
                this.reverseDirection();
                
            } else if (world.isBlocked(blockX, blockY)) {
            	
                this.reverseDirection();
                
            } else {
            	
            	this.moveToDestination(this.direction);
            	((Pushable)block).push(this.direction);
            	
            }
            
		} else {
			
			this.moveToDestination(this.direction);
		}
	}
	
	
	/** If the Rogue was blocked and could not 
	 * move, reverse its current direction.
	 * 
	 * @param void
	 * @return void
	 */
	private void reverseDirection() {
		
		if (this.direction == DIR_LEFT) {
			
			this.direction = DIR_RIGHT;
			
		} else {
			
			this.direction = DIR_LEFT;
		}
	}
}