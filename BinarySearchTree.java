import java.util.Iterator;
import java.util.ArrayDeque;

public class BinarySearchTree<T extends Comparable<T>> implements BSTInterface<T>{
	
	private Node<T> rootNode = null;
	
	public BinarySearchTree()
	{
		
	}
	public BinarySearchTree(T rootData)
	{
		Node<T> newNode = new Node<T>();
		newNode.setData(rootData);
		rootNode = newNode;
	}
	
	public boolean contains(T entry) 
	{
		return contains(this.getRootNode(), entry);
	}
	private boolean contains(Node<T> rootNode, T entry)
	{
		if(rootNode == null)
			return false;
		int compare = entry.compareTo(rootNode.getData());
		if(compare == 0)
			return true;
		else if(compare < 0)
			return contains(rootNode.getLeftChild(), entry);
		else if(compare > 0)
			return contains(rootNode.getRightChild(), entry);
		return false;
	}
	
	public T getEntry(T entry)
	{
		return getEntry(rootNode, entry);
	}
	private T getEntry(Node<T> rootNode,T entry) 
	{
		if(rootNode == null)
			return null;
		int compare = entry.compareTo(rootNode.getData());
		if(compare == 0)
			return rootNode.getData();
		else if(compare < 0)
			return getEntry(rootNode.getLeftChild(), entry);
		else if(compare > 0)
			return getEntry(rootNode.getRightChild(), entry);
		return null;
	}

	
	public T add(T newEntry) {
		if(this.isEmpty()) //checks to see if current BST is empty
		{
			Node<T> newNode = new Node<T>();
			newNode.setData(newEntry);
			rootNode = newNode;
			return null;
		}
		else 
		{
			return add(rootNode, newEntry);
		}
	}
	private T add(Node<T> rootNode, T entry) 
	{
		int compare = entry.compareTo(rootNode.getData());
		//if same value as entry is found end recursion
		if(compare == 0)
			return rootNode.getData();
		 //if entry is less than root node data move to left child
		else if(compare < 0)
		{
			//recursively calls add function unless leaf is reached
			if(rootNode.hasLeftChild())
			{
				return add(rootNode.getLeftChild(), entry);
			}
			else
			{
				rootNode.setLeftChild(entry);
				return null;
			}
		}
		//if entry greater than root node data then move to right child
		else if(compare > 0)
		{
			//recursively calls add function unless leaf is reached
			if(rootNode.hasRightChild()) 
			{
				return add(rootNode.getRightChild(), entry);
			}
			else
			{
				rootNode.setRightChild(entry);
				return null;
			}
		}
		return null;
	}
	
	public T remove(T entry) 
	{
		T oldEntry = getEntry(entry);
		if(oldEntry != null)
			setRootNode(remove(rootNode, entry));
		return oldEntry;
	}
	private Node<T> remove(Node<T> rootNode,T entry) 
	{
		if(rootNode != null)
		{
			if(entry.compareTo(rootNode.getData()) == 0)
			{
				rootNode = removeFromRoot(rootNode);
			}
			else if(entry.compareTo(rootNode.getData()) <= 0)
			{
				Node<T> subTreeRoot = remove(rootNode.getLeftChild(), entry);
				rootNode.setLeftChild(subTreeRoot);
			}
			else
			{
				Node<T> subTreeRoot = remove(rootNode.getRightChild(), entry);
				rootNode.setRightChild(subTreeRoot);
			}
		}
		return rootNode;
	}
	private Node<T> removeFromRoot(Node<T> rootNode)
	{
		if(rootNode.hasLeftChild() && rootNode.hasRightChild())
		{
			//sets value of largest value to the rootNode data
			Node<T> largestNode = findLargest(rootNode.getLeftChild());
			rootNode.setData(largestNode.getData());
			
			//remove largest node
			rootNode.setLeftChild(removeLargest(rootNode.getLeftChild()));
		}
		else if(rootNode.hasRightChild())
			rootNode = rootNode.getRightChild();
		else
			rootNode = rootNode.getLeftChild();
		return rootNode;
	}
	private Node<T> removeLargest(Node<T> rootNode)
	{
		if(rootNode.hasRightChild())
		{
			Node<T> newRoot = removeLargest(rootNode.getRightChild());
			rootNode.setRightChild(newRoot);
		}
		else
			rootNode = rootNode.getLeftChild();
		return rootNode;
	}
	private Node<T> findLargest(Node<T> rootNode)
	{
		if(rootNode.hasRightChild())
		{
			return findLargest(rootNode.getRightChild());
		}
		else
			return rootNode;
	}

	
	public Iterator<T> getInorderIterator() 
	{
		inorderIterator it = new inorderIterator(rootNode);
		return it;
	}
	public class inorderIterator implements Iterator<T>
	{
		ArrayDeque<T> iteratorQueue = new ArrayDeque<T>();
		
		public inorderIterator()
		{
			addTo(rootNode);
		}
		public inorderIterator(Node<T> rootNode)
		{
			addTo(rootNode);
		}
		private void addTo(Node<T> rootNode)
		{
			if(rootNode.hasLeftChild())
				addTo(rootNode.getLeftChild());
			iteratorQueue.add(rootNode.getData());
			if(rootNode.hasRightChild())
				addTo(rootNode.getRightChild());
		}
		public boolean hasNext() {
			return !iteratorQueue.isEmpty();
		}

		public T next() {
			return iteratorQueue.pollFirst();
			
		}
	}

	//prints all entries in the tree in-order
	public void inorderTraversal()
	{
		inorderTraversal(rootNode);
		System.out.println("");
	}
	private void inorderTraversal(Node<T> rootNode)
	{
		if(rootNode.hasLeftChild())
			inorderTraversal(rootNode.getLeftChild());
		System.out.print(rootNode.getData() + " ");
		if(rootNode.hasRightChild())
			inorderTraversal(rootNode.getRightChild());
		
	}
	
	//prints all entries in the tree in pre-order
	public void preorderTraversal()
	{
		preorderTraversal(rootNode);
		System.out.println("");
	}
	private void preorderTraversal(Node<T> rootNode)
	{
		System.out.print(rootNode.getData() + " ");
		if(rootNode.hasLeftChild())
			preorderTraversal(rootNode.getLeftChild());
		if(rootNode.hasRightChild())
			preorderTraversal(rootNode.getRightChild());
		
	}
	
	//prints all entries in the tree in post-order
	public void postorderTraversal()
	{
		postorderTraversal(rootNode);
		System.out.println("");
	}
	private void postorderTraversal(Node<T> rootNode)
	{
		System.out.print(rootNode.getData() + " ");
		if(rootNode.hasLeftChild())
			postorderTraversal(rootNode.getLeftChild());
		if(rootNode.hasRightChild())
			postorderTraversal(rootNode.getRightChild());
		
	}

	public T getRootData() {
		return rootNode.getData();
	}
	
	//returns predecessor data to entry for in order traversal
	public T getPredecessor(T entry)
	{
		Iterator<T> it = this.getInorderIterator();
		T current = it.next();
		if(!current.equals(entry))
		{
			T next = it.next();
			if(next.equals(entry)) 
			{
				return current;
			}
			while(it.hasNext())
			{
				//move both forward through iterator
				current = next;
				next = it.next();
				if(next.equals(entry)) 
				{
					return current;
				}
			}
			return null;//if entry is never found
		}
		else
		{
			return null;// if entry is first entry
		}
	}
	
	//returns successor data to entry for in order traversal
	public T getSuccessor(T entry)
	{
		Iterator<T> it = this.getInorderIterator();
		T current;
		while(it.hasNext())
		{
			current = it.next();
			if(entry.equals(current))
			{
				return it.next();
			}
		}
		return null;
	}
	
	//returns node containing data matching entry
	public Node<T> getNode(T entry)
	{
		return getNode(rootNode, entry);
	}
	private Node<T> getNode(Node<T> rootNode,T entry) 
	{
		if(rootNode == null)
			return null;
		int compare = entry.compareTo(rootNode.getData());
		if(compare == 0)
			return rootNode;
		else if(compare < 0)
			return getNode(rootNode.getLeftChild(), entry);
		else if(compare > 0)
			return getNode(rootNode.getRightChild(), entry);
		return null;
	}

	public boolean isEmpty() {
		if(rootNode == null)
			return true;
		else
			return false;
	}

	public void clear() {
		rootNode = null;
		
	}
	//getters and setters

	public Node<T> getRootNode() {
		return rootNode;
	}

	public void setRootNode(Node<T> rootNode) {
		this.rootNode = rootNode;
	}
	
	//private node class for binary nodes
	private class Node<T>
	{
		Node<T> leftChild;
		Node<T> rightChild;
		T data;
		
		public boolean hasLeftChild() 
		{
			if(leftChild == null)
				return false;
			else 
				return true;
			
		}
		public boolean hasRightChild() 
		{
			if(rightChild == null)
				return false;
			else 
				return true;
			
		}
		
		//getters and setters
		public Node<T> getLeftChild() {
			return leftChild;
		}
		public void setLeftChild(Node<T> leftChild) {
			this.leftChild = leftChild;
		}
		public void setLeftChild(T data) {
			Node<T> child = new Node<T>();
			child.setData(data);
			this.setLeftChild(child);
		}
		public Node<T> getRightChild() {
			return rightChild;
		}
		public void setRightChild(Node<T> rightChild) {
			this.rightChild = rightChild;
		}
		public void setRightChild(T data) {
			Node<T> child = new Node<T>();
			child.setData(data);
			this.setRightChild(child);
		}
		public T getData() {
			return data;
		}
		public void setData(T data) {
			this.data = data;
		}
		
	}
}


