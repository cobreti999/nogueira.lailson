/*
 * 
 * WordFinder auxiliary classes.
 * 
 */

import java.util.LinkedList;

/*
 * 
 * Node Class is the logical representation of a Node to be used by a Trie Tree
 * 
 */

class Node {
	boolean isEnd; 
	LinkedList<Node> childList; 
	int count; 
	char letter; 

	public Node(char letter){
		childList = new LinkedList<Node>();
		isEnd = false;
		this.letter = letter;
		count = 0;
	}  
	
	public Node subNode(char letter){
		if (childList != null)
			for (Node eachChild : childList)
				if (eachChild.letter == letter)
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

	public TrieTree(){
		root = new Node(' '); 
	}
	
	/*
	 * 
	 * add(String word) add a new element to the Tree 
	 * 
	 */
	
	public void add(String word){
		if (search(word) == true) 
			return;        
		Node currentNode = root; 
		for (char letter : word.toCharArray() )
		{
			Node child = currentNode.subNode(letter);
			if (child == null){
				currentNode.childList.add(new Node(letter));
				currentNode = currentNode.subNode(letter);
			}
			else{
				currentNode = child;
			}
			currentNode.count++;
		}
		currentNode.isEnd = true;
	}
	
	/*
	 * 
	 * search(String word) returns true if string is already inside the tree
	 * 
	 */
	public boolean search(String word){
		Node currentNode = root;  
		for (char letter : word.toCharArray() ){
			if (currentNode.subNode(letter) == null){
				return false;
			}
			else{
				currentNode = currentNode.subNode(letter);
			}
		}      
		if (currentNode.isEnd){ 
			return true;
		}
		return false;
	}
	
	/*
	 * 
	 * remove(String word) removes an word from the Trie Tree
	 * 
	 */
	
	public void remove(String word){ 
		Node currentNode = root;
		for (char letter : word.toCharArray()) { 
			Node child = currentNode.subNode(letter);
			if (child.count == 1) {
				currentNode.childList.remove(child);
				return;
			} 
			else {
				child.count--;
				currentNode = child;
			}
		}
		currentNode.isEnd = false;
	}              
}
