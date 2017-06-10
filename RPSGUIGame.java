	// Tyler Jones
	// Project 2 RPS GUI Game 10/15/2013

	/*
 * This program will use a GUI interface to get input from the user for a rock, paper, or scissors
 * move. It will then use the RPS class to generate a computer move, display the match outcome, 
 * display the running score, and display the users balance if they chose to bet. 
 */

package rock_paper_scissors;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class RPSGUIGame extends JFrame {

	// buttons for the user to enter their move
	private JButton rockButton, paperButton, scissorsButton;

	// labels to display the number of wins/losses/ties/bets
	private JLabel statusC, statusU, statusT, statusB;

	// images and labels to display the computer and user's moves and the
	// outcome of a match-up
	private ImageIcon rockImage, paperImage, scissorsImage;
	private JLabel compPlay, userPlay;
	private JLabel outcome;

	// declare all primitives
	private static int betAmount;
	private int computerPlay, userMove;
	private static boolean bet;
	
	// the game object
	private RPS game;

	public RPSGUIGame() {

		// initializes the window
		super("Rock, Paper, Scissors, Go!");
		setSize(425, 300);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(Color.black);
		setResizable(false);

		// creates the game object
		game = new RPS(0, betAmount);

		// creates the labels for displaying the computer and user's move;
		// the images for the moves and the outcome of a match-up will be
		// displayed
		// in a single panel
		rockImage = new ImageIcon("rock.jpg");
		paperImage = new ImageIcon("paper.jpg");
		scissorsImage = new ImageIcon("scissors.jpg");

		compPlay = new JLabel();
		compPlay.setVerticalTextPosition(SwingConstants.BOTTOM);
		compPlay.setHorizontalTextPosition(SwingConstants.CENTER);
		compPlay.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		compPlay.setForeground(Color.cyan);
		userPlay = new JLabel();
		userPlay.setVerticalTextPosition(SwingConstants.BOTTOM);
		userPlay.setHorizontalTextPosition(SwingConstants.CENTER);
		userPlay.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		userPlay.setForeground(Color.cyan);

		outcome = new JLabel();
		outcome.setHorizontalTextPosition(SwingConstants.CENTER);
		outcome.setForeground(Color.pink);

		JPanel imageOutcomePanel = new JPanel();
		imageOutcomePanel.setBackground(Color.black);
		imageOutcomePanel.setLayout(new BorderLayout());
		imageOutcomePanel.add(compPlay, BorderLayout.WEST);
		imageOutcomePanel.add(userPlay, BorderLayout.EAST);
		imageOutcomePanel.add(outcome, BorderLayout.SOUTH);

		// creates the labels for the status of the game (number of wins,
		// losses, and ties);
		// the status labels will be displayed in a single panel
		statusC = new JLabel();
		statusU = new JLabel();
		statusT = new JLabel();
		statusB = new JLabel();
		statusC.setForeground(Color.white);
		statusU.setForeground(Color.white);
		statusT.setForeground(Color.white);
		statusB.setForeground(Color.white);
		JPanel statusPanel = new JPanel();
		statusPanel.setBackground(Color.black);
		statusPanel.add(statusC);
		statusPanel.add(statusU);
		statusPanel.add(statusT);
		if(bet == true){
		statusPanel.add(statusB);
		}
		// the play and status panels are nested in a single panel
		JPanel gamePanel = new JPanel();
		gamePanel.setPreferredSize(new Dimension(250, 250));
		gamePanel.setBackground(Color.black);
		gamePanel.add(imageOutcomePanel);
		gamePanel.add(statusPanel);

		// creates the buttons and displays them in a control panel;
		// one listener is used for all three buttons
		rockButton = new JButton("Play Rock");
		rockButton.addActionListener(new GameListener());
		paperButton = new JButton("Play Paper");
		paperButton.addActionListener(new GameListener());
		scissorsButton = new JButton("Play Scissors");
		scissorsButton.addActionListener(new GameListener());

		JPanel controlPanel = new JPanel();
		controlPanel.add(rockButton);
		controlPanel.add(paperButton);
		controlPanel.add(scissorsButton);
		controlPanel.setBackground(Color.black);

		// the gaming and control panel are added to the window
		contentPane.add(gamePanel, BorderLayout.CENTER);
		contentPane.add(controlPanel, BorderLayout.SOUTH);

		setVisible(true);
	}

	/* determines which button was clicked and updates the game accordingly */
	private class GameListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			Object source = event.getSource();
			updateUserMoveDisplay(source);

			updateComputerMoveDisplay();

			updateStatusDisplay();

		}
		// This method updates the users move based on the button clicked, and 
		//updates the users move image accordingly.
		private void updateUserMoveDisplay(Object source) {
			userPlay.setText("User Move");
			if (source == rockButton) {
				userPlay.setIcon(rockImage);
				game.setUserMove(RPS.ROCK);
				userMove = RPS.ROCK;
			} else if (source == paperButton) {
				userPlay.setIcon(paperImage);
				game.setUserMove(RPS.PAPER);
				userMove = RPS.PAPER;
			} else if (source == scissorsButton) {
				userPlay.setIcon(scissorsImage);
				game.setUserMove(RPS.SCISSORS);
				userMove = RPS.SCISSORS;
				
			}
		}

		// This method gets the computer move from the RPS class and updates the 
		// computer move image accordingly.
		private void updateComputerMoveDisplay() {
			compPlay.setText("Computer Move");
			game.generateComputerPlay();
			computerPlay = game.getComputerPlay();
			if (computerPlay == RPS.ROCK) {
				compPlay.setIcon(rockImage);
			} else if (computerPlay == RPS.PAPER) {
				compPlay.setIcon(paperImage);
			} else if (computerPlay == RPS.SCISSORS) {
				compPlay.setIcon(scissorsImage);
			}

		}

		// This method updates the status of the game, including: the match outcome,
		// the score, and the users balance if they decided to bet.
		private void updateStatusDisplay() {
			game.findWinner(computerPlay, userMove);
			statusC.setText("Computer Wins: " + game.getComputerWins());
			statusU.setText("User Wins: " + game.getUserWins());
			statusT.setText("Ties: " + game.getTies());
			statusB.setText("Your Balance: $" + game.getBetBalance());
			
			if(computerPlay == userMove) {
				outcome.setText(game.getGameResult());
			}else if(computerPlay == RPS.ROCK) {
				if(userMove == RPS.SCISSORS) {
					outcome.setText(game.getGameResult() + " Rock smashes Scissors!");
				}else if(userMove == RPS.PAPER) {
					outcome.setText(game.getGameResult() + " Paper smothers Rock!");
				}
			}else if(computerPlay == RPS.PAPER) {
				if(userMove == RPS.ROCK) {
					outcome.setText(game.getGameResult() + " Paper smothers Rock!");
				}else if(userMove == RPS.SCISSORS) {
					outcome.setText(game.getGameResult() + " Scissors shred Paper!");
				}
			}else if(computerPlay == RPS.SCISSORS) {
				if(userMove == RPS.ROCK) {
					outcome.setText(game.getGameResult() + " Rock smashes Scissors!");
				}else if(userMove == RPS.PAPER) {
					outcome.setText(game.getGameResult() + " Scissors shred Paper!");
				}
			}
		}
	}

	public static void main(String args[]) {

		// This will provide a pop up window to determine if the user wants to bet.
		int userAnswer = 0;
		userAnswer = JOptionPane.showConfirmDialog(null,
				"Do you want to place a bet?");

		// If the user clicked yes to be this will provide a second pop up to get the amount
		if (userAnswer == JOptionPane.YES_OPTION) {
			String betString = JOptionPane
					.showInputDialog("How much per round?");
			betAmount = Integer.parseInt(betString);
			bet = true;
		}

		
		// create an object of your class
		RPSGUIGame frame = new RPSGUIGame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
