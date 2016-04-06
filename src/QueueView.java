/**
 * Created by Friedrik on 6-4-2016.
 */
import java.awt.*;
import javax.swing.*;
import java.awt.BorderLayout;

public class QueueView extends SimulatorAbstractView {

    public QueueView(Simulator simu) {
        super(simu);


        JFrame queueframe = new JFrame("Queue information");


        JLabel incoming = new JLabel("Incoming queue");
        incoming.setText("Incoming queue: " + simu.entranceCarQueue.queueSize());

        JLabel payment = new JLabel("Payment queue");
        payment.setText("Payment queue: " + simu.paymentCarQueue.queueSize());

        JLabel exit = new JLabel("Exit queue");
        exit.setText("Exit queue: " + simu.exitCarQueue.queueSize());

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(incoming,BorderLayout.NORTH);
        panel.add(payment,BorderLayout.CENTER);
        panel.add(exit,BorderLayout.SOUTH);

        queueframe.add(panel);



        queueframe.pack();
        queueframe.setVisible(true);

    }
}
