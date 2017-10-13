/* SWEN20003 Object Oriented Software Development
 * Project 2 - Shadow Blocks
 * 
 * Author: Emmanuel Macario <macarioe>
 * Student Number: 831659
 * Email: macarioe@student.unimelb.edu.au
 * 
 * This file contains the Loader class, which is a static class
 * responsible for loading the map from the CSV level file.
 */
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Loader {	
	
	/** Static Loader attributes. Used
	 * for centring sprites during rendering
	 * and checking bounds.
	 */
	private static int worldWidth;
	private static int worldHeight;
	private static int offsetX;
	private static int offsetY;
	
	
	/** Returns the x-coordinate, in tile units,
	 * taking into account offset.
	 * 
	 * @param x  x-coordinate, in pixels.
	 * @return tileX
	 */
	public static int getTileX(float x) {
		return (int)(x - offsetX)/App.TILE_SIZE;
	}
	
	
	/** Returns the y-coordinate, in tile units,
	 * taking into account offset.
	 * 
	 * @param y  y-coordinate, in pixels.
	 * @return tileY
	 */
	public static int getTileY(float y) {
		return (int)(y - offsetY)/App.TILE_SIZE;
	}
	
	
	/** Returns whether a given (x,y) coordinate, in 
	 * tile units is within the map boundaries.
	 * 
	 * @param tileX
	 * @param tileY
	 * @return True if in bounds else false.
	 */
	public static boolean inBounds(int tileX, int tileY) {
		if (tileX < 0 || tileX >= worldWidth ||
			tileY < 0 || tileY >= worldHeight) {
			return false;
		}
		return true;
	}
	
	/** Create a sprite given its type and location.
	 * 
	 * @param type  The type of the sprite.
	 * @param x		The x-coordinate, in pixel units.
	 * @param y		The y-coordinate, in pixel units.
	 * @return		The sprite object.
	 */
	private static Sprite createSprite(String type, float x, float y) {
		switch (type) {
			case "wall":
				return new Wall(x, y);
			case "floor":
				return new Floor(x, y);
			case "stone":
				return new Stone(x, y);
			case "target":
				return new Target(x, y);
			case "player":
				return new Player(x, y);
			case "cracked":
				return new Cracked(x,y);
			case "switch":
				return new Switch(x,y);
			case "door":
				return new Door(x,y);
			case "skeleton":
				return new Skeleton(x,y);
			case "rogue":
				return new Rogue(x,y);
			case "mage":
				return new Mage(x,y);
			case "ice":
				return new Ice(x,y);
			case "tnt":
				return new TNT(x,y);
		}
		return null;
	}
	
	
	/** Loads in all sprites from a given level file.
	 * 
	 * @param filename     Name of the level file.
	 * @return spriteList  A list of sprites.
	 */
	public static ArrayList<Sprite> loadSprites(String filename) {
		ArrayList<Sprite> spriteList = new ArrayList<>();
		
		/* Open the file.
		 */
		try (Scanner scanner = new Scanner(new FileReader(filename))) {
			
			String[] data;
			
			/* Find the world size.
			 */
			data = scanner.nextLine().split(",");
			worldWidth = Integer.parseInt(data[0]);
			worldHeight = Integer.parseInt(data[1]);
			
			/* Calculate the x and y axis offsets.
			 */
			offsetX = (App.SCREEN_WIDTH - worldWidth * App.TILE_SIZE) / 2;
			offsetY = (App.SCREEN_HEIGHT - worldHeight * App.TILE_SIZE) / 2;
			
			/* Loop over every line of the file.
			 */
			while (scanner.hasNextLine()) {
				String type;
				float x, y;
				
				/* Split the line into parts.
				 */
				data = scanner.nextLine().split(",");
				type = data[0];
				x = Integer.parseInt(data[1]);
				y = Integer.parseInt(data[2]);
				
				/* Account for offset.
				 */
				x = x * App.TILE_SIZE + offsetX;
				y = y * App.TILE_SIZE + offsetY;
				
				/* Create sprite and add it to the list.
				 */
				spriteList.add(createSprite(type, x, y));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/* Associate the door and switch sprites if 
		 * they exist in the loaded in level.
		 */
		Sprite door = null;
		Sprite doorSwitch = null;
		
		for (Sprite sprite : spriteList) {
			if (sprite instanceof Switch) {
				doorSwitch = sprite;
			} else if (sprite instanceof Door) {
				door = sprite;
			}
		}
		
		if (doorSwitch != null && door != null) {
			((Switch)doorSwitch).setDoor((Door)door);
		}
		
		return spriteList;
	}
}
	
	
	