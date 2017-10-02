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
import java.util.ArrayList;


public class World {	
	

	/** 
	 * World attributes.
	 */
	private ArrayList<Sprite> sprites;
	private Input input;
	private boolean playerMoved;
	
	
	/** 
	 * Creates the World object.
	 */
	public World() {
		this.sprites = Loader.loadSprites("res/levels/1.lvl");
	}
	
	
	
	/** 
	 * Update the game state for a frame.
	 * 
     * @param input  The Slick input object, used for getting keyboard input.
     * @param delta  Time passed since last frame (milliseconds).
     */
	public void update(Input input, int delta) {
		
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			System.exit(0);
		}
		
		for (Sprite sprite : sprites) {
			if (sprite != null) {
				sprite.update(input, delta);
			}
		}		
	}
	
	/** 
	 * Render the entire map, so it reflects the current game state.
	 * 
     * @param g  The Slick graphics object, used for drawing.
	 * @throws SlickException 
     */
	public void render(Graphics g) throws SlickException {
		for (Sprite sprite : sprites) {
			if (sprite != null) {
				sprite.render(g);
			}
		}
	}
	
	
	
}
