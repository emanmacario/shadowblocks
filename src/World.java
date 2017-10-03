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
	private int currentLevel;
	
	

	/** 
	 * Creates the World object.
	 */
	public World() {
		this.sprites = Loader.loadSprites("res/levels/0.lvl");
		this.currentLevel = 0;
	}
	
	
	/** Checks if a Movable object is moving onto
	 * a tile that is blocked.
	 * @param x 	   New x-coordinate of object.
	 * @param y 	   New y-coordinate of object.
	 * @return blocked true if blocked else false.
	 */
	public boolean isBlocked(float x, float y) {
		
		/* Convert to tile coordinates. */
		int tileX = Loader.getTileX(x);
		int tileY = Loader.getTileY(y);
		
		/* Check if new coordinates in map bounds. */
		if (!Loader.inBounds(tileX, tileY)) {
			return true;
		}
		
		Sprite sprite = getSpriteOfType("Blocked", x, y);
		
		if (sprite != null) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Finds a sprite that possesses a certain tag, and
	 * is at a particular location.
	 *
	 * @param tag
	 * @param x
	 * @param y
	 * @return sprite
	 */
	public Sprite getSpriteOfType(String tag, float x, float y) {
		
		int spriteTileX, spriteTileY;
		
		int tileX = Loader.getTileX(x);
		int tileY = Loader.getTileY(y);
		
		/* Iterate through all sprites. */
		for (Sprite sprite : sprites) {
			
			/* Get sprite's x and y tile coordinates. */
			spriteTileX = Loader.getTileX(sprite.getX());
			spriteTileY = Loader.getTileY(sprite.getY());
			
			/* Test sprite for match. */
			if (sprite.compareTag(tag) && tileX == spriteTileX
					&& tileY == spriteTileY) {
				return sprite;
			}
		}
		return null;
	}
	
	
	private float getTestX(float x, int direction) {
		switch (direction) {
			case Sprite.DIR_LEFT:
				return x-App.TILE_SIZE;
			case Sprite.DIR_RIGHT:
				return x+App.TILE_SIZE;
			default:
				return x;
		}
	}
	
	private float getTestY(float y, int direction) {
		switch (direction) {
			case Sprite.DIR_UP:
				return y-App.TILE_SIZE;
			case Sprite.DIR_DOWN:
				return y+App.TILE_SIZE;
			default:
				return y;
		}
	}
	
	/** 
	 * Finds the first sprite that matches a given tag.
	 * 
	 * @param tag
	 * @return
	 */
	public Sprite getSpriteOfType(String tag) {

		for (Sprite sprite : sprites) {
			if (sprite.compareTag(tag)) {
				return sprite;
			}
		}
		return null;
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
		
		int direction = Sprite.DIR_NONE;
		
		if (input.isKeyPressed(Input.KEY_W)) {
			direction = Sprite.DIR_UP;
		} else if (input.isKeyPressed(Input.KEY_A)) {
			direction = Sprite.DIR_LEFT;
		} else if (input.isKeyPressed(Input.KEY_S)) {
			direction = Sprite.DIR_DOWN;
		} else if (input.isKeyPressed(Input.KEY_D)) {
			direction = Sprite.DIR_RIGHT;
		}
		
		
		for (Sprite sprite : sprites) {
			
			float spriteX = getTestX(sprite.getX(), direction);
			float spriteY = getTestY(sprite.getY(), direction);
			
			
			float blockX = getTestX(spriteX, direction);
			float blockY = getTestY(spriteY, direction);
			
			if (sprite != null && sprite instanceof Player) {
				
				if (!isBlocked(spriteX, spriteY)) {
					
					((Movable)sprite).moveToDestination(direction);
					
				} else {
					
					Sprite object = getSpriteOfType("Block", spriteX, spriteY);
					
					if (object == null) {
						return;
					}
					
					if (!isBlocked(blockX, blockY)) {
						((Movable)sprite).moveToDestination(direction);
						((Movable)object).moveToDestination(direction);
					}
				}
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
