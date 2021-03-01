package model;

import java.util.ArrayList;

/**
 * DAOIF gives interface for DAO class.
 * The game-engine connects to the database through this interface.
 * 
 * @author Aki Koppinen
 * @version 2.5 01.03.2021
 */
public interface DAOIF {
	
	/**
	 * Creating a new played game to database
	 * @param PlayedGame object
	 * @return success info
	 */
	public boolean createPlayedGame(PlayedGame playedGame);
	/**
	 * Creating a new player to database
	 * @param Player object
	 * @return success info
	 */
	public boolean createPlayer(Player player);
	/**
	 * @return all played games from database
	 */
	public PlayedGame[] readPlayedGames();
	/**
	 * @return all players from the database
	 */
	public Player[] readPlayers();
	/**
	 * Updating a specific player
	 * @param player is the player to be updated
	 * @return success info
	 */
	public boolean updatePlayer(Player player);
	/**
	 * Updating a specific played game
	 * @param playedGame is the played game to be updated
	 * @return success info
	 */
	public boolean updatePlayedGame(PlayedGame playedGame);
	/**
	 * Deletes all players from database
	 * @return success info
	 */
	public boolean deleteAllPlayers();
	/**
	 * Delete all played games from database
	 * @return success info
	 */
	public boolean deleteAllPlayedGames();
	/**
	 * Deletes all players from database
	 * @return success info
	 */
	public boolean deletePlayer(int playerID);
	/**
	 * Delete all played games from database
	 * @return success info
	 */
	public boolean deletePlayedGame(int playedGameID);
	/**
	 * get a specific Player info from database
	 * @return the Player class
	 */
	public Player getPlayer(int playerID);
	/**
	 * get a specific PlayedGame info from database
	 * @return the PlayedGame class
	 */
	public PlayedGame getPlayedGame(int playedGameID);
	
	public ArrayList<String> readRankings();
	
	public ArrayList<Double> readCredits(int id);
	/**
	 * Searches for the given email and returns the password if found
	 * @param email in String form
	 * @return password in String form
	 */
	public String searchEmail(String email);
	/**
	 * Searches for the given email and returns the player object
	 * @param playerEmail
	 * @return Player object
	 */
	public Player getPlayer(String playerEmail);

}
