package main.gameElements;

import main.gameElements.ships.Ship;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Judge {
    private List<Board> boards;


    public Judge(List<Board> boards) {
        this.boards = boards;
    }

    public List<Integer> getInaccessibleMoves() {
        return inaccessibleMoves;
    }

    public void setInaccessibleMoves(List<Integer> inaccessibleMoves) {
        this.inaccessibleMoves = inaccessibleMoves;
    }

    private List<Integer> inaccessibleMoves = new LinkedList<>();


    /**
     * This is method for one mast ship
     * Check if player can place ship in specific position
     *
     * @param position     it's a given number from user
     * @param currentBoard
     * @return true if position is available and it's empty around this position
     */
    public boolean isValidMove(int position, Board currentBoard) throws Exception {
        return isEmptyAroundPosition(position, currentBoard) && isEmptyPosition(position, currentBoard);
    }

    /**
     * Method isEmptyPosition is used in placing and shooting methods
     *
     * @param position     it's a given number from user
     * @param currentBoard
     * @return true if position is empty
     */
    private boolean isEmptyPosition(int position, Board currentBoard) throws Exception {
        return currentBoard.get(position) == null;
    }

    public boolean isEmptyAroundPosition(int position, Board currentBoard) {
        int[] pointCoordinate = currentBoard.getXYCoordinateOfPosition(position);
        int x = pointCoordinate[0];
        int y = pointCoordinate[1];

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i == x && j == y) {
                    continue;
                }
                if (j >= 0 && j < 10 && i >= 0 && i < 10) {
                    Ship[][] boards = currentBoard.getBoard();
                    if (boards[i][j] != null) {
                        addPositionToInaccessibleMoveList(i, j);
                        System.out.println("There is a ship around position.");
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public boolean isValidMove(int position1, int position2, Board currentBoard, Ship ship) throws Exception {
        return isEmptyAroundPosition(position1, position2, currentBoard, ship) &&
                hasCorrectShape(position1, position2, ship);
    }

    private boolean hasCorrectShape(int position1, int position2, Ship ship) {
        int modulo1 = position1 % 10;
        int modulo2 = position2 % 10;
        if (modulo1 == 0) {
            modulo1 = 10;
        }
        if (modulo2 == 0) {
            modulo2 = 10;
        }

        if (Math.abs(position1 - position2) == (ship.getType().getLength() - 1) ||
                Math.abs(position1 - position2) == 10 * (ship.getType().getLength() - 1)) {
            for (int i = 0; i < ship.getType().getLength(); i++) {
                if (modulo1 == 10 - i) {
                    if (modulo2 == ship.getType().getLength() - i - 1) {
                        System.out.println("Wrong shape.");
                        return false;
                    }
                }
            }
            for (int i = 0; i < ship.getType().getLength(); i++) {
                if (modulo1 == 1 + i) {
                    if (modulo2 == 10 - ship.getType().getLength() + i + 1) {
                        System.out.println("Wrong shape.");
                        return false;
                    }
                }
            }
            return true;
        } else {
            System.out.println("Wrong shape.");
            return false;
        }
    }


    public boolean isVertical(int position1, int position2) {
        return Math.abs(position1 - position2) >= 10;
    }

    private boolean isEmptyAroundPosition(int position1, int position2, Board currentBoard, Ship ship) {
        int min = Math.min(position1, position2);

        int[] pointCoordinate = currentBoard.getXYCoordinateOfPosition(min);

        int x = pointCoordinate[0];
        int y = pointCoordinate[1];


        if (isVertical(position1, position2)) {
            for (int i = x - 1; i <= x + ship.getType().getLength(); i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (j >= 0 && j < 10 && i >= 0 && i < 10) {
                        Ship[][] boards = currentBoard.getBoard();
                        if (boards[i][j] != null) {
                            addPositionToInaccessibleMoveList(i, j);
                            System.out.println("There is a ship around position.");
                            return false;
                        }
                    }
                }
            }
        } else {
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + ship.getType().getLength(); j++) {
                    if (j >= 0 && j < 10 && i >= 0 && i < 10) {
                        Ship[][] boards = currentBoard.getBoard();
                        if (boards[i][j] != null) {
                            addPositionToInaccessibleMoveList(i, j);
                            System.out.println("There is a ship around position.");
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private void addPositionToInaccessibleMoveList(int i, int j) {
        int position = 1;
        mainloop:for (int k = 0; k < 10; k++) {
            for (int l = 0; l < 10; l++) {
                if (k == i && l == j) {
                    inaccessibleMoves.add(position);
                    break mainloop;
                }
                position++;
            }
        }
    }


        public boolean isGameOver () {
            if (playerOneWin()) {
                System.out.println("Player one win.");
                return true;
            } else if (playerTwoWin()) {
                System.out.println("Player two win.");
                return true;
            }
            return false;
        }

        private boolean playerTwoWin () {
            for (int i = 0; i < boards.get(0).getBoard().length; i++) {
                for (int j = 0; j < boards.get(0).getBoard()[i].length; j++) {
                    if (boards.get(0).getBoard()[i][j] != null) {
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean playerOneWin () {
            for (int i = 0; i < boards.get(1).getBoard().length; i++) {
                for (int j = 0; j < boards.get(1).getBoard()[i].length; j++) {
                    if (boards.get(1).getBoard()[i][j] != null) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

