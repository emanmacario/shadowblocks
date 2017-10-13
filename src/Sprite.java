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

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;

import java.util.ArrayList;

public abstract class Sprite {	
	
	/** Sprite constants. 
	 */
	public static final int DIR_NONE = 0;
	public static final int DIR_LEFT = 1;
	public static final int DIR_RIGHT = 2;
	public static final int DIR_UP = 3;
	public static final int DIR_DOWN = 4;
	
	
	/** Sprite attributes.
	 */
	private Image image;
	private float x;
	private float y;
	private ArrayList<String> tags;
		
	
	/** Creates a Sprite object.
	 * 
	 * @param imageSource The relative path to the sprite's image.
	 * @param x           The sprite's x-coordinate, in pixels.
	 * @param y           The sprite's y-coordinate, in pixels.
	 */
	public Sprite(String imageSource, float x, float y) {
		try {
			this.image = new Image(imageSource);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.x = x;
		this.y = y;
		this.tags = new ArrayList<>();
	}
	
	
	/** Associates a tag with this sprite.
	 * 
	 * @param tag
	 * @return void
	 */
	public void addTag(String tag) {
		this.tags.add(tag);
	}
	
	/** Removes a tag from this sprite.
	 * 
	 * @param tag
	 * @return void
	 */
	public void removeTag(String tag) {
		this.tags.remove(tag);
	}
	
	
	/** Returns whether a sprite
	 * possesses a given tag.
	 * 
	 * @param otherTag
	 * @return True if sprite has tag else false.
	 */
	public boolean compareTag(String otherTag) {
		
		/* Test each individual tag
		 * for equality.
		 */
		for (String tag : tags) {
			if (tag.equals(otherTag)) {
				return true;
			}
		}
		return false;
	}
	

	/** Returns a sprite's x-coordinate, in pixels.
	 * 
	 * @param void
	 * @return x 
	 */
	public float getX() {
		return this.x;
	}
	
	
	/** Sets a sprite's x-coordinate, in pixels.
	 * 
	 * @param x  New x-coordinate.
	 * @return void
	 */
	public void setX(float x) {
		this.x = x;
	}
	
	
	/** Returns a sprite's y-coordinate, in pixels.
	 * 
	 * @param void
	 * @return y 
	 */
	public float getY() {
		return this.y;
	}
	
	
	/** Sets a sprite's y-coordinate, in pixels.
	 * 
	 * @param y  New y-coordinate.
	 * @return void
	 */
	public void setY(float y) {
		this.y = y;
	}
	
	
	/** Update the game state for a frame. Default is an
	 * empty method. Subclasses provide their own overridden
	 * implementations.
	 * 
	 * @param world  The World object the sprite belongs to.
     * @param delta  Time passed since last frame (milliseconds).
     * @return void
     */
	public void update(World world, int delta) {
		
	}
	
	
	/** Renders a sprite, centred on screen. 
	 * 
	 * @param g  The Slick graphics container object.
	 * @throws SlickException
	 * @return void
	 */
	public void render(Graphics g) throws SlickException {
		image.drawCentered(x, y);
	}
	
	
	/** Standard overridden equals method.
	 * 
	 * @param object  Another object to be tested for equality.
	 * @return True if objects are the same else false.
	 */
	@Override
	public boolean equals(Object other) {
		
		if (this == other) {
			return true;
		}
		
		if (other == null) {
			return false;
		}
		
		if (this.getClass() != other.getClass()) {
			return false;
		}
		
		Sprite sprite = (Sprite) other;
		return Loader.getTileX(this.x) == Loader.getTileX(sprite.x)
				&& Loader.getTileY(this.y) == Loader.getTileY(sprite.y);
	}
	
}
