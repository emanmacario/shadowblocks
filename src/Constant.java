/* SWEN20003 Object Oriented Software Development
 * Project 2 - Shadow Blocks
 * 
 * Author: Emmanuel Macario <macarioe>
 * Student Number: 831659
 * Email: macarioe@student.unimelb.edu.au
 * 
 * This file contains the Constant class, which
 * contains game constants. This classes purpose
 * is to avoid magic strings/numbers, and to avoid
 * having to access each individual class to change
 * constants.
 */


public abstract class Constant {
	
	/* Level constants.
	 */
	public static final int START_LEVEL = 0;
	public static final int END_LEVEL = 5;
	public static final String LEVEL_DIR = "res/levels/";
	public static final String LEVEL_EXT = ".lvl";
	
	/* String tag constants.
	 */
	public static final String PLAYER_TAG = "Player";
	public static final String BLOCKED_TAG = "Blocked";
	public static final String CRACKED_TAG = "Cracked";
	public static final String ENEMY_TAG = "Enemy";
	public static final String BLOCK_TAG = "Block";
	
	/* Time constants (milliseconds).
	 */
	public static final int EXPLOSION_TIME = 400;
	public static final int ICE_INTERVAL = 250;
	public static final int SKELETON_INTERVAL = 1000;
	
}
