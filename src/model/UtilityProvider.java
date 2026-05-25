package model;

public abstract class UtilityProvider extends Cell{
    private int capacity;

    public UtilityProvider(int capacity, int x, int y, char symbol) {
        super(x,y,symbol);
        this.capacity=capacity;
    }

    public int getCapacity() {return capacity;}
}
