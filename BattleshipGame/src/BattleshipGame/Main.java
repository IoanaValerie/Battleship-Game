package BattleshipGame;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        //Player 1
        System.out.println("Player 1, place your ships on the game field");
        System.out.println();
        Field field1 = new Field();
        field1.print();

        for (Ship ship : Ship.values()) {
            field1.setShip(ship);
            field1.print();
        }

        promptEnterKey();

        //Player 2
        System.out.println("Player 2, place your ships on the game field");
        System.out.println();
        Field field2 = new Field();
        field2.print();

        for (Ship ship : Ship.values()) {
            field2.setShip(ship);
            field2.print();
        }

        promptEnterKey();

        Shot shot1 = new Shot(field2);
        Shot shot2 = new Shot(field1);
        while(true) {
            shot1.print1();
            System.out.println("---------------------");
            shot2.print();
            System.out.println();
            System.out.println("Player 1, it's your turn:");
            System.out.println();
            String string1 = shot1.setShot();
            if (string1 == "You sank the last ship. You won. Congratulations!") {
                System.out.println(string1);
                break;
            } else {
                System.out.println(string1);
                promptEnterKey();
            }

            shot2.print1();
            System.out.println("---------------------");
            shot1.print();
            System.out.println();
            System.out.println("Player 2, it's your turn:");
            System.out.println();
            String string2 = shot2.setShot();
            if (string2 == "You sank the last ship. You won. Congratulations!") {
                System.out.println(string2);
                break;
            } else {
                System.out.println(string2);
                promptEnterKey();
            }
        }
    }

    public static void promptEnterKey() {
        System.out.println("Press Enter and pass the move to another player");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
