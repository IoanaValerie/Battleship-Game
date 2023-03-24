package BattleshipGame;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Shot{
    char[][] field;

    char[][] field1;

    int[][] results;

    int cond = 0;

    public Shot(Field field) {
        this.field = field.field;
        this.results = field.results;

        field1 = new char[10][10];
        for (int i = 0; i < field1.length; i++) {
            for (int j = 0; j < field1[i].length; j++) {
                field1[i][j] = '~';
            }
        }
    }

    public String setShot() {
        Scanner scanner = new Scanner(System.in);
        int[] coordinates = null;
        boolean isCoordinatesValid = false;
        System.out.print("> ");
        while (!isCoordinatesValid) {
            coordinates = toDigitCoordinates(scanner.nextLine());
            System.out.println();
            try {
                isCoordinatesValid = validateShot(coordinates);
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
                System.out.println();
                System.out.print("> ");
            }
        }

        if (field[coordinates[0]][coordinates[1]] == 'O' || field[coordinates[0]][coordinates[1]] == 'X') {
            field[coordinates[0]][coordinates[1]] = 'X';
            field1[coordinates[0]][coordinates[1]] = 'X';

            int aux = 0;
            if (verifyCarrier() && verifyBattleship() && verifyCruiser() && verifySubmarine() && verifyDestroyer()) {
                return "You sank the last ship. You won. Congratulations!";
            } else {
                if (verifyCarrier())
                    aux++;
                if (verifySubmarine())
                    aux++;
                if (verifyBattleship())
                    aux++;
                if (verifyDestroyer())
                    aux++;
                if (verifyBattleship())
                    aux++;
                if (cond < aux) {
                    cond = aux;
                    return "You sank a ship!";
                }

            }
            if (cond == aux)
                return "You hit a ship!";

        } else if (field[coordinates[0]][coordinates[1]] == '~' || field[coordinates[0]][coordinates[1]] == 'M') {
            field[coordinates[0]][coordinates[1]] = 'M';
            field1[coordinates[0]][coordinates[1]] = 'M';
            return "You missed!";
        }
        return "";
    }

    public void print() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < field.length; i++) {
            System.out.print((char) (i + 65)); // A, B, C and so on.
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(" " + field[i][j]);
            }
            System.out.println();
        }
    }

    public void print1() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < field1.length; i++) {
            System.out.print((char) (i + 65)); // A, B, C and so on.
            for (int j = 0; j < field1[i].length; j++) {
                System.out.print(" " + field1[i][j]);
            }
            System.out.println();
        }
    }

    public boolean verifyCarrier() {
        for (int i = Math.min(results[0][0], results[0][2]); i <= Math.max(results[0][0], results[0][2]); i++)
            for (int j = Math.min(results[0][1], results[0][3]); j <= Math.max(results[0][1], results[0][3]) ; j++)
                if (field1[i][j] != 'X')
                    return false;
        return true;
    }

    public boolean verifyBattleship() {
        for (int i = Math.min(results[1][0], results[1][2]); i <= Math.max(results[1][0], results[1][2]); i++)
            for (int j = Math.min(results[1][1], results[1][3]); j <= Math.max(results[1][1], results[1][3]) ; j++)
                if (field1[i][j] != 'X')
                    return false;
        return true;
    }

    public boolean verifySubmarine() {
        for (int i = Math.min(results[2][0], results[2][2]); i <= Math.max(results[2][0], results[2][2]); i++)
            for (int j = Math.min(results[2][1], results[2][3]); j <= Math.max(results[2][1], results[2][3]) ; j++)
                if (field1[i][j] != 'X')
                    return false;
        return true;
    }

    public boolean verifyCruiser() {
        for (int i = Math.min(results[3][0], results[3][2]); i <= Math.max(results[3][0], results[3][2]); i++)
            for (int j = Math.min(results[3][1], results[3][3]); j <= Math.max(results[3][1], results[3][3]) ; j++)
                if (field1[i][j] != 'X')
                    return false;
        return true;
    }

    public boolean verifyDestroyer() {
        for (int i = Math.min(results[4][0], results[4][2]); i <= Math.max(results[4][0], results[4][2]); i++)
            for (int j = Math.min(results[4][1], results[4][3]); j <= Math.max(results[4][1], results[4][3]) ; j++)
                if (field1[i][j] != 'X')
                    return false;
        return true;
    }

    private int[] toDigitCoordinates(String coordinates) {
        int[] result = new int[2];
        String[] coordinatesSplit = coordinates.split(" ");
        result[0] = coordinatesSplit[0].charAt(0) - 65;
        result[1] = Integer.parseInt(coordinatesSplit[0].substring(1)) - 1;
        return result;
    }

    private boolean validateShot(int[] coordinates) throws IllegalAccessException {
        boolean isValid = true;

        if (coordinates[0] < 0 || coordinates[0] > 9 || coordinates[1] < 0 || coordinates[1] > 9) {
            throw new IllegalAccessException("Error! You entered the wrong coordinates! Try again:");
        }
        return isValid;
    }
}
