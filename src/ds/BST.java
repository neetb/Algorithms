package ds;

public class BST {

	private Node root;
	
	public Node insert(int item)	{
		Node newNode = new Node(item, null, null);
		if(root == null)	{
			this.root = newNode;
			return newNode;
		}
		Node node  =  this.root;
		while(node != null)	{
			if(node.item > item)	{
				node = node.
			}
		}
	}
	
	
	private class Node	{
		int item;
		Node left;
		Node right;
				
		public Node(int item, Node left, Node right)	{
			this.item = item;
			this.left = left;
			this.right = right;
		}
	}
}
