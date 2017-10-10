

public class Switch extends Sprite {
		
	private Door door;
	
	public Switch(float x, float y) {
		super("res/switch.png", x, y);
		this.door = null;
		this.addTag("Switch");
	}
	
	
	public void setDoor(Door door) {
		this.door = door;
	}
	
	public void toggle(boolean toggle) {
		door.setClosed(toggle);
	}
	
	
}