
public class Cracked extends Sprite {
	
	public Cracked(float x, float y) {
		super("res/cracked_wall.png", x, y);
		this.addTag("Blocked");
		this.addTag("Cracked");
	}
}
