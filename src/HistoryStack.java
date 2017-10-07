/** This file contains the HistoryStack class
 * which contains the move history of a movable
 * object.
 */

import java.util.Stack;
import org.newdawn.slick.geom.Vector2f;

public class HistoryStack {
	
	private Stack<Vector2f> moves;
	
	public HistoryStack() {
		this.moves = new Stack<>();
	}
	
	
	/** Returns the size of the moveHistory stack.
	 * 
	 * @return size 
	 */
	public int getStackSize() {
		return moves.size();
	}
	
	/** Adds a recent move to the history stack.
	 * 
	 * @param x
	 * @param y
	 */
	public void push(float x, float y) {
		
		moves.push(new Vector2f(x,y));
	}
	
	
	/** Returns the last move in the history stack.
	 * @param void
	 * @return
	 */
	public Vector2f pop() {
		
		return this.moves.pop();
	}
	
	
	public void printHistory() {
		Stack<Vector2f> newStack = new Stack<>();
		
		newStack.addAll(moves);
		
		while (!newStack.empty()) {
			Vector2f move = newStack.pop();
			
			System.out.println("x: " + move.getX() + "y: " + move.getY());
	
		}
		
	}
}
