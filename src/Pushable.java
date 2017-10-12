/* SWEN20003 Object Oriented Software Development
 * 
 * Author: Emmanuel Macario <macarioe>
 * Student Number: 831659
 * Email: macarioe@student.unimelb.edu.au
 * 
 * This file contains the Pushable class, which represents
 * a game object which can be pushed by a Unit.
 */

public abstract class Pushable extends Movable {
	
	public Pushable(String imageSource, float x, float y) {
		super(imageSource, x, y);
	}
	
	public void push(int direction) {
		moveToDestination(direction);
	}
}
