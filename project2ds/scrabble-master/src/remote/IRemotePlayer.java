package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemotePlayer extends Remote {

    /**
     * Used by the server to make the clients render the new game state.
     * @param jsonGameState: JSON representation of the game state.
     * @throws RemoteException: if thrown, that means that the client has disconnected ungracefully and the server
     * should en the game for other player gracefully.
     */
    void updateGameState(String jsonGameState) throws RemoteException;

    /**
     * Used by the server to get the username of a remote player.
     * @throws RemoteException: if thrown, that means that the client has disconnected ungracefully and the server
     * should en the game for other player gracefully.
     */
    String getUsername() throws RemoteException;

}
