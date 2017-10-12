
public class TNT extends Pushable {
	
	private boolean activated;
	
	public TNT(float x, float y) {
		super("res/tnt.png", x, y);
		this.activated = false;
		this.addTag("Block");
		this.addTag("Blocked");
	}
	
	
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
	@Override
	public void onMove(float newX, float newY) {
				
		//this.addToHistory();
		super.onMove(newX, newY);
	}
	
	
	@Override
	public void update(World world, int delta) {
		
		if (activated) {
			
			/* Create a new explosion sprite. 
             */
            Sprite explosion = new Explosion(this.getX(), this.getY());
      
            
            /* Destroy the cracked wall and TNT block, and
             * add the explosion sprite to the sprite list.
             */
            Sprite crackedWall = world.getSpriteOfType("Cracked");
            world.createSprite(explosion);
            world.destroySprite(crackedWall);
            world.destroySprite(this);
		}
	}
 }
