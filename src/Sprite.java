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
	
	/** 
	 * Sprite attributes. 
	 */
	public static final int DIR_NONE = 0;
	public static final int DIR_LEFT = 1;
	public static final int DIR_RIGHT = 2;
	public static final int DIR_UP = 3;
	public static final int DIR_DOWN = 4;
	
	private Image image;
	private float x;
	private float y;
	private ArrayList<String> tags;
		
	
	/** 
	 * Creates a Sprite object.
	 * 
	 * @param imagePath  The relative path to the sprite's image.
	 * @param x          The sprite's x-coordinate, in pixels.
	 * @param y          The sprite's y-coordinate, in pixels.
	 */
	public Sprite(String imagePath, float x, float y) {
		try {
			this.image = new Image(imagePath);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.x = x;
		this.y = y;
		this.tags = new ArrayList<>();
	}
	
	
	public void addTag(String tag) {
		this.tags.add(tag);
	}
	
	public void removeTag(String tag) {
		this.tags.remove(tag);
	}
	
	public boolean compareTag(String otherTag) {
		for (String tag : tags) {
			if (tag.equals(otherTag)) {
				return true;
			}
		}
		return false;
	}
	

	/**
	 * Getters and setters for sprite's pixel coordinates. 
	 */
	public float getX() {
		return this.x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return this.y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	/** 
	 * Update the game state for a frame.
	 * 
     * @param delta  Time passed since last frame (milliseconds).
     * @return void
     */
	public void update(int delta) {
		
	}
	
	
	/** 
	 * @param g  The Slick graphics container object.
	 * @throws SlickException
	 * @return void
	 */
	public void render(Graphics g) throws SlickException {
		image.drawCentered(x, y);
	}
	
	
	/** Standard overridden equals method.
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
