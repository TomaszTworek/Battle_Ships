package main.gameElements;

import main.gameElements.players.AIPlayer;
import main.gameElements.players.HumanPlayer;
import main.gameElements.players.Player;
import main.gameElements.ships.Ship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeaBattle {

    private Board boardOne = new Board(10, 10);
    private Board boardTwo = new Board(10, 10);
    private List<Board> boards = new ArrayList<>();

    private Judge judge = new Judge(boards);

    private Player playerOne = new HumanPlayer();
    private Player playerTwo = new HumanPlayer();
    private List<Player> players = new ArrayList<>();

    private void initializeBoardsAndPlayers() {
        players.add(playerOne);
        players.add(playerTwo);
        boards.add(boardOne);
        boards.add(boardTwo);
    }

    public void start() throws Exception {
        int j = 0;
        initializeBoardsAndPlayers();
        System.out.println("Welcome in game!");
        for (int i = 0; i < 2; i++) {
            Player currentPlayer = players.get(i);
            Board currentBoard = boards.get(i);
            judge.getInaccessibleMoves().clear();
            System.out.println(currentBoard);
            System.out.println("Player " + (i + 1) + " place ships: ");
            placeShips(currentPlayer, currentBoard);
        }

        while (!judge.isGameOver()) {
            Player currentPlayer = players.get(j % 2);
            Board currentBoard = boards.get(boards.size() - (j % 2) - 1);
            System.out.println("Player " + ((j % 2) + 1) + " shoot:");
            shoot(currentPlayer, currentBoard);
            j++;
        }


    }

    private void shoot(Player currentPlayer, Board currentBoard) throws Exception {
        int playerShoot = currentPlayer.getPositionToShoot();
        if (currentBoard.get(playerShoot) != null) {
            currentBoard.put(playerShoot, null);
            if (judge.isEmptyAroundPosition(playerShoot, currentBoard)) {
                System.out.println("Shoot and sink.");
            } else {
                System.out.println("Shoot.");
            }
        } else {
            System.out.println("Miss...");
        }
    }


    private void placeShips(Player currentPlayer, Board currentBoard) throws Exception {
        System.out.println("Place 1 mast: ");
        for (int i = 0; i < 4; i++) {
            placeOneMast(currentPlayer, currentBoard);
        }
        System.out.println(currentBoard);
        for (int i = 2; i < 5; i++) {
            Ship ship = currentPlayer.getShip(i);
            System.out.println("Place " + i + " mast: ");
            for (int j = 0; j < ship.getType().getAmount(); j++) {
                placeMoreMast(currentPlayer, currentBoard, i);
                System.out.println(currentBoard);
            }

        }

    }

    private void placeOneMast(Player currentPlayer, Board currentBoard) throws Exception {
        int position = 0;
        boolean correctPositionOfOneMast = false;
        while (!correctPositionOfOneMast) {
            position = currentPlayer.getPositionOfShip();
            if (judge.isValidMove(position, currentBoard)) {
                correctPositionOfOneMast = true;
            }
        }
        currentBoard.put(position, currentPlayer.getShip(1));
    }


    private void placeMoreMast(Player currentPlayer, Board currentBoard, int numberOfMast) throws Exception {
        Ship ship = currentPlayer.getShip(numberOfMast);
        int[] positions = new int[2];
        int position1 = 0;
        int position2 = 0;
        boolean correctPositionOfMoreMast = false;
        for (int j = 0; j < ship.getType().getAmount(); j++) {
            while (!correctPositionOfMoreMast) {
                positions = currentPlayer.getPositionsOfShip(numberOfMast);
                position1 = positions[0];
                position2 = positions[1];
                if (judge.isValidMove(position1, position2, currentBoard, ship)) {
                    correctPositionOfMoreMast = true;
                }
            }
            Arrays.sort(positions);
            int field;
            if (judge.isVertical(position1, position2)) {
                field = 10;
            } else {
                field = 1;
            }
            switch (numberOfMast) {
                case 2:
                    currentBoard.put(positions[0], ship);
                    currentBoard.put(positions[1], ship);
                    break;
                case 3:
                    currentBoard.put(positions[0], ship);
                    currentBoard.put(positions[0] + field, ship);
                    currentBoard.put(positions[1], ship);
                    break;
                case 4:
                    currentBoard.put(positions[0], ship);
                    currentBoard.put(positions[0] + field, ship);
                    currentBoard.put(positions[0] + 2 * field, ship);
                    currentBoard.put(positions[1], ship);
                    break;
            }
        }
    }



}
