package server;

import remote.ChatServerInterface;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ChatServerDriver {
    public static void main(String[] args) {
        try {
            // The following code is boilerplate code for setting up the server as a remote object on the RMI registry
            // 1. Instantiate a server
//            ChatServerInterface server = new ChatServer();

            // 2. Locate the rmi registry
//            Registry registry = LocateRegistry.getRegistry();

            // 3. Register the remote object on the registry under the "ChatServer" name
//            registry.bind("ChatServer", server);
            Naming.rebind("ChatServer", new ChatServer());
            System.out.println("Chat Server is Ready");

            //The server will continue running as long as there are remote objects exported into
            //the RMI runtime, to remove remote objects from the
            // RMI runtime so that they can no longer accept RMI calls you can use:
            // UnicastRemoteObject.unexportObject(remoteMath, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
