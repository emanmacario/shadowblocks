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
		/* Now, actually move. */
		onMove(direction, this.getX() + deltaX, this.getY() + deltaY);

	}
	
	/** Moves sprite in a given direction, with a 
	 * displacement of the input speed.
	 * 
	 * @param direction
	 * @return void
	 */
	public void moveToDestination(int direction, float speed) {
		float deltaX = 0, deltaY = 0;
		
		/* Translate the direction into x and y displacement. */
		switch (direction) {
			case DIR_LEFT:
				deltaX = -speed;
				break;
			case DIR_RIGHT:
				deltaX = speed;
				break;
			case DIR_UP:
				deltaY = -speed;
				break;
			case DIR_DOWN:
				deltaY = speed;
				break;
			default:
				break;
		}
		/* Now, actually move. */
		onMove(direction, this.getX() + deltaX, this.getY() + deltaY);
	}
	
	
	/** When a call to moveToDestination is made, convert the data
	 * and execute the move.
	 * 
	 * @param direction
	 * @param testX
	 * @param testY
	 */
	public void onMove(int direction, float testX, float testY) {
		
		/* Add the original position to history. */
		this.addToHistory(this.getX(), this.getY());
		
		/* Move to the new destination. */
		this.setX(testX);
		this.setY(testY);
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
	public void addToHistory(float x, float y) {
		this.history.push(x, y);
		
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
}
