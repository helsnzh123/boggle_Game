package boggle;

import java.io.*;
import java.util.*;

public class Trie {
	HashSet<String> words = new HashSet<>();
	private TrieNode root;//This is a dummy node. It can have any value because it will not be used.
	public Trie() {
		root = new TrieNode('\0');//Here we have set its value as null
	}
	
	class TrieNode{//This class has been made in order to store all the individual letters
		public char b;
		public boolean isEndWord; //This Boolean will tell us where the word ends in the Trie
		public TrieNode[] children;//This array will store all 26 children
		
		public TrieNode(char b) {
			this.b=b;
			isEndWord = false;//At the beginning we will initialize it to be zero
			children = new TrieNode[26];//We will initialize the array to have a size of 26
		}}
	
	public void insert(String word) {
		TrieNode curr = root;//This curr node will help us to keep track of where we are currently are when we are traversing the trie.
		for(int i = 0; i < word.length(); i++) {//This will loop over all our characters
			char b = word.charAt(i);//This is to extract the character
			if (curr.children[b - 'a'] == null) {//This if statement will check whether the curr has a node already created at char b
				curr.children[b - 'a'] = new TrieNode(b);//If the if statement holds true then that means there is no node present at curr so we will make a new node there
			}
			curr = curr.children[b - 'a'];//This will help us move down the chain inside of our trie
		}
		curr.isEndWord = true;//We have set the value as true here to signify that the word has ended
	}
private TrieNode getNode(String word) {//Helper function. This will return the very last node in the word we are looking for
	TrieNode curr = root;
	for (int i = 0; i < word.length(); i++) {
		char b = word.charAt(i);
		if (curr.children[b - 'a'] == null) {//This will check whether the node is even created at char b
			return  null;//If its not created then we just return null meaning we can't move forward
		}
		curr = curr.children[b - 'a'];
		}
	return curr;//We will return curr since its the very last node in the word
}

public boolean search(String word) {
	TrieNode node = getNode(word);//We are searching whether that specific word is inside of our Trie
	return node != null && node.isEndWord;//The first condition is important since getNode can return null
}

	public static void main(String[] args) throws Exception {
		Trie root1 = new Trie();
		Scanner scan = new Scanner(new FileInputStream("mydictionary.txt"));
		while (scan.hasNext()) {
			root1.insert(scan.next());
		}
		

}
	}
