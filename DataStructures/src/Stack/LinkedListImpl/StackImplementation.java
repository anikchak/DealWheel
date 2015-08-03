package Stack.LinkedListImpl;

public class StackImplementation {
StackDS head=null;

//push method
public void push(int data){
	if(head ==null){
		head = new StackDS(data);
	}
	else{
		StackDS newNode = new StackDS(data);
		newNode.setPreviousNode(head);
		head = newNode;
	}
}

//pop method
public void pop(){
	if(head==null){
		System.out.println("Empty Stack");
	}
	else{
		System.out.println("Popped = "+head.getData());
		head = head.getPreviousNode();
	}
}
}
