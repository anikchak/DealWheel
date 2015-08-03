package Stack.LinkedListImpl;

public class StackTest {

	public static void main(String[] args) {
		// Stack Creation using LinkedList
		System.out.println("Stack implementation using Linked List");
		StackImplementation stackimpl  = new StackImplementation();
		stackimpl.push(10);
		stackimpl.push(4);
		stackimpl.push(1);
		stackimpl.pop();
		stackimpl.pop();
		stackimpl.pop();stackimpl.pop();
	}

}
