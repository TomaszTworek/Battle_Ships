package main.gameElements.players;

import main.gameElements.Board;
import main.gameElements.Judge;
import main.gameElements.ships.Ship;

import java.util.*;

public class AIPlayer extends Player {
    @Override
    public int getPositionOfShip() {
        return 0;
    }

    @Override
    public int[] getPositionsOfShip(int numberOfMast) throws Exception {
        return new int[0];
    }

    @Override
    public int getPositionToShoot() {
        return 0;
    }
}