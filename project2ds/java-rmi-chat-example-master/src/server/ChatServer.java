package server;

import remote.ChatClientInterface;
import remote.ChatServerInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ChatServer extends UnicastRemoteObject implements ChatServerInterface {

    /**
     * Attributes
     */
    private ArrayList<ChatClientInterface> chatClients;

    /**
     * Constructor: Builds an instance of a chat server
     * @throws RemoteException
     */
    public ChatServer() throws RemoteException {
        super();
        this.chatClients = new ArrayList<>();
    }

    @Override
    /**
     * Receives a client as argument and adds it to the set of clients
     */
    public synchronized void registerChatClient(ChatClientInterface chatClient) throws RemoteException {
        chatClients.add(chatClient);
    }

    /** Receives a message from a client and broadcasts it to all clients in the current set of clients
     * @param message
     * @throws RemoteException
     */
    @Override
    public synchronized void broadcastMessage(String message) throws RemoteException {
        for (ChatClientInterface chatClient: chatClients) {
            chatClient.appendMessage(message);
        }
    }

    @Override
    public String sayHi() throws RemoteException {
        return "Hi From Server";
    }
}
