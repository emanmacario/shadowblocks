/* SWEN20003 Object Oriented Software Development
 * Project 2 - Shadow Blocks
 * 
 * Author: Emmanuel Macario <macarioe>
 * Student Number: 831659
 * Email: macarioe@student.unimelb.edu.au
 * 
 * This file contains the TNT class, which is
 * a pushable block.
 */

public class TNT extends Pushable {
	
	/** TNT attributes.
	 */
	private boolean activated;
	
	
	/** Creates a new TNT sprite.
	 * 
	 * @param x  The sprite's x-coordinate, in pixels.
	 * @param y  The sprite's y-coordinate, in pixels.
	 */
	public TNT(float x, float y) {
		super("res/tnt.png", x, y);
		this.activated = false;
		this.addTag(Constant.BLOCK_TAG);
		this.addTag(Constant.BLOCKED_TAG);
	}
	
	
	/** Set the TNT block as activated, meaning
	 * that it is/isn't primed for explosion.
	 * 
	 * @param activated  Represents whether the TNT block should be exploded.
	 */
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
		
	/** Updates the world if the TNT block is pushed
	 * into a cracked wall, and also creates an explosion
	 * effect for 0.4 secs.
	 * 
	 * @param world  The world object the TNT block belongs to.
	 * @param delta  Time passed since last frame (milliseconds).
	 * @return void
	 */
	@Override
	public void update(World world, int delta) {
		
		if (activated) {
			
			/* Create a new explosion sprite. 
             */
            Sprite explosion = new Explosion(this.getX(), this.getY());
      
            /* Destroy the cracked wall and TNT block, and
             * add the explosion sprite to the sprite list.
             */
            Sprite crackedWall = world.getSpriteOfType(Constant.CRACKED_TAG);
            world.createSprite(explosion);
            world.destroySprite(crackedWall);
            world.destroySprite(this);
		}
	}
 }
