
public class Ice extends Pushable {
	
	/** Ice block attributes.
	 */
	private Timer timer;
	private int direction;
	private float lastX;
	private float lastY;
	private boolean active;
	
	
	/** Creates a new (inactive) Ice block sprite.
	 * 
	 * @param x  The sprite's x-coordinate, in pixels.
	 * @param y  The sprite's y-coordinate, in pixels.
	 */
	public Ice(float x, float y) {
		super("res/ice.png", x, y);
		this.active = false;
		this.direction = DIR_NONE;
		this.lastX = getX();
		this.lastY = getY();
		this.addTag("Block");
		this.addTag("Blocked");
	}
	
	
	/** Pushes the ice block in a given direction, 
	 * moving 32 pixels every 0.25 seconds until 
	 * it is blocked.
	 * 
	 * @param direction
	 * @return void
	 */
	@Override
	public void push(int direction) {
		this.lastX = this.getX();
		this.lastY = this.getY();
		this.active = true;
		this.direction = direction;
		this.timer = new Timer(250);
		this.moveToDestination(direction);
	}
	
	
	/** Undoes the most recent move for the
	 * ice block. Also deactivates its movement
	 * (if it is currently moving).
	 * 
	 * @param void
	 * @return void
	 */
	@Override
	public void undo() {
		super.undo();
		this.active = false;
	}
	
	
	/** Adds the most recent move (last position 
	 * before being pushed) to the ice block's 
	 * move history.
	 * 
	 * @param void
	 * @return void
	 */
	@Override
	public void addToHistory() {
		
		HistoryStack history = this.getHistory();
		
		history.push(lastX, lastY);
		
	}
	
	
	/** Updates whether the ice block should be moving,
	 * increments the timer, records the last positions of
	 * the ice block, and pushes the ice block if a unit
	 * pushes it.
	 * 
	 * @param world  The World object the ice block belongs to.
     * @param delta  Time passed since last frame (milliseconds).
     * @return void
     */
	@Override
	public void update(World world, int delta) {
		
		/* Update the timer, keep the block
		 * moving in the current direction if it is
		 * active and unblocked.
		 */
		if (active) {
			timer.update(delta);
			
			if (timer.expired()) {
				
				float testX = this.getTestX(this.getX(), this.direction);
				float testY = this.getTestY(this.getY(), this.direction);
				
				if (!world.isBlocked(testX, testY)) {
					this.moveToDestination(this.direction);
					this.timer.reset();
				} else {
					this.active = false;
				}
			}
		} else {
			
			/* Otherwise, record its last position.
			 */
			this.lastX = this.getX();
			this.lastY = this.getY();
		}
	}
}