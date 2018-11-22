package client;

import remote.ChatServerInterface;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ChatClientDriver {

    public static void main(String args[]) {

        // 1. Get ip and client name from command line args
        String name = args[0];

        try {
            // 2.Find remote chat server using rmi registry
            Registry registry = LocateRegistry.getRegistry("localhost");
            ChatServerInterface chatServer = (ChatServerInterface)registry.lookup("ChatServer");

            // 3. Instantiate a ChatClient Instance
            System.out.println(chatServer.sayHi());
            ChatClient chatClient = new ChatClient(name, chatServer);

            // 4. Start the GUI
            chatClient.startGui();
        }
        catch (RemoteException e) {
            // Do some nice Exception handling
            e.printStackTrace();

        } catch (NotBoundException e) {
            // Do some nice Exception handling
            e.printStackTrace();
        }

    }
}
