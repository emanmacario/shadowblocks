
public class Ice extends Pushable {
	
	private Timer timer;
	private int direction;
	private float lastX;
	private float lastY;
	
	public Ice(float x, float y) {
		super("res/ice.png", x, y);
		this.timer = new Timer(250);
		this.addTag("Block");
		this.addTag("Blocked");
	}
	
	@Override
	public void push(int direction) {
		moveToDestination(direction);
	}
	
	@Override
	public void update(World world, int delta) {
		
		/* Update the timer. */
		if (this.active()) {
			this.timer.update(delta);
		}
		
		if (this.timer.expired()) {
			this.timer.reset();
		}
	}
	
	@Override
	public boolean active() {
		return true;
	}
	
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
}