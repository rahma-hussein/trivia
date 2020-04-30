// import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

// import java.util.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

// java main 
// javac -Xlint:unchecked main.java 

// https://chartcons.com/100-trivia-questions-kids-answers/
public class main{
	private static int LIVES = 3;
	private static Set<Integer> indexs = new HashSet<Integer>();
	public static void main(String[] args){
		System.out.println("\n\t\t\t===================\n\t\t\tGreetings!! ay bebe\n\t\t\t===================\n");
		// LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		// String[] qArray = qADic(map, "/home/seb/trivia.txt");

		tree t = new tree();
		System.out.println("Loading file...");
		if(generateTree(t, "./trivia.txt"))
			System.out.println("Done!");
		// t.fol();
		// System.out.println("Size: "+ t.size());

		String [] movie = getMovie(t);
		// StringBuilder newmovie = new StringBuilder(movie[1]);
		// if(newmovie.charAt(0)==' ')
		// 	newmovie.deleteCharAt(0);
		// movie[1] = newmovie.toString();
		StringBuilder[] riddle = {};
		if(!movie[0].equals("No")){
			System.out.println("Q"+movie[0]);
			//System.out.println("A:"+movie[1]);
			riddle = generatePuzzle(movie[1]);
			System.out.println(riddle[0].toString());
			// System.out.println(riddle[1].toString());
			//System.out.println(riddle[2].toString().replace("]", "").replace("[", "").replace(", ", ""));
		}
		else
			System.out.println("Quiting...");
		if(play(riddle[0], new ArrayList<Integer>(indexs), riddle[1]))
			System.out.println("YOU WON!!");
		else{
			System.out.println("Game Over!\nAnswer: "+movie[1]);
		}
	}

	private static Boolean generateTree(tree t, String file){
        BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			// System.out.println("Loading movies...");
			while (line != null) {
				if(line.isEmpty()){
					line = br.readLine();
					continue;
				}
				// System.out.println("From File: "+line);
				tree.insertNumbers(line);
				line = br.readLine();
			}
			// System.out.println("Done!");
			return true;
        }catch(IOException e){
			System.out.println(e.toString());
        }
		return false;
	}

	private static String[] getMovie(tree t){
		Random rand = new Random();
		int  randomNumber = rand.nextInt(t.size()) + 1;
		// System.out.println("Random: "+randomNumber);
		String movie = t.findKey(randomNumber);
		// System.out.println("Movie: "+movie);
		String[] parts = movie.split("\\?");
		if(parts[0].equals("No"))
			return new String[]{"No"};
		return new String[]{parts[0], parts[1].trim()};
	}

	private static StringBuilder[] generatePuzzle(String answer){
		Random rand = new Random();
		int lettersLeft = rand.nextInt(answer.length()-1)+1;
		// System.out.println("lettersLeft: "+lettersLeft+ " letters left");

		// int indeces[] = new int[lettersLeft];
		//generate Random values in array
		for(int x = 0; x < lettersLeft; x++) {
			int random = rand.nextInt(lettersLeft);
			if(answer.charAt(random) != ' ')
				indexs.add(random);
			else
				x--;
			// System.out.print(" "+indeces[x]);
		}
		// Arrays.sort(indeces);
		// System.out.println("Array:"+Arrays.toString(indeces));

		// System.out.println("My: "+indexs.toString());

		StringBuilder riddle = new StringBuilder(answer);
		StringBuilder charsLeftSB = new StringBuilder();

		Iterator it = indexs.iterator();
		for (int i : indexs){
			if(riddle.charAt(i) != ' '){
				riddle.setCharAt(i, '*');
				charsLeftSB.append(answer.charAt(i));
			}
		}

		// while(it.hasNext()) {
		// 	int i = (int)it.next();
		// 	if(riddle.charAt(i) != ' '){
		// 		riddle.setCharAt(i, '*');
		// 		charsLeftSB.append(answer.charAt(i));
		// 	}
		// }

		// for(int i = 0; i < indeces.length; i++){
		// 	if(riddle.charAt(indeces[i]) != ' '){
		// 		riddle.setCharAt(indeces[i], '*');
		// 		leftSB.append(answer.charAt(indeces[i]));
		// 	}
		// }

		// System.out.println(charsLeftSB.toString());
		// String message = String.join("-", indexs.toString());
	
		// System.out.println(message);

		return new StringBuilder[] {riddle, charsLeftSB};
	}

	private static Boolean play(StringBuilder riddle, List<Integer> indexs, StringBuilder charsLeftSB){
		Scanner input= new Scanner(System.in);
		int size = charsLeftSB.length();
		while(LIVES>0){
			// System.out.println("\n"+charsLeftSB.toString());
			// System.out.println(indexs);

			System.out.print("\nEnter Char: ");
			String userInput = input.nextLine();
			if(userInput.isEmpty()){
				LIVES--;
				continue;
			}

			for(int i = 0; i < charsLeftSB.length(); i++){
				if(userInput.charAt(0)  == charsLeftSB.charAt(i)){
					riddle.setCharAt(indexs.get(i), userInput.charAt(0));
					// riddle.setCharAt((int)indexs.charAt(i)-'0', userInput.charAt(0));
					charsLeftSB.deleteCharAt(i);
					indexs.remove(i);
					// indexs.deleteCharAt(i);
					// System.out.println(riddle);
				}
			}
			if(charsLeftSB.length()==0){
				System.out.println(riddle);
				LIVES=3;
				return true;
			}
			if(size == charsLeftSB.length()){
				LIVES--;
			}else{
				size = charsLeftSB.length();
				System.out.println(riddle);
			}
		}
		return false;
	}
/*
	private static String[] getMovieLetters(tree t){
		char LETTERS[] = {
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
			'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 
			's', 't', 'u','v', 'w', 'x', 'y', 'z'};
		Random rand = new Random();
		int  randomIndex = rand.nextInt(26) + 0;
		int  randomNumber = rand.nextInt(t.size()) + 1;

		System.out.println("Random: "+LETTERS[randomIndex]+", "+randomNumber);
		List movieList = t.findLetters('w', new String[]{"left", "right"});//LETTERS[randomIndex]);

		System.out.println(movieList);

		int randomIndexList = movieList.isEmpty()? -1: rand.nextInt(movieList.size()) + 0;
		String finalMovie = "Not found";
		if(randomIndexList != -1){
			finalMovie = movieList.get(randomIndexList).toString();
			System.out.println("[+] Movie Picked: "+finalMovie+", "+randomIndexList);
			String[] parts = finalMovie.split("\\?");
			return new String[]{parts[0].toString(), parts[1].toString()};
		}
		else
			System.out.println("Movies aren't sufficient, add more movies from a to z so it gets interesting.");
		return new String[]{"No", "No"};
	}

	private static String[] qADic(LinkedHashMap<String, String> map, String file){
        BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			System.out.println("Loading movies...");
			while (line != null) {
				if(line.isEmpty()){
					line = br.readLine();
					continue;
				}
				String[] parts = line.split("\\?");
				map.put(parts[0], parts[1]);
				// System.out.println(parts[0]+"  "+ parts[1]+"\nFrom File: "+line);
				line = br.readLine();
			}
			System.out.println("Done!");
        }catch(IOException e){
			System.out.println(e.toString());
        }
		Random rand = new Random();
		int  randomIndex = rand.nextInt(map.size()-1) + 0;

		Object question = (map.keySet().toArray())[randomIndex] ;
		Object answer = map.get(question);

		// System.out.println("Question: "+ question.toString());
		// System.out.println("Answer: "+ answer.toString());
		return new String[]{question.toString(), answer.toString()};
	}
	*/
}

