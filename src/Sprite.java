/* SWEN20003 Object Oriented Software Development
 * 
 * Author: Emmanuel Macario <macarioe>
 * Student Number: 831659
 * Email: macarioe@student.unimelb.edu.au
 * 
 * This file contains the Sprite class, which represents
 * one sprite on screen, including its graphics and its
 * behaviour.
 */


import org.newdawn.slick.Input;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;

public abstract class Sprite {	
	
	/** 
	 * Sprite attributes. 
	 */
	private Image sprite;
	private float x;
	private float y;
		
	
	/** 
	 * Creates a Sprite object.
	 * 
	 * @param imagePath  The relative path to the sprite's image.
	 * @param x          The sprite's x-coordinate, in pixels.
	 * @param y          The sprite's y-coordinate, in pixels.
	 */
	public Sprite(String imagePath, float x, float y) {
		try {
			this.sprite = new Image(imagePath);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.x = x;
		this.y = y;
	}
	
	
	/** 
	 * Getters and setters for sprite's pixel coordinates. 
	 * The setters ensure that a sprite's location is 
	 * never set outside of the game screen.
	 */
	public float getX() {
		return this.x;
	}
	
	public void setX(float x) {
		if (x < 0) {
			this.x = 0;
		} else if (x > App.SCREEN_WIDTH - App.TILE_SIZE) {
			this.x = App.SCREEN_WIDTH - App.TILE_SIZE;
		} else {
			this.x = x;
		}
	}
	
	public float getY() {
		return this.y;
	}

	public void setY(float y) {
		if (y < 0) {
			this.y = 0;
		} else if (y > App.SCREEN_HEIGHT - App.TILE_SIZE) {
			this.y = App.SCREEN_HEIGHT - App.TILE_SIZE;
		} else {
			this.y = y;
		}
	}
		
	
	/** 
	 * Offsets the sprite's x pixel coordinate, to ensure
	 * that the sprite is rendered in the right (centred) 
	 * position on the game screen.
	 */
	private float toWorldXCoordinate(float x) {
		
		return (float)(x + (App.SCREEN_WIDTH 
				- (1.0* App.TILE_SIZE * World.mapWidth)) / 2);
				
	}
	
	
	/** 
	 * Offsets the sprite's y pixel coordinate, to ensure
	 * that the sprite is rendered in the right (centred)
	 * position on the game screen.
	 */
	private float toWorldYCoordinate(float y) {
		
		return (float)(y + (App.SCREEN_HEIGHT 
				- (1.0* App.TILE_SIZE * World.mapWidth)) / 2);
				
	}
	
	
	/** 
	 * Update the game state for a frame.
	 * 
     * @param input  The Slick input object, used for getting keyboard input.
     * @param delta  Time passed since last frame (milliseconds).
     * @return void
     */
	public void update(Input input, int delta) {
	}
	
	
	/** 
	 * @param g  The Slick graphics container object.
	 * @throws SlickException
	 * @return void
	 */
	public void render(Graphics g) throws SlickException {
		sprite.drawCentered(toWorldXCoordinate(this.x), 
								toWorldYCoordinate(this.y));
	}
}
