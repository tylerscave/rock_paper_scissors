// Tyler Jones
// Project 2 RPS Game 10/15/2013

/*
 * This program describes the RPS Game (how the game works) and generates a random computer move. It also keeps 
 * track of wins, losses, ties, and the users balance (if they decided to bet). 
 */

package rock_paper_scissors;

import java.util.Random;

public class RPS {

	public static final int ROCK = 1, PAPER = 2, SCISSORS = 3;
	public static final int USER_WINS = 1, COMP_WINS = 2, TIE = 3;

	// instance data variables describe characteristics(state)
	private int computerPlay, gameResult, userMove;
	private int computerWins, userWins, ties;
	private int betAmount, betBalance;
	private String compMove, gameOutcome;

	// create a constructor
	public RPS(int theUserMove, int theBetAmount) {
		userMove = theUserMove;
		betAmount = theBetAmount;
	}

	// this method simulates a random a computer move and returns the outcome
	public int generateComputerPlay() {

		Random generator = new Random();
		computerPlay = generator.nextInt(3) + 1;
		return computerPlay;
	}

	// this method determines the winner of each match and keeps a running total
	// of the score and the bet balance
	public int findWinner(int compMove, int userMove) {

		if (compMove == ROCK) {
			if (userMove == ROCK) {
				gameResult = TIE;
			} else if (userMove == PAPER) {
				gameResult = USER_WINS;
			} else if (userMove == SCISSORS) {
				gameResult = COMP_WINS;
			}
		} else if (compMove == PAPER) {
			if (userMove == ROCK) {
				gameResult = COMP_WINS;
			} else if (userMove == PAPER) {
				gameResult = TIE;
			} else if (userMove == SCISSORS) {
				gameResult = USER_WINS;
			}
		} else if (compMove == SCISSORS) {
			if (userMove == ROCK) {
				gameResult = USER_WINS;
			} else if (userMove == PAPER) {
				gameResult = COMP_WINS;
			} else if (userMove == SCISSORS) {
				gameResult = TIE;
			}
		}
		if (gameResult == USER_WINS) {
			userWins++;
			betBalance += betAmount;
		} else if (gameResult == COMP_WINS) {
			computerWins++;
			betBalance -= betAmount;
		} else if (gameResult == TIE) {
			ties++;
		}
		return gameResult;

	}

	// setters
	public void setUserMove(int newUserMove) {
		userMove = newUserMove;
	}

	public void setBetAmount(int newBet) {
		betAmount = newBet;
	}

	// getters
	public int getComputerPlay() {
		return computerPlay;
	}

	public int getComputerWins() {
		return computerWins;
	}

	public int getUserWins() {
		return userWins;
	}

	public int getTies() {
		return ties;
	}

	public int getBetBalance() {
		return betBalance;
	}

	public String getCompMove() {
		if (computerPlay == ROCK) {
			compMove = "ROCK";
		} else if (computerPlay == PAPER) {
			compMove = "PAPER";
		} else if (computerPlay == SCISSORS) {
			compMove = "SCISSORS";
		}
		return compMove;
	}

	public String getGameResult() {
		if (gameResult == USER_WINS) {
			gameOutcome = "You Win!!";
		} else if (gameResult == COMP_WINS) {
			gameOutcome = "You Lose!";
		} else if (gameResult == TIE) {
			gameOutcome = "Tie!";
		}
		return gameOutcome;
	}

	// toString lets you print a text representation of my object
	public String toString() {
		String s = "wins: " + userWins + ", Losses: " + computerWins
				+ ", ties: " + ties + "\n";
		return s;
	}
}
