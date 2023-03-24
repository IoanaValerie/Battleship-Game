package BattleshipGame;

import java.util.Scanner;

public class Field {
    char[][] field;
    int[][] results;
    int k = 0;

    public Field() {
        field = new char[10][10];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = '~';
            }
        }

        results = new int[5][4];
    }

    public void setShip(Ship ship) {

        Scanner scanner = new Scanner(System.in);
        int[] coordinates = null;
        boolean isCoordinatesValid = false;
        System.out.printf("Enter the coordinates of the %s (%d cells):\n",
                ship.getPrintName(), ship.getLength());
        System.out.println();
        System.out.print("> ");
        while (!isCoordinatesValid) {
            coordinates = toDigitCoordinates(scanner.nextLine());
            System.out.println();
            try {
                isCoordinatesValid = validateCoordinates(ship, coordinates);
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage() + " Try again:");
                System.out.println();
                System.out.print("> ");
            }
        }

        for (int j = 0; j < 4; j++)
            results[k][j] = coordinates[j];
        k++;

        for (int i = Math.min(coordinates[0], coordinates[2]); i <= Math.max(coordinates[0], coordinates[2]); i++) {
            for (int j = Math.min(coordinates[1], coordinates[3]); j <= Math.max(coordinates[1], coordinates[3]) ; j++) {
                field[i][j] = 'O';
            }
        }
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
        System.out.println();
    }

    private int[] toDigitCoordinates(String coordinates) {
        int[] result = new int[4];
        String[] coordinatesSplit = coordinates.split(" ");
        result[0] = coordinatesSplit[0].charAt(0) - 65;
        result[1] = Integer.parseInt(coordinatesSplit[0].substring(1)) - 1;
        result[2] = coordinatesSplit[1].charAt(0) - 65;
        result[3] = Integer.parseInt(coordinatesSplit[1].substring(1)) - 1;
        return result;
    }

    private boolean validateCoordinates(Ship ship, int[] coordinates) throws IllegalAccessException {
        boolean isValid = true;

        if ((coordinates[0] == coordinates[2]) == (coordinates[1] == coordinates[3])) {
            throw new IllegalAccessException("Error! Wrong ship location!");
        }
        if ((Math.abs(coordinates[0] - coordinates[2]) + 1 != ship.getLength() && Math.abs(coordinates[1] - coordinates[3]) + 1 != ship.getLength())) {
            throw new IllegalAccessException("Error! Wrong length of the " + ship.getPrintName() + "!");
        }
        if (!checkNeighbors(coordinates)) {
            throw new IllegalAccessException("Error! You placed it too close to another one.");
        }

        return isValid;
    }

    private boolean checkNeighbors(int[] coordinates) {
        int fromCol = Math.min(coordinates[1], coordinates[3]);
        int toCol = Math.max(coordinates[1], coordinates[3]);
        int fromRow = Math.min(coordinates[0], coordinates[2]);
        int toRow = Math.max(coordinates[0], coordinates[2]);

        if (coordinates[0] == coordinates[2]) {
            for (int col = fromCol; col <= toCol; col++) {
                if (fromRow > 0)
                    if (field[fromRow - 1][col] == 'O')
                        return false;
                if (fromRow < 9)
                    if (field[fromRow + 1][col] == 'O')
                        return false;
                if (field[fromRow][col] == 'O')
                    return false;
            }
            if (fromCol > 0 && fromRow > 0 && fromRow < 9)
                if (field[fromRow - 1][fromCol - 1] == 'O' || field[fromRow][fromCol - 1] == 'O' || field[fromRow + 1][fromCol - 1] == 'O')
                    return false;
            if (toCol < 9 && fromRow > 0 && fromRow < 9)
                if (field[fromRow - 1][toCol + 1] == 'O' || field[fromRow][toCol + 1] == 'O' || field[fromRow + 1][toCol + 1] == 'O')
                    return false;
        } else {
            for (int row = fromRow; row <= toRow; row++) {
                if (fromCol > 0)
                    if (field[row][fromCol - 1] == 'O')
                        return false;
                if (fromCol < 9)
                    if (field[row][fromCol + 1] == 'O')
                        return false;
                if (field[row][fromCol] == 'O')
                    return false;
            }
            if (fromRow > 0 && fromCol > 0 && toCol < 9)
                if (field[fromRow - 1][fromCol - 1] == 'O' || field[fromRow - 1][fromCol] == 'O' || field[fromRow - 1][fromCol + 1] == 'O')
                    return false;
            if (toRow < 9 && fromCol > 0 && fromCol < 9)
                if (field[toRow + 1][fromCol - 1] == 'O' || field[toRow + 1][fromCol] == 'O' || field[toRow + 1][fromCol + 1] == 'O')
                    return false;
        }

        return true;
    }
}
