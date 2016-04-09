import java.util.LinkedList;
import java.util.Queue;

public class CarQueue {
    private Queue<Car> queue = new LinkedList<>();

    /**
     * adds a car to the queue
     * @param car
     * @return queue.add(car)
     */
    public boolean addCar(Car car) {
        return queue.add(car);
    }

    /**
     * removes a car from the queue
     * @return queue.poll()
     */
    public Car removeCar() {
        return queue.poll();
    }

    /**
     * returns the size of the queue
     * @return queue.size()
     */
    public int queueSize(){
        return queue.size();
    }

}
