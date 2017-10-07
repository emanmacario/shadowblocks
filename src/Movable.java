import org.newdawn.slick.geom.Vector2f;


public abstract class Movable extends Sprite {
	
	/** Instance variables.
	 */
	private HistoryStack history;
	
	/** 
	 * Creates a Movable object.
	 */
	public Movable(String imagePath, float x, float y) {
		super(imagePath, x, y);
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
			default:
				return;
		}
		
		/* Add previous position to history. */
		this.addToHistory(this.getX(), this.getY());
		
		/* Move to the new destination. */
		this.setX(this.getX() + deltaX);
		this.setY(this.getY() + deltaY);
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
				
		if (this.hasHistory()) {
			
			Vector2f move = this.history.pop();
			
			this.setX(move.getX());
			this.setY(move.getY());
		}
	}
}
