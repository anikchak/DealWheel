package Queue.LinkedListImplementation;

public class QueueDS {

	private int data;
	private QueueDS nextNode = null;
	
	public QueueDS(int value){
		this.data = value;
		this.nextNode = null;
	}
	public QueueDS getNextNode() {
		return nextNode;
	}
	public void setNextNode(QueueDS nextNode) {
		this.nextNode = nextNode;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
}
