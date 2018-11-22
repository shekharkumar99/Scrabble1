package remote;

public class InvalidUsernameException extends Exception {
    public InvalidUsernameException (String username) {
        super(String.format("The username '%s' is not valid for this game", username));
    }
}
