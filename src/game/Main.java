package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        final int SIZE = 50;
        Game game = new Game(SIZE);
        Scanner scanner = new Scanner(System.in);
        String eingabe = "";
        while(true){
            System.out.println(game);
            eingabe = scanner.nextLine();
            if(eingabe.equals("end") || eingabe.equals("stop")){
                break;
            }
            game.manageInput(eingabe);

        }
    }
}
