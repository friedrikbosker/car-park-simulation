/**
 * Created by Friedrik on 1-4-2016.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Runner {

    public static void main (String[] args) {
        Simulator sim = new Simulator();
        QueueView queue = new QueueView(sim);
        JFrame frame = new JFrame("Car park menu");

        Container contentPane = frame.getContentPane();

        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        JMenu fileMenu = new JMenu("Run");
        menubar.add(fileMenu);


        JMenuItem runsimulation = new JMenuItem("Run Simulation once");
        runsimulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                sim.tick();
            }
        });

        JMenuItem runsimulationhundred = new JMenuItem("Run Simulation a hundred times");
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

        fileMenu.add(runsimulation);
        fileMenu.add(runsimulationhundred);



        frame.pack();
        frame.setVisible(true);

    }


}