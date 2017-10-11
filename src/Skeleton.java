
public class Skeleton extends Unit {
	
	private Timer timer;
	
	public Skeleton(float x, float y) {
		super("res/skull.png", x, y, DIR_UP);
		this.timer = new Timer(1000);
		this.addTag("Unit");
		this.addTag("Enemy");
	}
		
	
	@Override
	public void onMove(int direction, float testX, float testY) {
		super.onMove(direction, testX, testY);
	}
	
	
	@Override
	public void update(World world, int delta) {
		
		/* Check if the skeleton's next position
		 * is blocked, and reverse direction if so.
		 */
		float testX = this.getTestX(this.getX(), this.getDirection());
		float testY = this.getTestY(this.getY(), this.getDirection());
		
		if (world.isBlocked(testX, testY)) {
			this.reverseDirection();
		}
		
		/* Update the timer. */
		this.timer.update(delta);
		
		/* Reset the timer if it expires. */
		if (timer.expired()) {
			this.moveToDestination(this.getDirection());
			timer.reset();
		}
	}
	
	
	/* If the Skeleton was blocked and could not 
	 * move, reverse its current direction.
	 */
	public void reverseDirection() {
		
		if (this.getDirection() == DIR_UP) {
			this.setDirection(DIR_DOWN);
		} else {
			this.setDirection(DIR_UP);
		}
		timer.reset();
	}
}