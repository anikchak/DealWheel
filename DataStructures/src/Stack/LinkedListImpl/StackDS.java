package Stack.LinkedListImpl;

public class StackDS {
	int data;
	StackDS previousNode;
	
	public StackDS(int value){
		this.data = value;
		this.previousNode = null;
	}
	
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public StackDS getPreviousNode() {
		return previousNode;
	}
	public void setPreviousNode(StackDS previousNode) {
		this.previousNode = previousNode;
	}
	
}
