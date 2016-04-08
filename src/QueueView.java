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


        JFrame queueframe = new JFrame("Queue information");


        JLabel incoming = new JLabel("Incoming queue");

        JLabel payment = new JLabel("Payment queue");

        JLabel exit = new JLabel("Exit queue");

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(incoming,BorderLayout.NORTH);
        panel.add(payment,BorderLayout.CENTER);
        panel.add(exit,BorderLayout.SOUTH);

        queueframe.add(panel);



        queueframe.pack();
        queueframe.setVisible(true);

    }

    //returns queue sizes
    private void returnvalues(){
        incomingValue = sim.entranceCarQueue.queueSize();
        paymentValue = sim.paymentCarQueue.queueSize();
        exitValue = sim.exitCarQueue.queueSize();

        incoming.setText("Entrance queue: " + incomingValue);
        payment.setText("Payment queue: " + paymentValue);
        exit.setText("Exit queue: " + exitValue);

    }
}
