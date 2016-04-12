/**
 * Created by Liam on 11-4-2016.
 */
import javax.swing.*;
import java.awt.*;

public class TypeView extends SimulatorAbstractView {
    private JLabel passes;
    private JLabel cars;
    private JPanel panel;


    /**
     * Constructor for the TypeView class
     * @param sim
     */
    public TypeView(Simulator sim) {
        super(sim);
        setSize(200,200);



        passes = new JLabel("Passes");
        passes.setText("Passes: " + sim.getPasses());

        cars = new JLabel("Cars without pass");
        cars.setText("Cars without pass: " + sim.getCars());

        panel = new JPanel(new BorderLayout());
        panel.add(passes,BorderLayout.NORTH);
        panel.add(cars,BorderLayout.SOUTH);


        add(panel);

        setVisible(true);


    }

    /**
     * Updates the text for the labels in TypeView
     * @param g
     */
    public void paintComponent(Graphics g) {
        passes.setText("Passes: " + sim.getPasses());
        cars.setText("Cars without pass:" + sim.getCars());
    }


}