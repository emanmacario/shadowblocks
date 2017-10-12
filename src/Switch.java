

public class Switch extends Sprite {
		
	private Door door;
	
	public Switch(float x, float y) {
		super("res/switch.png", x, y);
		this.door = null;
	}
	
	public void setDoor(Door door) {
		this.door = door;
	}
	
	@Override
	public void update(World world, int delta) {
		
		/* Check if there is a block
		 * currently on the door switch.
		 */
		Sprite block = world.getSpriteOfType("Block", 
								this.getX(), this.getY());
		
		/* Open the door if so, otherwise
		 * close the door if it is not already
		 * closed.
		 */
		if (block != null) {
			door.setClosed(false);
		} else {
			door.setClosed(true);
		}
	}
}