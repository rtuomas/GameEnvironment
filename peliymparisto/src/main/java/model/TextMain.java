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
		System.out.println("-----------Players------------");
		Player[] players1 = dao.readPlayers();
		for (Player player: players1) {
			System.out.println(player);
		}
		System.out.println("-----------Played Games-------------");
		PlayedGame[] playedGames1 = dao.readPlayedGames();
		for (PlayedGame playedGame: playedGames1) {
			System.out.println(playedGame);
		}
		
		System.out.println("--------Trying to add a Player----------");
		Player veli = new Player("Jari2", "Aarnio", "krpboss@poliisi.fi", "salasana123");
		dao.createPlayer(veli); //kommentoitu pois ettei testiajoissa tulisi liikaa lisayksia tietokantaan
		System.out.println("-----------Players------------");
		Player[] players2 = dao.readPlayers();
		for (Player player: players2) {
			System.out.println(player);
		}
		
		System.out.println("--------Trying to add a played game----------");
		PlayedGame veliVastaanVeli = new PlayedGame(1001, 1002, "pokeri", 1002, 30);
		//dao.createPlayedGame(veliVastaanVeli); //kommentoitu pois ettei testiajoissa tulisi liikaa lisayksia tietokantaan
		System.out.println("-----------Played Games-------------");
		PlayedGame[] playedGames2 = dao.readPlayedGames();
		for (PlayedGame playedGame: playedGames2) {
			System.out.println(playedGame);
		}

	}
}
