//
// Assignment 3 Question 1
// Written by: Michael Junior Osuji, 40182642
// For COMP 248 Section ECBX – Fall 2020
// Originally created October 21st, 2020
//	This program solves the locker puzzle game.
import java.util.Scanner;
public class LockerPuzzleGame {
	public static void main(String[] args) {
		// Welcome message that prompts the user to input N or any other key to indicate whether or not they want the answer.
		System.out.print("This programs solves the locker riddle. Do you want to know the answer? Enter \"N\" if not. "
				+ "Otherwise, enter any key.");
		// Creating a new Scanner that will read the user input.
		Scanner keyIn = new Scanner(System.in);
		// If the user enters N or n, print the closing message and close the program. If the input is not N, 
		// the program moves on and solves the riddle.
		if ((keyIn.next()).equalsIgnoreCase("N")) {
			System.out.println("No problem, Bye bye!");
			System.exit(0);
		}	
		// Creating constants for the number of lockers and the number of students.
		final int LOCKER_NUMBER = 100, STUDENT_NUMBER = 100;
		// Creating an array of boolean that will indicate whether the locker corresponding to the element is opened or closed.
		boolean[] lockers = new boolean[LOCKER_NUMBER];
		// Setting every element to false which we will use to indicate that a locker is closed.
		for (int i =0; i<LOCKER_NUMBER; i++) {
			lockers[i] = false;
		}
		// This loop goes through every student.
		for (int i = 1; i <= STUDENT_NUMBER; i++) {
			/* This loop starts at the locker that corresponds to the student's
				number, and increases by the student's number to go to the next 
				locker that is a multiple of the student's number.*/
			for (int j = i; j<=LOCKER_NUMBER; j+=i) {
				// Inverses the state of the locker from closed to open or from open to closed.
				lockers[j-1] = !lockers[j-1];
			}
		}
		// This loop goes through every locker and if they're open, prints that they are.
		for (int i =0; i<LOCKER_NUMBER; i++) {
			if (lockers[i])
			System.out.println("Locker " + (i+1) + " is open.");
		}
		//Print the closing message and closes the Scanner.
		System.out.println("");
		System.out.println("All done.");
		keyIn.close();
	}
}
