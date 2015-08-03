package Queue.DynamicArrayImpl;

import java.util.Arrays;

public class QueueDyanamicArrayImpl {

	int front = -1;
	int rear = -1;
	int capacity = 1;
	int queue[]= new int [capacity];
	
	public void enqueue(int data){
		int initialCap = capacity;
		if(front == (rear+1)%capacity){
			//Dynamic array expansion 
			capacity = capacity*2;
			int temp[] = queue;
			queue = null;
			queue = new int [capacity];
			for(int i=0;i<temp.length;i++){
			queue[i] = temp[i];
			}
			if(rear<front){
				for(int i=0;i<front;i++){
					queue[i+initialCap] = queue[i];
				}
				rear = rear+initialCap;
			}
		}
			rear = (rear+1)%capacity;
			queue[rear] = data;
			if(front==-1){
				front = rear;
			}
		//System.out.println("for data="+data+"front="+front+"rear="+rear+"capacity="+capacity);
	
	}
	public void deQ(){
		if(front == -1){
			System.out.println("Empty Q");
		}else{
			System.out.println("Dequeued = "+queue[front]);
			if(front == rear){
				front = rear = -1;
			}else{
				front = (front+1)%capacity;
			}
		}
	}
	public void tempQSize(){
		System.out.println("Temp Q size="+queue.length);
	}
}
