/* SWEN20003 Object Oriented Software Development
 * Project 2 - Shadow Blocks
 * 
 * Author: Emmanuel Macario <macarioe>
 * Student Number: 831659
 * Email: macarioe@student.unimelb.edu.au
 * 
 * This file contains the Switch class, responsible
 * for opening/closing an associated door.
 */

public class Switch extends Sprite {
	
	/** Switch attributes.
	 */
	private Door door;
	
	
	/** Creates a new Switch object.
	 * 
	 * @param The x-coordinate of the sprite, in pixels.
	 * @param The y-coordinate of the sprite, in pixels.
	 */
	public Switch(float x, float y) {
		super("res/switch.png", x, y);
		this.door = null;
	}
	
	/** Associates a door with the switch.
	 * 
	 * @param door  The door to be set.
	 * @return void
	 */
	public void setDoor(Door door) {
		this.door = door;
	}
	
	
	/** Updates the state of the door (open/closed)
	 * by checking if the position of the switch tile 
	 * is occupied by a block.
	 * 
	 * @param world  The World object the Switch belongs to.
     * @param delta  Time passed since last frame (milliseconds).
     * @return void
     */
	@Override
	public void update(World world, int delta) {
		
		/* Check if there is a block
		 * currently on the door switch.
		 */
		Sprite block = world.getSpriteOfType("Block", 
								this.getX(), this.getY());
		
		/* Open the door if so, otherwise
		 * close the door if it is not already
		 * closed.
		 */
		if (block != null) {
			door.setClosed(false);
		} else {
			door.setClosed(true);
		}
	}
}