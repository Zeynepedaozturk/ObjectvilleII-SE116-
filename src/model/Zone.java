package model;

public abstract class Zone extends Cell {
    protected int level;
    protected int input;
    protected int output;
    protected boolean hasElectricity;
    protected boolean hasWater;
    protected boolean hasInternet;
    protected boolean hasEducation;
    protected boolean hasHealth;
    protected boolean hasSecurity;

    public Zone(int x, int y, char symbol) {
        super(x, y, symbol);

        this.level = 0;
        this.input = 0;
        this.output = 0;

        this.hasElectricity = false;
        this.hasWater = false;
        this.hasInternet = false;

        this.hasEducation = false;
        this.hasHealth = false;
        this.hasSecurity = false;
    }

    public int getLevel() {
        return level;
    }

    public int getInput() {
        return input;
    }

    public int getOutput() {
        return output;
    }

    public void setInput(int input) {
        this.input = input;
    }

    public void setOutput(int output) {
        this.output = output;
    }

    public boolean hasElectricity() {
        return hasElectricity;
    }

    public boolean hasWater() {
        return hasWater;
    }

    public boolean hasInternet() {
        return hasInternet;
    }

    public boolean hasEducation() {
        return hasEducation;
    }

    public boolean hasHealth() {
        return hasHealth;
    }

    public boolean hasSecurity() {
        return hasSecurity;
    }

    public void setElectricity(boolean value) {
        this.hasElectricity = value;
    }

    public void setWater(boolean value) {
        this.hasWater = value;
    }

    public void setInternet(boolean value) {
        this.hasInternet = value;
    }

    public void setEducation(boolean value) {
        this.hasEducation = value;
    }

    public void setHealth(boolean value) {
        this.hasHealth = value;
    }

    public void setSecurity(boolean value) {
        this.hasSecurity = value;
    }

    public void levelUp() {
        if(level < 3){
            level++;
        }
    }

    public void levelDown() {
        if(level > 0){
            level--;
        }
    }

    public boolean canLevelUp() {

        return hasElectricity
                && hasWater
                && hasInternet
                && hasEducation
                && hasHealth
                && hasSecurity;
    }

    public void updateLevel() {

        if(canLevelUp()) {
            levelUp();
        }
        else {
            levelDown();
        }
    }

    public abstract void produce();
}