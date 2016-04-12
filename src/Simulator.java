import java.util.Random;

public class Simulator extends SimulatorAbstractModel {

    private static final int Parkingpasschance = 3;

    protected CarQueue entranceCarQueue;
    protected CarQueue paymentCarQueue;
    protected CarQueue exitCarQueue;
    private SimulatorView simulatorView;

    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    public int revenue = 0;
    public int typepass = 0;
    public int typecar = 0;

    private int tickPause = 100;

    int weekDayArrivals= 50; // average number of arriving cars per hour
    int weekendArrivals = 90; // average number of arriving cars per hour

    int enterSpeed = 3; // number of cars that can enter per minute
    int paymentSpeed = 10; // number of cars that can pay per minute
    int exitSpeed = 9; // number of cars that can leave per minute

    /**
     * Constructor for the Simulator class
     */
    public Simulator() {
        entranceCarQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        simulatorView = new SimulatorView(3, 6, 30);
    }

    /**
     * runs the simulator for 10000 ticks
     */
    public void run() {
        for (int i = 0; i < 10000; i++) {
            tick();
        }
    }

    /**
     * Runs the simulator for a single tick
     */
    public void tick() {
        // Advance the time by one minute.
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
        }

        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = day < 5
                ? weekDayArrivals
                : weekendArrivals;

        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.1;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        int numberOfCarsPerMinute = (int)Math.round(numberOfCarsPerHour / 60);

        // Add the cars to the back of the queue.
        for (int i = 0; i < numberOfCarsPerMinute; i++) {
            int randomnumber = random.nextInt(10);
            if(randomnumber > 5) {
                Car car = new AdHocCar();
                entranceCarQueue.addCar(car);
                typecar++;
            } else if (randomnumber <= 5 && randomnumber > 2) {
                Car car = new ParkingPass();
                entranceCarQueue.addCar(car);
                revenue = revenue + 10;
                typepass++;
            } else {
                Car car = new Reservation();
                entranceCarQueue.addCar(car);
            }

        }

        // Remove car from the front of the queue and assign to a parking space.
        for (int i = 0; i < enterSpeed; i++) {
            Car car = entranceCarQueue.removeCar();
            if (car == null) {
                break;
            }
            // Find a space for this car.
            Location freeLocation = simulatorView.getFirstFreeLocation();
            if (freeLocation != null) {
                simulatorView.setCarAt(freeLocation, car);
                int stayMinutes = (int) (15 + random.nextFloat() * 10 * 60);
                car.setMinutesLeft(stayMinutes);
            }
        }

        // Perform car park tick.
        simulatorView.tick();

        // Add leaving cars to the exit queue.
        while (true) {
            Car car = simulatorView.getFirstLeavingCar();
            if (car == null) {
                break;
            } else if(car instanceof ParkingPass){
                car.setIsPaying(false);
                simulatorView.removeCarAt(car.getLocation());
                exitCarQueue.addCar(car);
            } else if(car instanceof Reservation) {
                car.setIsPaying(false);
                simulatorView.removeCarAt(car.getLocation());
                exitCarQueue.addCar(car);
            }else{
                car.setIsPaying(true);
                paymentCarQueue.addCar(car);
                revenue++;
            }
        }

        // Let cars pay.
        for (int i = 0; i < paymentSpeed; i++) {
            Car car = paymentCarQueue.removeCar();
            if (car == null) {
                break;
            }
            // TODO Handle payment.
            simulatorView.removeCarAt(car.getLocation());
            exitCarQueue.addCar(car);
        }

        // Let cars leave.
        for (int i = 0; i < exitSpeed; i++) {
            Car car = exitCarQueue.removeCar();
            if (car == null) {
                break;
            }
            else if(car instanceof ParkingPass){
                typepass--;
            }
            else if(car instanceof AdHocCar){
                typecar--;
            }

            // Bye!
        }

        // Update the car park view.
        simulatorView.updateView();
        super.notifyViews();

        // Pause.
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getRevenue(){
        return revenue;
    }

    /**public void countTypes(){
        for(Car c : entranceCarQueue) {
            if (c instanceof ParkingPass) {
                typepass++;
            } else {
                typecar++;
            }


        }
    }
    **/

    public int getPasses(){
        return typepass;
    }

    public int getCars(){
        return typecar;
    }
}
