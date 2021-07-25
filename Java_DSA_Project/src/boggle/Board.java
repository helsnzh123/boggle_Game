package boggle;

import java.io.*;
import java.util.*;

public class Board {
	HashSet<String> words = new HashSet<>(); 
	private Trie trie;

	public Board() {
		trieCreation("mydictionary.txt");
	}

	static int randomIndice(String s)//This function is used to take in a string s which contains all the alphabets and then using random function of math we can get a random index number of the string
	{
		return  (int)(s.length()*Math.random());
	}

	static void randomBoggle(char [][]boggleBoard)//This is used to generate the random Boggle board by taking the randomIndice return value
	{
		String s="abcdefghijklmnopqrstuvwxyz";
		for (int i=0; i<4; i++)
		{
			for(int j=0; j<4; j++)
			{
				int index=randomIndice(s);
				boggleBoard[i][j]=s.charAt(index);
			}
		}
	}
	private void trieCreation(String filename)//This is used to insert the words of the dictionary into the newly formed Trie
	{
		trie = new Trie();
		Scanner scan;
		try {
			scan = new Scanner(new FileInputStream(filename));
			while (scan.hasNext()) {
				trie.insert(scan.next());
			}
			scan.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	void findWords(char[][] boggleBoard)	//This function will find all the valid words in the boggle
	{
		int m=boggleBoard.length;
		int n=boggleBoard[0].length;
		boolean[][] visited =new boolean[m][n];
		String str = "";


		for (int i = 0; i < m; i++) 
		{
			for (int j = 0; j < n; j++) 
			{
				searchWord(boggleBoard,visited,i,j,str,m,n);
			}
		}
	}

	/**
	 * @param boggleBoard A matrix of Board
	 * @param visited A boolean array to store which element is already visited
	 * @param i The row number
	 * @param j
	 * @param str
	 * @param m
	 * @param n
	 */
	void searchWord(char[][] boggleBoard, boolean[][] visited, int i, int j, String str, int m, int n)	//A recursive function to search all words present in the boggle
	{
		visited[i][j]=true;
		str+=boggleBoard[i][j];	//Marking current cell as visited and appending current character to string 
		if(trie.search(str))		//If string is present in dictionary then add it 
		{
			words.add(str);
		}

		for(int row=i-1; row<=i+1 && row<m ; row++)		//Now these two loops will traverse all the adjacent elements of the current element
		{
			for (int col = j - 1; col <= j + 1 && col < n; col++) 
			{
				if (row >= 0 && col >= 0 && !visited[row][col])
					searchWord(boggleBoard, visited, row, col, str, m, n);
			}
		}
		visited[i][j]=false;	
	}
	public static void main(String[] args) throws Exception {
		Board board = new Board();

		char [][]boggleBoard=new char[4][4];
		randomBoggle(boggleBoard);
		System.out.println("The random Board generated is as follows:");
		for (int i=0; i<4; i++)
		{
			for(int j=0; j<4; j++)
			{
				System.out.print(boggleBoard[i][j]);	
			}
			System.out.println();
		}
		System.out.println("The words found in the boggle board are as follows: ");
		board.findWords(boggleBoard);
		System.out.println(board.words);
	}
}




