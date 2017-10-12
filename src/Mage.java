
public class Mage extends Movable {
	
	private int direction;
	private float distX;
	private float distY;
	
	
	public Mage(float x, float y) {
		super("res/mage.png", x, y);
		this.direction = DIR_NONE;
		this.addTag("Enemy");
	}
	
		
	@Override
	public void update(World world, int delta) {
		
		/* Run the algorithm when player moves,
		 * and move if possible.
		 */
		if (world.getPlayerMoved()) {
			Sprite player = world.getSpriteOfType("Player");
			
			this.update(player.getX(), player.getY());
			
			/* Calculate mage's new candidate position. 
			 */
			float testX = this.getTestX(this.getX(), this.direction);
			float testY = this.getTestY(this.getY(), this.direction);
			
			if (!world.isBlocked(testX, testY)) {
				this.moveToDestination(this.direction);
			}
		}
	}
	
	
	/** Mage algorithm.
	 * 
	 * @param playerX
	 * @param playerY
	 */
	private void update(float playerX, float playerY) {
		
		distX = playerX - this.getX();
		distY = playerY - this.getY();
		
		if (Float.compare(Math.abs(distX), Math.abs(distY)) > 0) {
						
			if (sign(distX) == -1) {
				this.direction = DIR_LEFT;
			} else {
				this.direction = DIR_RIGHT;
			}
			
		} else {
						
			if (sign(distY) == -1) {
				this.direction = DIR_UP;
			} else {
				this.direction = DIR_DOWN;
			}
		}		
 	}
	
	private int sign(float x) {
		if (Float.compare(x, 0.0f) < 0) {
			return -1;
		}
		return 1;
	}
}