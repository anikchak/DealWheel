package Stack.LinkedListImpl;

import BinaryTree.TreeDS;

public class StackStrcForTree {
	TreeDS treeNode;
	StackStrcForTree previousNode;
	
	public StackStrcForTree(TreeDS node){
		this.treeNode = node;
		this.previousNode = null;
	}
	
	public TreeDS getTreeNode() {
		return treeNode;
	}
	public void setTreeNode(TreeDS treeNode) {
		this.treeNode = treeNode;
	}
	public StackStrcForTree getPreviousNode() {
		return previousNode;
	}
	public void setPreviousNode(StackStrcForTree previousNode) {
		this.previousNode = previousNode;
	}
}
