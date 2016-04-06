/**
 * Created by Friedrik on 6-4-2016.
 */

import java.util.ArrayList;
import java.util.List;

public abstract class SimulatorAbstractModel {
    private List<SimulatorAbstractView> views;

    public SimulatorAbstractModel() {
        views = new ArrayList<SimulatorAbstractView>();
    }

    public void addView(SimulatorAbstractView view){
        views.add(view);
    }

    public void notifyViews() {
        for(SimulatorAbstractView v: views){
            v.updateView();
        }
    }
}
