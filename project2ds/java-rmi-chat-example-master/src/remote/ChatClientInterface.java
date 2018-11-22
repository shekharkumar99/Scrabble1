package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClientInterface extends Remote {

    /**
     * Used by server to append a message to a chat client.
     * @param message
     * @throws RemoteException
     */
    void appendMessage(String message) throws RemoteException;

}
