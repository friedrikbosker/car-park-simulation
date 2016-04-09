/**
 * Created by Friedrik on 8-4-2016.
 */
import javax.swing.*;

public class SimulatorManager {
    private JFrame frame;
    private Simulator sim;
    private SimulatorRunController run;
    private QueueView queue;

    /**
     * Constructor for the SimulatorManager class
     * Opens a Simulatorview and a frame with buttons and views
     */
    public SimulatorManager(){
        sim = new Simulator();
        run = new SimulatorRunController(sim);
        queue = new QueueView(sim);

        frame = new JFrame("Car park simulator");
        frame.setSize(540, 285);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.getContentPane().add(queue);
        frame.getContentPane().add(run);
        queue.setBounds(10, 10, 200, 200);
        run.setBounds(0, 210, 450, 50);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
