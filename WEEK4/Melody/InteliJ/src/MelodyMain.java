// CSE 143, Homework 3: Melody
//
// Instructor-provided code.
// This program tests your Melody object on any file you want.
//
// When the program starts, type load and enter the name of the file you want to play
// You can then type play to hear it or any of the other commands to test you Melody's
// other functionality.


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;


public class MelodyMain {
	public static void main(String[] args) throws FileNotFoundException {
		test1();

		//test2();

	}

	private static void test1() throws FileNotFoundException {
		intro();

		Melody melody = new Melody();

		Scanner console = new Scanner(System.in);
		String command = "nothing";
		while (!command.equalsIgnoreCase("quit")) {
			System.out.print("What would you like to do? ");
			command = console.next();

			if (command.equalsIgnoreCase("load")) {
				System.out.print("File name? ");
				File file = checkFile(console.next(), console);
				Scanner input = new Scanner(file);
				melody.input(input);
			} else if (command.equalsIgnoreCase("play")) {
				melody.play();
			} else if (command.equalsIgnoreCase("playfrom")) {
				System.out.print("Start time? ");
				double time = console.nextDouble();
				melody.play(time);
			} else if (command.equalsIgnoreCase("reverse")) {
				melody.reverse();
			} else if (command.equalsIgnoreCase("save")) {
				System.out.print("Output file? ");
				PrintStream output = new PrintStream(new File(console.next()));
				melody.output(output);
			} else if (command.equalsIgnoreCase("length")) {
				System.out.println(melody.getLength() + " seconds long");
			} else if (command.equalsIgnoreCase("tempo")) {
				System.out.print("Percentage? ");
				double tempo = console.nextDouble();
				melody.changeTempo(tempo);
			} else if (command.equalsIgnoreCase("append")) {
				System.out.print("File name of second song? ");
				Melody other = new Melody();
				File file = checkFile(console.next(), console);
				other.input(new Scanner(file));
				melody.append(other);
			} else if (!command.equalsIgnoreCase("quit")) {
				System.out.println("Invalid command. Please try again.");
				intro();
			}
		}
	}

	static void test2() throws FileNotFoundException {
		Melody melody = new Melody();
		Scanner console = new Scanner(System.in);
		File file = checkFile("mystery.txt", console);
		Scanner input = new Scanner(file);

		melody.input(input);

		//melody.changeTempo(0.5);

		//melody.play();

		//melody.play(2.0);

		melody.reverse();

		file = checkFile("song.txt", console);
		input = new Scanner(file);

		Melody melody2 = new Melody();

		melody2.input(input);

		melody.append(melody2);

		melody.append(melody);

		melody.play();


		PrintStream output = new PrintStream(new File("out.txt"));
		melody.output(output);
	}

	// prints out an introduction describing how to use the program
	private static void intro() {
		System.out.println("Welcome to MelodyMain. Type the word in the left column to do the action on the right");
		System.out.println("load     : load a new input file");
		System.out.println("save     : output to a file");
		System.out.println("play     : play the last loaded song");
		System.out.println("playfrom : play the last loaded song from a certain time");
		System.out.println("reverse  : reverse the last loaded song");
		System.out.println("length   : print out the length of the last loaded song in seconds");
		System.out.println("tempo    : change the speed by a percentage");
		System.out.println("append   : appends notes from a second melody to the loaded one");
		System.out.println("quit     : exit the program");
	}
	
	// checks to make sure the file exists. Prompts the user for a new file until they 
	// input a valid one. Returns a file that exists.
	public static File checkFile(String name, Scanner console) {
		File file = new File(name);
		while (!file.exists()) {
			System.out.print("Invalid file. File name? ");
			file = new File(console.next());
		}
		return file;
	}
}
