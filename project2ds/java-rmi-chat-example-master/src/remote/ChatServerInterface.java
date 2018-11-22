package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ChatServerInterface extends Remote {
    /**
     * Used by clients to register themselves to the chat room.
     * @param chatClient
     * @throws RemoteException
     */
    void registerChatClient(ChatClientInterface chatClient) throws RemoteException;

    /**
     * Used by clients to send a message to everybody in the chat room.
     * @param message
     * @throws RemoteException
     */
    void broadcastMessage(String message) throws RemoteException;

    String sayHi() throws RemoteException;
}
