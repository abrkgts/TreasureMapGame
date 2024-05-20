package com.example.TreasureMap.Items;

public class Treasure implements MapItem {
    private final int[] position;
    private int quantity;

    public Treasure(int[] position, int quantity) {
        this.position = position;
        this.quantity = quantity;
    }

    @Override
    public int[] getPosition() {
        return position;
    }

    public int getQuantity() {
        return quantity;
    }

    public void removeOne() {
        this.quantity--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Treasure treasure = (Treasure) o;
        return quantity == treasure.quantity && java.util.Objects.deepEquals(position, treasure.position);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(java.util.Arrays.hashCode(position), quantity);
    }

    @Override
    public String toString() {
        return "Items.Treasure{" +
                "position=" + java.util.Arrays.toString(position) +
                ", quantity=" + quantity +
                '}';
    }
}
