/* SWEN20003 Object Oriented Software Development
 * Project 2 - Shadow Blocks
 * 
 * Author: Emmanuel Macario <macarioe>
 * Student Number: 831659
 * Email: macarioe@student.unimelb.edu.au
 * 
 * This file contains the Timer class, used
 * for timing movement intervals and effects time.
 */


public class Timer {
	
	/** Timer attributes.
	 */
	private int time;
	private int target;
	
	
	/** Creates a new Timer object.
	 * 
	 * @param target  Target time (milliseconds)
	 */
	public Timer(int target) {
		this.time = 0;
		this.target = target;
	}
	
	
	/** Updates the timer, based on
	 * the number of millseconds passed.
	 * 
	 * @param delta  Time passed since last frame (milliseconds).
	 * @return void
	 */
	public void update(int delta) {		
		this.time = this.time + delta;
	}
	
	
	/** Returns whether the timer has reached 
	 * its target time.
	 * 
	 * @param void
	 * @return True if time has reached its target, else false.
	 */
	public boolean expired() {
		if (this.time >= this.target) {
			return true;
		}
		return false;
	}
	
	
	/** Resets the timer (only if it has expired first). 
	 * 
	 * @param void
	 * @return void
	 */
	public void reset() {
		if (this.expired()) {
			this.time = 0;
		}
	}
	
}
