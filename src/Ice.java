
public class Ice extends Pushable {
	
	private Timer timer;
	private int direction;
	private float lastX;
	private float lastY;
	private boolean active;
	
	public Ice(float x, float y) {
		super("res/ice.png", x, y);
		this.active = false;
		this.direction = DIR_NONE;
		this.lastX = getX();
		this.lastY = getY();
		this.addTag("Block");
		this.addTag("Blocked");
	}
	
	
	@Override
	public void push(int direction) {
		this.lastX = this.getX();
		this.lastY = this.getY();
		this.active = true;
		this.direction = direction;
		this.timer = new Timer(250);
		this.moveToDestination(direction);
	}
	
	
	@Override
	public void addToHistory() {
		HistoryStack history = this.getHistory();
		
		history.push(lastX, lastY);
	}
	
	
	@Override
	public void update(World world, int delta) {
		
		/* Update the timer, and slide
		 * if active. 
		 */
		if (this.active) {
			this.timer.update(delta);
			
			if (this.timer.expired()) {
				
				float testX = this.getTestX(this.getX(), this.direction);
				float testY = this.getTestY(this.getY(), this.direction);
				
				if (!world.isBlocked(testX, testY)) {
					this.moveToDestination(this.direction);
					this.timer.reset();
				} else {
					this.active = false;
				}
				
			}
		}
	}
}