package main.gameElements.players;


import main.gameElements.ships.Ship;
import main.gameElements.ships.ShipType;

/**
 * Class Player is a user or computer who has Ships in your pocket
 */

public abstract class Player {

    private Ship[] ships = new Ship[4];

    Player() {
        ships[0] = new Ship(ShipType.ONE_MAST);
        ships[1] = new Ship(ShipType.TWO_MAST);
        ships[2] = new Ship(ShipType.THREE_MAST);
        ships[3] = new Ship(ShipType.FOUR_MAST);
    }

    public Ship getShip(int mast) {
        return ships[mast - 1];
    }

    /**
     * @return position for ONE MAST, also used in shoot method
     */
    public abstract int getPositionOfShip();

    /**
     * @return two extreme positions of TWO,THREE OR FOUR MAST
     */
    public abstract int[] getPositionsOfShip(int numberOfMast) throws Exception;


    public abstract int getPositionToShoot();
}
