package model;

public abstract class Cell {
    //parameters are protected because of encapsulation
    protected int x;
    protected int y;
    protected char symbol;


    public Cell(int x, int y, char symbol) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
    }



    public int getX() {return x;}
    public int getY() {return y;}
    public char getSymbol() {return symbol;}

    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public void setSymbol(char symbol) {this.symbol = symbol;}

    /**
     * When printing the cell to the console or output file allows it to appear as a character symbol.
     * H,I,G,P,W,T,F,D,S,R,E
     */
    @Override
    public String toString() {
        return String.valueOf(symbol);
    }

    //Checks whether two cells are coordinate-based equal.

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cell other = (Cell) obj;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}