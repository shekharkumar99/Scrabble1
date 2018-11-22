package remote;

public class OutOfTurnException extends Exception {
    public OutOfTurnException (String username) {
        super(String.format("It is %s turn.", username));
    }
}
