package remote;

public class InvalidNumberOfPlayersException extends Exception {
    public InvalidNumberOfPlayersException (int minPlayers) {
        super(String.format("Games need %d or more players", minPlayers));
    }
}
