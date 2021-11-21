//
// Assignment 2 Question 1
// Written by: Michael Junior Osuji, 40182642
// For COMP 248 Section ECBX – Fall 2020
//
//	This program prompts the user for a letter from a to z, prints its ASCII number, 
//	prints whether it is odd or even and whether it is a multiple of 2, 3, 5 or 7.

package assignement2;
import java.util.Scanner;

public class AToZ {

	public static void main(String[] args) {
		// Creating keyIn to read the user's inputs
		Scanner keyIn = new Scanner(System.in);
		// Welcome message and prompt message
		System.out.print("Please enter a letter from a to z: ");
		//Creating a character that stores the user input
		char ch = keyIn.next().charAt(0);
		//Storing the ASCII value of the character
		int ascii= ch; 
		//If the value is even, then this boolean is true.
		boolean even;
		String oddOrEven;
		//Tests if the value is even and if not, defaults to odd.
		switch (ascii%2) {
			case 0:
				even = true;
				oddOrEven = "even";
				break;
			default:
				even = false;
				oddOrEven = "odd";
		}
		 //Printing out the desired output.
		System.out.println("The ASCII number of " + ch + " is " + oddOrEven + " number " + ascii + ".");
		//Tests if the ASCII value is a multiple of any of the relevant numbers, and if it is, print that it is.
		if (even || ascii%3==0 || ascii%5==0 || ascii%7==0 ){
		if (even) System.out.println("It is a multiple of 2.");
		if (ascii%3==0) System.out.println("It is a multiple of 3.");
		if (ascii%5==0) System.out.println("It is a multiple of 5.");
		if (ascii%7==0) System.out.println("It is a multiple of 7.");
		}
		//If it is not a multiple of any of these numbers, print that it is not.
		else
			System.out.print("It is none of 2, 3, 5, and 7.");
		//Close the Scanner
		keyIn.close();
		
	}

}
