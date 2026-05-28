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

    //task 2.2 BFS and task 2.3 POOL
    protected int allocatedElectricity = 0;
    protected int allocatedWater = 0;
    protected int allocatedInternet = 0;
    protected int lastTickOutput = 0;
    // Fields to hold external pool resources for Task 2.3 (Student A task)
    protected int receivedPopulation = 0;
    protected int receivedGoods = 0;
    protected int receivedLifestyle = 0;

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

        this.allocatedElectricity = 0;
        this.allocatedWater = 0;
        this.allocatedInternet = 0;
        this.lastTickOutput = 0;

        this.receivedPopulation = 0;
        this.receivedGoods = 0;
        this.receivedLifestyle = 0;
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

    public int getLastTickOutput() { return lastTickOutput; }
    public void setLastTickOutput(int output) { this.lastTickOutput = output; }

    public int getAllocatedElectricity() { return allocatedElectricity; }
    public void addElectricity(int amount) {
        this.allocatedElectricity += amount;
        if(this.allocatedElectricity >= getUtilityDemand()) this.hasElectricity = true;
    }

    public int getAllocatedWater() { return allocatedWater; }
    public void addWater(int amount) {
        this.allocatedWater += amount;
        if(this.allocatedWater >= getUtilityDemand()) this.hasWater = true;
    }

    public int getAllocatedInternet() { return allocatedInternet; }
    public void addInternet(int amount) {
        this.allocatedInternet += amount;
        if(this.allocatedInternet >= getUtilityDemand()) this.hasInternet = true;
    }

    // task 2.3 getter setter
    public int getReceivedPopulation() { return receivedPopulation; }
    public int getReceivedGoods() { return receivedGoods; }
    public int getReceivedLifestyle() { return receivedLifestyle; }
    public void setReceivedPopulation(int p) { this.receivedPopulation = p; }
    public void setReceivedGoods(int g) { this.receivedGoods = g; }
    public void setReceivedLifestyle(int l) { this.receivedLifestyle = l; }

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

    // Method to reset infrastructure amounts at the beginning of each tick
    public void resetAllocatedUtilities() {
        this.allocatedElectricity = 0;
        this.allocatedWater = 0;
        this.allocatedInternet = 0;
        this.hasElectricity = false;
        this.hasWater = false;
        this.hasInternet = false;
    }
    // Amount of utility requested for the next tick (Formula: Current tick generation, minimum 1)
    public int getUtilityDemand() {
        return Math.max(1, this.output); // Project guide rule

    }


}