
public class Skeleton extends Movable {
	
	private int direction;
	private Timer timer;
	
	public Skeleton(float x, float y) {
		super("res/skull.png", x, y);
		this.direction = DIR_UP;
		this.timer = new Timer(1000);
		this.addTag("Enemy");
	}
		
	
	@Override
	public void onMove(int direction, float testX, float testY) {
		super.onMove(direction, testX, testY);
	}
	
	
	@Override
	public void update(World world, int delta) {
		
		/* Update the timer. */
		this.timer.update(delta);
		
		
		/* Check if the skeleton's next position
		 * is blocked, and reverse direction if so.
		 */
		float testX = this.getTestX(this.getX(), this.direction);
		float testY = this.getTestY(this.getY(), this.direction);
		
		if (world.isBlocked(testX, testY)) {
			this.reverseDirection();
			
			/* Test that the reverse direction is not blocked 
			 * as well. If it is, then the skeleton is trapped.
			 */
			testX = getTestX(getX(), direction);
			testY = getTestY(getY(), direction);
			
			if (world.isBlocked(testX, testY)) {
				timer.reset();
			}
		}
		
		/* Reset the timer if it expires. 
		 */
		if (timer.expired()) {
			this.moveToDestination(this.direction);
			timer.reset();
		}
	}
	
	
	/* If the Skeleton was blocked and could not 
	 * move, reverse its current direction, and
	 * reset its movement interval timer.
	 */
	private void reverseDirection() {
		
		if (this.direction == DIR_UP) {
			this.direction = DIR_DOWN;
		} else {
			this.direction = DIR_UP;
		}
		timer.reset();
	}
}