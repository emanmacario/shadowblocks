/** This file contains the HistoryStack class
 * which contains the move history of a movable
 * object.
 */

import java.util.Stack;

public class HistoryStack {
	
	private Stack<Float> xStack;
	private Stack<Float> yStack;
	
	public int getSize() {
		return xStack.size();
	}
	
	public void push(float x, float y) {
		xStack.push(x);
		yStack.push(y);
	}
	
	public void pop() {
		
	}
}
