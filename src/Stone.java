/* SWEN20003 Object Oriented Software Development
 * 
 * Author: Emmanuel Macario <macarioe>
 * Student Number: 831659
 * Email: macarioe@student.unimelb.edu.au
 * 
 * This file contains the Stone class.
 * 
 * Note: This class has been implemented now for
 * the purpose of any possible extensions for 
 * Project 2.
 */


public class Stone extends Pushable {
		
	/** 
	 * Creates the Stone object.
	 */
	public Stone(float x, float y) {
		super("res/stone.png", x, y);
		this.addTag("Block");
		this.addTag("Blocked");
	}
}
