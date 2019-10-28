package main.gameElements;

import main.gameElements.players.Player;
import main.gameElements.ships.Ship;

public class Board {

    private Ship[][] board;

    public Ship[][] getBoard() {
        return board;
    }

    public Board(int sizeX, int sizeY) {
        board = new Ship[sizeX][sizeY];
    }

    public int[] getXYCoordinateOfPosition(int position) {
        int[] coordinate = new int[2];
        int counter = 1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (counter == position) {
                    coordinate[0] = i;
                    coordinate[1] = j;
                }
                counter++;
            }
        }
        return coordinate;
    }

    public void put(int position, Ship ship) {
        int counter = 1;
        mainLoop:
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (position == counter) {
                    board[i][j] = ship;
                    break mainLoop;
                }
                counter++;
            }
        }
    }

    public Ship get(int position) throws Exception {
        int counter = 1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (counter == position) {
                    return board[i][j];
                }
                counter++;
            }
        }
        throw new Exception("Field doesn't exist.");
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int counter = 1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                result.append("[");
                if (counter < 10 && board[i][j] == null) {
                    result.append(" ");
                }

                if (board[i][j] == null) {
                    result.append(counter);
                } else {
                    result.append(board[i][j]);
                }

                result.append("]");

                counter++;
            }
            result.append("\n");
        }
        return result.toString();
    }
}
