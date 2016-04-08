

/**
 * Created by Friedrik & Liam on 8-4-2016.
 */
import javax.swing.*;
import java.awt.event.*;


public class SimulatorRunController extends SimulatorAbstractController {


    public SimulatorRunController(Simulator sim) {
        super(sim);
        setSize(450, 50);
        JButton runsimulation = new JButton("Run Simulation once");
        runsimulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                sim.tick();
            }
        });

        JButton runsimulationhundred = new JButton("Run Simulation a hundred times");
        runsimulationhundred.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 100; i++){
                    SwingWorker myWorker= new SwingWorker<String, Void>() {
                        @Override
                        protected String doInBackground() throws Exception {
                            sim.tick();
                            Thread.sleep(100);
                            return null;
                        }
                    };
                    myWorker.execute();

                }
            }

        });

        this.setLayout(null);
        add(runsimulation);
        add(runsimulationhundred);
        runsimulation.setBounds(10, 10, 200, 30);
        runsimulationhundred.setBounds(230, 10, 230, 30);

        setVisible(true);
    }
}
