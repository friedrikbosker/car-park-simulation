/**
 * Created by Friedrik on 6-4-2016.
 */
import java.awt.*;
import javax.swing.*;
import java.awt.BorderLayout;

public class QueueView extends SimulatorAbstractView {
     private JLabel incoming;
     private JLabel payment;
     private JLabel exit;
     private JPanel panel;


    /**
     * Constructor for the QueueView class
     * @param sim
     */
    public QueueView(Simulator sim) {
        super(sim);
        setSize(200,200);



        incoming = new JLabel("Entrance queue");
        incoming.setText("Entrance queue: " + sim.entranceCarQueue.queueSize());

        payment = new JLabel("Payment queue");
        payment.setText("Payment queue: " + sim.paymentCarQueue.queueSize());

        exit = new JLabel("Exit queue");
        exit.setText("Exit queue: " + sim.exitCarQueue.queueSize());

        panel = new JPanel(new BorderLayout());
        panel.add(incoming,BorderLayout.NORTH);
        panel.add(payment,BorderLayout.CENTER);
        panel.add(exit,BorderLayout.SOUTH);


        add(panel);

        setVisible(true);


    }

    /**
     * Updates the text for the labels in QueueView
     * @param g
     */
    public void paintComponent(Graphics g) {
        incoming.setText("Entrance queue: " + sim.entranceCarQueue.queueSize());
        payment.setText("Payment queue: " + sim.paymentCarQueue.queueSize());
        exit.setText("Exit queue: " + sim.exitCarQueue.queueSize());
    }


}

