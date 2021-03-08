package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import controller.Controller;
import model.Card;
import model.Player;
/**
 * CLI of the pokergame
 * @author Tatu Nordström, Aki Koppinen
 * @version 2.0 27.02.2021
 */

public class ViewCLI implements ViewIF {
	
	
	private static boolean gameOver = false;
	private double bet;
	private Controller controller;
	private static Scanner scanner = new Scanner(System.in);
	
	private Player player;
	
	public static void main(String[] args) {
		ViewCLI cli = new ViewCLI ();
		Controller clr = cli.init();
		
		cli.setBet();
		while(!gameOver) {
			int selection = scanner.nextInt();
			switch(selection) {
			case 1:
				cli.setBetIncrement();
				break;
			case 2:
				cli.setBetDecrement();
				break;
			case 3:
				clr.startPokerGame();
				cli.setSwappedCards();
				break;
			case 4:
				System.out.println("Heippa");
				gameOver = true;
				break;
			}
		}
	}
	
	public Controller init () {
		controller = new Controller(this);
		controller.getDefaultPlayer();
		return controller;
	}
	
	// bet stuffff
	public void setBetIncrement() {
		this.bet = controller.getBetIncrement();
		interActions();
	}
	
	public void setBetDecrement() {
		this.bet = controller.getBetDecrement();
		interActions();
	}
	
	public void setBet() {
		this.bet = controller.getBet();
		interActions();
	}
	
	@Override
	public Player getPlayer() {
		return this.player;
	}
	
	@Override
	public void setCards(ArrayList<String> cards) {
		for(String c : cards) {
			System.out.println(c.toString());
		}
	}
	
	@Override
	public void setScore(String score) {
		System.out.println(score); //NO_WIN ETC
		interActions();
	}
	
	@Override
	public void setCurrentPlayer(Player currentPlayer) {
		this.player = currentPlayer;
	}
	
	public void interActions () {
		System.out.println("Tämän hetkinen saldo: " + this.player.getCredits() + " panos: " + this.bet);
		System.out.println("1 = nosta panosta, 2 = vähennä panosta 3 = pelaa 4 = poistu");
	}
	
	@Override
	public void setSwappedCards () {
		System.out.println("Valitse kortit jotka haluat vaihtaa (1-5) lopeta 0:lla");
		int s = 15;
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		while(s != 0) {
			s = scanner.nextInt();
			if(s != 0) {
			indexes.add(s - 1);
			}
		}
		controller.setSwappedCardIndexes(indexes);
	}

	@Override
	public String getEmailInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPasswordInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showLogInError() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDefaultPlayer(Player defaultPlayer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getFirstNameRegInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLastNameRegInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProfileNameRegInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPasswordRegInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPasswordRegVerInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean getCreditTransferRegInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEmailRegInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handleRegistrationSuccess() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showRegistrationErrorEmptyFields() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showRegistrationErrorPasswordsNotMatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showRegistrationErrorEmailAlreadyExists() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showRegistrationErrorDatabase() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPokerGamePlayerCredits() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPokerGameBet(double bet) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void handlePlayerInfoChangeSuccess() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showPlayerInfoChangeErrorDatabase() {
		// TODO Auto-generated method stub
		
	}
	
}
