

public class Rogue extends Unit {
	
	public Rogue(float x, float y) {
		super("res/rogue.png", x, y, DIR_LEFT);
		this.addTag("Unit");
		this.addTag("Enemy");
	}
	
	@Override
	public void onMove(int direction, float testX, float testY) {
		super.onMove(direction, testX, testY);
	}
	
	@Override
	public void update(int delta) {
	}
	
	
	/* If the Rogue was blocked and could not 
	 * move, reverse its current direction.
	 */
	public void reverseDirection() {
		
		if (this.getDirection() == DIR_LEFT) {
			this.setDirection(DIR_RIGHT);
		} else {
			this.setDirection(DIR_LEFT);
		}
	}
}