// Tyler Jones
// Project 2 RPS Console Game 10/15/2013

/*
 * This program interacts with the user via the console. It will get the users move and 
 * access the RPS class to generate a computer move. It will then interact with the RPS 
 * class to print out the match outcome, the running score, and the users balance if 
 * they decided to bet. 
 */

package rock_paper_scissors;

import java.util.Scanner;

public class RPSConsoleGame {

	public static final int SENTINEL = 666;
	public static final int ROCK = 1, PAPER = 2, SCISSORS = 3;

	private static int computerPlay, userMove, betAmount;
	private static String compMove, gameOutcome, betAmountString;

	public static void main(String[] args) {

		// Invokes the constructor from the RPS class
		RPS player1 = new RPS(userMove, betAmount);

		// Get a bet amount from the user
		System.out.println("Enter the bet amount, or enter 0 to play for fun without betting:");
		Scanner scan = new Scanner(System.in);
		betAmountString = scan.nextLine();
		betAmount = Integer.parseInt(betAmountString);
		player1.setBetAmount(betAmount);

		do {

			// get user input and insure it is lower case
			System.out.println("To play, enter:");
			System.out.println("      'r' to play ROCK");
			System.out.println("      'p' to play PAPER");
			System.out.println("      's' to play SCISSORS");
			System.out.println("      or any other character to quit");

			char input = scan.nextLine().toLowerCase().charAt(0);

			// Validate the user move and convert it to the proper integer and
			// string values
			String stringMove = null;
			if (input == 'r') {
				userMove = ROCK;
				stringMove = "ROCK";
			} else if (input == 'p') {
				userMove = PAPER;
				stringMove = "PAPER";
			} else if (input == 's') {
				userMove = SCISSORS;
				stringMove = "SCISSORS";
			} else {
				userMove = SENTINEL;
			}

			// calls the generateComputerPlay method to get a random number to
			// initialize computerPlay
			player1.generateComputerPlay();
			computerPlay = player1.getComputerPlay();

			// calls the findWinner method to determine outcome and increment score
			player1.findWinner(computerPlay, userMove);

			// Check user input, determine match results, and print.
			if (stringMove == null) {
				System.out.println("Thanks for playing.");
			} else {

				// Print out the user move
				System.out.println("You played " + stringMove);

				// Get the computer move from RPS class
				compMove = player1.getCompMove();
				System.out.println("The computer played: " + compMove);

				// Get the game outcome from RPS class
				gameOutcome = player1.getGameResult();
				System.out.println(gameOutcome);

				// Print out the string representation from my object
				// This will print the running total of the score
				System.out.println(player1);

				// if the user bet, print out the balance
				if (betAmount != 0) {
					System.out.println("Your balance is: $"
							+ player1.getBetBalance() + "\n");
				}
			}
		} while (userMove != SENTINEL);
	}
}
