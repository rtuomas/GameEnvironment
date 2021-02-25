package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import controller.Controller;
import model.Card;
/**
 * CLI of the pokergame
 * @author Tatu Nordström
 *
 */

public class ViewCLI implements ViewIF {
	
	public Controller init () {
		controller = new Controller(this);
		return controller;
	}
	
	
	// bet stuff
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
	public String getPlayer() {
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
		System.out.println(score);
		interActions();
	}
	
	@Override
	public void setCredits(double credits) {
		this.credits = credits;
	}
	
	public void interActions () {
		System.out.println("Tämän hetkinen saldo: " + this.credits + " panos: " + this.bet);
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
			indexes.add(s);
			}
		}
		controller.setSwappedCardIndexes(indexes);
	}
	
	private static boolean gameOver = false;
	private String player = "Pena Aarnio";
	private double credits;
	private double bet;
	private Controller controller;
	private static Scanner scanner = new Scanner(System.in);
	
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
}
