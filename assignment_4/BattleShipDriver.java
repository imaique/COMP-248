/*
Assignment 4
Written by: Michael Junior Osuji, 40182642
For COMP 248 Section ECBX – Fall 2020

This program is a battleship game that prints the new grid after the user inputs the position where they want to
shoot. This version of battleship has boats that are only 1x1 and also contain grenades that skips the turn of the
player that shoots them. All of the user inputs can only be a character followed by an integer from 0-9
(example : A4), otherwise the code will break.

In the main class, the program first creates a grid where the game is going to be played. It then prompts the user to
enter the coordinates where they want to place their objects. After, it randomly places the computer's objects. Then, it
starts the game and prompts the user to enter a location they want to shoot a rocket at. Once the player's turn is over,
it moves on to the computer's turn where it randomly shoots on the grid, not shooting at its own objects. This goes on
until one of the players has all of their ships shot down. Once this happens, the opponent of that player is declared
the winner.
 */
import java.util.Scanner;
public class BattleShipDriver {
    // This array holds the different possible letters in order in this game.
    static char[] xLetters = {'a','b','c','d','e','f','g','h'};

    // Declaring the grid's dimensions and the number of boats and grenades as static because
    final static int GRID_WIDTH = 8;
    final static int GRID_HEIGHT =8;
    final static int BOAT_NB = 6;
    final static int GRENADES_NB = 4;

    // This will represent the boats that the computer and the user hits. If boatHitsComp reaches BOAT_NB, then
    // the computer wins and vice-versa.
    static int boatHitsComp = 0;
    static int boatHitsPlayer = 0;

    // These booleans are false if the corresponding player has not hit a grenade and true if they do
    static boolean skipPlayer = false;
    static boolean skipComp = false;

    // Declaring the Scanner to read the user input
    static Scanner keyIn = new Scanner(System.in);

    public static void main(String[] args) {
        // Welcome message
        System.out.println("Hello! Let's play Battleship.");
        System.out.println("");
        // Creating a new array of GridObject that will contain all of the boats and grenades of the players.
        // Each element of the array corresponds to a position in the grid.
        GridObject[][] playGrid = new GridObject[GRID_WIDTH][GRID_HEIGHT];
        // Initializing the elements of the array
        for (int i =0; i<GRID_WIDTH;i++){
            for (int j = 0; j< GRID_HEIGHT;j++){
                playGrid[i][j] = new GridObject();
            }
        }
        // These next two loops will read and store the user's boats and grenades positions.

        for (int i = 0; i< BOAT_NB; i++){
            // Declaring an input string to store the input of the user in the next and an int array that will store
            // the converted input.
            String input = "";
            int[] coord = new int[2];
            int x = 0;
            int y = 0;
            do {
                do {
                    System.out.print("Enter the coordinates of your ship #" + (i+1) + ": ");
                    input = keyIn.next();

                    // While the input is out of the bounds, keep asking for a new input.
                } while (isOutOfBounds(input));

                // Transform the inputs of the user into coordinates.
                coord = transform(input);
                x = coord[0];
                y = coord[1];

                // Check if the coordinates have already been selected by the user
                if (isOwned(playGrid[y][x]))
                    System.out.println("Sorry, these coordinates are already in use. Please try again.");

                // If the user already selected this position, tell them so and prompt a new coordinate.
            } while(isOwned(playGrid[y][x]));

            // Once the user entered a correct input, record that the object at this position is owned by the user and
            // that it is a boat.
            playGrid[y][x].setObject(GridObject.Owner.PLAYER, GridObject.Type.BOAT);
        }

        // This for loop is exactly the same as the previous one, but stores the grenades' positions.
        for (int i=0; i< GRENADES_NB; i++){
            String input = "";
            int[] coord = new int[2];
            int x = 0;
            int y = 0;
            do {
                do {
                    System.out.print("Enter the coordinates of your grenade #" + (i+1) + ": ");
                    input = keyIn.next();
                } while (isOutOfBounds(input));
                coord = transform(input);
                x = coord[0];
                y = coord[1];
                if (isOwned(playGrid[y][x]))
                    System.out.println("Sorry, these coordinates are already in use. Please try again.");
                // While the coordinates are occupied, ask for another coordinate
            } while (isOwned(playGrid[y][x]));
            playGrid[y][x].setObject(GridObject.Owner.PLAYER, GridObject.Type.GRENADE);
        }
        // Sets the position of the boats and the grenades of the computer.
        setComputer(playGrid);

        // This while loop will break once one of the player wins. It is the loop that will make the turns alternate
        // from the first player to the second.
        while (true){
            playPlayerTurn(playGrid);
            if (isPlayerWinner()) {
                System.out.println("Congratulations! You won!");
                break;
            }

            playComputerTurn(playGrid);
            if (isComputerWinner()){
                System.out.println("The computer won, better luck next time!");
                break;
            }
        }
        // Closing message
        System.out.println("");
        System.out.println("Thanks for playing! Bye bye.");
        // Closing the Scanner
        keyIn.close();
        System.exit(0);
    }
    // This method returns true if the user has hit 6 boats that was not his.
    public static boolean isPlayerWinner(){
        return (boatHitsPlayer==BOAT_NB);
    }
    // This method returns true if the computer has hit 6 boats that was not his.
    public static boolean isComputerWinner(){
        return (boatHitsComp==BOAT_NB);
    }
    // This method runs the desired effects on a position when this position has been hit by a rocket
    // (and has not been called previously)
    public static String performConsequence(GridObject playGrid, int player)
    {
        // If the object at this position is of type Boat, then add 1 to the boatHits integer of the opponent of the
        // owner of the object.
        if (playGrid.getType()== GridObject.Type.BOAT){
            if (playGrid.getOwner()== GridObject.Owner.COMPUTER)
                boatHitsPlayer++;
            if (playGrid.getOwner()== GridObject.Owner.PLAYER)
                boatHitsComp++;
        }
        // If the object at this position is of type Grenade, then skip the turn of the opponent of the owner of that
        // grenade
        if (playGrid.getType()== GridObject.Type.GRENADE) {
            if (player == GridObject.Owner.PLAYER)
                skipPlayer=true;
            if (player == GridObject.Owner.COMPUTER)
                skipComp=true;
        }
        // Return the message that should appear with the consequence
        return playGrid.getConsequence();

    }
    // This method sets the positions of the boats and grenades of the computer randomly.
    public static void setComputer(GridObject[][] playGrid){
        int [] coord;
        for (int i = 0; i< BOAT_NB; i++){
            do {
                // Generate random values (within the bounds) and assign them to the array that represents the
                // coordinates.
                coord = new int[] {(int)(Math.random()*GRID_WIDTH),(int)(Math.random()*GRID_HEIGHT)};

                // While the position is already owned by the computer or the user, generate a new position.
            } while (isOwned(playGrid[coord[1]][coord[0]]));

            // Assign this position to the computer and assign the type boat to it.
            playGrid[coord[1]][coord[0]].setObject(GridObject.Owner.COMPUTER, GridObject.Type.BOAT);
        }
        // This is the same loop as the previous one but for grenades.
        for (int i = 0; i< GRENADES_NB; i++){
            do {
                coord = new int[] {(int)(Math.random()*GRID_WIDTH),(int)(Math.random()*GRID_HEIGHT)};
            } while (isOwned(playGrid[coord[1]][coord[0]]));
            playGrid[coord[1]][coord[0]].setObject(GridObject.Owner.COMPUTER, GridObject.Type.GRENADE);
        }
        // Printing that the computer did place its objects.
        System.out.println("OK, the computer placed its ships and grenades at random. Let's play!");
    }
    //  This method does the turn of the player.
    public static void playPlayerTurn(GridObject[][] playGrid){
        // If the skipPlayer boolean is true, then set it to false and skip the turn.
        if (skipPlayer)
            skipPlayer = false;
        else{
            // If the skipPlayer boolean is false, play the player's turn
            // Create a string to store the user input and an array of integer to store the coordinates
            String input = "";
            int[] coord = new int[2];
            do {
                // Prompt the user for the position of their next rocket
                System.out.print("Position of your rocket: " );
                input = keyIn.next();

                // While their rocket's position is out of bounds, prompts for another position
            } while (isOutOfBounds(input));

            // Transforms the String input into integer an integer array and store the coordinates in x and y.
            coord = transform(input);
            int x = coord[0];
            int y = coord[1];

            // If the position has not been called, then call the consequence method on that position.
            // Finally, set the position to called
            if (!playGrid[y][x].getCalled()){
                System.out.println(performConsequence(playGrid[y][x], GridObject.Owner.PLAYER));
                playGrid[y][x].setCalled(true);
            }
            // Otherwise, tell the user that they wasted their turn.
            else
                System.out.println("This position was already called, you lose your turn.");
            // Print the grid
            print(playGrid);
       }
    }
    // this method performs the turn of the computer
    //** change
    public static void playComputerTurn(GridObject[][] playGrid){
        // If the skipComp boolean is true, then set it to false and skip the turn.
        if (skipComp)
            skipComp = false;
        else{
            int x;
            int y;
            do {
                // Generate random coordinates within the range of the grid (from 0-7)
                x = (int)(Math.random()*GRID_WIDTH);
                y = (int)(Math.random()*GRID_HEIGHT);

                // While the coordinates generated correspond to a coordinate already called or owned by the computer,
                // generate new coordinates.
            } while (playGrid[y][x].getCalled()||playGrid[y][x].getOwner()==GridObject.Owner.COMPUTER);

            // Print where the computer sends its rocket
            System.out.println("Position of the computer's rocket: " + toString(x,y) );

            // Run the consequenceMain method on the position and print the string returned by it.
            System.out.println(performConsequence(playGrid[y][x], GridObject.Owner.COMPUTER));

            // Set the position to called and print the grid
            playGrid[y][x].setCalled(true);
            print(playGrid);
        }
    }

    // converts coordinate values in integers into a string for output.
    public static String toString(int x, int y){
        // Adding one to y since it is from 0-7 instead of 1-8
        return String.valueOf(xLetters[x]).toUpperCase()+ String.valueOf(y+1);
    }

    // prints all of the positions in a 2D GridObject
    public static void print(GridObject[][] playGrid){
        // Prints 2 spaces to account for the column of numbers and the space in between it and the grid.
        System.out.print("  ");

        // Prints the letters that represents the columns
        for (int i = 0; i<xLetters.length;i++)
            System.out.print(String.valueOf(xLetters[i]).toUpperCase()+" ");

        // Prints all of the positions of the grid but starts with the number that corresponds to the row.
        System.out.println("");
        for (int i = 0; i<playGrid[0].length;i++){
            System.out.print((i+1) + " ");
            for (int j=0; j<playGrid.length;j++){
                System.out.print(playGrid[i][j]+" ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    // this method checks if the GridObject (a boat or a grenade) is owned by the computer or the player
    public static boolean isOwned(GridObject grid){
        return !(grid.getOwner()==GridObject.Owner.UNOWNED);
    }

    // transforms the user string input into coordinates from 0-7
    public static int[] transform(String input){
        // Initializing it to an invalid value to be able to remove the warning that x might not be initialized
        // and be able to debug if somehow this method returns x=-1.
        int x=-1;
        // Stores the character at the first position of the input and checks which x coordinate it corresponds to.
        char xChar = input.toLowerCase().charAt(0);
        for (int i =0; i<xLetters.length;i++) {
            if (xLetters[i] == xChar) {
                x = i;
                break;
            }
        }
        // Parses the input at the second position and stores it.
        int y = Integer.parseInt(input.substring(1));
        // Return y-1 because the integer will be from 1-8 but we want the corresponding index from 0-7.
        // x will always have a value since isOutOfBounds makes sure that the user inputs are within the range.
        return new int[]{x, y - 1};
    }

    // check is the user input coordinate is within the bounds of the grid
    public static boolean isOutOfBounds(String input){
        // if the length of the string is not 2, return that the input is out of bounds
        // (if the length is not 2 then the coordinate is not of the form A1)
        if (input.length()!=2)
            return true;
        // Stores the character at position 1 of the string, converts it to lowercase and checks if its one of the
        // letters in the grid.
        char xChar = input.toLowerCase().charAt(0);
        // Declaring a boolean that is true and turns false if found that that the letter coordinate is not within
        // the bound of the grid
        boolean outsideBounds = true;
        for (int i =0; i<xLetters.length;i++) {
            if (xLetters[i] == xChar) {
                outsideBounds = false;
                break;
            }
        }
        // if it the boolean is still true, that means that no letters within the bounds correspond to the input
        // letter so the coordinate is outside the bounds. If this is the case, return true and print that it is
        // outside of the bounds.
        if (outsideBounds){
            System.out.println("Sorry, these coordinates are outside of the grid. Please try again.");
            return true;
        }
        // If here, then the x coordinate is withing the bound. Check if the y is between 1 and 8 after turning
        // the substring into an integer and if not, return that it is outside the bounds and print that it is.
        int y = Integer.parseInt(input.substring(1));
        if (y>GRID_HEIGHT||y<1){
            System.out.println("Sorry, these coordinates are outside of the grid. Please try again.");
            return true;
        }
        // If we are here, then the coordinates are within the bounds, so return false.
        else
            return false;
    }
}
