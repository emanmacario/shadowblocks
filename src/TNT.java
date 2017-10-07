
public class TNT extends Pushable {
	
	public static final String TNT = "res/tnt.png";
	
	public TNT(float x, float y) {
		super(TNT, x, y);
	}
	
	public void push(int direction) {
		this.moveToDestination(direction);
	}
	
	public boolean active() {
		return false;
	}
 }
