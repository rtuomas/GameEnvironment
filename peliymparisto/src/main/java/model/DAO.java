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
 * @author Aki Koppinen, Tuomas Rajala
 * @version 1.6 05.03.2021
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
	
	public ArrayList<Player> readRankings() {
		ArrayList<Player> statsList = new ArrayList<>();
		try (Session session = sFactory.openSession()) {
			
			String hql = "from Player";
			
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<Player> result = query.list();
			Collections.sort(result);
			
			for (Player a: result) {
				statsList.add(a);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statsList;
	}
	
	public List[] readCredits(int id, int count) {
		
		ArrayList<Double> creditsList = new ArrayList<>();
		ArrayList<String> datesList = new ArrayList<>();
		String hql = "from PlayedGame where player1 = :id order by id desc";
		
		try (Session session = sFactory.openSession()) {
			
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(hql).setParameter("id", id).setMaxResults(count);
			@SuppressWarnings("unchecked")
			List<PlayedGame> result = query.list();
			
			if(count==1000) creditsList.add(100.0);

			for (PlayedGame a: result) {
				creditsList.add(a.getCreditAfterPlayer1());
				datesList.add((String) a.getPlayedOn().toString());
			}
			Collections.reverse(creditsList);
			Collections.reverse(datesList);
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

	@Override
	public ArrayList<PlayedGame> readPlayedGames(int playerId) {
		ArrayList<PlayedGame> playedGames = new ArrayList<>();
		String hql = "from PlayedGame where player1 = :id";
		
		try (Session session = sFactory.openSession()) {
			
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(hql).setParameter("id", playerId);
			@SuppressWarnings("unchecked")
			List<PlayedGame> result = query.list();
			for (PlayedGame a: result) {
				playedGames.add(a);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return playedGames;
	}
	
}