import java.util.Stack;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class treeNumbers {

public static class Node{
	String data;
	Node left;
	Node right;
	public Node(String data){
		this.data = data;
		left = null;
		right = null;
	}
}
	private static Node root;
	private static int size = 1;
	public treeNumbers(){
		this.root=null;
	}

	public static void insertNumbers(String newMovie){
		Node newNode = new Node(newMovie);
		if(root==null){
			root = newNode;
			return;
		}
		Node current = root;
		Node parent = null;
		while(true){
			parent = current;
			String[] parts = newMovie.split("\\.");
			String[] currentDataParts = current.data.split("\\.");
			int id = Integer.valueOf(parts[0]);
			int currentData = Integer.valueOf(currentDataParts[0]);

			if(id<currentData){				
				current = current.left;
				if(current==null){
					size++;
					parent.left = newNode;
					return;
				}
			}else{
				current = current.right;
				if(current==null){
					size++;
					parent.right = newNode;
					return;
				}
			}
		}
	}

	public static String findKey(int key){
		Node current = root;
		while (current!=null){
			String[] parts = current.data.split("\\.");
			int currentNumber = Integer.valueOf(parts[0]);
			if(currentNumber == key){
				// System.out.println("Number: "+currentNumber);
				return  current.data;
			}
			else if(currentNumber > key){
				// System.out.println("Big: "+current.data.charAt(0));
				current = current.left;
			}
			else{
				// System.out.println("Smaller: "+current.data.charAt(0));
				current = current.right;
			}
		}
		return "No";
	}
	
	public static int size(){
		return size;
	}
}