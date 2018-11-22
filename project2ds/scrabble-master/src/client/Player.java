package client;

import remote.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Player extends UnicastRemoteObject implements IRemotePlayer {

    /**
     * The player's username
     */
    private String username;

    /**
     * The reference to the remote game server
     */
    private IRemoteGame game;

    // Constructors
    // ----------------------------------------
    /**
     * Instantiates a new player and registers on the remote game server
     * @param username: must be unique
     * @param game: Game instance (remote object)
     * @throws RemoteException
     */
    public Player(String username, IRemoteGame game) throws RemoteException, OutOfMomentException, UsernameNotUniqueException {
        this.username = username;
        this.game = game;
        game.registerPlayer(this);
    }

    // Remote Interface
    // ----------------------------------------
    @Override
    public String getUsername() {
        return this.username;
    }


    @Override
    public void updateGameState(String jsonGameState) {
        System.out.println(jsonGameState);
    }

    // Public Interface
    // ----------------------------------------
    /**
     * Starts the client's GUI on the command line
     * TODO delete this dummy code
     */
    public void startGui() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a character to send to the server: ");
            String message;
            message = scanner.nextLine();
            try {
                game.addCharToCell(this.username, message.charAt(0), 1,1, true);
            } catch (RemoteException e) {
                System.out.println("Woops there is a problem communicating with the server;");
                e.printStackTrace();
            } catch (InvalidCellPlacementException e) {
                System.out.println("You made an invalid move");
                e.printStackTrace();
            } catch (OutOfTurnException e) {
                System.out.println("It is not your turn");
                e.printStackTrace();
            } catch (OutOfMomentException e) {
                System.out.println("We are not on PLAYING moment");
                e.printStackTrace();
            }
        }
    }
}
