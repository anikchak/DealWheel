package BinaryTree;

public class TreeDS {

	private int data;
	TreeDS rightChild=null;
	public TreeDS getRightChild() {
		return rightChild;
	}
	public void setRightChild(TreeDS rightChild) {
		this.rightChild = rightChild;
	}
	public TreeDS getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(TreeDS leftChild) {
		this.leftChild = leftChild;
	}
	TreeDS leftChild=null;
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
}
