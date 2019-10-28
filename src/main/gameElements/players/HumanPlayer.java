package main.gameElements.players;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends Player {

    private Scanner scanner = new Scanner(System.in);

    public HumanPlayer() {
        super();
    }

    @Override
    public int getPositionOfShip() {
        int position = 1;
        boolean correctPosition = false;
        while (!correctPosition) {
            try {
                System.out.print("Enter position: ");
                position = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You must input a number.");
                scanner.next();
                continue;
            }
            if (position < 1 || position > 100) {
                System.out.println("Move should be between 1-100.");
                continue;
            }
            correctPosition = true;
        }
        return position;
    }

    @Override
    public int getPositionToShoot() {
        int position = 1;
        boolean correctPosition = false;
        while (!correctPosition) {
            try {
                System.out.print("Enter position: ");
                position = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You must input a number.");
                scanner.next();
                continue;
            }
            if (position < 1 || position > 100) {
                System.out.println("Move should be between 1-100.");
                continue;
            }
            correctPosition = true;
        }
        return position;
    }

    @Override
    public int[] getPositionsOfShip(int numberOfMast) {
        int[] positions = new int[2];
        boolean correctPosition = false;
        while (!correctPosition) {
            try {
                System.out.print("Enter starting position: ");
                positions[0] = scanner.nextInt();
                System.out.print("Enter ending position: ");
                positions[1] = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You must input a number.");
                scanner.next();
                continue;
            }
            if (positions[0] < 1 || positions[0] > 100 || positions[1] < 1 || positions[1] > 100) {
                System.out.println("Move should be between 1-100.");
                continue;
            } else if (positions[0] == positions[1]) {
                System.out.println("Positions can't be the same.");
                continue;
            }
            correctPosition = true;
        }
        return positions;
    }
}
