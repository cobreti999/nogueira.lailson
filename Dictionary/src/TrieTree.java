/*
 * 
 * WordFinder auxiliary classes.
 * 
 */

import java.util.ArrayList;;

/*
 * 
 * Node Class is the logical representation of a Node to be used by a Trie Tree
 * 
 */

class Node 
{
	boolean isEnd; 
	ArrayList<Node> childList; 
	int count; 
	char letter; 

	public Node(char c)
	{
		childList = new ArrayList<Node>();
		isEnd = false;
		letter = c;
		count = 0;
	}  
	
	public Node subNode(char c)
	{
		if (childList != null)
			for (Node eachChild : childList)
				if (eachChild.letter == c)
					return eachChild;
		return null;
	}
}

/*
 * 
 * TrieTree is used to store the dictionary
 * 
 */

class TrieTree
{
	private Node root;

	public TrieTree()
	{
		root = new Node(' '); 
	}
	
	/*
	 * 
	 * add(String word) add a new element to the Tree 
	 * 
	 */
	
	public void add(String word)
	{
		if (search(word) == true) 
			return;        
		Node current = root; 
		for (char ch : word.toCharArray() )
		{
			Node child = current.subNode(ch);
			if (child != null)
				current = child;
			else 
			{
				current.childList.add(new Node(ch));
				current = current.subNode(ch);
			}
			current.count++;
		}
		current.isEnd = true;
	}
	
	/*
	 * 
	 * search(String word) returns true if string is already inside the tree
	 * 
	 */
	
	public boolean search(String word)
	{
		Node current = root;  
		for (char ch : word.toCharArray() )
		{
			if (current.subNode(ch) == null)
				return false;
			else
				current = current.subNode(ch);
		}      
		if (current.isEnd == true) 
			return true;
		return false;
	}
	
	/*
	 * 
	 * remove(String word) removes an word from the Trie Tree
	 * 
	 */
	
	public void remove(String word)
	{ 
		if (search(word) == false)
        {
            System.out.println(word +" does not exist in trie\n");
            return;
        } 
		Node current = root;
		for (char ch : word.toCharArray()) 
		{ 
			Node child = current.subNode(ch);
			if (child.count == 1) 
			{
				current.childList.remove(child);
				return;
			} 
			else 
			{
				child.count--;
				current = child;
			}
		}
		current.isEnd = false;
	}              
}
