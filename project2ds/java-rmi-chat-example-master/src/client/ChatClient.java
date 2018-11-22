package client;

import remote.ChatClientInterface;
import remote.ChatServerInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ChatClient extends UnicastRemoteObject implements ChatClientInterface {

    /**
     * The name of the person chatting
     */
    private String name;

    /**
     * The instance of the chat server to which the client is connected to.
     */
    private ChatServerInterface chatServer;

    /**
     * Constructor: instantiates a new chat client connected to a chatServer instance.
     * The connection happens by registering the chatClient with the ChatServer.
     * @throws RemoteException
     */
    protected ChatClient(String name, ChatServerInterface chatServer) throws RemoteException {
        this.name = name;
        this.chatServer = chatServer;
        chatServer.registerChatClient(this);
    }

    @Override
    public void appendMessage(String message) throws RemoteException {
        System.out.println(message);
    }


    /**
     * Starts the client's GUI on the command line
     */
    public void startGui() {
        Scanner scanner = new Scanner(System.in);
        String message;
        while (true) {
            message = scanner.nextLine();
            try {
                chatServer.broadcastMessage(this.name + ": " + message );
            } catch (RemoteException e) {
                System.out.println("Woops there is a problem communicating with the server;");
                e.printStackTrace();
            }
        }
    }
}
