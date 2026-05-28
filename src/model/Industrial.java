package model;

public class Industrial extends Zone {

    public Industrial(int x, int y) {
        super(x, y, 'I');
    }

    @Override
    public void produce() {
        output = (level + 1) * 15;
        this.lastTickOutput = this.output;
    }
}