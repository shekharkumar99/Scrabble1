package remote;

public class InvalidCellPlacementException extends Exception {
    public InvalidCellPlacementException (Character c, int x, int y) {
        super(String.format("Placing %c at position (%d, %d) in an invalid move.", c, x, y));
    }
}