import java.util.Scanner;
import java.util.Iterator;
public class BSTDemo {
	static BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>(9);
	public static void main(String[] args)
	{
		bst.add(12);
		bst.add(6);
		Iterator<Integer> it = bst.getInorderIterator();
		System.out.println(it.next());
		System.out.println(it.next());
	}
}
