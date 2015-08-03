package Queue.LinkedListImplementation;

public class QueueImplementation {

	QueueDS head = null, tail = null;
	
	public void enqueue(int data){
		if(head == null){
			tail = new QueueDS(data);
			head = tail;
		}
		else{
			QueueDS newNode = new QueueDS(data);
			tail.setNextNode(newNode);
			tail = newNode;
		}
	}
	
	public void dequeue(){
		if(head==null){
			System.out.println("Queue is empty");
		}
		else{
			System.out.println("Dequeued = "+head.getData());
			head = head.getNextNode();
		//	System.out.println("--"+head.getData());
		}
	}
}
