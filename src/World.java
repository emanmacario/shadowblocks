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
	private boolean playerMoved;
	private int currentLevel;
		
	

	/** Creates the World object.
	 */
	public World() {
		this.sprites = Loader.loadSprites("res/levels/3.lvl");
		this.playerMoved = false;
		this.currentLevel = 3;
	}
	
	
	/** Restarts the current level.
	 * 
	 * @param void
	 * @return void
	 */
	public void restartLevel() {
		this.sprites = Loader.loadSprites("res/levels/" + currentLevel + ".lvl");
	}
	
	
	/** Loads the next level.
	 * 
	 * @param void
	 * @return void
	 */
	public void loadNextLevel() {
		this.currentLevel += 1;
		this.sprites = Loader.loadSprites("res/levels/" + currentLevel + ".lvl"); 
	}
	
	/** Checks if the current level is over by checking
	 * if each target tile is covered by a block.
	 * 
	 * @param void
	 * @return null
	 */
	public boolean isLevelOver() {
		
		if (this.currentLevel == 5) {
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
	
	
	private int getDirection(Input input) {
		
		if (input.isKeyPressed(Input.KEY_W)) {
			return Sprite.DIR_UP;
		} else if (input.isKeyPressed(Input.KEY_A)) {
			return Sprite.DIR_LEFT;
		} else if (input.isKeyPressed(Input.KEY_S)) {
			return Sprite.DIR_DOWN;
		} else if (input.isKeyPressed(Input.KEY_D)) {
			return Sprite.DIR_RIGHT;
		}
		return Sprite.DIR_NONE;
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
		
		/* Exit game if 'ESC' key is pressed. */
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			System.exit(0);
		}
		/* Loads next level once current one is finished. */
		if (isLevelOver()) {
			loadNextLevel();
		}
		/* Restart level if 'R' key is pressed. */
		if (input.isKeyDown(Input.KEY_R)) {
			restartLevel();
		}
		/* Undo a move if 'Z' key is pressed. */
		if (input.isKeyPressed(Input.KEY_Z)) {
			undoMovables();
		}
		
		int direction = getDirection(input);
		
		if (direction != Sprite.DIR_NONE) {
			playerMoved = true;
		} else {
			playerMoved = false;
		}
		
		
		for (Sprite sprite : sprites) {
			
			/* Check for null. */
			if (sprite == null) {
				continue;
			}
			
			/* Update the sprite. */
			sprite.update(delta);
			
			
			if (sprite instanceof Skeleton) {
				float testX = getTestX(sprite.getX(), ((Unit)sprite).getDirection());
				float testY = getTestY(sprite.getY(), ((Unit)sprite).getDirection());
				
				if (isBlocked(testX, testY)) {
					((Skeleton)sprite).reverseDirection();
				}
				continue;
			}
		
			if (!playerMoved) {
				continue;
			}
			
			/* If a sprite is a 'unit', we will update it. */
			if (sprite.compareTag("Unit")) {
				
				/* Update player direction. */
				if (sprite instanceof Player) {
					((Player)sprite).setDirection(direction);
				}
				
				/* Get new position coordinates. */
				float testX = getTestX(sprite.getX(), ((Unit)sprite).getDirection());
				float testY = getTestY(sprite.getY(), ((Unit)sprite).getDirection());
				
				/* And the next tile after the new candidate position. */
				float blockX = getTestX(testX, ((Unit)sprite).getDirection());
				float blockY = getTestY(testY, ((Unit)sprite).getDirection());
				
				
				/* If the sprite is a skeleton, check if we need to reverse
				 * its direction.
				 
				if (sprite instanceof Skeleton) {
					
					System.out.println("Skeleton");
					
					if (isBlocked(testX, testY)) {
						System.out.println("Skeleton blocked");
						((Skeleton)sprite).reverseDirection();
					}
					continue;
				}
				*/
				
				
				if (sprite instanceof Rogue) {
							
					if (isBlocked(testX, testY)) {
						
						Sprite block = getSpriteOfType("Block", testX, testY);
						
						if (block == null) {
							((Rogue)sprite).reverseDirection();
						} else if (isBlocked(blockX, blockY)) {
							((Rogue)sprite).reverseDirection();
						}
					}
				}
				
			
				
				int unitDirection = ((Unit)sprite).getDirection();
				
				
				/* If a unit is not blocked, then just move to the
				 * new destination.
				 */
				if (!isBlocked(testX, testY)) {
					((Movable)sprite).moveToDestination(unitDirection);
					
				} 
				/* Otherwise, check if a block can be pushed. */
				else {
					
					Sprite block = getSpriteOfType("Block", testX, testY);
					
					if (block == null) {
						continue;
					}
					
					Sprite oldTarget = getSpriteOfType("Target", testX, testY);
					Sprite newTarget = getSpriteOfType("Target", blockX, blockY);
					
					if (!isBlocked(blockX, blockY)) {
						((Movable)sprite).moveToDestination(unitDirection);
						
						((Pushable)block).push(unitDirection);
						
						
						if (oldTarget != null) {
							((Target)oldTarget).setActivated(false);
						}
						
						if (newTarget != null) {
							((Target)newTarget).setActivated(true);
						}
						
					} else if (block instanceof TNT) {
						
						Sprite crackedWall = getSpriteOfType("Blocked", blockX, blockY);
						
						if (crackedWall != null && crackedWall instanceof Cracked) {
							
							/* Create a new explosion sprite. 
							 */
							Explosion explosion = new Explosion(blockX, blockY);
							
							/* Destroy the cracked wall and TNT block, and
							 * add the explosion sprite to the sprite list.
							 */
							this.sprites.remove(crackedWall);
							this.sprites.remove(block);
							this.sprites.add(explosion);
							break;
						}
					}
				}
			}
			/* Check if the player is dead. */
			if (isPlayerDead()) {
				restartLevel();
			}
		}
	}
	
	
	/** Undoes a bunch of moves.
	 */
	public void undoMovables() {
		
		int maxSize = getMaxSize();
		
		for (Sprite sprite : sprites) {
			if (sprite instanceof Movable) {
				if (((Movable)sprite).getStackSize() == maxSize) {
					((Movable)sprite).undo();
				}
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
		
		int maxSize = getMaxSize();
		
		for (Sprite sprite : sprites) {
			if (sprite instanceof Movable &&
					((Movable)sprite).getStackSize() < maxSize) {
				
				((Movable)sprite).addToHistory(sprite.getX(), sprite.getY());	
			}
		}
	}
	
	
	/** Returns if a player is dead, in other words
	 * if the player has stepped onto the same tile as an enemy unit.
	 * 
	 * @return
	 */
	public boolean isPlayerDead() {
		
		/* Get the player sprite and see if it shares a
		 * tile position with an enemy unit.
		 */
		Sprite player = getSpriteOfType("Player");
		Sprite enemy = null;
		
		if (player != null) {
			enemy = getSpriteOfType("Enemy", player.getX(), player.getY());
		}
		
		if (enemy != null) {
			return true;
		}
		return false;
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
