package com.example.TreasureMap.Items;

public class Mountain implements MapItem {
    private final int[] position;

    public Mountain(int[] position) {
        this.position = position;
    }

    @Override
    public int[] getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mountain mountain = (Mountain) o;
        return java.util.Objects.deepEquals(position, mountain.position);
    }

    @Override
    public int hashCode() {
        return java.util.Arrays.hashCode(position);
    }

    @Override
    public String toString() {
        return "Items.Mountain{" +
                "position=" + java.util.Arrays.toString(position) +
                '}';
    }
}
