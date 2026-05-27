package model;

public class Housing extends Zone {

    public Housing(int x, int y) {
        super(x, y, 'H');
    }

    @Override
    public void produce() {
        output = (level + 1) * 10;
    }
}
