import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Door extends Sprite {
	
	private boolean closed;
	
	public Door(float x, float y) {
		super("res/door.png", x, y);
		this.closed = true;
	}
	
	
	public boolean getClosed() {
		return this.closed;
	}
	
	public void setClosed(boolean closed) {
		
		/* Set door to open/closed. */
		this.closed = closed;
		
		if (!closed) {
			this.removeTag("Blocked");
		} else {
			this.addTag("Blocked");
		}
		
	}
	
	@Override
	public void render(Graphics g) throws SlickException {
		if (!closed) {
			return;
		}
		super.render(g);

	}
	
	
}