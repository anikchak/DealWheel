package Stack.DynamicArrayImpl;

public class StackDynamicImplTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StackDynamicArray s = new StackDynamicArray();
		s.push(10);
		s.push(5);
		s.push(9);
		s.push(50);
		s.push(12);
		s.pop();
		s.push(99);
		s.pop();
		//s.pop();
		//s.pop();
		s.pop();
		s.pop();
		s.push(3);
		s.pop();
		s.pop();
	}

}
