package model;
/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
*/

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * The Data Access Object to handle database connections, uses hibernate
 * @author Aki Koppinen
 * @version 1.5 01.03.2021
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
			System.out.println("Connecting to database...");
			sFactory = new Configuration().configure().buildSessionFactory();
			System.out.println("Connection OK");
		} catch (Exception e) {
			System.err.println("Creating the session factory failed: " + e.getMessage());
			System.exit(-1);
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
	public String searchEmail(String email) {
		try (Session session = sFactory.openSession()) {
			String hql = "from Player";
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<Player> result = query.list();
			
			for (Player a: result) {
				if (a.getEmail().equals(email)) {
					return a.getPassword();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/** Should make this hql command work instead
	public Player getPlayer(String email) {
		Player player = null;
		try (Session session = sFactory.openSession()) {
			String hql = "from Player where email=:email";
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(hql);
			player = (Player)query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return player;
	}
	*/
	
	/**	{@inheritDoc} */
	public Player getPlayer(String email) {
		try (Session session = sFactory.openSession()) {
			String hql = "from Player";
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<Player> result = query.list();
			
			for (Player a: result) {
				if (a.getEmail().equals(email)) {
					return a;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**	{@inheritDoc} */
	public Player getPlayer(int playerID) {
		Player player = new Player();
		try (Session session = sFactory.openSession()) {
			session.beginTransaction();
			player = (Player) session.get(Player.class, playerID);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return player;
	}
	
	/**	{@inheritDoc} */
	public PlayedGame getPlayedGame(int playedGameID) {
		PlayedGame playedGame = new PlayedGame();
		try (Session session = sFactory.openSession()) {
			session.beginTransaction();
			playedGame = (PlayedGame) session.get(PlayedGame.class, playedGameID);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return playedGame;
	}
	
	public ArrayList<String> readRankings() {
		ArrayList<String> statsList = new ArrayList<>();
		try (Session session = sFactory.openSession()) {
			
			String hql = "from Player";
			
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<Player> result = query.list();
			Collections.sort(result);
			for (Player a: result) {
				statsList.add(a.getCredits() + ", " + a.getFirstName() + " " + a.getLastName());
			}
			System.out.println("readrankings" + statsList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statsList;
	}
	
	public List[] readCredits(int id, int timestamp) {
		
		ArrayList<Double> creditsList = new ArrayList<>();
		ArrayList<String> datesList = new ArrayList<>();
		
		
		try (Session session = sFactory.openSession()) {
			
			switch(timestamp) {
			case 0:
				System.out.println("DAO   " + timestamp);
				//hql = "from PlayedGame where player1 = :id";
				break;
			case 1:
				System.out.println("DAO   " + timestamp);
				//hql = "from PlayedGame where player1 = :id";
				break;
			case 2:
				System.out.println("DAO   " + timestamp);
				//hql = "from PlayedGame where player1 = :id";
				break;
		}
			
			//String hql = "SELECT * FROM PlayedGame where `playedOn` >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH)";
			//String hql = "SELECT * FROM playedgame where player1 = " + id;
			String hql = "from PlayedGame where player1 = :id";
			
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(hql).setParameter("id", id);
			
			@SuppressWarnings("unchecked")
			List<PlayedGame> result = query.list();
			//List result = query.list();
			creditsList.add(100.0);
			
			for (PlayedGame a: result) {
				//System.out.println(a.getPlayedOn());
				//datesList.add((a.getPlayedOn());
				creditsList.add(a.getCreditAfterPlayer1());
				datesList.add((String) a.getPlayedOn().toString());
				//map.put((String) a.getPlayedOn().toString(), a.getCreditAfterPlayer1());
			}
			
			/*
			for (PlayedGame a: result) {
				//datesList.add((a.getPlayedOn());
				creditsList.add(a.getCreditAfterPlayer1());
				datesList.add((String) a.getPlayedOn().toString());
				//map.put((String) a.getPlayedOn().toString(), a.getCreditAfterPlayer1());
			}
			*/
			System.out.println("Result " + result);
			System.out.println("readcredits, dates" + datesList);
			System.out.println("readcredits, credits" + creditsList);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new List[] {datesList, creditsList};
	}

	/**	{@inheritDoc} */
	@Override
	public boolean updatePlayer(Player player) {
		Transaction transact = null;
		try (Session session = sFactory.openSession()) {
			transact = session.beginTransaction();
			session.update(player);
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
	public boolean updatePlayedGame(PlayedGame playedGame) {
		Transaction transact = null;
		try (Session session = sFactory.openSession()) {
			transact = session.beginTransaction();
			session.update(playedGame);
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
	public boolean deleteAllPlayers() {
		// TODO Auto-generated method stub
		return false;
	}

	/**	{@inheritDoc} */
	@Override
	public boolean deleteAllPlayedGames() {
		Transaction transact = null;
		try (Session session = sFactory.openSession()) {
			transact = session.beginTransaction();
			String hql = "delete from PlayedGame";
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(hql);
			int rows = query.executeUpdate();
			System.out.println("Delete successful, " + rows + " rows were deleted");
			transact.commit();
		} catch (Exception e) {
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
	public boolean deletePlayer(int playerID) {
		Transaction transact = null;
		try (Session session = sFactory.openSession()) {
			transact = session.beginTransaction();
			String hql = "delete from Player where id = :ID";
			session.createQuery(hql).setParameter("ID", playerID).executeUpdate();
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
	public boolean deletePlayedGame(int playedGameID) {
		Transaction transact = null;
		try (Session session = sFactory.openSession()) {
			transact = session.beginTransaction();
			String hql = "delete from PlayedGame where id = :ID";
			session.createQuery(hql).setParameter("ID", playedGameID).executeUpdate();
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

