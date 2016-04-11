import javax.swing.*;
import java.awt.*;

/**
 * Created by Liam on 11-4-2016.
 */
public class PaymentView extends SimulatorAbstractView {
    private JLabel revenue;
    private JPanel panel;


    /**
     * Constructor for the PaymentView class
     * @param sim
     */
    public PaymentView(Simulator sim) {
        super(sim);
        setSize(200,200);



        revenue = new JLabel("Revenue");
        revenue.setText("Revenue: " + sim.getRevenue());

        panel = new JPanel(new BorderLayout());
        panel.add(revenue,BorderLayout.NORTH);


        add(panel);

        setVisible(true);


    }

    /**
     * Updates the text for the labels in PaymentView
     * @param g
     */
    public void paintComponent(Graphics g) {
        revenue.setText("Revenue: " + sim.getRevenue());
    }


}
