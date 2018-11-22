package remote;

public class UsernameNotUniqueException extends Exception {
    public UsernameNotUniqueException (String username) {
        super(String.format("%s is already taken. Please try a different username.", username));
    }
}

