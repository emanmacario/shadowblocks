
public abstract class Unit extends Movable {

	private int direction;
	
	public Unit(String imageSource, float x, float y, int direction) {
		super(imageSource, x, y);
		this.direction = direction;
	}
	
	
	/** Getters/setters. */
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public int getDirection() {
		return this.direction;
	}

}
