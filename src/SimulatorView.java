import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SimulatorView extends JFrame {
    private CarParkView carParkView;
    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private Car[][][] cars;
    private ArrayList<Location> reservedSpots;

    /**
     * Constructor for the SimulatorView class
     * @param numberOfFloors
     * @param numberOfRows
     * @param numberOfPlaces
     */
    public SimulatorView(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
        reservedSpots = new ArrayList<Location>();
        
        carParkView = new CarParkView();

        Container contentPane = getContentPane();
        //contentPane.add(stepLabel, BorderLayout.NORTH);
        contentPane.add(carParkView, BorderLayout.CENTER);
        //contentPane.add(population, BorderLayout.SOUTH);
        pack();
        setVisible(true);

        updateView();
    }

    /**
     * updates the view
     */
    public void updateView() {
        carParkView.updateView();
    }

    /**
     * returns the number of floors
     * @return numberOfFloors
     */
     public int getNumberOfFloors() {
            return numberOfFloors;
        }

    /**
     * returns the number of rows
     * @return numberOfRows
     */
        public int getNumberOfRows() {
            return numberOfRows;
        }

    /**
     * returns the number of places
     * @return numberOfPlaces
     */
        public int getNumberOfPlaces() {
            return numberOfPlaces;
        }

    /**
     * reserves a spot
     * @param location
     */
    public void ReserveSpot(Location location){
            reservedSpots.add(location);
    }

    /**
     * reserves spots in the car park
     */
    public void reserveSpotsInCarPark(){
        for(int floor = 0; floor < getNumberOfFloors(); floor++){
            Location location = new Location(floor, getNumberOfRows() - 1, getNumberOfPlaces());
            ReserveSpot(location);
        }
        }

    /**
     * Checks if the spot is reserved
     * @param location
     * @return false or true
     */
    public boolean isSpotReserved(Location location){
        String reservedSpotTest = "false";
        for(Location l : reservedSpots){
            if(l.equals(location)){
                reservedSpotTest = "true";
            }
        }
        if(reservedSpotTest.equals("true")){
            return true;
        }
        return false;
    }


    /**
     * returns the car on a location if it's there
     * @param location
     * @return null or car
     */
        public Car getCarAt(Location location) {
            if (!locationIsValid(location)) {
                return null;
            }
            return cars[location.getFloor()][location.getRow()][location.getPlace()];
        }



    /**
     * sets a car at a location if it's valid
     * @param location
     * @param car
     * @return false or true
     */
        public boolean setCarAt(Location location, Car car) {
            if (!locationIsValid(location)) {
                return false;
            } else if (isSpotReserved(location) && !(car instanceof Reservation)){
                return false;
            } else if(isSpotReserved(location) && car instanceof Reservation){
                Car oldCar = getCarAt(location);
                if (oldCar == null) {
                    cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
                    car.setLocation(location);
                return true;
            }
            }
            Car oldCar = getCarAt(location);
            if (oldCar == null) {
                cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
                car.setLocation(location);
                return true;
            }
            return false;
        }

    /**
     * removes a car at a location if it's valid
     * @param location
     * @return false or true
     */
        public Car removeCarAt(Location location) {
            if (!locationIsValid(location)) {
                return null;
            }
            Car car = getCarAt(location);
            if (car == null) {
                return null;
            }
            cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
            car.setLocation(null);
            return car;
        }

    /**
     * returns the first free location if there is one
     * @return location or null
     */
        public Location getFirstFreeLocation() {
            for (int floor = 0; floor < getNumberOfFloors(); floor++) {
                for (int row = 0; row < getNumberOfRows(); row++) {
                    for (int place = 0; place < getNumberOfPlaces(); place++) {
                        Location location = new Location(floor, row, place);
                        if (getCarAt(location) == null) {
                            return location;
                        }
                    }
                }
            }
            return null;
        }

    /**
     * returns the first leaving car if there is one
     * @return car or null
     */
        public Car getFirstLeavingCar() {
            for (int floor = 0; floor < getNumberOfFloors(); floor++) {
                for (int row = 0; row < getNumberOfRows(); row++) {
                    for (int place = 0; place < getNumberOfPlaces(); place++) {
                        Location location = new Location(floor, row, place);
                        Car car = getCarAt(location);
                        if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                            return car;
                        }
                    }
                }
            }
            return null;
        }

    /**
     * calls tick() for all cars
     */
    public void tick() {
            for (int floor = 0; floor < getNumberOfFloors(); floor++) {
                for (int row = 0; row < getNumberOfRows(); row++) {
                    for (int place = 0; place < getNumberOfPlaces(); place++) {
                        Location location = new Location(floor, row, place);
                        Car car = getCarAt(location);
                        if (car != null) {
                            car.tick();
                        }
                    }
                }
            }
        }

    /**
     * returns whether or not a location is valid
     * @param location
     * @return false or true
     */
        private boolean locationIsValid(Location location) {
            int floor = location.getFloor();
            int row = location.getRow();
            int place = location.getPlace();
            if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
                return false;
            }
            return true;
        }

    
    private class CarParkView extends JPanel {
        
        private Dimension size;
        private Image carParkImage;    
    
        /**
         * Constructor for objects of class CarParkView
         */
        public CarParkView() {
            size = new Dimension(0, 0);
        }
    
        /**
         * Overridden. Tell the GUI manager how big we would like to be.
         */
        public Dimension getPreferredSize() {
            return new Dimension(800, 500);
        }
    
        /**
         * Overriden. The car park view component needs to be redisplayed. Copy the
         * internal image to screen.
         */
        public void paintComponent(Graphics g) {
            if (carParkImage == null) {
                return;
            }
    
            Dimension currentSize = getSize();
            if (size.equals(currentSize)) {
                g.drawImage(carParkImage, 0, 0, null);
            }
            else {
                // Rescale the previous image.
                g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
            }
        }

        public void updateView() {
            // Create a new car park image if the size has changed.
            if (!size.equals(getSize())) {
                size = getSize();
                carParkImage = createImage(size.width, size.height);
            }
            Graphics graphics = carParkImage.getGraphics();
            for(int floor = 0; floor < getNumberOfFloors(); floor++) {
                for(int row = 0; row < getNumberOfRows(); row++) {
                    for(int place = 0; place < getNumberOfPlaces(); place++) {
                        Location location = new Location(floor, row, place);
                        Car car = getCarAt(location);
                        Color color = Color.white;
                        if(car == null && !isSpotReserved(location)){
                            color = Color.white;
                        }
                        else if(car == null && isSpotReserved(location)){
                            color = Color.black;
                        }
                        else if(car instanceof ParkingPass){
                            color = Color.yellow;
                        }
                        else if(car instanceof Reservation){
                            color = Color.blue;
                        }
                        else {
                            color = Color.RED;
                        }
                        drawPlace(graphics, location, color);
                    }
                }
            }
            repaint();
        }
    
        /**
         * Paint a place on this car park view in a given color.
         */
        private void drawPlace(Graphics graphics, Location location, Color color) {
            graphics.setColor(color);
            graphics.fillRect(
                    location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                    60 + location.getPlace() * 10,
                    20 - 1,
                    10 - 1); // TODO use dynamic size or constants
        }
    }

}
