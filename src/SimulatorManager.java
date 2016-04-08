/**
 * Created by Friedrik on 8-4-2016.
 */
import javax.swing.*;

public class SimulatorManager {
    private JFrame frame;
    private Simulator sim;
    private SimulatorRunController run;

    public SimulatorManager(){
        sim = new Simulator();
        run = new SimulatorRunController(sim);

        frame = new JFrame("Car park simulator");
        frame.setSize(540, 285);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.getContentPane().add(run);
        run.setBounds(0, 210, 450, 50);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
