/* SWEN20003 Object Oriented Software Development
 * 
 * Author: Emmanuel Macario <macarioe>
 * Student Number: 831659
 * Email: macarioe@student.unimelb.edu.au
 * 
 * This file contains the Timer class, used
 * for timing movement interval and effect time.
 * 
 */
public class Timer {
	
	private int time;
	private int target;
	
	public Timer(int target) {
		this.time = 0;
		this.target = target;
	}
	
	public void update(int delta) {		
		this.time = this.time + delta;
	}
	
	public boolean expired() {
		if (this.time >= this.target) {
			return true;
		}
		return false;
	}
		
	public int getTime() {
		return this.time;
	}
}
