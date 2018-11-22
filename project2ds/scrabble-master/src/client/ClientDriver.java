package client;

import remote.IRemoteGame;
import remote.OutOfMomentException;
import remote.UsernameNotUniqueException;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClientDriver {

    public static void main(String args[]) {

        // 1. Get username
        System.out.println("Enter your username (must be unique): ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();

        try {
            // 2.Find remote game server using rmi registry
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            IRemoteGame game = (IRemoteGame)registry.lookup("game");

            // 3. Instantiate this client
            Player player = new Player(username, game);
            System.out.println("Joined the Waiting Room");

            // TODO: this is dummy code
            player.startGui();
        }
        catch (RemoteException e) {
            // Do some nice Exception handling
            e.printStackTrace();

        } catch (NotBoundException e) {
            // Do some nice Exception handling
            e.printStackTrace();
        } catch (UsernameNotUniqueException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (OutOfMomentException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
