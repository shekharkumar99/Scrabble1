package server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Board {

    public final static int BOARD_SIZE = 20;

    // Attributes
    // ----------------------------------------
    // TODO define how will the cells be modelled
    private Character[][] cells;

    // Constructor
    // ----------------------------------------
    public Board() {
        this.cells = new Character[BOARD_SIZE][BOARD_SIZE];
    }

    // Instance methods
    // ----------------------------------------
    // TODO addCharToCell(x,y,char, orientationBool) uses board rules to check if the char can be added at the given position
    // If invalid, returns an exception for each possible error
    // If valid returns the string just being formed by adding the word according to the orientation given as param

    @Override
    public String toString() {
        String rep = "";
        for (int x = 0; x<BOARD_SIZE; ++x){
            for (int y = 0; y<BOARD_SIZE; ++y) {
                rep += "|" + this.cells[x][y];
            }
            rep += "|\n";
        }
        return rep;
    }
}
