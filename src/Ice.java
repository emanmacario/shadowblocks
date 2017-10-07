
public class Ice extends Pushable {
	
	public static final String ICE = "res/ice.png";
	
	private int direction;
	private float lastX;
	private float lastY;
	
	public Ice(float x, float y) {
		super(ICE, x, y);
	}
	
	public void push(int direction) {
	}
	
	public void update(int delta) {
		
	}
	
	public boolean active() {
		return false;
	}
	
	
	public void addToHistory() {
	}
}