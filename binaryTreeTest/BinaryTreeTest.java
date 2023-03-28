package binaryTreeTest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import binaryTreePD.BinaryTree;
import binaryTreePD.Student;


public class BinaryTreeTest {
	public void testTree() {
		BinaryTree<Integer, Student> tree = new BinaryTree<Integer, Student>();
		Student student;
		
		String fileName ="testdata.csv";
		String line = null;
	    try {
	        // FileReader reads text files in the default encoding.
	        FileReader fileReader = new FileReader(fileName);

	        // Always wrap FileReader in BufferedReader.
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        
	        while((line = bufferedReader.readLine()) != null) 
	        {
	        	String[] token = line.split(",");
	        	student = new Student(Integer.parseInt(token[0]), token[1]);
	        	tree.insert(student.getKey(), student);
	        }
	        
	        // Always close files.
	        bufferedReader.close();            
	    }
	    catch(FileNotFoundException ex) 
	    {
	      System.out.println("Unable to open file '" +  fileName + "'");                
	    }
	    catch(IOException ex) 
	    {
	       System.out.println ("Error reading file '" + fileName + "'");   	
	    }
	    
	    Student searchResult;
	    
	    System.out.println("Test Binary Tree Test");
	    System.out.println("Binary Tree Height = " + tree.height());
	    
	    System.out.println("Search for 782209");
	    searchResult = tree.search(782209);
	    if(searchResult == null)
	    	System.out.println("Search result:782209 not found");
	    else
	    	System.out.println("Search result:782209:" + searchResult);
	    
	    tree.remove(782209);
	    
	    searchResult = tree.search(782209);
	    if(searchResult == null)
	    	System.out.println("Search result:782209 not found");
	    else
	    	System.out.println("Search result:782209:" + searchResult);
	    
	    for(Student person:tree.inOrderTraversal()) {
	    	System.out.println(person);
	    }
	}
}