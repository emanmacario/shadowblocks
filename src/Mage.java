
public class Mage extends Unit {
	
	private float distX;
	private float distY;
	private int sign;
	
	public Mage(float x, float y) {
		super("res/mage.png", x, y, DIR_NONE);
		this.addTag("Unit");
		this.addTag("Mage");
		this.addTag("Enmy");
	}
	
		
	
	public void update(float playerX, float playerY) {
		
		distX = playerX - this.getX();
		distY = playerY - this.getY();
		
		if (Float.compare(Math.abs(distX), Math.abs(distY)) > 0) {
			
			System.out.println("x distance > y distance");
			
			sign = sgn(distX);
			
			System.out.println("distX = " + distX + ", sign(x) = " + sign);
			
			
			if (sign == -1) {
				this.setDirection(DIR_LEFT);
			} else {
				this.setDirection(DIR_RIGHT);
			}
					
		} else {
			
			
			System.out.println("y distance > x distance");
			
			sign = sgn(distY);
			
			System.out.println("distY = " + distY + ", sign(y) = " + sign);
			
			
			if (sign == -1) {
				this.setDirection(DIR_UP);
			} else {
				this.setDirection(DIR_DOWN);
			}
		}
		System.out.println(this.getDirection());
		
 	}
	
	private int sgn(float x) {
		if (Float.compare(x, 0.0f) < 0) {
			return -1;
		}
		return 1;
	}
}