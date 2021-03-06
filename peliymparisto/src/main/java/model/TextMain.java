package model;

/**
 * Testing class
 * 
 * Here game functionality and database testing and minor database modifications can be done.
 * @author Aki K, Tuomas R, Jere L, Ville R, Tatu N
 * @version 7.3 04.05.2021
 */
public class TextMain {

	public static void main(String[] args) {
//		Card card = new Card (1,12);
//		System.out.println(card.toString());
//		
//		Deck deck = new Deck();
//		deck.shuffle();
//		Hand hand = new Hand(deck);
//		
//			
//		Card cards [] = hand.getHand();
//		System.out.println(hand.toString());
		
		/*
		Card fours [] = { new Card (0, 4), new Card (2, 4), new Card (3, 4), new Card (0,4), new Card(0,2) };

		Card threes [] = { new Card (2, 7), new Card (4, 4), new Card (13, 7), new Card (11,14), new Card(2,7) };
		Card str [] = { new Card (2, 6), new Card (4, 7), new Card (1, 8), new Card (1,9), new Card(2,10) };
		Card flsh [] = { new Card (2, 4), new Card (2, 4), new Card (2, 4), new Card (2,12), new Card(2,2) };
		Card fhous [] = { new Card (2, 4), new Card (4, 4), new Card (2, 3), new Card (0,3), new Card(2,4) };
		Card strflsh [] = { new Card (2, 7), new Card (2, 11), new Card (2, 8), new Card (2,9), new Card(2,10) };
		Card twop [] = { new Card (2, 11), new Card (0, 3), new Card (0, 14), new Card (1,14), new Card(2,11) };
		Card acep [] = { new Card (2, 1), new Card (0, 6), new Card (0, 1), new Card (1,9), new Card(2,14) };

		//Deck deck = new Deck ();
		
		Hand h4s = new Hand(fours);
		Hand h3s = new Hand(threes);
		Hand hfls = new Hand(flsh);
		Hand hfhous = new Hand(fhous);
		Hand hstrflsh = new Hand(strflsh);
		Hand htwop = new Hand (twop);
		Hand hacep = new Hand(acep);
		Hand hstr = new Hand(str);
	
		System.out.println(h4s.getScore());
		System.out.println(h3s.getScore());
		System.out.println(hfls.getScore());
		System.out.println(hfhous.getScore());
		System.out.println(hstrflsh.getScore());
		System.out.println(htwop.getScore());
		System.out.println(hacep.getScore());
		System.out.println(hstr.getScore());
		
		*/
		
		//Tietokanta testailua
		DAOIF dao = new DAO();
		//printPlayers(dao);
		//printPlayedGames(dao);
		Player sis = dao.getPlayer(1001);
		System.out.println(sis.getProfileName());
		//sis.setProfileName("N/A");
		//dao.updatePlayer(sis);
		//System.out.println(sis.getProfileName());
		//dao.deleteAllPlayedGames();
		//Player jari = dao.getPlayer(1003);
		//jari.setFirstName("Kari");
		//dao.updatePlayer(jari);
		//printPlayers(dao);
		//addJari(dao);
		//addAarnioGame(dao);
		//removeAarnioGame(dao);
		//removeJari(dao);
		
	}
	
	private static void testIfTrue(boolean value) {
		if (value) {
			System.out.println("Success");
		} else {
			System.out.println("Error");
		}
	}
	
	private static void printPlayers(DAOIF dao) {
		System.out.println("-----------Showing Players------------");
		Player[] players1 = dao.readPlayers();
		for (Player player: players1) {
			System.out.println(player);
		}
	}
	
	private static void printPlayedGames(DAOIF dao) {
		System.out.println("-----------Showing Played Games-------------");
		PlayedGame[] playedGames1 = dao.readPlayedGames();
		for (PlayedGame playedGame: playedGames1) {
			System.out.println(playedGame);
		}
	}
	
	private static void addJari(DAOIF dao) {
		System.out.println("--------Trying to add a Player----------");
		Player veli = new Player("Jari", "Aarnio", "krpboss@poliisi.fi", "salasana123");
		boolean test = dao.createPlayer(veli);
		testIfTrue(test);
	}
	
	private static void addAarnioGame(DAOIF dao) {
		System.out.println("--------Trying to add a Played game----------");
		PlayedGame veliVastaanVeli = new PlayedGame(1002, 1003, "poker", 1003, 30, 60, 130);
		boolean test = dao.createPlayedGame(veliVastaanVeli);
		testIfTrue(test);
	}
	
	private static void removeAarnioGame(DAOIF dao) {
		System.out.println("--------Trying to remove a Played game----------");
		boolean test = dao.deletePlayedGame(2);
		testIfTrue(test);
	}
	
	private static void removeJari(DAOIF dao) {
		System.out.println("--------Trying to remove a Player----------");
		boolean test = dao.deletePlayer(1003);
		testIfTrue(test);
	}
	
	
}
