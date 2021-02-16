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
	
	
	public void increaseBet() {
		if(this.bet!=10.0) {
			this.bet = betTable[Arrays.asList(betTable).indexOf(this.bet)+1];
		}
		interActions();
	}
	
	public void decreaseBet() {
		if(this.bet!=0.1) {
			this.bet = betTable[Arrays.asList(betTable).indexOf(this.bet)-1];
		}
		interActions();
	}
	
	@Override
	public double getBet() {
		return this.bet;
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
	
	private static boolean gameOver = false;
	private Double[] betTable = {0.1, 0.2, 0.4, 0.5, 0.6, 0.8, 1.0, 1.5, 2.0, 3.0, 4.0, 5.0, 10.0};
	private double bet = betTable[6];
	private String player = "Kari Aarnio";
	private double credits;
	private Controller controller;
	
	public static void main(String[] args) {
		ViewCLI cli = new ViewCLI ();
		Controller clr = cli.init();
		
		Scanner scanner = new Scanner(System.in);
		
		cli.interActions();
		while(!gameOver) {
			int selection = scanner.nextInt();
			switch(selection) {
			case 1:
				cli.increaseBet();
				break;
			case 2:
				cli.decreaseBet();
				break;
			case 3:
				clr.startPokerGame();
				break;
			case 4:
				System.out.println("Heippa");
				gameOver = true;
				break;
			}
		}
	}
}
