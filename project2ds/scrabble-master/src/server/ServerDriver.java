package server;

import java.rmi.Naming;

public class ServerDriver {
    public static  void main(String[] args) {

        // TODO do a smarter exception handling
        try {
            Game game = new Game();
            Naming.rebind("game", game);
            System.out.println("Game Server is Ready");

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
