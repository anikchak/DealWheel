package Queue.DynamicArrayImpl;

public class QueueDynamicArrayImplTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QueueDyanamicArrayImpl q = new QueueDyanamicArrayImpl();
		q.enqueue(10);
		q.enqueue(9);
		q.deQ();
		q.enqueue(21);
		//q.tempQSize();
		q.deQ();
		q.enqueue(17);
		//q.tempQSize();
		q.deQ();
		q.deQ();
	//	q.enqueue(1);
		//q.enqueue(99);
		//q.enqueue(4);
		//q.tempQSize();
		q.deQ();
		q.deQ();
		//q.deQ();
		//q.deQ();
		//q.deQ();
	}

}
