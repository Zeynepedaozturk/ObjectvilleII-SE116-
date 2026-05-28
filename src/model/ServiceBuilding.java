package model;

public abstract class ServiceBuilding extends Cell{
    private int radius;

    public ServiceBuilding(int radius, int x, int y, char symbol){
        super(x,y,symbol);
        this.radius=radius;
    }

    public int getRadius() {return radius;}

    public boolean isInRange(int targetX, int targetY) {
        int distance = Math.abs(this.x - targetX) + Math.abs(this.y - targetY);
        return distance <= radius;
    }
}
