/**
 * Created by Friedrik on 6-4-2016.
 */
import javax.swing.*;

public abstract class SimulatorAbstractView extends JPanel {
    protected Simulator simu;

    public SimulatorAbstractView(Simulator simu) {
        this.simu = simu;
        simu.addView(this);
    }

    public Simulator getModel() {
        return simu;
    }

    public void updateView(){
        repaint();
    }
}
