import java.util.Iterator;
public interface BSTInterface <T>
{
	//gets data of the tree's root node
	//@return data from rootNode
	public T getRootData();
	
	//checks if tree is empty
	//@return true if tree is empty
	public boolean isEmpty();
	
	//clears all entries in the tree
	public void clear();
	
	//Searches for a specific entry in this tree
	//@param entry, An object to be found
	//@return true if object is contained in tree
	public boolean contains(T entry);
	
	//retrieves a specific entry from the tree
	//@param the object to be found
	//@return object matching entry or null if entry cannot be found
	public T getEntry(T entry);
	
	//adds a new entry to the tree if it does not match an existing entry
	//		else replaces exiting entry
	//@param entry, an object to add
	//@return null if the object was successfully added, else the existing object
	//		that was replaced
	public T add(T newEntry);
	
	//removes a specific entry from the tree
	//@param entry, an object to be removed
	//@return the object that was removed from the tree or null if no
	//		such object exists
	public T remove(T entry);
	
	//creates an iterator that traverses all the entries in the tree in-order
	//@return an iterator that traverses all the entries in the tree in-order
	public Iterator<T> getInorderIterator();
}
