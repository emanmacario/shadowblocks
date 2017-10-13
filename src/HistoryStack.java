/** This file contains the HistoryStack class
 * which contains the move history of a movable
 * object.
 */

import java.util.Stack;
import org.newdawn.slick.geom.Vector2f;

public class HistoryStack {
	
	/** HistoryStack attributes.
	 */
	private Stack<Vector2f> moves;
	
	
	/** Creates a new HistoryStack object,
	 * containing an empty stack of recent moves.
	 * 
	 * @param void
	 */
	public HistoryStack() {
		this.moves = new Stack<>();
	}
	
	
	/** Returns the size of the moveHistory stack.
	 * 
	 * @param void
	 * @return size 
	 */
	public int getStackSize() {
		return moves.size();
	}
	
	
	/** Adds a recent move to the history stack.
	 * 
	 * @param x  x-coordinate, in pixels.
	 * @param y  y-coordinate, in pixels.
	 */
	public void push(float x, float y) {
		moves.push(new Vector2f(x,y));
	}
	
	
	/** Returns the last move in the history stack.
	 * 
	 * @param void
	 * @return move The most recent position, prior to a move.
	 */
	public Vector2f pop() {
		return moves.pop();
	}
}
