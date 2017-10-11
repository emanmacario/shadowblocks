import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Door extends Sprite {
	
	private boolean closed;
	
	public Door(float x, float y) {
		super("res/door.png", x, y);
		this.closed = true;
		this.addTag("Door");
	}
	
	
	public boolean getClosed() {
		return this.closed;
	}
	
	public void setClosed(boolean closed) {
		
		/* Set door to open/closed. 
		 */
		this.closed = closed;
		
		/* And add/remove "Blocked" tag
		 * to indicate to Movable objects
		 * that they cannot move through.
		 */
		if (!closed) {
			
			if (this.compareTag("Blocked")) {
				
				System.out.println("Removed blocked tag");
				this.removeTag("Blocked");
			}
			
		} else {
			
			if (!this.compareTag("Blocked")) {
				System.out.println("Added blocked tag");
				this.addTag("Blocked");
			}
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