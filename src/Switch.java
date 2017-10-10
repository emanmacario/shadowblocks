

public class Switch extends Sprite {
		
	private Door door;
	
	public Switch(float x, float y) {
		super("res/switch.png", x, y);
		this.addTag("Switch");
	}
	
	
	public void setDoor(Door door) {
		this.door = door;
	}
	
	public void toggleSwitch(boolean toggle) {
		door.setClosed(toggle);
	}
	
	
}