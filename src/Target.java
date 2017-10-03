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
	
	public static final String TARGET = "res/target.png";
	
	private boolean activated;

	/** 
	 * Creates the Target object.
	 */
	public Target(float x, float y) {
		super(TARGET, x, y);
		this.activated = false;
	}
	
	/* 
	@Override
	public void update(int delta) {
		
	}
	*/
	
	
	public boolean getIsActive() {
		return this.activated;
	}
	
	public void setIsActive(boolean activated) {
		this.activated = activated;
	}

}
