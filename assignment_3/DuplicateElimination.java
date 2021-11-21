//
// Assignment 3 Question 2
// Written by: Michael Junior Osuji, 40182642
// For COMP 248 Section ECBX – Fall 2020
// Originally created October 18th, 2020
// This program prompts the user for ten numbers between 10 and 100, inclusively, then outputs the unique ones. 

package Lessons7;
import java.util.Scanner;
public class DuplicateElimination {

	public static void main(String[] args) {
		// Creating a new scanner to read the user input.
		Scanner keyIn = new Scanner(System.in);
		// User prompt
		System.out.println("Please input a value in [10,100]");
		// Declaring this as a final constant because the number of input values should not change within the program. 
		// This constant represents the number of values we want from the user.
		final int INPUT_NB = 10;
		// Creating an array that will store all of the values.
		int[] values = new int[INPUT_NB];
		// Initializing the elements of the array to -1 to indicate that they are empty.
		for (int i=0; i<INPUT_NB;i++) 
			values[i]=-1;
		// Printing the value number and storing the input in order in an element of the array
		for (int i=0; i<INPUT_NB;i++) { 
			System.out.print("value " + (i+1) + ": ");
			values[i]=keyIn.nextInt();
			// If the value given does not fall within the prompted range, then prompt the user for another value that is.
			while (!(values[i]>=10 && values[i] <= 100)) {
				System.out.print("Wrong value, please input a value in [10,100]: ");
				values[i]=keyIn.nextInt();
			}
		}
		// This loop goes through every integer of the array
		for (int i=0; i<INPUT_NB; i++) {
			// This loop goes though every integer of the array that is at an index bigger than i
			for (int j=1+i; j<INPUT_NB; j++) {
				/* If the integer at that j position is equal to the one at the index i,
				 	set it to -1 because it is a duplicate value. The value at that position
				 	also needs to not already be equal to -1. */
				if (values[i]==values[j]&&values[j]!=-1) {
					values[j]=-1;
				}	
			}
		}
		// Printing out the unique values by printing all of the integers contained in the array that aren't equal to -1.
		System.out.println("The unique values are: ");
		for (int i=0; i<INPUT_NB; i++) {
			if (values[i]!=-1)
				System.out.print(values[i] + "\t");
		}
		// Printing the closing message and closing the scanner.
		System.out.println("");
		System.out.print("All done!");
		keyIn.close();
	}
}
