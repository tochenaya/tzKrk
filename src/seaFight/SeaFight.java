package seaFight;

import java.util.Scanner;

public class SeaFight {


    private static int size = 10;
    private static char[][] field = new char[size][size];
    private static char[][] fieldFind = new char[size][size];
    private static int quantityOne;
    private static int quantityTwo;
    private static int quantityThree;
    private static int quantityFour;

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < size; i++) {
            String line = scanner.nextLine();
            field[i] = line.toCharArray();
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j] == 'X' && fieldFind[i][j] != 'X') {
                    boolean toRight = false;
                    boolean toDown = false;
                    if (j + 1 < size && field[i][j + 1] == 'X') {
                        toRight = true;
                    }
                    if (i + 1 < size && field[i + 1][j] == 'X') {
                        toDown = true;
                    }
                    if (toRight && toDown) {
                        System.out.println("NO");
                        return;
                    } else if (toRight) {
                        if (!inspection(i, j, 0, 1)) {
                            System.out.println("NO");
                            return;
                        }
                    } else { // ?
                        if (!inspection(i, j, 1, 0)) {
                            System.out.println("NO");
                            return;
                        }
                    }
                }
            }
        }

        if(quantityOne != 4 || quantityTwo != 3 || quantityThree != 2 || quantityFour != 1) {
            System.out.println("NO");
            return;
        }

        System.out.println("YES");

    }

    private static boolean inspection(int i, int j, int incI, int incJ) {
        int shipSize = 1;
        if (i - 1 >= 0 && j - 1 >= 0 && field[i - 1][j - 1] == 'X')
            return false;
        if (incI == 1) {
            if (i - 1 >= 0 && j + 1 < size && field[i - 1][j + 1] == 'X')
                return false;
        } else if (incJ == 1) {
            if (i + 1 < size && j - 1 >= 0 && field[i + 1][j - 1] == 'X')
                return false;
        }

        fieldFind[i][j] = 'X';

        while (true) {

            if (incI == 1) {
                if ((j - 1 >= 0 && field[i][j - 1] == 'X') || (j + 1 < size && field[i][j + 1] == 'X'))
                    return false;
            } else if (incJ == 1) {
                if ((i - 1 >= 0 && field[i - 1][j] == 'X') || (i + 1 < size && field[i + 1][j] == 'X'))
                    return false;
            }

            i += incI;
            j += incJ;

            if (i < size && j < size && field[i][j] == 'X') {
                fieldFind[i][j] = 'X';
                shipSize+=1;
            } else {
                break;
            }
        }

        if(shipSize > 4)
            return false;

        i -= incI;
        j -= incJ;

        if (i - 1 >= 0 && j + 1 < size && field[i - 1][j + 1] == 'X')
            return false;
        if (incI == 1) {
            if (i - 1 >= 0 && j - 1 >= 0 && field[i - 1][j - 1] == 'X')
                return false;
        } else if (incJ == 1) {
            if (i + 1 < size && j + 1 < size && field[i + 1][j + 1] == 'X')
                return false;
        }

        switch (shipSize){
            case 1: quantityOne+=1;
                break;
            case 2: quantityTwo+=1;
                break;
            case 3: quantityThree+=1;
                break;
            case 4: quantityFour+=1;
                break;
        }

        if(quantityOne > 4) return false;
        if(quantityTwo > 3) return false;
        if(quantityThree > 2) return false;
        if(quantityFour > 1) return false;

        return true;

    }
}
