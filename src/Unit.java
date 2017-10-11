
public abstract class Unit extends Movable {

	private int direction;
	
	public Unit(String imageSource, float x, float y, int direction) {
		super(imageSource, x, y);
		this.direction = direction;
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
	
	
	/** Getters/setters. */
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public int getDirection() {
		return this.direction;
	}

}
