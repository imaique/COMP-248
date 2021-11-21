//
// Assignment 1 Question 2
// Written by: Michael Junior Osuji, 40182642
// For COMP 248 Section ECBX – Fall 2020
//
//	This program prompts the user for his name and states how many letters are in it. 
//	Then the program prompts the user for the city where they live, then prints the city name with various variations as to whether the letters are uppercase or lowercase.

import java.util.Scanner;
public class IAmAChatBot {

	public static void main(String[] args) {
		// Welcome message and name prompt
		Scanner keyIn = new Scanner(System.in);
		System.out.println("	I am a chat bot");
		System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ ");
		System.out.println("");
		System.out.print("-- Hi, my name is chatBot. What is your name?");
		// Creating a string to store the user's name and an integer to store it's length
		String name = keyIn.next();
		int nameLength = name.length();
		System.out.println("");
		
		// Setting the name input to upper case and prompting for a city name
		System.out.println("-- Hello " + name.toUpperCase() + ", your name has " + nameLength + " characters.");
		System.out.println("-- Where are you? (City name with at least 3 letters)");
		String city = keyIn.next();
		
		// Creating variables needed for the next steps.
		// This is to set every case in city to lower case
		city = city.toLowerCase();
		
		// Creating integers to store the length of city and it's length divided by two 
		// for manipulations with the user's city input
		int cityLength = city.length();
		int middleNumber = cityLength/2;
		
		
		
		// city1 is city with its middle letter in upper case
		String city1 = city.substring(0,middleNumber) + city.substring(middleNumber,middleNumber+1).toUpperCase() 
				+ city.substring(middleNumber+1);
		
		System.out.println("-- With the middle letter(s) in upper case: " + city1);
		// city2 is city with its second letter in upper case
		String city2 = city.substring(0,1) + city.substring(1,2).toUpperCase() + city.substring(2);
		
		System.out.println("-- With the second letter in upper case: " + city2);
		// city3 is city with its second to last letter in upper case
		// secondToLast is the length of the city input -2 to account for the fact that the index of substring starts at 0. 
		int secondToLast = cityLength - 2; 
		String city3 = city.substring(0,secondToLast) + city.substring(secondToLast,cityLength).toUpperCase() 
				+ city.substring(cityLength);
		
		System.out.println("-- With the second to last letter in upper case: " + city3);
		
		// Exit message and closing the Scanner variable.
		System.out.println("-- I am done. Byebye.");
		keyIn.close();
	}

}
