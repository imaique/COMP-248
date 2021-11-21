//
// Assignment 1 Question 1
// Written by: Michael Junior Osuji, 40182642
// For COMP 248 Section ECBX – Fall 2020
//
//	This program prompts the user for two integers and calculates their addition, subtraction, multiplication and division.


import java.util.Scanner;

public class CallMeCalculator {
	public static void main(String[] args) 
	{
		// Welcome message and prompt message
		System.out.println("\\-------------------------------\\");
		System.out.println("/	Call me a Calculator	/");
		System.out.println("\\-------------------------------\\");
		System.out.println();
		System.out.print("Enter two integers and the second one cannot be zero: ");
		
		// Creating keyIn to read the user's inputs
		Scanner keyIn = new Scanner(System.in);
		// Creating integer variables for the two inputed numbers
		int num1 = keyIn.nextInt();
		int num2 = keyIn.nextInt();
		
		// Performing basic calculations with the two inputs
		System.out.println(num1 + " + " + num2 + " = " + (num1+num2));
		System.out.println(num1 + " - " + num2 + " = " + (num1-num2));
		System.out.println(num1 + " * " + num2 + " = " + (num1*num2));
		// Setting num1 to float otherwise it will not produce the desired answer as num1 and num2 are integers
		System.out.println(num1 + " / " + num2 + " = " + ((float)num1/num2));
		// Closing message and closing the Scanner variable
		System.out.println("All done!");
		keyIn.close();
	}
}
