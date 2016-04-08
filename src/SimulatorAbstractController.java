import javax.swing.*;

/**
 * Created by Friedrik on 8-4-2016.
 */
public abstract class SimulatorAbstractController extends JPanel {
    protected Simulator sim;

    public SimulatorAbstractController(Simulator sim) {
        this.sim = sim;
    }
}
