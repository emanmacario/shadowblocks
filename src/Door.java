import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Door extends Sprite {
	
	/** Door attributes.
	 */
	private boolean closed;
	
	
	/** Creates a new Door object. The
	 * default is that the door is closed.
	 * 
	 * @param x  The x-coordinate of the sprite, in pixels.
	 * @param y  The x-coordinate of the sprite, in pixels.
	 */
	public Door(float x, float y) {
		super("res/door.png", x, y);
		this.closed = true;
	}
	
	
	/** Returns if the door is currently closed.
	 * 
	 * @return True if closed else false.
	 */
	public boolean getClosed() {
		return this.closed;
	}
	
	
	/** Opens or closes the door.
	 * 
	 * @param closed  True to close, false to open.
	 * @return void
	 */
	public void setClosed(boolean closed) {
		
		/* Set door to open/closed. 
		 */
		this.closed = closed;
		
		/* And add/remove "Blocked" tag
		 * to indicate that Movable objects
		 * cannot/can move through this location.
		 */
		if (!closed && compareTag("Blocked")) {
			
			this.removeTag("Blocked");
			
		} else if (closed && !compareTag("Blocked")){
			
			this.addTag("Blocked");
		}
	}
	
	
	/** Only renders the door if the door is closed. 
	 * 
	 * @param g  The Slick graphics container object.
	 * @throws SlickException
	 * @return void
	 */
	@Override
	public void render(Graphics g) throws SlickException {
		if (!closed) {
			return;
		}
		super.render(g);
	}
}