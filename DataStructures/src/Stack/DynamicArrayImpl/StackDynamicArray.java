package Stack.DynamicArrayImpl;

import java.util.Arrays;

public class StackDynamicArray {

	int capacity = 1;
	int top = -1;
	int arr[]=null;
	
	public void push(int data){
		if(top==-1){
			arr = new int[capacity];
			arr[++top] = data;
		}
		else if(top<capacity-1){
			arr[++top] = data;
		}else{
			capacity = capacity*2;
			int tempArr[] = arr;
			arr = null;
			arr = Arrays.copyOf(tempArr, capacity);
			arr[++top] = data;
		}
	}
	
	public void pop(){
		if(top==-1){
			System.out.println("empty stack");
		}else{
			System.out.println("Popped="+arr[top]);
			top--;
		}
	}
}
