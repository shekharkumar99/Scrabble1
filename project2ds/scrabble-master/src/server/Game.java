package server;

import org.json.simple.JSONObject;
import remote.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class Game extends UnicastRemoteObject implements IRemoteGame {
    public final static int MIN_PLAYERS = 2;

    // Attributes
    // ----------------------------------------
    /**
     * Holds a list of all references to remote clients playing the game
     */
    private ArrayList<IRemotePlayer> players;

    /**
     * Holds an Enum instance that defines the game moment
     */
    private Moment moment;

    /**
     * Holds the username of the player with the current turn
     */
    private String playerTurn;

    /**
     * Holds the player scores indexed by username
     */
    private HashMap<String, Integer> scores;

    /**
     * Holds the board
     */
    private Board board;


    // Constructors
    // ----------------------------------------
    /**
     * Starts a new game.
     * TODO handle the remote exception instead of throwing it
     */
    public Game() throws RemoteException {
        super();
        this.players = new ArrayList<>();
        this.scores = new HashMap<>();
        this.board = new Board();
        this.moment = Moment.WAITING_ROOM;
    }

    // Remote Interface
    // ----------------------------------------
    /**
     * Called by a player to register to the game
     * TODO handle the remote exception  for instead of throwing it.  If a client disconnected, remove it from list
     * and continue on the WAITING ROOM
     */
    @Override
    public synchronized void registerPlayer(IRemotePlayer player) throws OutOfMomentException, UsernameNotUniqueException, RemoteException {

        String username = player.getUsername();
        if (this.moment != Moment.WAITING_ROOM){
            throw new OutOfMomentException("The game is currently not taking new players, please wait.");

        } else if (usernameTaken(username)) {
            throw new UsernameNotUniqueException(username);

        } else {
            this.players.add(player);
            this.scores.put(username, 0); // Hack to make usernames available on the waiting room through the score keys
            this.broadcastGameStateToAllPlayers();
            System.out.println(String.format("%s joined the Waiting Room.", username));
        }
    }

    /**
     * Used by any player inside the waiting room to start the game with all the current players on the waiting room.
     * @throws InvalidNumberOfPlayersException: when num players < MIN_PLAYERS
     * @throws RemoteException: when there is a problem reaching any remote player.
     * TODO Handle RemoteException. What should happen if we cannot communicate with a client at this point?
     */
    @Override
    public synchronized void startGame() throws OutOfMomentException, InvalidNumberOfPlayersException, RemoteException {
        if (this.moment != Moment.WAITING_ROOM) {
            throw new OutOfMomentException("The game cannot be started at this moment.");

        } else if (this.players.size() < MIN_PLAYERS) {
            throw new InvalidNumberOfPlayersException(MIN_PLAYERS);

        } else {
            // 1. TODO ping each of the players to make sure they are alive, remove anyone that is not alive. remove from this.players and this.scores

            // 2. Change Moment and assign first turn
            this.moment = Moment.PLAYING;
            this.playerTurn = this.players.get(0).getUsername();

            // 3. Broadcast to everyone that the game has started
            this.broadcastGameStateToAllPlayers();
        }
    }

    // TODO addCharToCell: checks moment logic, checks turn logic, calls addCharToCell on board
    // TODO Handle remote exception instead of throwing it. If a client is unreachable at this point, end the game.
    @Override
    public synchronized void addCharToCell(String username, Character c, int x, int y, boolean horizontal) throws RemoteException, OutOfTurnException, OutOfMomentException, InvalidCellPlacementException {
        // The following is dummy code
        String message = String.format("The user %s sent character %c for position (%d, %d) on horizontal %s", username, c, x, y, horizontal);
        System.out.println(message);
        this.broadcastGameStateToAllPlayers();
    }

    // TODO pass logic
    @Override
    public synchronized void pass(String username) throws RemoteException, InvalidUsernameException, OutOfTurnException, OutOfMomentException {

    }

    // TODO logout
    @Override
    public synchronized void logout(String username) throws RemoteException, InvalidUsernameException {

    }

    // Public  Interface
    // ----------------------------------------
    /**
     * Returns a strigified JSON representation of the current state of the game.
     * TODO define the rest of this JSON structure
     */
    public String toJSON() {
        JSONObject json = new JSONObject();
        json.put("moment", this.moment.toString());
        json.put("scores", this.scores);
        json.put("playerTurn", this.playerTurn);
        json.put("board", this.board.toString());
        return json.toString();
    }

    // Private Interface
    // ----------------------------------------

    /**
     * Returns true if a username is taken, false otherwise
     * @param username: username to check
     */
    private boolean usernameTaken(String username) throws RemoteException {

        for (IRemotePlayer player: this.players) {
            if (player.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Broadcasts the current game state JSON to all players.
     */
    private void broadcastGameStateToAllPlayers() throws  RemoteException {
        for (IRemotePlayer player: this.players) {
            player.updateGameState(this.toJSON());
        }
    }


}
