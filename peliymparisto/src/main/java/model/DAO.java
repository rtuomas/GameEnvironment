package model;
/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
*/

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * The Data Access Object to handle database connections, uses hibernate
 * @author Aki Koppinen
 * @version 1.0 06.02.2021
 */
public class DAO implements DAOIF {
	
	/**
	 * Session factory needed for handling the database
	 */
	private SessionFactory sFactory = null;
	
	/**
	 * The constructor for DAO class which tries to create a connection to the database
	 * Uses information from hibernate.cfx.xml document found in src/main/recources file
	 */
	public DAO() {
		try {
			sFactory = new Configuration().configure().buildSessionFactory();
			System.out.println("Kaikki OK");
		} catch (Exception e) {
			System.err.println("Creating the session factory failed: " + e.getMessage());
			System.exit(-1); //delete this for testing
		}
	}
	
	@Override
	public void finalize() {
		try {
			if (sFactory != null) {
				sFactory.close();
			}
		} catch (Exception e) {
			System.err.println("Closing the session factory failed: " + e.getMessage());
		}
	}
	
	/**	{@inheritDoc} */
	@Override
	public boolean createPlayedGame(PlayedGame newPlayedGame) {
		Transaction transact = null;
		try (Session session = sFactory.openSession()) {
			transact = session.beginTransaction();
			PlayedGame playedGameToBeSaved = newPlayedGame;
			session.saveOrUpdate(playedGameToBeSaved);
			transact.commit();
		} catch(Exception e) {
			if (transact != null) {
				transact.rollback();
			}
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**	{@inheritDoc} */
	@Override
	public boolean createPlayer(Player newPlayer) {
		Transaction transact = null;
		try (Session session = sFactory.openSession()) {
			transact = session.beginTransaction();
			Player playerToBeSaved = newPlayer;
			session.saveOrUpdate(playerToBeSaved);
			transact.commit();
		} catch(Exception e) {
			if (transact != null) {
				transact.rollback();
			}
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**	{@inheritDoc} */
	@Override
	public PlayedGame[] readPlayedGames() {
		ArrayList<PlayedGame> playedGamesList = new ArrayList<>();
		
		try (Session session = sFactory.openSession()) {
			String hql = "from PlayedGame";
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<PlayedGame> result = query.list();
			
			for (PlayedGame a: result) {
				playedGamesList.add(a);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		PlayedGame[] playedGamesArray = new PlayedGame[playedGamesList.size()];
		
		return (PlayedGame[])playedGamesList.toArray(playedGamesArray);
	}

	/**	{@inheritDoc} */
	@Override
	public Player[] readPlayers() {
		ArrayList<Player> playersList = new ArrayList<>();
		
		try (Session session = sFactory.openSession()) {
			String hql = "from Player";
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<Player> result = query.list();
			
			for (Player a: result) {
				playersList.add(a);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		Player[] playersArray = new Player[playersList.size()];
		
		return (Player[])playersList.toArray(playersArray);
	}

	/**	{@inheritDoc} */
	@Override
	public boolean updatePlayer(Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	/**	{@inheritDoc} */
	@Override
	public boolean updatePlayedGame(PlayedGame playedGame) {
		// TODO Auto-generated method stub
		return false;
	}

	/**	{@inheritDoc} */
	@Override
	public boolean deletePlayers() {
		// TODO Auto-generated method stub
		return false;
	}

	/**	{@inheritDoc} */
	@Override
	public boolean deletePlayedGames() {
		// TODO Auto-generated method stub
		return false;
	}

	/**	{@inheritDoc} */
	@Override
	public boolean deletePlayer(Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	/**	{@inheritDoc} */
	@Override
	public boolean deletePlayedGame(PlayedGame playedGame) {
		// TODO Auto-generated method stub
		return false;
	}
	
}

/*
public class DAO implements DAOIF {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC-ajurin lataus epäonnistui");
			System.exit(-1); // lopetus heti virheen vuoksi
		}
		//ArrayList<Valuutta> valuutat = new ArrayList<>();
		//final String URL = "jdbc:mysql://localhost/game_database";
		String URL = "jdbc:mysql://localhost:2206/game_database";
		final String USERNAME = "akikoppinen";
		final String PASSWORD = "rootpassword";
		try {
			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			Statement stmt1 = conn.createStatement();
			String query1 = "SELECT firstName, lastName FROM player";
			ResultSet rs = stmt1.executeQuery(query1);
			
			while (rs.next()) {
				 String etunimi = rs.getString("firstName"); // tai rs.getInt(1)
				 String sukunimi = rs.getString("lastName");
				 System.out.println(etunimi);
				 //valuutat.add(new Valuutta(etunimi, sukunimi));
			}
			
			ResultSetMetaData rsMetaData = rs.getMetaData();
			int a = rsMetaData.getColumnCount();
			String b = rsMetaData.getColumnName(1); //starts from 1, käytä (getInt(), getDouble(),getBoolean() ja getString()
			
			System.out.println(a);
			System.out.println(b);
			
			//for (Valuutta valuutta: valuutat) {
				//System.out.println(valuutta);
			//}
			
			/*
			Statement stmt2 = conn.createStatement();
			String query2 = "INSERT INTO valuutta " +
			 "VALUES ('NTD', 40.0, 'New Taiwan Dollar')";
			int count = stmt2.executeUpdate(query2); //rivimuutosten määrä
			
			
			rs.close(); // tulosjoukko
			stmt1.close(); // kysely
			//stmt2.close(); // kysely
			conn.close(); // tietokantayhteys, vapauta AINA
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}*/

