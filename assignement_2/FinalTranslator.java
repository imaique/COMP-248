//
// Assignment 2 Question 3
// Written by: Michael Junior Osuji, 40182642
// For COMP 248 Section ECBX – Fall 2020
//
//	This program prompts the user to enter a sentence with a precise format and returns a reorganized version of the input.  
//	The user can also end the program by entering "q" or "Q" instead of a sentence.

package assignement2;
import java.util.Scanner;
public class FinalTranslator {

	public static void main(String[] args) {
		// Welcome message and prompt message
		System.out.println("---------------------------------------------------------");
		System.out.println("	    Language Translator Program		");
		System.out.println("---------------------------------------------------------");
		System.out.println(" ");
		System.out.println("Please enter the input sentence (press q to exit): ");
		System.out.println(" ");
		// Creating keyIn to read the user's inputs
		Scanner keyIn = new Scanner(System.in);
		//Creating a loop that is always true and will break if "q" is what the user inputs.
		while (true){
			//Creating a constant containing the current year
			final int currentYear = 2020;
			//Creating the Strings that will be useful to the program.
			String name, city, country, year;
			//Setting the user inputs to their corresponding variables
			name = keyIn.next();
			//If the user inputs "q" or "Q", then the program ends.
			if (name.equalsIgnoreCase("q"))
				break;
			//These two next user inputs are not needed for the program output so they are not stored (came and to).
			keyIn.next(); 
			keyIn.next(); 
			city = keyIn.next();
			//Removing the comma at the end of city.
			city = city.substring(0,city.length()-1);
			country = keyIn.next(); 
			//This next input is not needed for the program output (in).
			keyIn.next(); 
			year = keyIn.next();
			//Removing the point at the end of the Year variable and converting it to an integer.
			int intYear = Integer.parseInt(year.substring(0,year.length()-1));
			//Calculating how long the person has been in the city.
			int howLong = currentYear-intYear;
			
			//Printing the desired output and the beginning prompt for the loop to work. 
			System.out.println(" ");
			System.out.println(name + " stays in " + city + " for " + howLong + " years. " + city + " is in " + country + ".");
			System.out.println(" ");
			System.out.println("Please enter the input sentence (press q to exit): ");
			System.out.println(" ");
			}
		//Printing the closing message, closing the Scanner and exiting the program.
		System.out.println(" ");
		System.out.println("Thanks for using translator program.");
		keyIn.close();
		System.exit(0);
	}
}
				

