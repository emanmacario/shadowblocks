/* SWEN20003 Object Oriented Software Development
 * Project 2 - Shadow Blocks
 * 
 * Author: Emmanuel Macario <macarioe>
 * Student Number: 831659
 * Email: macarioe@student.unimelb.edu.au
 * 
 * This file contains the World class, which 
 * represents the entire game world.
 */

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import java.util.ArrayList;


public class World {	
	
	/** World attributes.
	 */
	private ArrayList<Sprite> sprites;
	private ArrayList<Sprite> spritesToCreate;
	private ArrayList<Sprite> spritesToDestroy;
	private boolean playerMoved;
	private int currentLevel;
	private Input input;
	

	/** Creates a new World object.
	 * 
	 * @param void
	 * @return New world starting at level zero.
	 */
	public World() {
		this.sprites = Loader.loadSprites("res/levels/0.lvl");
		this.spritesToCreate = new ArrayList<>();
		this.spritesToDestroy = new ArrayList<>();
		this.playerMoved = false;
		this.currentLevel = 0;
	}
	
	
	/** Restarts the current level.
	 * 
	 * @param void
	 * @return void
	 */
	public void restartLevel() {
		this.sprites = Loader.loadSprites("res/levels/" 
								+ currentLevel + ".lvl");
		this.spritesToCreate.clear();
		this.spritesToDestroy.clear();
	}
	
	
	/** Checks if a Movable object is moving 
	 * onto a tile that is blocked.
	 * 
	 * @param x  New x-coordinate of object.
	 * @param y  New y-coordinate of object.
	 * @return   true if blocked else false.
	 */
	public boolean isBlocked(float x, float y) {
		
		/* Convert pixel to tile coordinates. 
		 */
		int tileX = Loader.getTileX(x);
		int tileY = Loader.getTileY(y);
		
		/* Check if new coordinates in map bounds. 
		 */
		if (!Loader.inBounds(tileX, tileY)) {
			return true;
		}
		
		/* Now check to actually see if the 
		 * tile is blocked.
		 */
		if (getSpriteOfType("Blocked", x, y) != null) {
			return true;
		}
		return false;
	}
	
	
	/** Finds a sprite that matches both a given
	 * tag and is at a particular given location.
	 *
	 * @param tag  The tag a sprite possesses.
	 * @param x    The x-coordinate (in pixels).
	 * @param y    The y-coordinate (in pixels).
	 * @return sprite
	 */
	public Sprite getSpriteOfType(String tag, float x, float y) {
		
		int spriteTileX, spriteTileY;
		
		int tileX = Loader.getTileX(x);
		int tileY = Loader.getTileY(y);
		
		/* Iterate through all sprites. 
		 */
		for (Sprite sprite : sprites) {
			
			if (sprite == null) {
				continue;
			}
			
			/* Get a sprite's x and y tile coordinates. 
			 */
			spriteTileX = Loader.getTileX(sprite.getX());
			spriteTileY = Loader.getTileY(sprite.getY());
			
			/* Test sprite for match. 
			 */
			if (sprite.compareTag(tag) && tileX == spriteTileX 
					&& tileY == spriteTileY) {
				
				return sprite;
			}
		}
		return null;
	}
	
	
	/** Finds the first sprite that matches a given tag.
	 * 
	 * @param tag The tag a sprite possesses.
	 * @return sprite
	 */
	public Sprite getSpriteOfType(String tag) {

		for (Sprite sprite : sprites) {
			if (sprite != null && sprite.compareTag(tag)) {
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
		
		this.input = input;
		
		/* Exit game if 'ESC' key is pressed. 
		 */
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			System.exit(0);
		}
		/* Loads next level if the current level
		 * is completed. 
		 */
		if (isLevelOver()) {
			restartLevel();
		}
		/* Restart level if 'R' key is pressed. 
		 */
		if (input.isKeyDown(Input.KEY_R)) {
			restartLevel();
		}
		/* Undo moves if 'Z' key is pressed. 
		 */
		if (input.isKeyPressed(Input.KEY_Z)) {
			undoMovables();
		}
		
		/* Now, update all the current sprites.
		 */
		for (Sprite sprite : sprites) {
			
			/* Check for null. 
			 */
			if (sprite == null) {
				continue;
			}
			sprite.update(this, delta);
		}
		
		/* Check if we need to create any sprites,
		 * (namely explosions) and if so create them!
		 */
		sprites.addAll(spritesToCreate);
		spritesToCreate.clear();
		
		/* Check if we need to destroy any sprites,
		 * and if so destroy them!.
		 */
		sprites.removeAll(spritesToDestroy);
		spritesToDestroy.clear();
	}
	
	
	
		
	
	/** Makes the player and all current blocks return 
	 * to the original position they were before they 
	 * last moved. 
	 * 
	 * @param void
	 * @return void
	 */
	public void undoMovables() {
				
		for (Sprite sprite : sprites) {
			if (sprite instanceof Movable) {
				((Movable)sprite).undo();
			}
		}
	}
	

	/** Updates the movement history of the
	 * player and each block. Even if an object
	 * did not move, their current (or last)
	 * position may be added to their history.
	 * 
	 * @param void
	 * @return void
	 */
	public void updateMovableHistory() {
		
		for (Sprite sprite : sprites) {
			if (sprite.compareTag("Block") || 
					sprite.compareTag("Player")) {
				
				((Movable)sprite).addToHistory();
			}
		}
	}
	
		
	/** Takes a sprite and 'creates' it, or in other
	 * words just adds it to the world's sprite list.
	 * 
	 * @param sprite  The sprite to be created.
	 * @return void
	 */
	public void createSprite(Sprite sprite) {
		this.spritesToCreate.add(sprite);
	}
	
	
	/** Takes a sprite and 'destroys' it, or in other
	 * words removes it from the world's sprite list.
	 * 
	 * @param sprite  The sprite to be destroyed.
	 * @return void
	 */
	public void destroySprite(Sprite sprite) {
		this.spritesToDestroy.add(sprite);
	}
	
	
	/** Sets if the player has recently moved.
	 * 
	 * @param playerMoved  Indicates if the player has moved.
	 * @return void
	 */
	public void setPlayerMoved(boolean playerMoved) {
		this.playerMoved = playerMoved;
	}
	
	
	/** Returns if the player has recently moved.
	 * 
	 * @param void
	 * @return playerMoved  Indicates if the player has moved.
	 */
	public boolean getPlayerMoved() {
		return this.playerMoved;
	}
	
	
	/** Returns the input object.
	 * 
	 * @param void
	 * @return input  The input object.
	 */
	public Input getInput() {
		return this.input;
	}

	
	/** Render the entire map, so it reflects the current game state.
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
	
	
	/** Checks if the current level is over by 
	 * checking if each target tile is covered 
	 * by a block. If the level is one, increments
	 * the current level.
	 * 
	 * @param void
	 * @return True if all targets covered else false.
	 */
	private boolean isLevelOver() {
		
		if (currentLevel == 5) {
			return false;
		}
		
		for (Sprite sprite : sprites) {
			
			if (sprite == null) {
				continue;
			}
			
			if (sprite instanceof Target) {
				if (!((Target)sprite).getActivated()) {
					return false;
				}
			}
		}
		currentLevel++;
		return true;
	}
}
