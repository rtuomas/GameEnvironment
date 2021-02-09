package model;

/**
 * Testing class
 * @author ----
 * @version 7.0 06.02.2021
 */
public class TextMain {

	public static void main(String[] args) {
		Card card = new Card (1,12);
		System.out.println(card.toString());
		
		Deck deck = new Deck();
		deck.shuffle();
		Hand hand = new Hand(deck);
		
		
		Card cards [] = hand.getHand();
		System.out.println(hand.toString());
		
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
