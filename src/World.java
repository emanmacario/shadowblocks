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
	private ArrayList<Sprite> spritesToCreate;
	private ArrayList<Sprite> spritesToDestroy;
	private boolean playerMoved;
	private int currentLevel;
	private Input input;
	

	/** 
	 * Creates the World object.
	 */
	public World() {
		this.sprites = Loader.loadSprites("res/levels/2.lvl");
		this.spritesToCreate = new ArrayList<>();
		this.spritesToDestroy = new ArrayList<>();
		this.playerMoved = false;
		this.currentLevel = 2;
	}
	
	
	/** Restarts the current level.
	 * 
	 * @param void
	 * @return void
	 */
	public void restartLevel() {
		this.sprites = Loader.loadSprites("res/levels/" + currentLevel + ".lvl");
		this.spritesToCreate.clear();
		this.spritesToDestroy.clear();
	}
	
	
	/** Checks if the current level is over by checking
	 * if each target tile is covered by a block.
	 * 
	 * @param void
	 * @return null
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
	
	
	
	/** Checks if a Movable object is moving onto
	 * a tile that is blocked.
	 * 
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
			
			if (sprite == null) {
				continue;
			}
			/* Get a sprite's x and y tile coordinates. */
			spriteTileX = Loader.getTileX(sprite.getX());
			spriteTileY = Loader.getTileY(sprite.getY());
			
			/* Test sprite for match. */
			if (sprite.compareTag(tag) && tileX == spriteTileX && tileY == spriteTileY) {
				return sprite;
			}
		}
		return null;
	}
	
	

	
	
	
	
	/** 
	 * Finds the first sprite that matches a given tag.
	 * 
	 * @param tag
	 * @return
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
		
		/* Set the input. */
		this.input = input;
		
		/* Exit game if 'ESC' key is pressed. */
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			System.exit(0);
		}
		/* Loads next level once current one is finished. */
		if (isLevelOver()) {
			restartLevel();
		}
		/* Restart level if 'R' key is pressed. */
		if (input.isKeyDown(Input.KEY_R)) {
			restartLevel();
		}
		/* Undo a move if 'Z' key is pressed. */
		if (input.isKeyPressed(Input.KEY_Z)) {
			undoMovables();
		}
		
		
		for (Sprite sprite : sprites) {
			
			/* Check for null. */
			if (sprite == null) {
				continue;
			}
			
			/* Update the sprite. */
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
	
	
	
		
	
	/** Undoes a bunch of moves.
	 */
	public void undoMovables() {
		
		//int maxSize = getMaxSize();
		
		for (Sprite sprite : sprites) {
			if (sprite instanceof Movable) {
				((Movable)sprite).undo();
			}
		}
	}
	
	
	/** Gets the max move history stack size for
	 * all movable objects.
	 * @return
	 */
	private int getMaxSize() {
		
		int maxSize = 0;
		int stackSize;
		
		for (Sprite sprite : sprites) {
			if (sprite instanceof Movable) {
				stackSize = ((Movable)sprite).getStackSize();
					if (stackSize > maxSize) {
						maxSize = stackSize;
					}
			}
		}
		return maxSize;
	}
	
	
	public void updateMovableHistory() {
		
		for (Sprite sprite : sprites) {
			if (sprite.compareTag("Block") || sprite.compareTag("Player")) {
				
				((Movable)sprite).addToHistory();
			}

		}
		
	}
	
		
	/** Takes a sprite and 'creates' it, or in other
	 * words just adds it to the world's sprite list.
	 * 
	 * @param sprite
	 * @return void
	 */
	public void createSprite(Sprite sprite) {
		this.spritesToCreate.add(sprite);
	}
	
	
	/** Takes a sprite and 'destroys' it, or in other
	 * words removes it from the world's sprite list.
	 * 
	 * @param sprite
	 * @return void
	 */
	public void destroySprite(Sprite sprite) {
		this.spritesToDestroy.add(sprite);
	}
	
	
	
	/* Bunch of getters/setters. 
	 */
	
	public void setPlayerMoved(boolean playerMoved) {
		this.playerMoved = playerMoved;
	}
	
	public boolean getPlayerMoved() {
		return this.playerMoved;
	}
	
	public Input getInput() {
		return this.input;
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
