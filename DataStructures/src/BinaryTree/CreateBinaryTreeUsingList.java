package BinaryTree;

import java.util.ArrayList;

import Stack.LinkedListImpl.StackForTreeDS;
import Stack.LinkedListImpl.StackStrcForTree;

public class CreateBinaryTreeUsingList {

	TreeDS currentNode = null;
	TreeDS rootNode = null;
	ArrayList tempList = new ArrayList();
	
	void addNode(int data){
		if(currentNode==null){
			//System.out.println("Root node not present. Adding element as root.");
			currentNode = new TreeDS();
			currentNode.setData(data);
			rootNode = currentNode;
			tempList.add(currentNode);
		}
		else{
			//TreeDS currentNode=null;
			if(tempList!=null){
				
			currentNode = (TreeDS)tempList.get(0);
			//System.out.println("Current Node has the data ="+currentNode.getData());
			}
			if(currentNode.getLeftChild()==null){
				//System.out.println("Left child for node="+currentNode.getData());
				TreeDS left = new TreeDS();
				left.setData(data);
				currentNode.setLeftChild(left);
				tempList.add(left);
			}else if(currentNode.getRightChild()==null){
				//System.out.println("Right child for node="+currentNode.getData());
				TreeDS right = new TreeDS();
				right.setData(data);
				currentNode.setRightChild(right);
				tempList.add(right);
				tempList.remove(0);
			}
		}
	}
	void traverseTree(){
		System.out.println("Preorder Tree traversal - Recursion");
		preorderRecursion(rootNode);
		System.out.println("\nPreorder Tree traversal - NonRecursive");
		preorderNonRecursive(rootNode);
		
		System.out.println("\nPostorder Tree traversal - Recursion");
		postOrderRecusion(rootNode);
		System.out.println("\nPostorder Tree traversal - NonRecursive");
		postorderNonRecursive(rootNode);
		
		System.out.println("\nInorder Tree traversal - Recursion");
		inorderRecursion(rootNode);
		System.out.println("\nInorder Tree traversal - NonRecursive");
		inorderNonRecursive(rootNode);
	}
	
	//Preorder Tree Traversal  - Recursive call 
	public void preorderRecursion(TreeDS node){
		while(node !=null){
			System.out.print(" "+node.getData());
			preorderRecursion(node.getLeftChild());
			preorderRecursion(node.getRightChild());
			return;
		}
	}
	
	//Postorder Tree Traversal - Recursion
	public void postOrderRecusion(TreeDS node)
	{
		while(node !=null){
			//System.out.println("current node = "+node.getData());
			postOrderRecusion(node.getLeftChild());
			postOrderRecusion(node.getRightChild());
			System.out.print(" "+node.getData());
			return;
		}
		
		
	}
	
	//Inorder Tree Traversal  - Recursive call 
		public void inorderRecursion(TreeDS node){
			while(node !=null){
				
				inorderRecursion(node.getLeftChild());
				System.out.print(" "+node.getData());
				inorderRecursion(node.getRightChild());
				return;
			}
		}
	
	//PreOrder Tree Traversal  - Non Recursive call
	public void preorderNonRecursive(TreeDS node) {
		StackForTreeDS s = new StackForTreeDS();
		while (true) {
			while (node != null) {
				System.out.print(" " + node.getData());
				s.push(node);
				node = node.getLeftChild();
			}
			if (s.isStackEmpty()) {
				break;
			} else {
				node = s.pop();
				node = node.getRightChild();
			}
		}
	}
	
	//Postorder Tree Traversal - Non recursive call
	public void postorderNonRecursive(TreeDS node){
		StackForTreeDS s = new StackForTreeDS();
		while(true){
			while(node !=null){
				s.push(node);
				node = node.getLeftChild();
			}
			if(s.isStackEmpty()){
				break;
			}else{
				if(s.top().getRightChild()==null){
					node  = s.pop();
					System.out.print(" "+node.getData());
					if(s.top().getRightChild() == node){
						node = s.pop();
						System.out.print(" "+node.getData());
						if(!s.isStackEmpty() && node == s.top().getRightChild()){
							System.out.print(" "+s.pop().getData());
						}
					}
				}
				if(!s.isStackEmpty()){
					node = s.top().getRightChild();
				}else{
					node = null;
				}
			}
		}
	}
	
	//Inorder Tree Traversal - Non Recursive call
	public void inorderNonRecursive(TreeDS node){
		StackForTreeDS s = new StackForTreeDS();
		while(true){
			while(node!=null){
				s.push(node);
				node = node.getLeftChild();
			}
			if(s.isStackEmpty())break;
			else{
				node = s.pop();
				System.out.print(" "+node.getData());
				node = node.getRightChild();
			}
		}
	}
}
