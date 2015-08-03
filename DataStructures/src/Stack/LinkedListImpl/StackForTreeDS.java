package Stack.LinkedListImpl;

import BinaryTree.TreeDS;

public class StackForTreeDS {

StackStrcForTree head=null;

//push method
public void push(TreeDS node){
	if(head ==null){
		head = new StackStrcForTree(node);
	}
	else{
		StackStrcForTree newNode = new StackStrcForTree(node);
		newNode.setPreviousNode(head);
		head = newNode;
	}
}

//pop method
public TreeDS pop(){
	TreeDS popedNode = null;
	if(head==null){
		//System.out.println("Empty Stack");
		popedNode = null;
	}
	else{
		//System.out.println("Popped = "+head.getData());
		popedNode = head.getTreeNode();
		head = head.getPreviousNode();
	}
	return popedNode;
}

//Method to return the top element of the stack
public TreeDS top(){
	return head.getTreeNode();
}
//Method to check if the stack is empty or not

public boolean isStackEmpty(){
	if(head==null) return true;
	else return false;
}

}
