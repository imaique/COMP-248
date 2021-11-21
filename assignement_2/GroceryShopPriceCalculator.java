//
// Assignment 2 Question 2
// Written by: Michael Junior Osuji, 40182642
// For COMP 248 Section ECBX – Fall 2020
//
//	This program prompts the user for the quantity of each product that they want to buy, asks if they are a member  
//	and returns the total price after discounts have been applied and also returns the amount of points received if they are a member.

package assignement2;
import java.util.Scanner;

public class GroceryShopPriceCalculator {

	public static void main(String[] args) {
		
		//Initializing double variables that store the price of every products.
		final double price1 = 26.99;
		final double price2 = 22.99;
		final double price3 = 13.99;
		final double price4 = 56.99;
		final double price5 = 38.99;
		//Printing the welcome message/
		System.out.println("---------------------------------------------------------");
		System.out.println("	    Grocery Shop Price Calculator		");
		System.out.println("---------------------------------------------------------");
		System.out.println();
		System.out.print("Please enter the quantities for each item in the list? ");
		// Creating keyIn to read the user's inputs
		Scanner keyIn = new Scanner(System.in);
		// Creating variables that store the amount of every product
		int num1 = keyIn.nextInt();
		int num2 = keyIn.nextInt();
		int num3 = keyIn.nextInt();
		int num4 = keyIn.nextInt();
		int num5 = keyIn.nextInt();
		//Prompting the user to input a string that indicates whether or not they have a membership
		System.out.print("Do you have a membership? (Y/N) ");
		//Storing the input as a boolean that is true if they do have a membership
		boolean Yes = (keyIn.next().compareToIgnoreCase("Y")==0);
		//Creating a double to store the total price
		double totalPrice = num1*price1 + num2*price2 + num3*price3 + num4*price4 + num5*price5;
		//Creating a double to store the total price without the sea food
		double totalPriceNoSea = num1*price1 + num2*price2 + num3*price3 + num4*price4;
		//Creating necessary double and integer variables
		double percentOff, discountedPrice;
		int pointMultiplier;
		int points;
		//Identifying which offers can be applied to the user's order depending on how much they spent
		if (totalPrice > 500) {
			percentOff = 20;
			pointMultiplier = 3;
		}
		else if (totalPrice >= 250) {
			percentOff = 15;
			pointMultiplier = 2;
		}
		else {
			percentOff = 10;
			pointMultiplier = 2;		
		}
		//Calculating the price after the discount has been applied
		discountedPrice = totalPrice - totalPriceNoSea*percentOff/100;
		//Calculating how many points the user will receive from this order
		points = (int)Math.round(discountedPrice*pointMultiplier);
		//Printing the total price the user will have to pay
		System.out.printf("The total price is $%.3f.", discountedPrice);
		//Printing the amount of points the user will receive if they are a member
		if (Yes)
			System.out.println(" You will receive "+points+" points.");
		//Printing the closing message and closing the Scanner
		System.out.println();
		System.out.println("Thanks for shopping! See you next time!");
		keyIn.close();
	}

}
