import java.util.Scanner;

public class BstTest {
	static BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
	public static void main(String[] args)
	{
		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter a the initial sequence of values");
		String initialInput = kb.nextLine();
		Scanner input = new Scanner(initialInput);
		while(input.hasNextInt())
			bst.add(input.nextInt());
		input.close();
		
		System.out.print("Pre-order: ");
		bst.preorderTraversal();
		System.out.print("In-order: ");
		bst.inorderTraversal();
		System.out.print("Post-order: ");
		bst.postorderTraversal();
		
		while(true)
		{
			System.out.print("Command? ");
			String command = kb.next();
			command = command.toLowerCase();
			if(command.equals("h")) 
			{
				System.out.println("\tI Insert a value"
						+ "\n\tD Delete a value"
						+ "\n\tP Find predecessor"
						+ "\n\tS Find successor"
						+ "\n\tE Exit the program"
						+ "\n\tH Display this message");
				kb.nextLine();
			}
			else if(command.equals("e"))
			{
				break;
			}
			else 
			{
				Integer value = new Integer(kb.nextInt());
				kb.nextLine();
				
				if(command.equals("i"))
					add(value);
				if(command.equals("d"))
					delete(value);
				if(command.equals("p"))
					predecessor(value);
				if(command.equals("s"))
					successor(value);
			}
		}
		kb.close();
	}
	public static void add(Integer value)
	{
		Integer check = bst.add(value);
		if(check != null)
			System.out.println(value +" already exists, ignore.");
		else
		{
			System.out.print("In-order: ");
			bst.inorderTraversal();
		}
	}
	public static void delete(Integer value)
	{
		Integer check = bst.remove(value);
		if(check == null)
			System.out.println(value +" doesn't exist");
		else
		{
			System.out.print("In-order: ");
			bst.inorderTraversal();		
		}
	}
	public static void predecessor(Integer value) 
	{
		Integer check = bst.getPredecessor(value);
		if(check != null)
			System.out.println(check);
		else
			System.out.println(value +" has no predecessor");
	}
	public static void successor(Integer value) 
	{
		Integer check = bst.getSuccessor(value);
		if(check != null)
			System.out.println(check);
		else
			System.out.println(value +" has no successor");
	}
}