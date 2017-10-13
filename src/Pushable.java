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
	
	/** Creates a new Pushable object.
	 * 
	 * @param imageSource The relative path to the sprite's image.
	 * @param x           The sprite's x-coordinate, in pixels.
	 * @param y           The sprite's y-coordinate, in pixels.
	 */
	public Pushable(String imageSource, float x, float y) {
		super(imageSource, x, y);
	}
	
	
	/** Pushes this sprite in the given direction.
	 * 
	 * @param direction
	 * @return void
	 */
	public void push(int direction) {
		moveToDestination(direction);
	}
}
