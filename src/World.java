/* SWEN20003 Object Oriented Software Development
 * 
 * Author: Emmanuel Macario <macarioe>
 * Student Number: 831659
 * Email: macarioe@student.unimelb.edu.au
 * 
 * This file contains the World class, which represents
 * the entire game world.
 */

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import java.util.Arrays;


public class World {	
	
	private static final String LEVEL_FILE = "res/levels/0.lvl";
	private static int[][] wallCoordinates = Loader.loadWallCoordinates(LEVEL_FILE);
	public static final int mapWidth  = Loader.getMapWidth(LEVEL_FILE);
	public static final int mapHeight = Loader.getMapHeight(LEVEL_FILE);
	
	
	/** 
	 * World attributes.
	 */
	private Sprite[] sprites;
	private Sprite player;
	
	
	/** 
	 * Creates the World object.
	 */
	public World() {
		this.sprites = Loader.loadSprites(LEVEL_FILE);
		this.player  = getPlayer(sprites);
	}
	
	
	/** 
	 * Gets the player sprite.
	 * 
	 * @param sprites   Array of loaded in sprites.
	 * @return      	The player sprite.
	 */
	private Sprite getPlayer(Sprite[] sprites) {
		Sprite player = null;
		
		for (Sprite sprite : sprites) {
			if (sprite instanceof Player) {
				player = sprite;
				break;
			}
		}
		return player;
	}
	
	
	/** 
	 * Creates a copy of the wall coordinates array, and returns 
	 * it. Does this by deep copying the whole array.
	 * 
	 * @param void
	 * @return A copy of all the wall tiles coordinates.
	 */
	public static int[][] getWallCoordinates() {
		
		int[][] wallCoordinatesCopy = 
				new int[wallCoordinates.length][wallCoordinates[0].length];
		
		for (int i = 0; i < wallCoordinates.length; i++) {
			wallCoordinatesCopy[i] = Arrays.copyOf(wallCoordinates[i], 
														wallCoordinates[i].length);
		}
		
		return wallCoordinatesCopy;
	}
	
	
	/** 
	 * Update the game state for a frame.
	 * 
     * @param input  The Slick input object, used for getting keyboard input.
     * @param delta  Time passed since last frame (milliseconds).
     */
	public void update(Input input, int delta) {
		
		// Exit if 'ESC' key is pressed.
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			System.exit(0);
		}
		
		// Update player's position.
		player.update(input, delta);
	}
	
	
	/** 
	 * Render the entire map, so it reflects the current game state.
	 * 
     * @param g  The Slick graphics object, used for drawing.
     */
	public void render(Graphics g) throws SlickException {
		for (Sprite sprite : sprites) {
			sprite.render(g);
		}
	}
}
