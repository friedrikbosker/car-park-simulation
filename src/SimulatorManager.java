/**
 * Created by Friedrik on 8-4-2016.
 */
import javax.swing.*;
import java.awt.*;

public class SimulatorManager {
    private JFrame frame;
    private Simulator sim;
    private SimulatorRunController run;
    private QueueView queue;
    private PaymentView pay;
    private TypeView type;
    //private JPanel test;

    /**
     * Constructor for the SimulatorManager class
     * Opens a Simulatorview and a frame with buttons and views
     */
    public SimulatorManager(){
        sim = new Simulator();
        run = new SimulatorRunController(sim);
        //test = new JPanel(new BorderLayout());
        queue = new QueueView(sim);
        pay = new PaymentView(sim);
        type = new TypeView(sim);

        frame = new JFrame("Car park simulator");
        frame.setSize(650, 350);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.getContentPane().add(queue);
        frame.getContentPane().add(run);
        //test.add(run, BorderLayout.SOUTH);
        frame.getContentPane().add(pay);
        frame.getContentPane().add(type);
        queue.setBounds(10, 10, 200, 200);
        run.setBounds(0, 210, 450, 50);
        pay.setBounds(80, 10, 450, 50);
        type.setBounds(130, 10, 750, 50);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
