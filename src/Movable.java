
public abstract class Movable extends Sprite {
	
	/** 
	 * Creates a Movable object.
	 */
	public Movable(String imagePath, float x, float y) {
		super(imagePath, x, y);
	}
	
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
		}
		
		/* Set new position. */
		this.setX(this.getX() + deltaX);
		this.setY(this.getY() + deltaY);
	}
}
