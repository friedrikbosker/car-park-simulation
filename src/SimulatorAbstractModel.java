/**
 * Created by Friedrik on 6-4-2016.
 */

import java.util.ArrayList;
import java.util.List;

public abstract class SimulatorAbstractModel {
    private List<SimulatorAbstractView> views;

    /**
     * Constructor for the SimulatorAbstractModel class
     */
    public SimulatorAbstractModel() {
        views = new ArrayList<SimulatorAbstractView>();
    }

    /**
     * Adds a view to the Arraylist views
     * @param view
     */
    public void addView(SimulatorAbstractView view){
        views.add(view);
    }

    /**
     * Updates all views in the Arraylist views
     */
    public void notifyViews() {
        for(SimulatorAbstractView v: views){
            v.updateView();
        }
    }
}
