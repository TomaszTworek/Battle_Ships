package main.gameElements.ships;

public enum ShipType {
    ONE_MAST(1, 4), TWO_MAST(2, 3), THREE_MAST(3, 2), FOUR_MAST(4, 1);
    private int length;
    private int amount;

    ShipType(int length, int amount) {
        this.length = length;
        this.amount = amount;
    }

    public int getLength() {
        return length;
    }

    public int getAmount() {
        return amount;
    }
}
