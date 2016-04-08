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

     private int incomingValue;
     private int paymentValue;
     private int exitValue;

    public QueueView(Simulator sim) {
        super(sim);
        setSize(200,200);



        JLabel incoming = new JLabel("Entrance queue");
        incoming.setText("Entrance queue: " + sim.entranceCarQueue.queueSize());

        JLabel payment = new JLabel("Payment queue");
        payment.setText("Payment queue: " + sim.paymentCarQueue.queueSize());

        JLabel exit = new JLabel("Exit queue");
        exit.setText("Exit queue: " + sim.exitCarQueue.queueSize());

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(incoming,BorderLayout.NORTH);
        panel.add(payment,BorderLayout.CENTER);
        panel.add(exit,BorderLayout.SOUTH);


        add(panel);

        setVisible(true);


    }

}
