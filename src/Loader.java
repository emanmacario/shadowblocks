/* SWEN20003 Object Oriented Software Development
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
	
	/** 
	 * Loader constants.
	 */
	private static final String PLAYER_TILE     = "player";
	private static final String WALL_TILE       = "wall";
	private static final String STONE_TILE      = "stone";
	private static final String FLOOR_TILE      = "floor";
	private static final String TARGET_TILE     = "target";
	private static final String IMAGE_DIR       = "res/";
	private static final String IMAGE_EXTENSION = ".png";
	
	
	/** 
	 * Converts a pixel coordinate to a tile coordinate,
	 * and returns if that location is a blocked tile.
	 * 
	 * @param x  The x world coordinate.
	 * @param y  The y world coordinate.
	 * @return   Boolean indicating whether tile is blocked.
	 */
	public static boolean isBlocked(float x, float y) {
	
		// Calculate candidate position of sprite.
		int xPos = toTileCoordinate(x);
		int yPos = toTileCoordinate(y);
		
		// Retrieve wall coordinates from World class.
		int[][] wallCoordinates = World.getWallCoordinates();
		
		// Linear search through all wall coordinates to see 
		// if the the tile coordinate is the location of a wall.
		for (int i = 0; i < wallCoordinates.length; i++) {
			if (wallCoordinates[i][0] == xPos && 
					wallCoordinates[i][1] == yPos) {
				
				return true;
			}
		}
		return false;
	}
	
	
	/** 
	 * Counts the number of wall tiles in the level file.
	 * 
	 * @param filename  The name of the level file.
	 * @return          The number of walls in the level.
	 */
	private static int countWalls(String filename) {
		int nWalls = 0;
		
		try (Scanner level = new Scanner(new FileReader(filename))) {
			while (level.hasNextLine()) {
				String[] tileData = level.nextLine().split(",");
				
				if (tileData[0].equals(WALL_TILE)) {
					nWalls++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nWalls;
	}
	
	
	/**
	 * Reads contents of a level file and returns an array
	 * containing the coordinates of all wall tiles.
	 * 
	 * @param filename  The name of the level file.
	 * @return 		    An array of wall coordinates.
	 */
	public static int[][] loadWallCoordinates(String filename) {
		
		int nWalls = countWalls(filename);
		int[][] wallCoordinates = new int[nWalls][2];
		
		try (Scanner level = new Scanner(new FileReader(filename))) {
			
			// While there are still tiles to be read...
			int count = 0;
			while (level.hasNextLine()) {
				String[] tileData = level.nextLine().split(",");
				
				// If a tile is a wall tile, store its coordinates.
				if (tileData[0].equals(WALL_TILE)) {
					wallCoordinates[count][0] = 
									Integer.parseInt(tileData[1]);
					wallCoordinates[count][1] = 
									Integer.parseInt(tileData[2]);
					count++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wallCoordinates;
	}
	
	
	/** 
	 * Converts a tile coordinate into a pixel coordinate.
	 */
	private static float toPixelCoordinate(int x) {
		return (float)(x * App.TILE_SIZE);
	}
	
	/** 
	 * Converts a pixel coordinate into a tile coordinate.
	 */
	private static int toTileCoordinate(float x) {
		return (int)(x / App.TILE_SIZE);
	}
	
	
	/** 
	 * Returns location of sprite image based on the tile type
	 * 
	 * @param tileType  The type of the tile. Either wall, floor, stone, 
	 * 			        player or target.
	 * 
	 * @return          The relative path of the respective image file.
	 */
	private static String getImagePath(String tileType) {
		return IMAGE_DIR + tileType + IMAGE_EXTENSION;
	}
	
	
	/**
	 * Returns the width of the map, in tile units.
	 * 
	 * @param filename  The name of the level file.
	 * @return          Map width in tile units.
	 */
	public static int getMapWidth(String filename) {
		int mapWidth=0;
		
		try (Scanner level = new Scanner(new FileReader(filename))) {
			
			String[] mapDimensions = level.nextLine().split(",");
			mapWidth = Integer.parseInt(mapDimensions[0]);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapWidth;
	}
	
	
	/**
	 * Returns the height of the map, in tile units.
	 * 
	 * @param filename  The name of the level file.
	 * @return          Map height in tile units.
	 */
	public static int getMapHeight(String filename) {
		int mapHeight=0;
		
		try (Scanner level = new Scanner(new FileReader(filename))) {
			
			 String[] mapDimensions = level.nextLine().split(",");
			 mapHeight = Integer.parseInt(mapDimensions[1]);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapHeight;
	}
	
	
	
	/**
	 * Loads the sprites from a given level file.
	 * 
	 * @param filename  Name of the level file.
	 * @return          Array of loaded in Sprites.
	 */
	public static Sprite[] loadSprites(String filename) {
		
		// First, get a list of all the sprites. This is
		// so we don't have to worry about the exact 
		// quantity of sprites being loaded in.
		ArrayList<Sprite> spriteList = getSpriteList(filename);
		
		// Now, convert the list to an array of sprites.
		Sprite[] sprites = spriteList
							.toArray(new Sprite[spriteList.size()]);
	
		return sprites;
	}
	
	/**
	 * Creates an intermediate ArrayList of the 
	 * Sprites contained in a level file.
	 * 
	 * @param filename     Name of the level file.
	 * @return spriteList  ArrayList of Sprites.
	 */
	private static ArrayList<Sprite> getSpriteList(String filename) {
		ArrayList<Sprite> spriteList = new ArrayList<Sprite>();
		
		try (Scanner level = new Scanner(new FileReader(filename))) {
			
			// Skip the line consisting of map dimensions.
			level.nextLine();
			
			// While there are still tiles to be read...
			while (level.hasNextLine()) {
				
				// Get the next tile.
				String[] tileData = level.nextLine().split(",");
				
				// Parse the tile data.
				String type = tileData[0];
				int tileX = Integer.parseInt(tileData[1]);
				int tileY = Integer.parseInt(tileData[2]);
				
				// Convert data values to meaningful game information.
				String imagePath = getImagePath(type);
				float pixelX = toPixelCoordinate(tileX);
				float pixelY = toPixelCoordinate(tileY);
				
				// Add sprite to the array of sprites.
				switch (type) {
					case PLAYER_TILE:
						Sprite player = new Player(imagePath, pixelX, pixelY);
						spriteList.add(player);
						break;
					case WALL_TILE:
						Sprite wall = new Wall(imagePath, pixelX, pixelY);
						spriteList.add(wall);
						break;
					case TARGET_TILE:
						Sprite target = new Target(imagePath, pixelX, pixelY);
						spriteList.add(target);
						break;
					case FLOOR_TILE:
						Sprite floor = new Floor(imagePath, pixelX, pixelY);
						spriteList.add(floor);
						break;
					case STONE_TILE:
						Sprite stone = new Stone(imagePath, pixelX, pixelY);
						spriteList.add(stone);
						break;
					default:
						System.err.println("Error. Invalid tile type.");
						System.exit(0);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return spriteList;
	}
}
