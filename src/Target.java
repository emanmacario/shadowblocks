/* SWEN20003 Object Oriented Software Development
 * 
 * Author: Emmanuel Macario <macarioe>
 * Student Number: 831659
 * Email: macarioe@student.unimelb.edu.au
 * 
 * This file contains the Target class.
 * 
 */

public class Target extends Sprite {
		
	private boolean activated;

	/** 
	 * Creates the Target object.
	 */
	public Target(float x, float y) {
		super("res/target.png", x, y);
		this.activated = false;
		this.addTag("Target");
	}
	
	public boolean getActivated() {
		return this.activated;
	}
	
	public void setActivated(boolean activated) {
		
		/* Debug statement. */
		if (activated) {
			//System.out.println("You activated a target!");
		} else {
			//System.out.println("You deactivated a target!");
		}
		this.activated = activated;
	}
	
	
	@Override
	public void update(World world, int delta) {
		
		Sprite block = world.getSpriteOfType("Block", 
										this.getX(), this.getY());
		
		if (block != null) {
			this.setActivated(true);
		} else {
			this.setActivated(false);
		}
		
	}
	

}
