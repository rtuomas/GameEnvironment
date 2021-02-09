package model;

/**
 * Testing class
 * @author ----
 * @version 7.0 06.02.2021
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
		
		DAOIF dao = new DAO();
		
		printPlayers(dao);
		printPlayedGames(dao);
		
		System.out.println("--------Trying to add a Player----------");
		Player veli = new Player("Jari", "Aarnio", "krpboss@poliisi.fi", "salasana123");
		//dao.createPlayer(veli); //kommentoitu pois ettei testiajoissa tulisi liikaa lisayksia tietokantaan
		printPlayers(dao);
		
		System.out.println("--------Trying to add a played game----------");
		PlayedGame veliVastaanVeli = new PlayedGame(1001, 1002, "pokeri", 1002, 30, 60, 130);
		//dao.createPlayedGame(veliVastaanVeli); //kommentoitu pois ettei testiajoissa tulisi liikaa lisayksia tietokantaan
		printPlayedGames(dao);

	}
	
	private static void printPlayers(DAOIF dao) {
		System.out.println("-----------Players------------");
		Player[] players1 = dao.readPlayers();
		for (Player player: players1) {
			System.out.println(player);
		}
	}
	
	private static void printPlayedGames(DAOIF dao) {
		System.out.println("-----------Played Games-------------");
		PlayedGame[] playedGames1 = dao.readPlayedGames();
		for (PlayedGame playedGame: playedGames1) {
			System.out.println(playedGame);
		}

	}
}
