
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Explosion extends Sprite {
	
	private Timer timer;
	
	/**
	 * Explosion constructor.
	 */
	public Explosion(float x, float y) {
		super("res/explosion.png", x, y);
		this.timer = new Timer(400);
	}
	
	
	/** Updates the timer.
	 * 
	 * @param delta
	 */
	@Override
	public void update(int delta) {
		this.timer.update(delta);
	}
	
	
	public boolean active() {
		if (!this.timer.expired()) {
			return true;
		}
		return false;
	}
	
	
	@Override
	public void render(Graphics g) throws SlickException {
		if (this.timer.expired()) {
			return;
		}
		super.render(g);
	}
	
}
