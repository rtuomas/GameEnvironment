package model;

/**
 * DAOIF gives interface for DAO class.
 * The game-engine connects to the database through this interface.
 * 
 * @author Aki Koppinen
 * @version 2.0 (06.02.2021)
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
	public boolean deletePlayers();
	/**
	 * Delete all played games from database
	 * @return success info
	 */
	public boolean deletePlayedGames();
	/**
	 * Deletes all players from database
	 * @return success info
	 */
	public boolean deletePlayer(Player player);
	/**
	 * Delete all played games from database
	 * @return success info
	 */
	public boolean deletePlayedGame(PlayedGame playedGame);

}
