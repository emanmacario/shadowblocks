/* SWEN20003 Object Oriented Software Development
 * 
 * Author: Emmanuel Macario <macarioe>
 * Student Number: 831659
 * Email: macarioe@student.unimelb.edu.au
 * 
 * This file contains the Pushable class, which represents
 * a game object which can be pushed by a Movable object.
 */

public abstract class Pushable extends Movable {
	
	public Pushable(String imagePath, float x, float y) {
		super(imagePath, x, y);
	}
	
	
	public void push(int direction) {
		super.moveToDestination(direction);
	}
	
	public boolean active() {
		return false;
	}
}
