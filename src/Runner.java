/**
 * Created by Friedrik on 1-4-2016.
 */
import java.awt.*;
        import java.awt.event.*;
                import javax.swing.*;

public class Runner {

    public static void main (String[] args){
         JFrame frame = new JFrame("Car park menu");
        Container contentPane = frame.getContentPane();
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        JMenu fileMenu = new JMenu("Run");
        menubar.add(fileMenu);
        JMenuItem runsimulation = new JMenuItem("Run Simulation");
        runsimulation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Simulator simulatin = new Simulator();
                simulatin.run();
            }
        });
        fileMenu.add(runsimulation);

        frame.getContentPane().add(runsimulation);
        frame.pack();
        frame.setVisible(true);

    }
}
