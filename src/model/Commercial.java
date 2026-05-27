package model;

public class Commercial extends Zone {

    public Commercial(int x, int y) {
        super(x, y, 'C');
    }

    @Override
    public void produce() {
        output = (level + 1) * 12;
    }
}
