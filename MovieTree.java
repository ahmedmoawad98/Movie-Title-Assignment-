/**
 * 
 */
package MovieTreePackage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;


//import MovieTree.Node;

/**
 * @author Ahmed
 *
 */
public class MovieTree {
	public MovieNode root;

	/**
	 * 
	 */
	public MovieTree() {
		// TODO Auto-generated constructor stub
		root = null;
	}
	/**
	 * 
	 */
	public void addNode(int releaseYear, String title) {
		// Create a new Node and initialize it
		MovieNode newNode = new MovieNode(releaseYear,title);
		// If there is no root this becomes root
	     if (root == null) {
	         root = newNode;
	     } else {
	    	// Set root as the Node we will start
	         // with as we traverse the tree
	    	 MovieNode CurrNode = root;
	    	// Future parent for our new Node
	    	 MovieNode parent;
	    	 while (true) {
	    		 // root is the top parent so we start there 
	    		 parent = CurrNode;
	    		// Check if the new node should go on
	             // the left side of the parent node
	    		 if (title.compareTo(CurrNode.title) <0){
	    			// Switch focus to the left child
	                 CurrNode = CurrNode.leftChild; 
	                 // If the left child has no children
	                 if (CurrNode == null) {
	                	// then place the new node on the left of it
	                	 parent.leftChild = newNode;
	                	 return; // All Done
	                 } 
	    		 } else { // If we get here put the node on the right
	    			 CurrNode= CurrNode.rightChild;
	    			// If the right child has no children
	    			 if (CurrNode==null) {
	    				 // then place the new node on the right of it
	    				 parent.rightChild = newNode;
	    				 return; // All Done
	    			 }
	    			 
	    		 }
	    	 }
	     }
	}

	
	 // All nodes are visited in ascending order
	 // Recursion is used to go to one node and
	 // then go to its child nodes and so forth
	 public void inOrderTraverseTree(MovieNode CurrNode) {
	     if (CurrNode != null) {
	         // Traverse the left node
	         inOrderTraverseTree(CurrNode.leftChild);
	         // Visit the currently focused on node
	         System.out.println(CurrNode);
	         // Traverse the right node
	         inOrderTraverseTree(CurrNode.rightChild);
	     }
	 }
	 public void subSet(MovieNode CurrNode, String start, String end) {
	     if (CurrNode != null) {
	         // Traverse the left node
	    	 subSet(CurrNode.leftChild, start, end);
	         // Visit the currently focused on node
	    	 if (  (CurrNode.title.compareTo(start) > 0 || CurrNode.title.compareTo(start) == 0)
	    		  &&	 
	    			((CurrNode.title.compareTo(end) < 0) || CurrNode.title.compareTo(end) == 0)
	    		 ) {
	    		 System.out.println(CurrNode);
	    	 }
	         
	         // Traverse the right node
	         subSet(CurrNode.rightChild, start, end);
	     }
	 }
	 public void preorderTraverseTree(MovieNode CurrNode) {
	     if (CurrNode != null) {
	         System.out.println(CurrNode);
	         preorderTraverseTree(CurrNode.leftChild);
	         preorderTraverseTree(CurrNode.rightChild);
	     }
	 }
	 public void postOrderTraverseTree(MovieNode CurrNode) {
	     if (CurrNode != null) {
	         postOrderTraverseTree(CurrNode.leftChild);
	         postOrderTraverseTree(CurrNode.rightChild);
	         System.out.println(CurrNode);
	     }
	 }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String csvFileName = "C:\\Users\\ahmed\\eclipse-workspace\\Movies\\src\\MovieTreePackage\\Data\\movies.csv";
		// TODO Auto-generated method stub
		// code to write output to a text file
		BufferedReader br =null; 
		String line = "";
		int lineCount = 0;
		int RecIndex = 0;
		//String csvSplitBy= ",";
		//We want to match only commas that are not between parentheses. 
		String csvSplitBy = ",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)";
		int SkipLines= 1; 
		MovieTree myMovieTree = new MovieTree();	
		try { 
			br= new BufferedReader( new FileReader(csvFileName)); 
			while ((line= br.readLine()) !=null) {
				lineCount++;
				if( lineCount > SkipLines) {
					String[] strLine = line.split(csvSplitBy, -1);
					String movieId = strLine[0];
					String title   = strLine[1].trim();
					String genres  = strLine[2];
					title = title.replaceAll("\"", "");
					
					// to skip the movies that don't have a release year i used a try and catch method
					try {
						int year = Integer.parseInt(title.substring(title.length() - 5, title.length() - 1));
						title = title.substring(0, title.length() - 7);
						/* print the movies from the files for testing purpose
						System.out.print("movieId: " + movieId);
						System.out.print(" title: "   + title);
						System.out.print(" year: "   + year);
						System.out.println(" genres: "  + genres);
						*/
						myMovieTree.addNode(year, title);
					}catch(Exception e){
			 		}
					
					
				}
			}
		}catch(FileNotFoundException e ){ 
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (br !=null) {
				try {
					br.close();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		System.out.println("inOrderTraverseTree");
		myMovieTree.inOrderTraverseTree(myMovieTree.root);
		
		System.out.println("preorderTraverseTree");
		myMovieTree.preorderTraverseTree(myMovieTree.root);
		
		System.out.println("postOrderTraverseTree");
		myMovieTree.postOrderTraverseTree(myMovieTree.root);
		
		myMovieTree.subSet(myMovieTree.root, "American Pie", "American Pop");
		
	}
	
		
	

}
