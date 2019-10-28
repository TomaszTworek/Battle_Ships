package main.gameElements.ships;

public class Ship {
    private String sign = "X";
    private ShipType type;

    public Ship(ShipType type) {
        this.type = type;
    }

    public ShipType getType() {
        return type;
    }

    @Override
    public String toString() {
        return sign;
    }
}
