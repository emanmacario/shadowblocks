import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Door extends Sprite {
	
	private boolean closed;
	
	public static final String DOOR = "res/door.png";
	
	public Door(float x, float y) {
		super(DOOR, x, y);
		this.closed = true;
	}
	
	public boolean getClosed() {
		return this.closed;
	}
	
	public void setClosed(boolean closed) {
		this.closed = closed;
	}
	
	@Override
	public void render(Graphics g) throws SlickException {
		if (!closed) {
			return;
		}
		super.render(g);

	}
	
	
}