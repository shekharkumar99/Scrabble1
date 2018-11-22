package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface implemented by the Game server object to provide remote services to clients
 */
public interface IRemoteGame extends Remote {
    /**
     * Used by clients to register themselves to the game.
     * @throws RemoteException: problem reaching the server, handle gracefully
     * @throws OutOfMomentException: A player is trying to join when the game is not taking players.
     */
    void registerPlayer(IRemotePlayer player) throws OutOfMomentException, UsernameNotUniqueException, RemoteException;

    /**
     * Used by a client to start the game for everybody with all the players on the waiting room
     * @throws RemoteException: problem reaching the server, handle gracefully
     * @throws InvalidNumberOfPlayersException: A player is trying to start a game with not enough players
     */
    void startGame() throws OutOfMomentException, InvalidNumberOfPlayersException, RemoteException;

    /**
     * Used by the players to indicate to the game their intention to add a character c to cell at
     * position (x,y) and to get points for the resulting horizontal / vertical word formed.
     * RemoteException means that there is a problem reaching the server. The client must handle this gracefully.
     */
    void addCharToCell(String username, Character c, int x, int y, boolean horizontal)
            throws RemoteException, OutOfTurnException, OutOfMomentException, InvalidCellPlacementException;
    /**
     * Used by a player to indicate that they want to pass on their turn.
     * @param username: the username that wants to pass.
     * RemoteException means that there is a problem reaching the server. The client must handle this gracefully.
     */
    void pass(String username) throws RemoteException, InvalidUsernameException, OutOfTurnException, OutOfMomentException;

    /**
     * Used by a player to indicate that he wants to logout from the game.
     * @param username: the username that wants to logout.
     * RemoteException means that there is a problem reaching the server. The client must handle this gracefully.
     */
    void logout(String username) throws RemoteException, InvalidUsernameException;

}
