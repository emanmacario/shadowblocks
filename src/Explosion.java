
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Explosion extends Sprite {
	
	/** Explosion attributes.
	 */
	private Timer timer;
	
	
	/** Creates a new Explosion sprite.
	 * 
	 * @param x  The explosion's x-coordinate, in pixels.
	 * @param y  The explosion's y-coordinate, in pixels.
	 */
	public Explosion(float x, float y) {
		super("res/explosion.png", x, y);
		this.timer = new Timer(400);
	}
	
	
	/** Updates the explosion timer.
	 * 
	 * @param delta
	 * @return void
	 */
	@Override
	public void update(World world, int delta) {
		timer.update(delta);
	}
	
	
	/** Renders an explosion, only if the timer has
	 * not yet expired.
	 * 
	 * @param g  The Slick graphics container object.
	 * @throws SlickException
	 * @return void
	 */
	public void render(Graphics g) throws SlickException {
		if (timer.expired()) {
			return;
		}
		super.render(g);
	}
	
}
