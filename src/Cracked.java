
public class Cracked extends Sprite {
	
	/** Creates a new Cracked object.
	 *
	 * @param x  The sprite's x-coordinate, in pixels.
	 * @param y  The sprite's y-coordinate, in pixels.
	 */
	public Cracked(float x, float y) {
		super("res/cracked_wall.png", x, y);
		this.addTag("Blocked");
		this.addTag("Cracked");
	}
}
