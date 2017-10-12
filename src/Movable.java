import org.newdawn.slick.geom.Vector2f;


public abstract class Movable extends Sprite {
	
	
	/** Instance variables.
	 */
	private HistoryStack history;
	
	
	/** 
	 * Creates a Movable object.
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
		
		/* Translate the direction into x and y displacement. */
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
		
	
	/** Returns a pixel coordinate based on the direction
	 * of movement.
	 * 
	 * @param x
	 * @param direction
	 * @return position
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
	 * @return
	 */
	public boolean hasHistory() {
		if (this.history.getStackSize() > 0) {
			return true;
		}
		return false;
	}
	
	
	/** Adds a move to the move history. */
	public void addToHistory() {
		this.history.push(this.getX(), this.getY());
		
	}
	
	/** Undo the most previous move done by a movable object.
	 * 
	 * @param void
	 * @return void
	 */
	public void undo() {
		
		/* First, check if the sprite has a move history. */
		if (this.hasHistory()) {
			
			/* Get the most previous move, and undo it. */
			Vector2f move = this.history.pop();
			
			this.setX(move.getX());
			this.setY(move.getY());
		}
	}
	
	/** Returns the size of the stack. */
	public int getStackSize() {
		return this.history.getStackSize();
	}
	
	
	public HistoryStack getHistory() {
		return this.history;
	}
}
