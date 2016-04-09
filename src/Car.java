public abstract class Car {

    private Location location;
    private int minutesLeft;
    private boolean isPaying;

    /**
     * Constructor for objects of class Car
     */
    public Car() {

    }

    /**
     * returns the location of the car.
     * @return location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * sets the location of the car.
     * @param location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * returns the amount of minutes left
     * @return minutesLeft
     */
    public int getMinutesLeft() {
        return minutesLeft;
    }

    /**
     * sets minutesLeft
     * @param minutesLeft
     */
    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }

    /**
     * returns whether or not a car is paying
     * @return isPaying
     */
    public boolean getIsPaying() {
        return isPaying;
    }

    /**
     * sets isPaying
     * @param isPaying
     */
    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    /**
     * lets time pass
     */
    public void tick() {
        minutesLeft--;
    }

}