

public class Rogue extends Unit {
	
	public Rogue(float x, float y) {
		super("res/rogue.png", x, y, DIR_LEFT);
		this.addTag("Unit");
		this.addTag("Enemy");
	}
	
	@Override
	public void onMove(int direction, float testX, float testY) {
		super.onMove(direction, testX, testY);
	}
	
	@Override
	public void update(World world, int delta) {
		
		
		/* Firstly, check if the player moved.
		 */
		if (!world.getPlayerMoved()) {
			return;
		}
		
		/* Get new candidate position. */
		float testX = getTestX(this.getX(), this.getDirection());
		float testY = getTestY(this.getY(), this.getDirection());
		
		
		/* Debug statements.
		System.out.println("Rogue direction: " + this.getDirection());
		System.out.println("old x: " + getX() + " old y: "+ getY());
		System.out.println("new x: " + testX + " new y: " + testY);
		*/
		
		/* Get adjacent position of candidate position,
		 * in the respective direction. 
		 */
		float blockX = getTestX(testX, this.getDirection());
		float blockY = getTestY(testY, this.getDirection());
		
		/* Either move, move/push a block, or reverse direction. */
		if (world.isBlocked(testX, testY)) {
			
			System.out.println("Rogue is blocked");
			
			Sprite block = world.getSpriteOfType("Block", testX, testY);
           
            if (block == null) {
                this.reverseDirection();
            } else if (world.isBlocked(blockX, blockY)) {
                this.reverseDirection();
            } else {
            	this.moveToDestination(this.getDirection());
            	((Pushable)block).push(this.getDirection());
            }
		} else {
			
			/* Rogue moves freely. */
			
			System.out.println("Rogue moving freely");
			
			this.moveToDestination(this.getDirection());
		}
	}
	
	
	/* If the Rogue was blocked and could not 
	 * move, reverse its current direction.
	 */
	public void reverseDirection() {
		
		if (this.getDirection() == DIR_LEFT) {
			
			this.setDirection(DIR_RIGHT);
			
		} else {
			
			this.setDirection(DIR_LEFT);
		}
	}
}