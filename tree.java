// import java.util.Stack;
// import java.io.*;
// import java.util.ArrayList;
// import java.util.List;

public class tree {
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
	public tree(){
		this.root=null;
	}

	public static int size(){
		return size;
	}

	public static void display(Node root){
		if(root!=null){
			System.out.println(" " + root.data);
			display(root.left);
			display(root.right);
		}
	}

	public static void fol(){
		display(root);
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
			int id = Integer.valueOf(newMovie.split("\\.")[0]);
			int currentData = Integer.valueOf(current.data.split("\\.")[0]);

			// String[] parts = newMovie.split("\\.");
			// String[] currentDataParts = current.data.split("\\.");
			// int id = Integer.valueOf(parts[0]);
			// int currentData = Integer.valueOf(currentDataParts[0]);

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



/*
	public void fileHandler(String name){
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(name));
			String line = br.readLine();
			while (line != null) {
		        System.out.println(line);
				line = br.readLine();
			}
		}catch(IOException e){
			System.out.println(e.toString());
		}
	}
	public static void insertLetter(String movie){
		Node newNode = new Node(movie.toLowerCase());
		if(root==null){
			root = newNode;
			// System.out.println("Root Data: "+root.data);
			return;
		}

		Node current = root;
		Node parent = null;
		while(true){
			parent = current;
			String currentData = current.data;
			int compare = currentData.compareToIgnoreCase(newNode.data);
			if(compare > 0){ //<0 means it's smaller
				// System.out.println("Smaller: ");
				current = current.left;
				if(current==null){
					parent.left = newNode;
					size+=1;
					// System.out.println("From tree: "+movie.toLowerCase());
					return;
				}
			}else if(compare < 0){
				// System.out.println("Bigger: ");
				current = current.right;
				if(current==null){
					parent.right = newNode;
					size+=1;
					// System.out.println("From tree: "+movie.toLowerCase());
					return;
				}
			}
			// else
			// 	return;
		}
	}

	public static List<String> findLetters(char letter, String[] rightLeft){
		List<String>  moviesList = new ArrayList<String>();
		Node current = root;
		while (current!=null){
			if(current.data.charAt(0) == letter){
				System.out.println("Found: "+current.data);
				moviesList.add(current.data);

				// if the next is not null and the first letter == the letter in the parameters

				if(current.left != null && 
				(current.left.data.charAt(0) == letter || current.left.data.charAt(0) > letter)){
					current = current.left;
				}
				// else if (current.left != null && current.left.data.charAt(0) < letter)
				// 	current = current.left;

				else if(current.right != null && 
				(current.right.data.charAt(0) == letter || current.right.data.charAt(0) < letter)){
					current = current.right;
				}
				// else if (current.right != null && current.right.data.charAt(0) > letter)
				// 	current = current.right;

				else{
					System.out.println("Quitting");
					break;
				}
			}
			else if(current.data.charAt(0) > letter){
				System.out.println("Smaller: "+current.data.charAt(0));
				current = current.left;
				continue;
			}
			else{
				System.out.println("Bigger: "+current.data.charAt(0));
				current = current.right;
				continue;
			}
		}
		return moviesList;
	}
	*/
}




