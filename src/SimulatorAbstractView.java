/**
 * Created by Friedrik on 6-4-2016.
 */
import javax.swing.*;

public abstract class SimulatorAbstractView extends JPanel {
    protected Simulator sim;

    /**
     * Constructor for the SimulatorAbstractView class
     * @param sim
     */
    public SimulatorAbstractView(Simulator sim) {
        this.sim = sim;
        sim.addView(this);
    }

    /**
     * Returns the Simulator
     * @return sim
     */
    public Simulator getModel() {
        return sim;
    }

    /**
     * Repaints the view
     */
    public void updateView(){
        repaint();
    }


}
