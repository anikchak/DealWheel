package Queue.LinkedListImplementation;

public class QueueTest {

	public static void main(String[] args) {
		// Queue implementation using Linked List
		QueueImplementation qImpl = new QueueImplementation();
		qImpl.enqueue(10);
		qImpl.enqueue(78);
		qImpl.enqueue(1);
		qImpl.dequeue();
		qImpl.dequeue();
		qImpl.dequeue();
	}

}
