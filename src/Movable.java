import org.newdawn.slick.geom.Vector2f;


public abstract class Movable extends Sprite {
	
	
	/** Movable object attributes.
	 */
	private HistoryStack history;
	
	
	/** Creates a new Movable object, with 
	 * an empty movement history.
	 * 
	 * @param imageSource The relative path to the sprite's image.
	 * @param x           The sprite's x-coordinate, in pixels.
	 * @param y           The sprite's y-coordinate, in pixels.
	 */
	public Movable(String imageSource, float x, float y) {
		super(imageSource, x, y);
		this.history = new HistoryStack();
	}
	
	
	/** Moves sprite in a given direction, with a default
	 * displacement of the standard tile size.
	 * 
	 * @param direction
	 * @return void
	 */
	public void moveToDestination(int direction) {
		
		float deltaX = 0, deltaY = 0;
		
		/* Translate the direction into x and y displacement. 
		 */
		switch (direction) {
			case DIR_LEFT:
				deltaX = -App.TILE_SIZE;
				break;
			case DIR_RIGHT:
				deltaX = App.TILE_SIZE;
				break;
			case DIR_UP:
				deltaY = -App.TILE_SIZE;
				break;
			case DIR_DOWN:
				deltaY = App.TILE_SIZE;
				break;
			case DIR_NONE:
				break;
		}
		
		/* Perform any other required actions 
		 * specific to the sprite currently moving.
		 */
		onMove(this.getX() + deltaX, this.getY() + deltaY);
	}
	
	
	/** When a call to moveToDestination is made, convert the data
	 * and execute the move.
	 * 
	 * @param newX
	 * @param newY
	 * @return void
	 */
	public void onMove(float newX, float newY) {
		
		/* Move to the new destination. 
		 */
		this.setX(newX);
		this.setY(newY);
	}
		
	
	/** Returns a candidate x-coordinate, in pixel units,
	 * based on the direction of movement.
	 * 
	 * @param x          Current x-coordinate
	 * @param direction  The direction of movement
	 * @return The new x-coordinate.
	 */
	public float getTestX(float x, int direction) {
		switch (direction) {
			case DIR_LEFT:
				return x - App.TILE_SIZE;
			case DIR_RIGHT:
				return x + App.TILE_SIZE;
			default:
				return x;
		}
	}
	
	
	/** Returns a candidate y-coordinate, in pixel units,
	 * based on the direction of movement.
	 * 
	 * @param y          Current y-coordinate
	 * @param direction  The direction of movement
	 * @return The new y-coordinate.
	 */
	public float getTestY(float y, int direction) {
		switch(direction) {
			case DIR_UP:
				return y - App.TILE_SIZE;
			case DIR_DOWN:
				return y + App.TILE_SIZE;
			default:
				return y;
		}
	}
	
	
		
	/** Returns true if a movable object has a 
	 * movement history.
	 * 
	 * @return True if yes, else false.
	 */
	public boolean hasHistory() {
		if (this.history.getStackSize() > 0) {
			return true;
		}
		return false;
	}
	
	
	/** Adds a move to the movement history. 
	 * 
	 * @param void
	 * @return void
	 */
	public void addToHistory() {
		history.push(getX(), getY());
		
	}
	
	/** Undo the most previous move done by a movable object.
	 * 
	 * @param void
	 * @return void
	 */
	public void undo() {
		
		/* First, check if the sprite has a move history. 
		 */
		if (this.hasHistory()) {
			
			/* Get the most previous move, and undo it. 
			 */
			Vector2f move = history.pop();
			this.setX(move.getX());
			this.setY(move.getY());
		}
	}
	
	
	/** Returns the size of the stack
	 * containing all movement history.
	 * 
	 *  @param void
	 *  @return size
	 */
	public int getStackSize() {
		return this.history.getStackSize();
	}
	
	
	/** Returns the move history of
	 * a Movable object.
	 * 
	 * @return history
	 */
	public HistoryStack getHistory() {
		return this.history;
	}
}
