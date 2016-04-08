/**
 * Created by Friedrik on 6-4-2016.
 */
import javax.swing.*;

public abstract class SimulatorAbstractView extends JPanel {
    protected Simulator sim;

    public SimulatorAbstractView(Simulator sim) {
        this.sim = sim;
        sim.addView(this);
    }

    public Simulator getModel() {
        return sim;
    }

    public void updateView(){
        repaint();
    }


}
